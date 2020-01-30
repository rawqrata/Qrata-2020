package com.qrata.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.qrata.entity.AddUserForm;
import com.qrata.enums.Constants;
import com.qrata.enums.Status;
import com.qrata.models.Role;
import com.qrata.models.User;
import com.qrata.models.UserInfo;
import com.qrata.respository.UserRespository;
import com.qrata.utility.PaginationUtility;

@Controller
@RequestMapping("admin")
public class UserController {
	
	@Autowired
	private UserRespository userRespository;
	
	@RequestMapping(value = { "listUsers.htm" }, method = { RequestMethod.GET, RequestMethod.POST})
	public String listUsersPage(HttpServletRequest request, Model model, @ModelAttribute("userForm") AddUserForm userForm) {

        //pagination/sorting values
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "userList");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "userList");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "userList");
		

        String searchTerm = null;

        //the saveUser.htm redirects here with a flash value of the newly created
        //user's last name; this allows to show new user in list with
        Map<String,?> flashAttributes = RequestContextUtils.getInputFlashMap(request);
        if(flashAttributes != null) {
            if (flashAttributes.containsKey("searchVal")) {
                searchTerm = flashAttributes.get("searchVal").toString();
            }
        } else {
            searchTerm = userForm.getUserSearchVal();
        }

        List<User> userList = userRespository.findAll();
        		
        		int totalUsers = userList.size();

		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "userList");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "userList");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "userList");
		String sortFieldRequestParam;
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "userList", "userInfo.firstname");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "userList", sortField);
		}
		
		model.addAttribute("userForm", userForm);
		model.addAttribute("users", userList);
		model.addAttribute("totalUsers", totalUsers);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);

		return "users/listUsers";
	}
	
	@RequestMapping(value = { "saveUser.htm" }, method = { RequestMethod.POST })
	public String addUser(HttpServletRequest request, @ModelAttribute("userForm") AddUserForm addUser, final RedirectAttributes redirectAttributes) {
		String success = null;
		User user = new User();
		user.setUserName(addUser.getUserName());
		user.setPassword(addUser.getPassword());
		UserInfo info = new UserInfo();
		info.setFirstname(addUser.getFirstName());;
		info.setLastname(addUser.getLastName());
		/*
		 * info.set String id = userRespository.saveAll(addUser); if(! id.isEmpty()){
		 * success = "1"; }
		 */

        //set flash values: success goes through to listUsers.jsp to show success notice,
        //searchVal used by listUsersPage to limit list to last name of new user
        redirectAttributes.addFlashAttribute("success", success);
        redirectAttributes.addFlashAttribute("searchVal", addUser.getLastName());

        if(request.isUserInRole("ROLE_" + Constants.Roles.ADMIN)){
			return   "redirect:listUsers.htm";
		}else{
			return "redirect:listExperts.htm";
		}
	}
	
	@RequestMapping(value = { "deleteUser.htm" }, method = { RequestMethod.GET })
	public String deleteUser(@RequestParam("id") String userName, final RedirectAttributes redirectAttributes) {
		User user = userRespository.findByUserName(userName);
		user.setStatus(Status.INACTIVE.getValue());
		user.setStatus(Status.DELETED.getValue());
		String success = null;
		if(userRespository.save(user)!=null){
			success = "2";
		}
        redirectAttributes.addFlashAttribute("success", success);
		return "redirect:listUsers.htm";
	}
	
	@RequestMapping(value = { "editUser.htm" }, method = { RequestMethod.GET })
	public String editUser(HttpServletRequest request, Model model , @RequestParam("userId") long id,
			@RequestParam(value="prp")String prp, @RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, 
			@RequestParam("sunrp")String sunrp) {
		
		User user = userRespository.findById(id).get();
		
		AddUserForm form=new AddUserForm();
		form.setFirstName(user.getUserinfo().getFirstname());
		form.setLastName(user.getUserinfo().getLastname());
        form.setUserName(user.getUserName());
		List<String> roleNames = new ArrayList<>(3);
        for(Role role:user.getRoles()){
            roleNames.add(role.getName());
        }
        form.setRoles(roleNames);
        form.setBio(user.getUserinfo().getBio());
        form.setEmail(user.getUserinfo().getEmail());
        form.setId(user.getId());
		//userService.getEditForm(user, model);

        model.addAttribute("userData", form);
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		return "users/edituser";
	}
	
	@RequestMapping(value = { "addUser.htm" }, method = { RequestMethod.GET })
	public String addUserPage(Model model) {
		Long id=(long) 1;
		AddUserForm userForm = new AddUserForm();
        model.addAttribute("userForm", userForm);
       User user= userRespository.findById(id).get();
        model.addAttribute("currentUser",user);
		return "users/addUser";
	}
	
	
	@RequestMapping(value = { "emailExists.htm" }, method = { RequestMethod.GET })
	public @ResponseBody String emailExists(@RequestParam("email") String email) {
		boolean flag = false;
		JSONObject obj = new JSONObject();
		try {
			obj.put("exists", flag);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@RequestMapping(value="existsLogin.htm", method={RequestMethod.GET})
	public @ResponseBody String existsUser(@RequestParam("name") String name ){
		User user = userRespository.findByUserName(name);
		boolean exists = false;
		if(user!=null ){
			exists=true;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("exists", exists);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@RequestMapping(value={"logincheck.htm"}, method = { RequestMethod.GET, RequestMethod.POST})
	public String logincheck(HttpSession session, HttpServletRequest request, Model model, @RequestParam("j_username") String uname , @RequestParam("j_password") String pwd) {
		User user = userRespository.checkUserLogin(uname, pwd);
		if(user == null) {
			model.addAttribute("usernotexist", "Not Exist");
		}else if(user != null) { 
			session.setAttribute("user_data", user);
			return "redirect:/";
		}
		return "/login";
	}
	
	
	

}
