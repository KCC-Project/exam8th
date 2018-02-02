<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if (session.getAttribute("adminUserName") != null) {
		response.sendRedirect("home");
	} else if (session.getAttribute("studentUserName") != null) {
		response.sendRedirect("user");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>






<spring:url value="/assets/css" var="css" />
<spring:url value="/assets/font-awesome/css/font-awesome.min.css"
	var="fontAwasome" />



<link href="${css}/w3school.css" rel="stylesheet">
<link href="${fontAwasome}" rel="stylesheet">
<link href="${css }/bootstrap.min.css" rel="stylesheet">
<link href="${css}/loginAndResetPassword.css" rel="stylesheet">

<spring:url value="/assets/js/extraJs" var="extraJs" />
<script src="${extraJs}/jquery-3.2.1.min.js"></script>
<script src="${extraJs}/bootstrap.min.js"></script>
</head>
<body>
	<div id="google_translate_element"></div>
	<div class="container">
		<!--action="ApiLoginOut"  -->
		<form method="post" action="#" enctype='application/json' id="myForm">
			<div
				class="col-xs-12 col-sm-6 col-md-6 col-lg-4 col-sm-offset-3 col-md-offset-3 col-lg-offset-4  form_margin">
				<div class="form-content ">
					<div class="border">
						<div class="form-header back white ">
							<div class="form-top-left ">
								<h3>Login</h3>
								<p>Be a part of your College Community</p>
							</div>
							<div class="form-top-right">
								<span class="glyphicon glyphicon-pencil"></span>
							</div>

							<br>
						</div>


						<div class="p">

							<div class="form-group">

								<div class="form-group category">

									<label for="category"> <span
										class="glyphicon glyphicon-user"></span> Login as
									</label> <select required class="form-control form-element"
										name='category' id="category">
										<option class='drop-down' value="" disabled selected>
											Select One</option>
										<option class='drop-down' value='admin'>Admin</option>
										<option class='drop-down' value='student'>Student</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1"><span
									class="glyphicon glyphicon-user"> </span> Username / Email </label> <input
									type="text" class="form-control" required
									name="InputEmail1User" id="InputEmail1User"
									placeholder="Username/Email">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1"><span
									class="glyphicon glyphicon-eye-open"></span> Password</label> <input
									type="password" class="form-control" required
									name="InputPassword1" id="InputPassword1"
									placeholder="Password">
							</div>

							<div class="checkbox">
								<label class="pull-left"> <input type="checkbox"
									name="rememberMe" id="rememberMe"> Remember Me
								</label>

							</div>

							<Button type="submit" class="btn btn-success btn-block login-btn">
								<span class="glyphicon glyphicon-lock"></span>&nbsp;Login

							</Button>
							<hr>
							<label class="pullright"> <a href="" data-toggle="modal"
								data-target="#myModal">forgot password?</a>
							</label>
						</div>
					</div>
				</div>

			</div>


		</form>
	</div>



	<!-- Modal popup of forgot password -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Forgot Password</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="input for forgot email"><span
							class="glyphicon glyphicon-user"> </span> Email </label> <input
							onkeyup="showSubmit();" type="text" class="form-control" required
							id="forgotemailname" placeholder="Email">
					</div>
					<div class="rotationIcon text-center" hidden id="rotationIcon">
						<i class='fa fa-refresh w3-spin' style="font-size: 50px;"></i>
					</div>
					<Button onclick="send();" type="submit" id="btnn"
						class="btn btn-primary active btn-block submit-btn login-btnReset">
						<span class="glyphicon glyphicon-lock"></span>&nbsp;Send
						verification link
					</Button>
					<div id="errmsg"></div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<script>
		$(document).ready(function() {
			$("#myForm").submit(function(event) {

				$.ajax({
					type : "POST",
					url : "/exam-project-8/ApiLoginOut",
					contentType : "application/json",
					data : formToJSON(),

					success : function(data) {
						if (data=="success") {
							//alert("inside");
							  window.location.href = "/exam-project-8/home";
						}else{
							alert("something wrong!!");
						}
					},
					error : function() {
						alert("Error...!!!");
					}

				});

				event.preventDefault();
			});

		});

		function formToJSON() {
			var data = JSON.stringify({

				"category" : $('#myForm').find('[name="category"]').val(),
				"adminUserName" : $('#myForm').find('[name="InputEmail1User"]').val(),
				"InputPassword1" : $('#myForm').find('[name="InputPassword1"]').val(),

			});
			//alert(data);
			return data;
		}
	</script>


</body>
</html>