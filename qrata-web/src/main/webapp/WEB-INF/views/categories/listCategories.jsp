<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="b" uri="/WEB-INF/breadcrumb.tld"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Insert title here</title>
      <script type="text/javascript">
         $(document).ready(function() {
         	var autoCompleter = new CommonAutocompleter();
         	autoCompleter.commonAutocomplete($("#contxt").val()+"/admin/autoSuggestCategoryByNameAndType.htm", "categorySearch", "CATEGORY", "${id}");
         });
         
         /*
         function editCategory(categoryId, domainId){
         	var subString = window.location.search.substring(1);
         	var tempString = subString.split("id=");
         	alert(tempString.length);
         	if(tempString.length != 0){
         		if(tempString[1] != domainId){
         			alert(tempString[1]);
         			var tempString2 = tempString[1].substring(2);
         			var url = tempString2.split("&success=");
         			window.location.href = "editCategory.htm?id="+categoryId+"&domainId="+domainId+"&url="+url[0];					
         		}else{
         			tempString = subString.split("&success=");
         			aler("In Else Part");
         			if(tempString[0] != ""){
         				window.location.href = "editCategory.htm?id="+categoryId+"&domainId="+domainId+"&url="+tempString[0];
         			}else{
         				window.location.href = "editCategory.htm?id="+categoryId+"&domainId="+domainId;
         			}	
         		}
         	}
         }*/
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
               <li class="none">Categories</li>
            </ul>
         </div>
         <div class="col-md-4 col-4">
            <div class="back-button">
               <a href="javascript:;" onclick="javascript:historyButton()">&#60;&#60;Go Back </a>
            </div>
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
      <div class="categorymainBox mt-4">
         <form:form cssClass="form-search" commandName="categoryForm" action="listCategories.htm?id=${id}" autocomplete="off">
            <div class="row">
               <div class="col-md-12">
                  <h5>
                     <a href="listDomains.htm">Root</a>://
                     <b:breadcrumb separator="/" serialId="${id}" type="CATEGORY" />
                  </h5>
               </div>
               <div class="col-md-12">
                  <div class=:form-group">
                     <div class="input-group mb-2 serachinputbox">
                        <form:input path="domainSearchVal" cssClass="span2 form-control search-query" placeholder="Enter category name" 
                           id="categorySearch" cssStyle="width: 310px;" />
                        <div class="input-group-prepend">
                           <span class="input-group-text">
                           <button type="submit" class="btn"><i class="fa fa-search"></i></button>
                           </span>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </form:form>
         <span>To add a new Category to this list, <a
            href="addCategory.htm?id=${id}&type=c">click here..</a></span> <br />
         <display:table id="category" name="${categories}" pagesize="10" class="displayTable table table-striped table-hover table-bordered table-condensed"
            style="margin-bottom:1%;margin-top:1%;" defaultsort="1" sort="external" defaultorder="descending" size="${totalActiveCategories}" partialList="true" requestURI="listCategories.htm" 
            offset="0">
            <display:column title="Category Name" style="width: 540px;" sortable="true" sortName="name">
               <a href="${ctx}/admin/listSubCategory.htm?id=${category.id}">${category.name}</a>
            </display:column>
            <display:column title="Edit">
               <a href="editCategory.htm?id=${category.uuid}&domainId=${id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"><i class="icon-pencil"></i></a>
            </display:column>
            <display:column title="Add Sub Category">
               <a href="addSubCategory.htm?id=${category.id}&domainId=${id}">Add</a>
            </display:column>
            <display:column title="Delete">
               <a href="deleteCategory.htm?id=${category.id}&domainId=${id}&prp=${prp}&orp=${orp}&sfrp=${sfrp}&sunrp=${sunrp}"
                  onclick="return confirm('Warning: This deletes all of the data under this Category(Sub Category, Topic, Sub Topic, Content)! Are you sure?')">
               <i class="icon-remove"></i>
               </a>
            </display:column>
         </display:table>
      </div>
   </body>
</html>