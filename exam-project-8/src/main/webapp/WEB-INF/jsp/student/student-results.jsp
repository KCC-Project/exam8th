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
		<li><a><span class="glyphicon glyphicon-user black"> View Results </span></a></li>
	</ol>

	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
			
			<!-- This is for filter and search area -->
			
			<!-- 	<a><button type="button" class="btn btn-info pull-right"
						data-toggle="modal" data-target="#searchStudentModal"
						id="modal-box">Filter</button></a>
				<div class="col-xs-3"
					style="margin-left: -34px; /* border: 2px solid black; */ height: 37px;">
					<div class="form-group">
						<div class="input-group">
							<select class="form-control" id="sel1"></select> <span
								class="input-group-addon"> <i class="fa fa-search"></i>
							</span>
						</div>

					</div>

				</div> -->
				
				
				<div class="col-xs-12 text-center" id="sembtn"></div>
			</div>
		</div>
	</div>
	
	
	
	<table id="view-student-exam"
		class="table table-hover table-striped table-responsive"
		cellspacing="0" width="100%">
		<thead>
			<tr class="info">

				<th>Id</th>
				<th>Exam Type</th>
				<th>Subject Name</th>
				<th>Semester</th>
				<th>Date</th>
				<th>Full / Pass Marks</th>
				<th>Attendance</th>
				<th>Obtain Marks</th>
				<th>Grade</th>
			</tr>
		</thead>
	</table>
	
	
	<script>
		

		$(document).ready(function() {
			
			var url = window.context + "/ApiStudent/GetStudent/"+${ studentID };
			var method = "GET";
			var data="";
		
			loadStudentInfo(url,method,data);
			
			function loadStudentInfo(url, method, data) {
				var count;
				var current_sem;
				var student_id;

				$.ajax({
					url : url,
					method : method,
					dataType : 'json',
					contentType : 'application/json',
					data : data,

					cache : true,
					success : function(data) {
						$("#sembtn").empty();
						var content = '';
						for (var i = 1; i <= data[0].current_semester; i++) {

							content += '<input style="margin-right:10px;" id='+i+'  ids='+data[0].s_id+' type="button"  class="btn btn-default btnSelected" values='
																							+ data[0].s_id
																							+ ' value='
																							+i
																							+ '>';
							count = i;

						}
						current_sem = data[0].current_semester;
						student_id = data[0].s_id;

						studentFullName = data[0].first_name + " " + data[0].middle_name + " " + data[0].last_name;

						$("#sembtn").append(content);
						var color = current_sem;
						//this if is to show defult current semester result
						if (count == current_sem) {
							$('#' + current_sem + '').addClass('btn btn-primary');
							var url = window.context + "/ApiStudentsExams/GetStudentExamByStudentIdAndSemesterNo/" + student_id + "/" + current_sem;
							var method = "GET";
							var data = "";
							loadsExamInformation(url, method, data);
						}

						//this is run when we click specific semester and change color of button also
						$("input").click(function(event) {
							studentId = $(this).attr('ids');
							var idForSelectionColor = event.target.id;
							$('#' + idForSelectionColor + '').addClass('btn btn-primary');
							var temp = color;
							if (temp != 0) {
								$('#' + temp + '').removeClass('btn btn-primary');
								$('#' + temp + '').addClass('btn btn-default');
							}
							color = idForSelectionColor;
							//alert("idForSelectionColor = "+idForSelectionColor);
							//alert(" temp= "+temp);
							var semesterNo = event.target.value;
							var url = window.context + "/ApiStudentsExams/GetStudentExamByStudentIdAndSemesterNo/" + studentId + "/" + semesterNo;
							var method = "GET";
							var data = "";
							loadsExamInformation(url, method, data);

							//alert("studentId = "+ studentId +"  "+ " semesterNo = "+semesterNo);
						});
					},
					error : function() {
						alert("Error...!!!");
					}
				});
			}
			
			// Datatable for viewing student exam
			function loadsExamInformation(url, method, data) {

				$('#view-student-exam').DataTable({
					destroy : true,
					paging : true,
					searching : true,
					"processing" : true,
					"serverSide" : false,
					"order" : [ [ 0, "desc" ] ],
					"ajax" : {
						"url" : url,
						"type" : method,
						"data" : data,
						"dataSrc" : "",
						"dataType" : "json",
						"async" : false,
						dataSrc: function(json) {
				            var rows = [];
				            for (var i=0;i<json.length;i++) {
				                if (json[i].grade) rows.push(json[i]); 
				            }
				            return rows;
				        }
					},
					"columns" : [ {
						data : null,
						render : function(data, type, row) {
							console.log("res = " + JSON.stringify(data));
							return data.students_exams_id;
						},
					},  {
						data : null,
						render : function(data, type, row) {
							//alert("examtype name = "+data.exam.examtype.type_name)
							return data.type_name;
						},
					}, {
						data : null,
						render : function(data, type, row) {
							//alert("examtype name = "+data.exam.examtype.type_name)
							return data.subject_name;
						},
					}, {
						data : null,
						render : function(data, type, row) {

							return data.semester_no;
						},
					}, {
						data : null,
						render : function(data, type, row) {
							return data.exam_date;
						},
					}, {
						data : null,
						render : function(data, type, row) {
							return 'Full: ' + data.full_marks + '\n Pass: ' + data.pass_marks + '';
						},
					}, {
						data : null,
						render : function(data, type, row) {
							var statusStatus = "";

							if (data.attendance_status == 0) {
								statusStatus = "Absent";
							} else if (data.attendance_status == 1) {
								statusStatus = "Present";
							}

							return '' + statusStatus + '';
						},
					}, {
						"data" : "obtained_marks"
					}, {
						data : null,
						render : function(data, type, row) {
							if (data.grade == "A") {
								return '  <button type="button" class="btn btn-primary">' + data.grade + '</button>';

							} else if (data.grade == "B") {
								return '  <button type="button" class="btn btn-success">' + data.grade + '</button>';
							} else if (data.grade == "C") {
								return '  <button type="button" class="btn btn-info">' + data.grade + '</button>';
							} else if (data.grade == "D") {
								return '  <button type="button" class="btn btn-warning">' + data.grade + '</button>';
							} else if (data.grade == "F") {
								return '  <button type="button" class="btn btn-danger">' + data.grade + '</button>';
							} else {
								return '<input type="button" class="btn-defult" value='+ data.grade+'>';
							}
						},
					},]
				});

				
			}


		});

		
		
	</script>

</div>
