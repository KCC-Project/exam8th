<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>


<jsp:include page="../student/student-header.jsp" />


<div id="page-content-wrapper">


	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading main-color-bg">Profile</div>
			<div class="panel-body">

				<div
					class="col-lg-4 col-sm-8 col-sm-offset-2 col-lg-offset-0 profile-info ">
					<h4 class="text-center" id="full-name"></h4>
					<div align="center" class="profile-userpic">
						<img alt="User Pic" src="${cp}/assets/images/blank_user.png"
							class="img-responsive">
					</div>
					<h6 class="text-center">${ studentUserName }</h6>
				</div>

				<div class="col-lg-4 col-sm-6 profile-info" id="first_details">
					<p>First Name : ${first_name }</p>
					<p>Middle Name :${ middle_name }</p>
					<p>Last Name : ${ last_name}</p>
					<p>Gender : ${ gender}</p>
					<p>DOB : ${ dob}</p>
					<p>Email : ${ email}</p>
					<p>Status : ${ status}</p>
					<p>Phone : ${ phone}</p>
				</div>

				<div class="col-lg-4 col-sm-6 profile-info" id="side-profile-info">

					<p>Student ID : ${ studentID}</p>
					<p>Username : ${ studentUserName}</p>
					<p>Password : ${ password}</p>

				</div>

			</div>
		</div>
	</div>

	<div class="panel">
		<div class="panel-heading">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#recent-activity">
						<i class="glyphicon glyphicon-home"></i> Daily Activity
				</a></li>
				<li><a data-toggle="tab" href="#profile"> <i
						class="glyphicon glyphicon-user"></i> Profile
				</a></li>
				<li class=""><a data-toggle="tab" href="#edit-profile"> <i
						class="icon-envelope"></i> Edit Profile
				</a></li>
			</ul>
		</div>

		<div class="panel-body">
			<div class="tab-content">
				<div id="recent-activity" class="tab-pane active">
					<div class="profile-activity"></div>
				</div>

				<!-- profile -->
				<div id="profile" class="tab-pane">
					<div class="panel">
						<div class="panel-body profile-panel">
							<div class="table-responsive"></div>
						</div>
					</div>

				</div>
				<!-- edit-profile -->
				<div id="edit-profile" class="tab-pane">
					<div class="panel">
						<div class="panel-body profile-panel"></div>
					</div>
				</div>

			</div>
		</div>
	</div>

</div>



</body>
</html>