<%@page import="java.net.URI"%>
<%@page import="org.springframework.context.annotation.Import"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" scope="application">${pageContext.request.contextPath}</c:set>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "${cp }/index "/>
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

<!-- Images Directory , don't know why, doesn't link.. need to specify contextPath/ImageFolder/imgName.jpg -->
<spring:url value="/assets/images/" var="images" />

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MIS</title>

<!-- Linked CSS -->
<link href="${css }bootstrap.min.css" rel="stylesheet">
<link href="${css}select2.min.css" rel="stylesheet">
<link href="${css}select2-bootstrap.min.css" rel="stylesheet">
<link href="${css}bootstrap-editable.css" rel="stylesheet">
<link href="${fontAwasome}" rel="stylesheet">
<link href="${css}bootstrap-switch.min.css" rel="stylesheet">
<link href="${css}datatables.min.css" rel="stylesheet">
<!-- Written CSS -->
<link href="${css }style.css" rel="stylesheet">
<link href="${css }sidebar.css" rel="stylesheet">
<link href="${css }style-responsive.css" rel="stylesheet">
<link href="${css}profile.css" rel="stylesheet">


<!-- Linked JS -->
<script src="${extraJs}jquery-3.2.1.min.js"></script>
<script src="${extraJs}bootstrap.min.js"></script>
<script src="${extraJs}datatables.min.js"></script>
<script src="${extraJs}select2.min.js"></script>
<script src="${extraJs}bootstrap-editable.min.js"></script>
<script src="${extraJs}bootstrapValidator.min.js"></script>
<script src="${extraJs}bootstrap-switch.min.js"></script>
<script src="${extraJs}bootbox.min.js"></script>
<!-- Written JS -->
<script src="${custome}custome_editableJs.js"></script>
<script src="${custome}reUsableJs.js"></script>
<script src="${Js}default.js"></script>


<script>
	window.context = '${cp}';
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
						<a href="#menu-toggle" class="btn btn-default menu-toggle">Toggle
							Menu</a> <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						Dashboard <small>Manage</small>


					</h4>
				</div>
				<div class="hidden-xs pull-right" id="dash-info">
					<a href="${cp }/logoutAdmin"><input type="submit"
						class="btn btn-defult pull-right" value="logout"></a>
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
			<jsp:include page="admin-sidemenu.jsp" />
		</div>


		<!-- ------------------------------------------ -->