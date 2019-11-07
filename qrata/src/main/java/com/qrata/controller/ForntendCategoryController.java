package com.qrata.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qrata.entity.CategoryForm;
import com.qrata.service.CategoryService;
import com.qrata.utility.PaginationUtility;
import com.qrata.utility.Utility;
import com.qrata.enums.*;

@Controller("forntendCategoryController")
@RequestMapping(value="categories")
public class ForntendCategoryController {
	
	@Autowired 
	CategoryService categoryService;

    @RequestMapping(value={"listCategories2.htm"}, method=RequestMethod.GET)
    public ModelAndView handleRequest(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("categories/listCategories");
        mav.getModel().put("prop", 23);
        return mav;
    }
    
	@RequestMapping(value={"listCategories.htm"}, method=RequestMethod.GET)
	public String listCategories(HttpServletRequest request , Model model, @RequestParam("id") int domainId,@ModelAttribute("categoryForm") CategoryForm form) {
		int start = PaginationUtility.getStartOffsetByRequestAndTableId(request, "category");
	
		List<CategoryForm> categoryList = categoryService.getActiveCategoryForm_Type(Utility.replaceSpecialCharacters(form.getDomainSearchVal())
				,Constants.CategoryType.CATEGORY,domainId,PaginationUtility.pageSize, null, null,start);
		int totalActiveCategories =categoryList.size();
        model.addAttribute("categoryList", categoryList)
                .addAttribute("id", domainId)
                .addAttribute("totalActiveCategories", totalActiveCategories);
        return "categories/listCategories";
	}
}
