<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://insonix.com/qrata/jsp/taglib/breadcrumb"
   prefix="b"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <script type="text/javascript">
         $(document).ready(function() {
         	var autoCompleter = new CommonAutocompleter();
         	autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestCategoryByNameAndType.htm", "subCategorySearch", "SUBCATEGORY");
         });
      </script>
   </head>
   <body>
      <div class="row">
         <div class="col-md-12">
            <c:choose>
               <c:when test="${param.success eq '1' }">
                  <div class="success-box">
                     <c:out value="Category has been created successfully" />
                  </div>
               </c:when>
               <c:when test="${param.success eq '2' }">
                  <div class="success-box">
                     <c:out value="The data has been deleted successfully" />
                  </div>
               </c:when>
               <c:when test="${param.success eq '3' }">
                  <div class="success-box">
                     <c:out value="Data has been updated successfully" />
                  </div>
               </c:when>
            </c:choose>
         </div>
      </div>
      <div class="row">
         <div class="col-md-12">
            <div class="date-text">&nbsp;</div>
         </div>
      </div>
      <div class="row">
         <div class="col-md-8 col-8">
            <ul class="shape-design">
               <li>Manage</li>
               <li><img src="${ctx}/resources/images/breadcrumb-arrow.png" /></li>
               <li class="none">Sub Categories</li>
            </ul>
         </div>
         <div class="col-md-4 col-4">
            <div class="back-button">
               <a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;
               Go Back </a>
            </div>
         </div>
      </div>
      <div class="subcategorbox mt-4">
         <form:form cssClass="form-search" commandName="categoryForm" action="allSubCategories.htm" autocomplete="off">
            <div class="row">
               <div class="col-md-12">
                  <div class=:form-group">
                     <div class="input-group mb-2 serachinputbox">
                        <form:input path="domainSearchVal" cssClass="span2 form-control search-query" placeholder="Enter sub category name" 
                           id="subCategorySearch" cssStyle="width: 310px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <button type="submit" class="btn"><i class="fa fa-search"></i></button>
                           </span>
                        </div>
                     </div>
                  </div>
                  <div class="input-append">
                  </div>
               </div>
            </div>
         </form:form>
         <div style="font-size: 12px;">
            <span>&nbsp;</span>
         </div>
         <display:table id="subCategory" name="${subCategoryList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
            style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalActiveSubCategories}" 
            partialList="true" requestURI="allSubCategories.htm">
            <display:column title="Sub Category Name" style="width: 540px;" sortable="true" sortName="name">
               <a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="${ctx}/admin/listTopics.htm?id=${subCategory.id}"
                  style="color: #000; text-decoration: underline;">${subCategory.name}</a>
               <p>
                  <b><a onclick="showAccordianAndActiveLink(0, 'domainsLink')" href="listDomains.htm">Root:</a></b>&#47;&#47;&nbsp;
                  <b:breadcrumb separator="&nbsp;&#62;&#62;&nbsp;" serialId="${subCategory.parentCategory.id}" type="CATEGORY" />
               </p>
            </display:column>
            <display:column title="Edit">
               <a href="${ctx}/admin/editSubCategory.htm?id=${subCategory.uuid}&catId=${subCategory.parentCategory.id}
                  &prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&ale=1"><i class="icon-pencil"></i></a>
            </display:column>
            <display:column title="Add Topic">
               <a href="addTopic.htm?id=${subCategory.id}&catId=${subCategory.parentCategory.id}">Add</a>
            </display:column>
            <display:column title="Delete">
               <a href="deleteSubCategory.htm?id=${subCategory.id}&CategoryId=${subCategory.parentCategory.id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}&ale=1" 
                  onclick="return confirm('Warning: This deletes all of the data under this Sub Category (Topic, Sub Topic, Content)! Are you sure?')">
               <i class="icon-remove"></i>
               </a>
            </display:column>
         </display:table>
      </div>
   </body>
</html>