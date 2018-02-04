<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
<spring:url value="/assets/css" var="css" />




<
<link href="${css }/bootstrap.min.css" rel="stylesheet">
<link href="${css }/loginAndResetPassword.css" rel="stylesheet">
<spring:url value="/assets/js/extraJs" var="extraJs" />
<script src="${extraJs}/jquery-3.2.1.min.js"></script>


</head>
<body>
	<div class="container">
		<form id="resetPasswordForm" >
			<div
				class="col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sm-offset-3 col-md-offset-3 col-lg-offset-4  form_margin_resetPassword_only">
				<div class="form-content ">
					<div class="border">
						<div class="form-header back white ">
							<div class="form-top-left ">
								<h3>Reset your password</h3>
							</div>
							<div class="form-top-right">
								<span class="glyphicon glyphicon-pencil"></span>
							</div>
							<br>
						</div>
						<div class="p">

							<div class="form-group">
								<label for="password"><span
									class="glyphicon glyphicon-eye-open"> </span> Password </label> <input
									type="password" class="form-control" required
									name="passwordField" placeholder="Enter password"
									id="passwordField" onkeyup="isPasswordSame();">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1"><span
									class="glyphicon glyphicon-eye-open"></span> Confirm password</label> <input
									type="password" class="form-control" required
									id="confirmPassword" name="confirmPassword"
									placeholder="Confirm password" onkeyup="isPasswordSame();">
							</div>
						
							
							<Button type="submit" id="resetBtn"
								class="btn btn-success btn-block login-btn_resetPassword_only"
							>
								<span class="glyphicon glyphicon-lock"></span>&nbsp;Reset
							</Button>

						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
	
	var vCode;
		var tablename;
		var ID;
		
		window.onload = function() {

			//alert("Automatic loads this script");
			var fullURL = window.location.search.substring(1);
			var parameterArray = fullURL.split('&');
			
			if (parameterArray == "" || parameterArray.length < 4) {
				 window.location.href = "/exam-project-8/home";
			} else {
				var currentParameterCode = parameterArray[1].split('=');
				var currentParameterTablename = parameterArray[2].split('=');
				var currentParameterID = parameterArray[3].split('=');

				if (currentParameterCode == ""
						|| currentParameterTablename == ""
						|| currentParameterID == "") {
					 window.location.href = "/exam-project-8/home";
				}
				vCode = currentParameterCode[1]; //vcode leko
				tablename = currentParameterTablename[1];
				ID = currentParameterID[1];

				if (vCode == null || tablename == null || ID == null) {
					 window.location.href = "/exam-project-8/home";
				} else {
					
					$.ajax({
						type : "GET",
						url : "/exam-project-8/ApiResetPassword/"+vCode+"/"+tablename+"/"+ID,
						data : "",

						success : function(data) {
						//alert("data from server = "+data);
						
						if (data.match("false")) {
							
							 window.location.href = "/exam-project-8/home";
							 
						}
						},
						error : function() {
							alert("Error...!!!");
						}

					});
				}
			}
		}

		function isPasswordSame() {
			var password = document.getElementById("passwordField").value
					.trim();
			var confirmPassword = document.getElementById("confirmPassword").value
					.trim();
			if (password === confirmPassword) {
				//alert("Password match=" + confirmPassword);
				document.getElementById("resetBtn").disabled = false;
				document.getElementById("resetBtn").innerHTML = "<span class='glyphicon glyphicon-lock'></span>&nbsp;Reset";
			} else {
				document.getElementById("resetBtn").disabled = true;
				document.getElementById("resetBtn").innerHTML = "<span class='glyphicon glyphicon-lock'></span>&nbsp;Password not matched";
			}
		}
		
		
		// error here not redirecting to home page but password will be reset
		
	$(document).ready(function() {
		
		$("#resetPasswordForm").submit(function(event) {
			$.ajax({
				type : "POST",
				url : "/exam-project-8/ApiResetPasswordNext",
				data : formToJSON(),
				contentType : "application/json",
				success : function(data) {
					//alert("success");
					if (data>0) {
						
						  window.location.href = "/exam-project-8/home";
					}else{
						
						alert("something wrong!!");
						 window.location.href = "/exam-project-8/home";
					}
				},
				error : function() {
					//alert("failed");
					if (window.confirm('Your password has been changed. Press "ok" to login. ')) 
					{
					window.location.href='/index';
					};
				
				}

			});
		});
			
	});
	
	function formToJSON() {
		var data = JSON.stringify({
			"vCode" : vCode,
			"tablename" :tablename,
			"ID":ID,
			"password" : $('#resetPasswordForm').find('[name="confirmPassword"]').val(),
		});
		//alert(data);
		return data;
	}
	
		/*
		function setDateToHiddenInputField(){
			document.getElementById("tableName").value = tablename;
			document.getElementById("verificationCode").value = vCode;
			document.getElementById("id").value =ID;
		}
		*/
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>