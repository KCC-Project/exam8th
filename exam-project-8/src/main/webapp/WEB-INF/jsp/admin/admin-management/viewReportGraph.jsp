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
		<li><a href="${cp}/home"><span
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
				<div class="col-lg-6 ">
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


					<div class="col-lg-6 news ">
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

<script>
	
$(document).ready(function () {
	var scienceTotal;
	var managementTotal;
	var artsTotal;
	var lawTotal;
	
	//fail total
	var science_fail;
	var management_fail;
	var arts_fail;
	var law_fail;
	
$.ajax({
	url : window.context + "/ApiViewController/FailRatioCountView",
	method : "",
	dataType : 'json',
	contentType : 'application/json',
	data : "",
	cache : true,
	async:true,
	success : function(data) {
		
		console.log(JSON.stringify(data));
		
		scienceTotal=data[0].science_totalstudent
		managementTotal=data[0].management_totalstudent
		artsTotal=data[0].arts_totalstudent
		lawTotal=data[0].law_totalstudent
		
		science_fail = data[0].science_fail_totalstudent;
		management_fail = data[0].management_fail_totalstudent;
		arts_fail = data[0].arts_fail_totalstudent;
		law_fail = data[0].law_fail_totalstudent;
		
		maxFailSubject();
	
	},
error : function() {
alert("Error...!!!");
}
});





// Load google charts
google.charts.load('current', {
	'packages' : [ 'corechart' ]
});
google.charts.setOnLoadCallback(drawChartTotal);
google.charts.setOnLoadCallback(drawChartFail);

// Draw the chart and set the chart values
function drawChartTotal() {
	var data = google.visualization.arrayToDataTable([
			[ 'Subject', 'Total Students Count' ], [ 'Science and Tech', scienceTotal ], [ 'Management', managementTotal ],
			[ 'Arts', artsTotal ], [ 'Law', lawTotal ] ]);

	// Optional; add a title and set the width and height of the chart
	
	
	var options = {
		'width' : 480,
		'height' : 300
		
	};

	// Display the chart inside the <div> element with id="piechart"
	var chart = new google.visualization.PieChart(document
			.getElementById('piechart1'));
	
	chart.draw(data, options);
}



function drawChartFail() {
	var data = google.visualization.arrayToDataTable([
			[ 'Subject', 'Pass:fail' ], [ 'Science and Tech', (science_fail/scienceTotal)*100 ], [ 'Management', (management_fail/managementTotal)*100 ],
			[ 'Arts', (arts_fail/artsTotal)*100 ], [ 'Law', (law_fail/lawTotal)*100 ] ]);

	// Optional; add a title and set the width and height of the chart
	
	
	var options = {
		'width' : 480,
		'height' : 300
		
	};

	// Display the chart inside the <div> element with id="piechart"
	var chart = new google.visualization.PieChart(document
			.getElementById('piechart2'));
	
	chart.draw(data, options);
}

function drawChartMaxFailSubject() {
	var data = google.visualization.arrayToDataTable([
		[ 'Subject', 'Max fail' ], [ 'Science and Tech', (science_fail/scienceTotal)*100 ], [ 'Management', (management_fail/managementTotal)*100 ],
		[ 'Arts', (arts_fail/artsTotal)*100 ], [ 'Law', (law_fail/lawTotal)*100 ] ]);

	alert("data = "+ data);
	// Optional; add a title and set the width and height of the chart
	
	
	var options = {
		'width' : 400,
		'height' : 300
		
	};

	// Display the chart inside the <div> element with id="piechart"
	var chart = new google.visualization.PieChart(document
			.getElementById('piechart3'));
	
	chart.draw(data, options);
}

function maxFailSubject(){
	var url = window.context + "/ApiViewController/MaxFailFubjectView";
	var method ="GET";
	var data ="";
	
	$('#max_fail_subject').DataTable({
		destroy : true,
		paging : true,
		searching : true,
		"processing" : true,
		"serverSide" : false,
		"order" : [ [ 0, "asc" ] ],
		"ajax" : {
			"url" : url,
			"type" : method,
			"data" : data,
			"dataSrc" : "",
			"dataType" : "json",
			"async" : true
		},
		"columns" : [ {
			data : null,
			render : function(data, type, row,i) {
				return (i.row)+1;
			},
		}, {
			data : null,
			render : function(data, type, row) {

				return data.subject_name;
			},
		}, {
			data : null,
			render : function(data, type, row) {
				return data.total_failed;
			},
		}, {
			data : null,
			render : function(data, type, row) {
				return data.program_name;
			},
		} ]
	});
	
};
});

</script>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
