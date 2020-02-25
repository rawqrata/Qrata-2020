<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <script type="text/javascript">
         $(document).ready(function() {
         	var autoCompleter = new CommonAutocompleter();
         	autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestCategoryByNameAndType.htm", "subCategorySearch", "SUBCATEGORY", "${id}");
         });
      </script>
   </head>
   <body>
      <div class="row">
         <div class="col-md-12">
            <c:choose>
               <c:when test="${param.success eq '1' }">
                  <div class="success-box">
                     <c:out value="Sub Category has been created successfully" />
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
            <div class="back-button"  ><a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60; Go Back </a></div>
         </div>
      </div>
      <div class="row">
         <div class="col-md-12">
            <h5>
               <a href="listDomains.htm">Root</a>://
               <b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" />
            </h5>
         </div>
      </div>
      <div class="sbcatbox">
         <form:form cssClass="form-search" commandName="categoryForm" action="listSubCategory.htm?id=${id}" autocomplete="off">
            <div class="row">
               <div class="col-md-12">
                  <div class=:form-group">
                     <div class="input-group mb-2 serachinputbox">
                        <form:input path="domainSearchVal" cssClass="span2 form-control search-query" placeholder="Enter sub category name" 
                           id="subCategorySearch" cssStyle="width: 310px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <button type="submit" class="btn">
                           <i class="fa fa-search"></i>
                           </button>
                           </span>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </form:form>
         <span>To add a new Sub Category to this list, <a href="addSubCategory.htm?id=${id}&type=d">click here..</a></span>
         <br />
         <display:table id="subCategory" name="${subCategoryList}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed" 
            style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalActiveSubCategories}" partialList="true" requestURI="listSubCategory.htm">
            <display:column title="Sub Category Name" style="width: 540px;" sortable="true" sortName="name">
               <a href="listTopics.htm?id=${subCategory.id}">${subCategory.name}</a>
            </display:column>
            <display:column title="Edit">
               <a href="${ctx}/admin/editSubCategory.htm?id=${subCategory.uuid}&catId=${id}
                  &prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="icon-pencil"></i></a>
            </display:column>
            <display:column title="Add Topic">
               <a href="addTopic.htm?id=${subCategory.id}&catId=${id}">Add</a>
            </display:column>
            <display:column title="Delete">
               <a href="deleteSubCategory.htm?id=${subCategory.id}&CategoryId=${id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}" 
                  onclick="return confirm('Warning: This deletes all of the data under this Sub Category (Topic, Sub Topic, Content)! Are you sure?')">
               <i class="icon-remove"></i>
               </a>
            </display:column>
         </display:table>
      </div>
   </body>
</html>