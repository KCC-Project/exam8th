<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="cp" scope="application">${pageContext.request.contextPath}</c:set>

<c:if test="${ empty sessionScope.studentUserName }">
	<c:redirect url="/index " />
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>

<!-- CSS Directory -->
<spring:url value="/assets/css/" var="css" />
<spring:url value="/assets/font-awesome/css/font-awesome.min.css"
	var="fontAwasome" />

<!-- JS Directory -->
<spring:url value="/assets/js/extraJs/" var="extraJs" />
<spring:url value="/assets/js/" var="Js" />
<spring:url value="/assets/js/custome/" var="custome" />

<!-- Images Directory -->
<spring:url value="/assets/images/" var="images" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MIS</title>

<!-- Linked CSS -->
<link href="${css }/bootstrap.min.css" rel="stylesheet">
<link href="${css}/select2.min.css" rel="stylesheet">
<link href="${css}/select2-bootstrap.min.css" rel="stylesheet">
<link href="${css}/bootstrap-editable.css" rel="stylesheet">
<link href="${fontAwasome}" rel="stylesheet">
<link href="${css}/bootstrap-switch.min.css" rel="stylesheet">
<!-- Written CSS -->
<link href="${css }style.css" rel="stylesheet">
<link href="${css }sidebar.css" rel="stylesheet">
<link href="${css }/style-responsive.css" rel="stylesheet">
<link href="${css}/profile.css" rel="stylesheet">
<link href="${css}datatables.min.css" rel="stylesheet">

<!-- Linked JS -->
<script src="${extraJs}/jquery-3.2.1.min.js"></script>
<script src="${extraJs}/bootstrap.min.js"></script>
<script src="${extraJs}/datatables.min.js"></script>
<script src="${extraJs}/select2.min.js"></script>
<script src="${extraJs}/bootstrap-editable.min.js"></script>
<script src="${extraJs}/bootstrapValidator.min.js"></script>
<script src="${extraJs}/bootstrap-switch.min.js"></script>
<!-- Written JS -->
<script src="${custome}/custome_editableJs.js"></script>
<script src="${custome}/reUsableJs.js"></script>
<script src="${Js}/default.js"></script>

<script>
	window.context = '${cp}';

	$(document).ready(function() {
		$("#open-menu").hide();
		$("#side-menu").css("width", "18%");

		$("#open-menu").click(function(event) {
			$("#side-menu").css("width", "250px");
			$("#page-content-wrapper").css("margin-left", "250px");

			$("#open-menu").hide();
			$("#close-menu").show();
		});
		$("#close-menu").click(function(event) {
			$("#side-menu").css("width", "0");
			$("#page-content-wrapper").css("margin-left", "0");

			$("#close-menu").hide();
			$("#open-menu").show();
		});
	});
</script>
</body>

</head>
<body>
	<!-- --------------------------------------  -->
	<div class="container-fluid dashboard-container">
		<div class="row">
			<header id="dashboard-header">

				<!-- Side-bar toggler -->

				<!-- Dash info display -->
				<div class="hidden-xs pull-left" id="dash-info">
					<h4>
						<button id="open-menu" class="btn btn-default menu-toggle">Open &#9776; </button>
						<button id="close-menu" class="btn btn-default closebtn">Close &times;</button>
						
							<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						Dashboard <small>View</small>

					</h4>
				</div>
				<div class="hidden-xs pull-right" id="dash-info">
					<a href="${cp }/logoutStudent"><input type="submit"
						class="btn btn-danger pull-right" value="logout"></ 
				</div>
				<!-- Dashboard search bar -->
				<div class="search-field pull-left">
					<input type="text" placeholder="search" class="form-control">
				</div>
			</header>
		</div>
	</div>

	<div id="wrapper">
		<div id="sidebar-wrapper">
			<jsp:include page="student-sidebar.jsp" />
		</div>