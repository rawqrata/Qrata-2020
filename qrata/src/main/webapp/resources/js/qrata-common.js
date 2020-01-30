/**
 * @Author Gurminder Singh
 */

	function getDomains() {
		$.ajax({
			type: "GET",
			url: $("#contxt").val()+"/getDomains.htm",
			dataType: "json",
			contentType: "application/json",
			cache: false,
			success: function(response) {
				generateDomainHTML(response.obj);
			},
			error: function(jqXhr) {
				alert("error");
			}
		});
	}
	
	function generateDomainHTML(objList) {
		var htm = "";
		if(objList.length != 0) {
			for(var i in objList) {
				htm += "<li><a href='"+$("#contxt").val()+"/categories/listCategories.htm?id="+objList[i].id+"' title='"+objList[i].name+"'>"+objList[i].name+"</a></li>";
			}
		} else {
			htm += "<li>No domains available</li>";
		}
		
		$("#top-bar1").html(htm);
		$("#top-bar-ul").html(htm);

/*        if($('.nano') !== undefined) {
            $('.nano').nanoScroller({
                preventPageScrolling: true,
                alwaysVisible: true
            });
        }*/
	}
	
	
	/*
	 * 
	 * Search Auto Completer
	 * 
	 */
	$(document).ready(function() {
		$("#searchTerm" ).autocomplete({
			 source: function(request, response) {
				 $.ajax({
					 url: $("#contxt").val()+"/reviews/qrataSearchKeywords.htm",
					 dataType: "json",
					 data: {
						 q: request.term
					 },
					 success: function(data) {
						 response(
							 $.map(data, function(item) {
								 return {
									 label: item.name,
									 value: item.name
								 }
							 })
						 );
					 }
				 });
			 },
			 select: function(event, ui) {
				 if(ui.item) {
					 $(event.target).val(ui.item.value);
				 }
				 $(event.target.form).submit();
				 return false;
			 }
		});
		
		

	});
	
	