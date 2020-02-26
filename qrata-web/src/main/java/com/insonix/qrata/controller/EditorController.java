package com.insonix.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.insonix.qrata.constants.Constants.Roles;
import com.insonix.qrata.entity.AddUserForm;
import com.insonix.qrata.models.User;
import com.insonix.qrata.pagination.PaginationUtility;
import com.insonix.qrata.service.EditorService;
import com.insonix.qrata.service.UserService;
import com.insonix.qrata.utility.Utility;

/**
 * @author Raman
 *
 */
@Controller("editorController")
@RequestMapping({ "admin" })
public class EditorController {

	@Autowired
	UserService userService;

	@Autowired
	EditorService editorService;
	

	@RequestMapping(value = "listEditors.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public String getAllEditors(HttpServletRequest request , @ModelAttribute("userForm") AddUserForm userForm , Model model) {
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "editor");
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "editor");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "editor");
		String name = Utility.replaceSpecialCharacters(userForm.getUserSearchVal());
		
		int totalEditor = userService.getTotalUser_Role(name, Roles.EDITOR);
		List<AddUserForm> addUserForms =  editorService.getSortingEditor(name,sortField,sortOrder,start,PaginationUtility.pageSize);
		
		model.addAttribute("editors", addUserForms);
		model.addAttribute("userForm", userForm);
		model.addAttribute("totalEditorSize",totalEditor);
		
		return "editor/listEditors";
	}
	
	@RequestMapping(value="/deleteEditor.htm",method={ RequestMethod.GET,RequestMethod.POST})
	public String delete(Model model,@RequestParam("id") String uuid){
//		editorService.delete(uuid);
		return "redirect:listEditors.htm";
	}
	
	@RequestMapping(value = "searchEditors.htm", method = { RequestMethod.POST })
	public String searchEditors(@ModelAttribute("userForm") AddUserForm userForm, Model model) {
		List<User> editors = editorService.searchEditors(userForm.getUserSearchVal());
		model.addAttribute("editors", editors);
		model.addAttribute("userForm", userForm);
		return "editor/listEditors";
	}
	
}
