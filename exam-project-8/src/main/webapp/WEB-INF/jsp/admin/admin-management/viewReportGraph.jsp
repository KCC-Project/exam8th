<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" scope="application">${pageContext.request.contextPath}</c:set>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${ empty sessionScope.adminUserName }">
	<c:redirect url="/index " />
</c:if>

<jsp:include page="../shared/header.jsp" />

<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="admin-dashboard.jsp"><span
				class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Analysis</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Graph</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	
<div class="col-md-12 col-sm-12 col-lg-12 display-table-cell "
			id="content-area">


			<div class="panel-group" id="first-one">
	
				<div class="row ">
				<div class="col-lg-7 ">
						<div class="row">
						<div class="col-lg-12">
							<div class="card">
								<div class="header">
									<h4 class="title">1. Total Student</h4>
									<p class="category">Per Faculty</p>
								</div>
								<div class="content " >
									<div id="piechart1" style="background-color: white;"></div>
								</div>
							</div>
							</div>
							</div>
						</div>


					<div class="col-lg-5 news ">
						<div class="row">
							<div class="col-lg-12">

								<div class="panel panel-default">
									<div class="panel-heading">
										<span class="glyphicon glyphicon-list-alt"
											style="font-size: 15px;" aria-hidden="true"></span>&nbsp;Notice
									</div>
									<div class="panel-body news_pannel_body">
										<div class="danger">
											<p>
												<strong>Graph 1 !</strong> This graph show the ratio of students distributed in all faculties.
											</p>
										</div>

										<div class="success">
											<p>
												<strong>Graph 2 !</strong> This graph show the ratio of  students failed per faculty.
											</p>
										</div>
										<div class="warning">
											<p>
												<strong>Table !</strong> This table show the student's most failed subject with program.
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- =================================1st graph========================================= -->
				<br>
				<div class="row hidden-xs">
					<div class="col-lg-12 ">
						<!-- 1st garph -->
						<div class="col-lg-6 ">
						<div class="row">
						<div class="col-lg-12">
							<div class="card">
								<div class="header">
									<h4 class="title">2. Total Failed Student</h4>
									<p class="category">Per Faculty</p>
								</div>
								<div class="content " >
								<div id="piechart2" style="background-color: white;"></div>
								
								</div>
							</div>
							</div>
							</div>
						</div>
						
						<div class="col-lg-6  ">
						<div class="row">
						<div class="col-lg-12">
							<div class="card">
								<div class="header">
									<h4 class="title">3. Most Failed Subjects</h4>
									<p class="category">All Faculty</p>
								</div>
								<div class="content  " >
										<div  style="background-color: white;">

											<table id="max_fail_subject"
												class="table table-hover table-striped table-responsive"
												cellspacing="0" width="100%">
												<thead>
													<tr class="info">

														<th>#</th>
														<th>Subject Name</th>
														<th>NOS</th>
														<th>Program</th>

													</tr>
												</thead>
											</table>

										</div>
								</div>
							</div>
							</div>
						</div>
						</div>
					</div>

				</div>


				<!-- =================================================================================== -->
			</div>
			<!--end row here  -->
		</div>
	

</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
