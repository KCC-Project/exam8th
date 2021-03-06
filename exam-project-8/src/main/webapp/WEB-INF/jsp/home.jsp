

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" scope="application">${pageContext.request.contextPath}</c:set>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
      
<jsp:include page="../jsp/admin/shared/header.jsp" />

<body>
        
        <!-- Content Area -->
        <div  id="page-content-wrapper">
          <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!-- Content Area Added by mausam-->
		<div class="col-md-12 col-sm-12 col-lg-12 display-table-cell "
			id="content-area">

			<ol class="breadcrumb">
				<li><a href="${cp}/home"><span
						class="glyphicon glyphicon-home"> Home</span></a></li>
				<li><a><span class="glyphicon glyphicon-blackboard black">
							Dashboard</span></a></li>
			</ol>

			<div class="panel-group" id="first-one">

				<!--NOtice and message loading section  -->
				<div class="callout callout-info msg-of-day">
					<h4 style="color: midnightblue;">
						<i class="fa fa-bullhorn"></i> Message of day box
					</h4>
					<p>
						<marquee class="dash-msg" scrollamount="7" behavior="alternate" direction="left">Each
							Day is a GIFT don't send it BACK unopened. Have a nice Day !</marquee>
					</p>
				</div>
				<!--End  of notice and message section  -->

				<!--4 box start from here  -->
				<div class="row ">
					<div class="col-lg-3 col-xs-6 ">
						<!-- small box -->
						<div class="small-box bg-aqua">
							<div class="inner" id="student_count">
								<!--(Number) Total Students -->
							</div>
							<div class="icon">
								<i class="ion ion-ios-people"></i>
							</div>
							<a class="small-box-footer" href="${cp}/student/view?dash=1" >More
								info <i class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-green">
							<div class="inner" id="program_count">
								<!--(Number) Total Programs -->
							</div>
							<div class="icon">
								<i class="fa fa-graduation-cap"></i>
							</div>
							<a class="small-box-footer" href="${cp}/program?dash=1">More
								info <i class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-yellow">
							<div class="inner" id="subject_count">
								<!--(Number) Total Subjects -->
							</div>
							<div class="icon">
								<i class="fa fa-book"></i>
							</div>
							<a class="small-box-footer" href="${cp}/subject/view?dash=1">More
								info <i class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-3 col-xs-6">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner" id="exam_count">
								<!--(Number) Active Exam -->
							</div>
							<div class="icon">
								<i class="fa fa-sitemap"></i>
							</div>
							<a class="small-box-footer" href="${cp}/viewexam?dash=1">More
								info <i class="fa fa-arrow-circle-right"></i>
							</a>
						</div>
					</div>
					<!-- ./col -->
				</div>

				
				<div class="row ">
					<div class=" col-lg-7  ">
						<div class="col-lg-12">
							<section class="panel notice-panel">
								<header class="panel-heading">
									<ul class="nav nav-pills small ">
										<li class="pull-left notice-board-name "><a><i
												class="fa fa-inbox"></i> Notice Board</a>
										<li>
										<li class="pull-right"><a data-toggle="tab" href="#staff">
												<i class="icon-envelope"></i> Staff
										</a></li>
										<li class="pull-right"><a data-toggle="tab"
											href="#student"> <i class="glyphicon glyphicon-user"></i>
												Student
										</a></li>
										<li class="active pull-right"><a data-toggle="tab"
											href="#general"> <i class="glyphicon glyphicon-home"></i>
												General
										</a></li>
									</ul>
								</header>

								<div class="panel-body">
									<div class="tab-content">

										<div id="general" class="tab-pane active">
											<div class="notice-div">

												<div class="notice-info">
													<ul>
														<li>
															<p class="bg-primary">
																<span class="title-message"> Summer Vacation </span> <span
																	class="small pull-right"> 2017-06-27 </span>
															</p>
														</li>
														<li><p>Summer Vacation starts from June to 2nd
																week of July.</p></li>
													</ul>
												</div>

											</div>
										</div>


										<div id="student" class="tab-pane">
											<div class="notice-div">

												<div class="notice-info">
													<ul>
														<li>
															<p class="bg-primary">
																<span class="title-message"> Next Week Summer </span> <span
																	class="small pull-right"> 2017-08-15 </span>
															</p>
														</li>
														<li><p>Summer Exam will be conducted on Next
																week. All The Best!!!</p></li>
													</ul>
												</div>
												<div class="notice-info">
													<ul>
														<li>
															<p class="bg-primary">
																<span class="title-message"> Student exam form
																	fillup </span> <span class="small pull-right">
																	2017-07-07 </span>
															</p>
														</li>
														<li><p>All Students come and fill their exam
																forms</p></li>
													</ul>
												</div>

											</div>
										</div>


										<div id="staff" class="tab-pane">
											<div class="notice-div">

												<div class="notice-info">
													<ul>
														<li>
															<p class="bg-primary">
																<span class="title-message"> Monthly Report </span> <span
																	class="small pull-right"> 2017-07-02 </span>
															</p>
														</li>
														<li><p>All Staff have to submit their report on
																month end.</p></li>
													</ul>
												</div>
												<div class="notice-info">
													<ul>
														<li>
															<p class="bg-primary">
																<span class="title-message"> Attendance Report </span> <span
																	class="small pull-right"> 2017-07-28 </span>
															</p>
														</li>
														<li><p>All Staff collect their class wise
																attendance report.</p></li>
													</ul>
												</div>
												<div class="notice-info">
													<ul>
														<li>
															<p class="bg-primary">
																<span class="title-message"> Attendance Report </span> <span
																	class="small pull-right"> 2017-07-28 </span>
															</p>
														</li>
														<li><p>All Staff collect their class wise
																attendance report.</p></li>
													</ul>
												</div>

											</div>
										</div>

									</div>
								</div>
							</section>
						</div>
					</div>


					<div class="col-lg-5 news ">
						<div class="row">
							<div class="col-lg-12">

								<div class="panel panel-default">
									<div class="panel-heading">
										<span class="glyphicon glyphicon-list-alt"
											style="font-size: 15px;" aria-hidden="true"></span>&nbsp;News
									</div>
									<div class="panel-body news_pannel_body">
										<div class="danger">
											<p>
												<strong>Danger!</strong> This is a place of news. Lates news
												will be loaded here.
											</p>
										</div>

										<div class="success">
											<p>
												<strong>Danger!</strong> This is a place of news. Lates news
												will be loaded here.
											</p>
										</div>
										<div class="warning">
											<p>
												<strong>Danger!</strong> This is a place of news. Lates news
												will be loaded here.
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			
			</div>
			<!--end row here  -->
		</div>
         <!-- Content Area Added by mausam end here from above div-->
    </div>
    
    <script>
    var res;
    	$(document).ready(function() {
    		
    		$.ajax({
    			url: window.context + "/ApiCount/Student",
    			method: "GET",
    			dataType: "json",
    			cache: true,
    			success: function(data){
    				console.log(data);
    				$("#student_count").html('<h3>'+data[0].student_count+'</h3><p> Students</p>');
    				$("#program_count").html('<h3>'+data[0].program_count+'</h3><p> Programs</p>');
    				$("#subject_count").html('<h3>'+data[0].subject_count+'</h3><p> Subjects</p>');
    				$("#exam_count").html('<h3>'+data[0].exam_count+'</h3><p>Active Exam</p>');
    			},
    			error: function(data){
    				console.log("error!");
    			}
    			
    		});
    	});
    </script>

	<jsp:include page="../jsp/admin/shared/footer.jsp" />