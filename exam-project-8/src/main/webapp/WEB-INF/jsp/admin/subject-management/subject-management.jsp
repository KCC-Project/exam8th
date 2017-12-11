

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../shared/header.jsp" />

<body>

	<div class="container-fluid display-table ">

		<div class="row display-table-row col-lg-12  ">

			<jsp:include page="../shared/admin-sidemenu.jsp" />


			<!-- Content Area -->
			
			
			<c:if test="${subject_view_clicked == true}" >
				<h1>-> form to select subject or 	<button>View all subjects</button> </h1>
			</c:if>
			
			<c:if test="${subject_addNew_clicked == true}" >
				<h1>-> Input form to add new subject</h1>
			</c:if>
			
			<c:if test="${subject_semester_clicked == true}" >
				<h1>Input form to manage subject semester</h1>
				<h3> -> search all subjects by program ('p_name'), semester_no:?</h3>
				<h3> -> search all data of subject ('name') or ('sub_code) , display: subject_name, program_name, semester_no ...</h3>	
			</c:if>


		</div>
	</div>


	<jsp:include page="../shared/footer.jsp" />>