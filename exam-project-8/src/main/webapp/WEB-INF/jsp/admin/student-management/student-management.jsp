

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
<jsp:include page="../shared/header.jsp" />

	<!-- Content Area -->
	<div id="page-content-wrapper">


		<c:if test="${student_view_clicked == true}">
			<h1>
				-> form to select student or
				<button>
					<a href="${cp }/student-management/view/all" id=#studentAll>View All Students </a>
				</button>
			</h1>
		</c:if>

		<c:if test="${student_addNew_clicked == true}">
			<h1>-> Input form to add new student</h1>
		</c:if>

		<c:if test="${student_semester_clicked == true}">
			<h1>Input form to manage student semester</h1>
			<h3>-> search all students by program ('p_name'), semester_no:?
				, batch year: ? , status:?(current, completed, back..)</h3>
			<h3>-> search all data of student ('name') , display: Program,
				semester_no, Batch year, semester_status:?(current, completed,
				back..)</h3>
		</c:if>



	</div>

	<jsp:include page="../shared/footer.jsp" />