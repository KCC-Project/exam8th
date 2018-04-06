<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ empty sessionScope.studentUserName }">
	<c:redirect url="/index " />
</c:if>

<jsp:include page="../student/student-header.jsp" />

<!-- Content Area -->
<div id="page-content-wrapper">

	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span class="glyphicon glyphicon-home"> Home </span></a></li>
		<li><a><span class="glyphicon glyphicon-user black"> Upcoming Exams </span></a></li>
	</ol>
		
<div class="table table-responsive">
	<table class="table table-hover table-striped" id="view_exam" width="100%">
		<thead>
			<tr class="info">

				<th>#</th>
				<th>Subject Name</th>
				<th>Exam Type</th>
				<th>Semester</th>
				<th>Date</th>
				<th>F.M</th>
				<th>P.M</th>
				<th>Start Time</th>
				<th>End Time</th>
			
			</tr>
		</thead>
	</table>
</div>


</div>

<script>
	
	$(document).ready(function() {
		
		var url = window.context + "/ApiExam/getRoutineForParentsMode/"+${studentID};
		var method = "GET";
		var data="";
		
		loadExamInformation(url,method,data);
		
	function loadExamInformation(url, method, data) {
	
		$('#view_exam').DataTable({
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
				"async" : false
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
					return data.type_name;
				},
			}, {
				data : null,
				render : function(data, type, row) {
					return data.semester_no;
				},
			}, {
				"data" : "exam_date"
			}, {
				"data" : "full_marks"
			}, {
				"data" : "pass_marks"
			}, {
				"data" : "time_from"
			}, {
				"data" : "time_to"
			}]
		});

	}
	
	});
	
</script>
