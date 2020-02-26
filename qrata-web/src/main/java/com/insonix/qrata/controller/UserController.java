/**
 * 
 */
package com.insonix.qrata.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.constants.Status;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.models.Role;
import com.insonix.qrata.models.User;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.CategoryService;
import com.insonix.qrata.service.RoleService;
import com.insonix.qrata.service.UserInfoService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.QrataImagesUtil;
import com.insonix.qrata.utility.Utility;

/**
 * @author kamal
 * 
 */
@Controller("UserController")
@RequestMapping("admin")
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	RoleService rolesService;
	@Autowired
	CategoryService categoryService;

	/**
	 * @param request
	 * @return
	 */

	/*
	 * this method is used to show the home page where we can see all users
	 */
	@RequestMapping(value = { "listUsers.htm" }, method = { RequestMethod.GET, RequestMethod.POST})
	public String loginForm(HttpServletRequest request, Model model, @ModelAttribute("userForm") AddUserForm userForm) {
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "userList");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "userList");
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "userList");
		int totalUsers = userService.getTotalActiveUsersCount(Utility.replaceSpecialCharacters(userForm.getUserSearchVal()));		
		List<User> userList = userService.findAll(Utility.replaceSpecialCharacters(userForm.getUserSearchVal()),
				start, PaginationUtility.pageSize, sortField, sortOrder);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "userList");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "userList");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "userList");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "userList", "userInfo.firstname");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "userList", sortField);
		}
		
		List<Role> roles = rolesService.getActiveRoles();
		model.addAttribute("userForm", userForm);
		model.addAttribute("roles", roles);
		model.addAttribute("users", userList);
		model.addAttribute("totalUsers", totalUsers);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "user/listUsers";
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	/*
	 * this method is used to open the page where we can add new user's
	 * information
	 */
	@RequestMapping(value = { "addUser.htm" }, method = { RequestMethod.GET })
	public String addUserPage(HttpServletRequest request, Model model) {
		AddUserForm userForm = new AddUserForm();
		List<Role> roles=rolesService.getActiveRoles();
		model.addAttribute("userForm", userForm);
		model.addAttribute("roles",roles);
		return "user/addUser";
	}
	
	/**
	 * @param request
	 * @param addUser
	 * @return
	 */

	/*
	 * this method is used to save a new user , its role and its information and
	 * we are using model here called AddUserForm
	 */
	@RequestMapping(value = { "saveUser.htm" }, method = { RequestMethod.POST })
	public String addUser(HttpServletRequest request, @ModelAttribute("userForm") AddUserForm addUser,Model model) {
		String success = null;
		String id = userService.save(addUser);
		if(! id.isEmpty()){
			success = "1";
		}
		User user = (User) request.getSession().getAttribute("user");
		String returnResult = "";
		if(user.getRole().getId() == Roles.ADMIN.getValue()){
			returnResult = "redirect:listUsers.htm?success="+success;
		}else{
			returnResult = "redirect:listExperts.htm?success="+success;
		}
		return returnResult;

	}

	/**
	 * @param request
	 * @return
	 */
	/*
	 * this method is used to delete user > actually we are not deleting user
	 * here we are only disable user by change its status
	 */
	@RequestMapping(value = { "deleteUser.htm" }, method = { RequestMethod.GET })
	public String deleteUser(HttpServletRequest request) {
		String id = request.getParameter("id");
		User user = userService.getUser_UserName(id);
		user.setStatus(Status.INACTIVE.getValue());
		boolean result = userService.delete(user);
		String success = null;
		if(result){
			success = "2";
		}

		return "redirect:listUsers.htm?success="+success;
	}

	/**
	 * @param request
	 * @param model
	 * @return
	 */
	/*
	 * this method is used to open the edituser page. and getting the data
	 * through model
	 */
	@RequestMapping(value = { "editUser.htm" }, method = { RequestMethod.GET })
	public String editUser(HttpServletRequest request, Model model , @RequestParam("userId") long id,
			@RequestParam(value="prp")String prp, @RequestParam("orp")String orp, @RequestParam("sfrp")String sfrp, 
			@RequestParam("sunrp")String sunrp) {
		
		User user = userService.getUser_Id(id);
		
		AddUserForm form=new AddUserForm();
		form.setFirstName(user.getSortName());
		form.setLastName(user.getSortName());
		
		userService.getEditForm(user, model);
		List<Role> roles=rolesService.getActiveRoles();
		
		model.addAttribute("roles",roles);
		
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		return "user/edituser";
	}

	/**
	 * @param request
	 * @param addUserForm
	 * @param model
	 * @return
	 */
	/*
	 * this method is used to update the information of any user
	 */
	@RequestMapping(value = { "editUser.htm" }, method = { RequestMethod.POST })
	public String editUserPost(HttpServletRequest request, AddUserForm addUserForm, Model model) {
		
		String prp = request.getParameter("prp");
		String orp = request.getParameter("orp");
		String sfrp = request.getParameter("sfrp"); 
		String sunrp = request.getParameter("sunrp");
		
		User user = userService.getUser_Id(addUserForm.getId());
		user.setSortName(addUserForm.getFirstName().trim());
		user.setSortName(addUserForm.getLastName().trim());

		boolean result = userService.editUser(user, addUserForm);
		model.addAttribute("userId", addUserForm.getId());
		String success = null;
		if(result){
			success = "3";
		}	

		return "redirect:listUsers.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;

	}

	/**
	 * @param request
	 * @return
	 * @throws IOException
	 */

	@RequestMapping(value = { "getCategorizedUsers.htm" }, method = { RequestMethod.GET })
	public @ResponseBody
	String getUsers_Category(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("name") String firstName)
			throws IOException {

		String categorizedUsers = userService.getUsers_Category(firstName);

		return categorizedUsers;

	}

	/**
	 * @param email
	 * @return
	 */
	@RequestMapping(value = { "emailExists.htm" }, method = { RequestMethod.GET })
	public @ResponseBody
	String emailExists(@RequestParam("email") String email) {
		boolean flag = userService.getUser_Email(email);
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
		User user =  userService.getUser_UserName(name);
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
	
	@RequestMapping(value="existsEmail.htm",method={RequestMethod.GET})
	public @ResponseBody String existsEmail(@RequestParam("email")String email){
		boolean exists = userService.getUser_Email(email);		
		JSONObject obj = new JSONObject();
		try {
			obj.put("exists", exists);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString();	
	}

		/*
		 * Names Auto Suggestion
		 */
	@RequestMapping(value="autoSuggestUserByNameOrRole.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestUserByNameOrRole(@RequestParam("name") String name, @RequestParam("type") String type) {
		String str = userService.suggestUserByNameOrRole(name, type);
		return str;
	}
	
		/*
		 * Display User's Image
		 */
	@RequestMapping(value="getUserImage.htm", method=RequestMethod.GET)
	public void getUserImage(@RequestParam("userId") long userId, HttpServletResponse response) {
		OutputStream out  = null;
		try {		
			User user = userService.getUser_Id(userId);
			if(StringUtils.isNotEmpty(user.getUserinfo().getImagePath())) {
				File qrataData = QrataImagesUtil.getQrataDataFolder();
				File expertPicFile = new File(qrataData, user.getUserinfo().getImagePath());
				
				out = response.getOutputStream();
				BufferedImage img = ImageIO.read(expertPicFile);
				ImageIO.write(img, "png", out);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(out != null)
					out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
