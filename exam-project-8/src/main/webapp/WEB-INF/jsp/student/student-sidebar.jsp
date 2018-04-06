<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ empty sessionScope.studentUserName }">
	<c:redirect url="/index " />
</c:if>
<div id="side-menu" class="sidenav">
	<div class="profile-userpic">
			<c:choose>
							<c:when test="${ gender =='Male'}">
								<img alt="User Pic" src="${cp}/assets/images/profileIcon.png"
									class="img-responsive">

							</c:when>

							<c:otherwise>
								<img alt="User Pic" src="${cp}/assets/images/girlICon.png"
									class="img-responsive">
							</c:otherwise>
						</c:choose>
	</div>
	<div class="profile-usertitle">
		<div class="profile-usertitle-name">
			<p class="student-name" id="full-name"></p></br>
		</div>
		<p class="student-name" id="current-semester"></p>
	</div>


	<a href="${cp }/user" id="view-profile" class="btn btn-success btn-sm profile-viewbtn">View Profile</a>


	<div class="profile-usermenu">
		<ul class="nav">
			<li class="active"><a href="${cp }/user"> <i class="glyphicon glyphicon-home"></i> Overview
			</a></li>
			<li><a href="${cp }/results"> <i class="glyphicon glyphicon-user"></i> Results
			</a></li>
			<li><a href="${cp }/upcoming-exams"> <i class="glyphicon glyphicon-ok"></i> Upcoming Exams
			</a></li>
			<li><a href="${cp }/subjects-details"> <i class="glyphicon glyphicon-flag"></i> Subjects Details
			</a></li>
		</ul>
	</div>
	<!-- END MENU -->
</div>

</script>
