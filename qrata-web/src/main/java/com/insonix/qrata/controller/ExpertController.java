package com.insonix.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.entity.ExpertBioForm;
import com.insonix.qrata.models.Topic;
import com.insonix.qrata.models.User;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.ExpertService;
import com.insonix.qrata.service.TopicExpertAssignmentService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.Utility;


@Controller("expertController")
@RequestMapping({ "admin", "expert" })
public class ExpertController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	ExpertService expertService;
	@Autowired
	TopicExpertAssignmentService topicExpertAssignmentService;

	
	@RequestMapping(value = "listExperts.htm", method = { RequestMethod.GET , RequestMethod.POST})
	public String getAllExperts(@ModelAttribute("userForm") AddUserForm userForm , Model model,HttpServletRequest request) {
		
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "expert");
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "expert");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "expert");
		String name = userForm.getUserSearchVal();
		
		int totalExpert = userService.getTotalUser_Role(name, Roles.EXPERT);
		List<AddUserForm> experts = expertService.getSortingExpert(name,sortField,sortOrder,start,PaginationUtility.pageSize);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "expert");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "expert");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "expert");
		String sortFieldRequestParam = "";
		if(sortField == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "expert", "userInfo.firstname");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "expert", sortField);
		}
				
		model.addAttribute("experts", experts);
		model.addAttribute("userForm", userForm);
		model.addAttribute("totalExpertSize",totalExpert);
		
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		return "expert/listExperts";
	}
	
	@RequestMapping(value = { "allExperts_Editors.htm" }, method = {RequestMethod.GET, RequestMethod.POST })
	public String getAllExperts_Editors(HttpServletRequest request, @RequestParam long id, @ModelAttribute("userForm") AddUserForm userForm,
			Model model) {
		int	start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "expert");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "expert");
		String sortColumn = PaginationUtility.getSortFieldByRequestAndTableId(request, "expert");
		String name = Utility.replaceSpecialCharacters(userForm.getUserSearchVal());
		User user = (User) request.getSession().getAttribute("user");
		
		int totalExpert = expertService.getTotalExperts_Editor_Id(name, id);
		List<AddUserForm> experts = expertService.getSortingALLExpert(name, sortColumn, sortOrder, start,
				PaginationUtility.pageSize, id, user);
		
		String pageRequestparam = PaginationUtility.getCurrentPageRequestParam(request, "expert");
		String orderRequestParam = PaginationUtility.getOrderRequestParam(request, "expert");
		String sortUsingNameRequestParam = PaginationUtility.getSortUsingNameRequestParam(request, "expert");
		String sortFieldRequestParam = "";
		if(sortColumn == null){
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "expert", "userInfo.firstname");
		}else{
			sortFieldRequestParam = PaginationUtility.getSortFieldRequestParam(request, "expert", sortColumn);
		}
	
		request.setAttribute("experts",experts);
		model.addAttribute("userForm", userForm);
		model.addAttribute("totalExpertSize", totalExpert);
		model.addAttribute("prp", pageRequestparam);
		model.addAttribute("orp",orderRequestParam);
		model.addAttribute("sfrp",sortFieldRequestParam);
		model.addAttribute("sunrp",sortUsingNameRequestParam);
		model.addAttribute("id", id);
		
		return "expert/allExperts_Editors";
	}
	
	@RequestMapping(value = "editExpertBio.htm", method = { RequestMethod.GET })
	public String editExpertBio(HttpServletRequest request, Model model, @RequestParam("id") long id,
			@RequestParam(value="prp") String prp, @RequestParam("orp") String orp, @RequestParam("sfrp") String sfrp, 
			@RequestParam("sunrp") String sunrp) {		
		User user = userService.getUser_Id(id);
		ExpertBioForm form = new ExpertBioForm(user.getUserinfo().getBio(), id, user.getUserinfo().getImageName());
		
		model.addAttribute("bioForm", form);
		
		model.addAttribute("prp", prp);
		model.addAttribute("orp", orp);
		model.addAttribute("sfrp", sfrp);
		model.addAttribute("sunrp", sunrp);
		return "expert/editExpertBio";
	}

	@RequestMapping(value = "saveExpertBio.htm", method = { RequestMethod.POST })
	public String saveExpertBio(Model model, HttpSession session, @ModelAttribute(value="bioForm") ExpertBioForm expertBioForm,
			HttpServletRequest request) {		
		String prp = request.getParameter("prp");
		String orp = request.getParameter("orp");
		String sfrp = request.getParameter("sfrp"); 
		String sunrp = request.getParameter("sunrp");
		
		boolean result = expertService.saveExpertBio(expertBioForm);
		String success = null;
		if(result) {
			success = "3";
		}
		model.addAttribute("id", expertBioForm.getId());
		return "redirect:expertBio.htm?"+prp+"&"+orp+"&"+sfrp+"&"+sunrp+"&success="+success;
	}

	
	@RequestMapping(value = "expertBio.htm", method = { RequestMethod.GET })
	public String expertBioPix(HttpServletRequest request, Model model, @RequestParam("id") long id) {	
		User user = userService.getUser_Id(id);
		model.addAttribute("userInfo", user.getUserinfo());
		List<Topic> topics = topicExpertAssignmentService.getTopics_ExpertId(user);
		model.addAttribute("topics", topics);
		return "expert/expertBio";
	}

		/*
		 * Names Auto Suggestion
		 */
	@RequestMapping(value="autoSuggestExpertByNameAndEditor.htm", method=RequestMethod.GET)
	public @ResponseBody String autoSuggestExpertByNameAndEditor(@RequestParam("name") String name, @RequestParam("parentId") Long editorId) {
		String str = userService.autoSuggestExpertByNameAndEditor(name, editorId);
		return str;
	}
	
		/*
		 * Remove Expert's Image
		 */
	@RequestMapping(value="removeExpertPic.htm", method=RequestMethod.GET)
	public @ResponseBody String removeExpertPic(@RequestParam("id") Long id) {
		String str = expertService.removeExpertPic(id);
		return str;
	}

}
