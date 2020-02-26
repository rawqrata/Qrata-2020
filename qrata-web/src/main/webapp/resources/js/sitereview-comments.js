/**
 * Author Gurminder Singh
 */
	
	$(document).ready(function() {
		/*
		 * Show/Hide Comments Area Event
		 */
		$("#show-hide").click(function() {
			$("#text-input").val("");
			$("#comments-area").toggle("slow", function() {
				if($(this).css("display") != "none") {
					fetchAllComments();
				} else {
					$("#comments-view").html("");
				}
			});
		});
		
		
		/*
		 * Add Comment Click Event
		 */
		$("#comments-button").click(function() {
			addComment();
		});
		
		/*
		 * Get New Comments
		 */
		getUnreadComments();
		
	});
	
	/*
	 * Get Unread(New) Comments
	 */
	function getUnreadComments() {
		var siteReviewId = $("#siteReviewId").val();
		$.ajax({
			url: "getUnreadComments.htm?siteReviewId="+siteReviewId,
			dataType: "json",
			contentType: "application/json",
			type: "GET",
			cache: false,
			success: function(response) {
				if(response.total != 0) {
					$("#new-comments-span").show();
					$("#new-comments-span").html(response.total+" New comments");
				}
			},
			error: function() {
				//alert("Error in callback");
			}
		});
	}
	
	/*
	 * Fetch All Comments
	 */
	function fetchAllComments() {
		var siteReviewId = $("#siteReviewId").val();
		$.ajax({
			url: "fetchAllComments.htm?siteReviewId="+siteReviewId,
			dataType: "json",
			contentType: "application/json",
			type: "GET",
			cache: false,
			beforeSend: function() {
				showLoadingImage();
			},
			complete: function() {
				hideLoadingImage();
			},
			success: function(response) {
				$("#new-comments-span").hide();
				if(response.obj.length != 0) {
					for(var i in response.obj) {
						generateCommentHTML(response.obj[i]);
					}
				}
			},
			error: function() {
				//alert("Error in callback");
			}
		});
	}
	
			
	/*
	 * Add Comment
	 */
	function addComment() {
		var siteReviewId = $("#siteReviewId").val();
		var comment = $("#text-input").val();
		var jsonObj = {siteReviewId : siteReviewId, comment : comment};
		var jsonStr = JSON.stringify(jsonObj);
		if(comment != null && comment != "") {
			$.ajax({
				url: "addComment.htm",
				data: jsonStr,
				dataType: "json",
				contentType: "application/json",
				type: "POST",
				beforeSend: function() {
					showLoadingImage();
				},
				complete: function() {
					hideLoadingImage();
				},
				success: function(response) {
					$("#text-input").val("");
					generateCommentHTML(response.obj);
				},
				error: function() {
					//alert("Error in callback");
				}
			});
		} else {
			alert("Please write something in the comment box.");
			return false;
		}
	}
	
	
	/*
	 * Generate Comment HTML
	 */
	function generateCommentHTML(obj) {
		var id = obj.id;
		var userName = obj.userName;
		var comment = obj.comment;
		var dateCreated = obj.dateCreated;
		
		var htm = "<div id='comment-space-"+id+"' style='float: left;width: 100%;height: 3px;background-color: #FFFFFF;'></div>" +
				"<div id='comment-"+id+"' style='float: left;width: 100%;'>" +
				"<div style='float: left; width: 87%;padding-left: 5px;'><span style='font-weight: bold;font-size: 13px;'>"+userName+"</span><p style='word-wrap: break-word;font-size: 13px;'>"+comment+"</p></div>" +
					"<div style='float: right;font-size: 11px;width: 10%;'>"+dateCreated+"</div></div>";
		var htmEle = $(htm);
		$("#comments-view").append(htmEle);
	}
	
	
	/*
	 * Show Loading Image
	 */
	function showLoadingImage() {
		var loading = $("<span><img src='"+$("#context").val()+"/resources/images/fancybox_loading.gif' alt='Loading....' /></span>");
		$("#comments-loading").html(loading);
		$("#comments-loading").show();
	}	
	/*
	 * Hide Loading Image
	 */
	function hideLoadingImage() {
		$("#comments-loading").html("");
		$("#comments-loading").hide();
	}
	