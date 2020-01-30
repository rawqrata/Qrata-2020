	function validateAddUser() {
		if (!checkRequired("addUser")) {
			$("#addUserError").text("You must fill in all of the fields.");
			$("#addUserError").show();
			return false;
		}
	
		if (!validateEmail($("#email").val())) {
			$("#addUserError").text("Please enter a valid email ID.");
			$("#addUserError").show();
			$("#email").focus();
			return false;
		}
		if (!validateFirstName($("#firstName").val())) {
			$("#addUserError").text(
					"First name and Last name should be of only alphabets.");
			$("#addUserError").show();
	
			return false;
		}
		if (!validateLastName($("#lastName").val())) {
			$("#addUserError").text(
					"First name and Last name should be of only alphabets.");
			$("#addUserError").show();
			return false;
		}
	
		if (!validateLogin($("#login").val())) {
			$("#addUserError")
					.text(
							"Special character and blank spaces are not allowed at Login field.");
			$("#addUserError").show();
			$("#login").focus();
			return false;
		}
	
		if ($("#userPassword").val().trim().length < 6
				|| $("#userPassword").val().trim().indexOf(" ") > 0
				|| $("#userPassword").val().indexOf(" ") >= 0) {
			$("#addUserError")
					.text(
							"Your password must be at least 6 characters long and should not contain space(s) and should not be blank. Please try another.");
			$("#addUserError").show();
			return false;
		}
		if ($("#userPassword").val().trim() != $("#repassword").val().trim()) {
			$("#addUserError").text(
					"Your Password and Re-Enter password are not same.");
			$("#addUserError").show();
			return false;
		}
	
		// var flag = true;
	
		if ($("#login").val() != null) {
			// alert("hello");
			$
					.ajax({
						type : "GET",
						url : "existsLogin.htm?name=" + $("#login").val(),
						dataType : "json",
						contentType : "application/json",
						success : function(response) {
							if (response.exists) {
								$("#msgDiv")
										.html(
												"<span style='color: red'>This login already exists.</span>");
								return false;
							} else {
								$("#msgDiv").html("");
								existEmail($("#email").val());
								return false;
							}
						},
						error : function() {
							alert("error");
						}
					});
		}
	}

	function existEmail(val) {
		$
				.ajax({
					type : "GET",
					url : "existsEmail.htm?email=" + val,
					dataType : "json",
					contentType : "application/json",
					success : function(response) {
						if (response.exists) {
							$("#msg1Div")
									.html(
											"<span style='color: red'>This email already exists.</span>");
							return false;
						} else {
							$("#msg1Div").html("");
							document.forms["addUser"].submit();
						}
					},
					error : function() {
						alert("error");
					}
				});
	}


	function validateLogin(login) {
		var letter = /^[A-Za-z0-9]+$/;
		var reg = /^[A-Za-z0-9]+\.[A-Za-z0-9]+$/;
		if (login.match(letter) || login.match(reg)) {
			// alert("good");
			return true;
		} else {
			// alert("error");
			return false;
		}
	}

	function validateFirstName(firstName) {
		firstName = trimField(firstName);
		// alert("firstname"+firstName);
		var letters = /^[A-Za-z]+$/;
		if (firstName.match(letters)) {
			// alert('Your name have accepted : you can try another');
			return true;
		} else {
			// alert('Please input alphabet characters only');
			return false;
		}
	
	}
	
	function validateLastName(lastName) {
		lastName = trimField(lastName);
		var reg = /^[A-Za-z]+$/;
		if (lastName.match(reg)) {
			// alert("your lastname have accepted");
			return true;
		} else {
			// alert("please try again");
			return false;
		}
	}


	function validateEmail(email) {
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		return reg.test(email);
	}
	
	function checkRequired(formId) {
		var isValid = true;
		$('#' + formId).find(":text,:password")
				.each(
						function(index) {
							if ($(this).hasClass("required")) {
								if ($.trim($(this).val()) == ""
										|| $(this).val().indexOf(
												$(this).attr('title')) >= 0) {
									// $(this).attr("placeholder",
									// $(this).attr("title"));
									isValid = false;
								}
							}
						});
		return isValid;
	}


	/*
	 * Ajax Method for Fetching Count of Links for Admin/Editor on Page Load
	 */
	function fetchCount() {
		fetchCountMyData();
		fetchCountExpertData();
	}
	/*
	 * My Publishing Counts
	 */
	function fetchCountMyData() {
		$.ajax({
			type: "GET",
			url: $("#contxt").val()+"/admin/fetchCountMyData.htm",
			dataType: "json",
			contentType: "application/json",
			cache: false,
			beforeSend: function() {
				$(".myDataLoadingImg").show();
			},
			complete: function() {
				$(".myDataLoadingImg").hide();
			},
			success: function(response) {				
				$("#rateTopics").html(response.rateTopics);
				$("#rateSubTopics").html(response.rateSubTopics);
				$("#rateContents").html(response.rateContents);
				$("#inProgress").html(response.inProgress);
				$("#published").html(response.published);
			},
			error: function() {
				//alert("error");
			}
		});
	}
	/*
	 * Expert Publishing Counts
	 */
	function fetchCountExpertData() {
		$.ajax({
			type: "GET",
			url: $("#contxt").val()+"/admin/fetchCountExpertData.htm",
			dataType: "json",
			contentType: "application/json",
			cache: false,
			beforeSend: function() {
				$(".expertDataLoadingImg").show();
			},
			complete: function() {
				$(".expertDataLoadingImg").hide();
			},
			success: function(response) {
				$("#expertTopics").html(response.expertTopics);
				$("#expertSubTopics").html(response.expertSubTopics);
				$("#expertContents").html(response.expertContents);				
				$("#expertInProgress").html(response.expertInProgress);
				$("#expertPendingApproval").html(response.expertPendingApproval);
				$("#expertRework").html(response.expertRework);
				$("#expertPublished").html(response.expertPublished);
			},
			error: function() {
				//alert("error");
			}
		});
	}
	
	
	/*
	 * Ajax Method for Fetching Count of Links for Expert on Page Load
	 */
	function fetchCountExpert() {
		$.ajax({
			type: "GET",
			url: $("#contxt").val()+"/admin/fetchCountExpert.htm",
			dataType: "json",
			contentType: "application/json",
			cache: false,
			beforeSend: function() {
				$(".loadingImgExpert").show();
			},
			complete: function() {
				$(".loadingImgExpert").hide();
			},
			success: function(response) {
				$("#expertTopics").html(response.responseObj.expertTopics);
				$("#expertSubTopics").html(response.responseObj.expertSubTopics);
				$("#expertContents").html(response.responseObj.expertContents);				
				$("#expertInProgress").html(response.responseObj.expertInProgress);
				$("#expertPendingApproval").html(response.responseObj.expertPendingApproval);
				$("#expertRework").html(response.responseObj.expertRework);
				$("#expertPublished").html(response.responseObj.expertPublished);
			},
			error: function() {
				//alert("error");
			}
		});
	}
	
	
		/*
		 *  Get Criteria Description
		 */	
	function getCriteriaDescription(id) {
		var url = $("#contxt").val()+"/admin/getDescription.htm?id="+id;
		$.ajax({
	  		type: "GET",
	  		url: url,
	  		dataType: "json",
	  		contentType: "application/json",
	  		cache: false,
	  		success: function(response) {
	  			var obj = response.obj;
	  			var htm = "<div id='descrHolder' style='float: left;width: 500px;height: auto;'><table width='100%'>" +
	  					"<tr><th colspan='2' align='center' class='table-heading'>Criteria Details</th></tr>" +
	  					"<tr><th width='20%' valign='top' align='left'>Name:</th><td align='left'>"+obj.name+"</td></tr><tr><th valign='top' align='left'>Description:</th><td align='left'>"+obj.description+"</td></tr>" +
	  					"</table></div>";
	  			$.fancybox({
	  				content: htm
	  			});
	  		},
	  		error: function(xhr,status,error) {
 			 	//alert("Error: " + error);
		    }
		});
	}
	
	
		/*
		 * Left Panel Settings
		 */
	function implementAccordian() {
		var activeTab = getCookieValue("activeTab");
		if(activeTab != null && activeTab != "none") {
			activeTab = parseInt(activeTab);
		} else {
			activeTab = "none";
		}
		$("#accordion").accordion({active: activeTab, collapsible: true});
	}
	
	function showActiveLink() {
		var activeLink = getCookieValue("activeLink");
		if(activeLink != null) {
			$("#"+activeLink).parent().css({'background-color' : '#E6E6E6'});
			$("#"+activeLink).css({'font-weight' : 'bold'});
		}
	}
	
	function showAccordianAndActiveLink(activeTab, activeLink) {
		document.cookie = "activeTab="+activeTab+";path=/";
		document.cookie = "activeLink="+activeLink+";path=/";
	}
	
	
		/*
		 * History Back button Function
		 */
	function historyButton() {
		window.history.back(1);
		document.cookie = "value=1;";
	}
	
		/*
		 * Check Back Button Cookie
		 */
	function checkBackButton() {
		var value = getCookieValue("value");
		if(value == 1) {
			 document.cookie = "value=0;";
			 window.location.reload(true);
		}
	}
	
	
		/*
		 * Get Cookie Value  by Name 
		 */
	function getCookieValue(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
		}
		return null;
	}
	
		/*
		 * Trim a field's value
		 */
	function trimField(fieldVal) {
		if(fieldVal) {
			fieldVal = $.trim(fieldVal);
		}
		return fieldVal;
	}
	
	
		/*
		 * Common Autocompletion Facility
		 */
	function CommonAutocompleter() {		
	}
	
	CommonAutocompleter.prototype.commonAutocomplete = function(url, inputId, type, parentId) {
		if(!parentId) {parentId = 0;} if(!type) {type = null;}
		$("#"+inputId).autocomplete({
			 source: function(request, response) {
				 $.ajax({
					 url: url,
					 dataType: "json",
					 data: {
						 name: request.term,
						 type: type,
						 parentId: parentId
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
	};
		
	/*
	 * Monkey Patching The Jquery Autocompleter (Highlight the Search Term)
	 */
	function monkeyPatchAutoComplete() {
		$.ui.autocomplete.prototype._renderItem = function(ul, item) {
	          var re = new RegExp("^"+this.term, "i");
	          var text = item.label.replace(re, "<span style='font-weight: bold;color: Blue;'>"+this.term+"</span>");
	          return $( "<li></li>" )
	              .data( "item.autocomplete", item )
	              .append( "<a>" + text + "</a>" )
	              .appendTo( ul );
	    };
	}
	
	
	/*
	 * Validate image file upload for extension i.e. Allow only 'gif','png','jpg','jpeg' extensions
	 */
	function validateImage(inputId) {
		var ext = $('#'+inputId).val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
		    alert('Please select a valid image with extension .gif,.png,.jpg or .jpeg');
		    return false;
		}
		return true;
	}
