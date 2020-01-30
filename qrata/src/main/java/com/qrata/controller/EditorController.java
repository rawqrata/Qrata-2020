package com.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qrata.entity.AddUserForm;
import com.qrata.service.EditorService;
import com.qrata.utility.PaginationUtility;
import com.qrata.utility.Utility;

@Controller("editorController")
@RequestMapping({ "admin" })
public class EditorController {

	@Autowired
   private EditorService editorService;

	@RequestMapping(value = "listEditors.htm", method = { RequestMethod.GET, RequestMethod.POST })
	public String getAllEditors(HttpServletRequest request , @ModelAttribute("userForm") AddUserForm userForm , Model model) {
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "editor");
		String sortField = PaginationUtility.getSortFieldByRequestAndTableId(request, "editor");
		String sortOrder = PaginationUtility.getSortOrderByRequestAndTableId(request, "editor");
		String name = Utility.replaceSpecialCharacters(userForm.getUserSearchVal());
		List<AddUserForm> addUserForms =  editorService.getSortingEditor(name,sortField,sortOrder,start,PaginationUtility.pageSize);
		int totalEditor =addUserForms.size();
		model.addAttribute("editors", addUserForms);
		model.addAttribute("userForm", userForm);
		model.addAttribute("totalEditorSize",totalEditor);
		
		return "editor/listEditors";
	}
	
}
