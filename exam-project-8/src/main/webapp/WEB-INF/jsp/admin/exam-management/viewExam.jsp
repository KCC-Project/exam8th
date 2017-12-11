<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="../shared/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="admin-dashboard.jsp"><span
				class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					view</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right"
						data-toggle="modal" data-target="#searchStudentModal"
						id="modal-box">Filter</button></a>

			</div>
		</div>
	</div>


	<table id="view_exam"
		class="table table-hover table-striped table-responsive"
		cellspacing="0" width="100%">
		<thead>
			<tr class="info">

				<th>Id</th>
				<th>Subject Name</th>
				<th>Exam Type</th>
				<th>Programe/Semester</th>
				<th>Date</th>
				<th>F.M</th>
				<th>P.M</th>
				<th>Start Time</th>
				<th>End Time</th>
				<th>Status</th>
				<th>Option</th>
			</tr>
		</thead>
	</table>


	<!--=========================================================================================  -->
	<div class="modal fade" id="searchStudentModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Exam Search</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group " style="margin-bottom: 0px;">
									<select required class="form-control" id="p-faculty-box"
										name="faculty_id">
									</select>
								</div>
							</div>
							<div class="form-group col-sm-4" style="margin-bottom: 0px;">
								<select required class="form-control" id="p-program-box"
									name="program_id">
									<option value="" disabled selected>Select Programme</option>
								</select>
							</div>
							<div class="form-group col-sm-4" style="margin-bottom: 0px;">
								<select required class="form-control" name="semester"
									id="p-semester-box">
									<option value="" disabled selected>select Semester</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
								</select>
							</div>




						</div>
					</div>
					<br>

					<div class="row">
						<div class="col-sm-12">
							<div class="form-group col-sm-6" style="margin-bottom: 0px;">
								<select required class="form-control" name="subject_id"
									id="p-subject-box">
									<option value="" disabled selected>Select Subject</option>
								</select>
							</div>
							<div class="form-group col-sm-6" style="margin-bottom: 0px;">
								<select required class="form-control" name="subject_id"
									id="p-Exam-Type-box">
									<option value="" disabled selected>Select Exam Type</option>
								</select>
							</div>


						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success"
							id="searchbtnClicked" data-dismiss="modal">Search</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
<script>
	var subjectId;
	var subjectname;
	var examTypeId;
	var examTypeName;
	var programeName;
	var programId;
	var semesterNo;
	$(document).ready(function() {

		$("#modal-box").click(function(event) {
			load_faculty(event, "p-faculty-box");

		});
		$("#p-faculty-box").change(function(event) {
			load_program(event, "p-program-box");
		});
		$("#p-program-box").change(function(event) {
			var getid = event.target.id;
			programeName = $('#' + getid).find(":selected").text();
			programId = $('#' + getid).find(":selected").val();
			//load_subject(event, "p-subject-box");
		});

		$("#p-semester-box").change(function(event) {
			var getid = event.target.id;
			semesterNo = $('#' + getid).find(":selected").text();
			load_subject1(event, "p-subject-box");
		});

		$("#p-subject-box").change(function(event) {
			load_exam_type(event, "p-Exam-Type-box");
		});

		$("#p-Exam-Type-box").change(function(event) {

			var getid = event.target.id;
			examTypeId = $('#' + getid).find(":selected").val();
			examTypeName = $('#' + getid).find(":selected").text();

		});

		$("#searchbtnClicked").click(function(event) {
			alert("examTypeId = " + examTypeId + " " + examTypeName + " " + subjectId);
			var url = window.context + "/ApiExam/GetExamByExamTypeAndSubjectId";
			var method = "POST";
			//var data = {examTypeId: 'examTypeId', subjectId: 'subjectId'};
			var data = {
				"examTypeId" : examTypeId,
				"subjectId" : subjectId
			};

			loadExamInformation(url, method, data);
		});

	});

	function load_exam_type(event, target) {
		var getid = event.target.id;
		subjectId = $('#' + getid).find(":selected").val();
		subjectname = $('#' + getid).find(":selected").text();
		semesterNo = $('#' + getid).find(":selected").attr('data-sem');
		$.ajax({
			url : window.context + "/ApiExam_type/GetAllExam_type",
			method : "GET",
			dataType : 'json',
			cache : true,
			success : function(data) {
				var json = data;
				//console.log("json size=" + data.length);
				var content = '';
				content += "<option selected='true' > Select Exam Type </option>"
				for (var i = 0; i < data.length; i++) {
					var examTypeName = data[i].type_name;
					var examTypeNameId = data[i].exam_type_id;
					//console.log("faculty name =" + facultyName);

					content += '<option value='+examTypeNameId+'>' + examTypeName + '</option>';
				}
				$('#' + target).html(content);
			},
			error : function() {
				alert("Error...!!!");
			}
		});
	}



	function load_subject1(e, target) {
		$.ajax({
			url : window.context + "/ApiSubject/GetSubjectByParameters",
			method : "POST",
			cache : true,
			data : {
				programId : programId,
				semester_no : semesterNo

			},
			success : function(data) {
				var content = '';
				content += "<option selected='true' > Select Subject </option>"
				for (var i = 0; i < data.length; i++) {
					content += '<option value='+data[i].subject_id +' data-sem='+data[i].semester_no+'>' + data[i].subject_name + '</option>';
					//alert("data[i].semester_no="+data[i].semester_no);
				}

				$('#' + target).html(content);
			
			},
			error : function() {
				alert("Error...!!!");
			}
		});
	}
	function loadExamInformation(url, method, data) {
		//alert(url+"  "+method+  +data);
		//alert("data=" + data);
		//alert(subjectname);
		//alert(examTypeName);
		//alert(programeName);
		$('#view_exam').DataTable({
			destroy : true,
			paging : true,
			searching : true,
			"processing" : true,
			"serverSide" : false,
			 "order": [[ 0, "desc" ]],
			"ajax" : {
				"url" : url,
				"type" : method,
				"data" : data,
				"dataSrc" : "",
				"dataType" : "json",
				"async" : false
			},
			"columns" : [ {
				"data" : "exam_id"
			}, {
				data : null,
				render : function(data, type, row) {
					return subjectname;
				},
			}, {
				data : null,
				render : function(data, type, row) {
					return examTypeName;
				},
			}, {
				data : null,
				render : function(data, type, row) {
					console.log("view exam = " + JSON.stringify(data));

					return programeName + ' / ' + semesterNo;
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
			}, {
				data : null,
				render : function(data, type, row) {
					console.log(JSON.stringify(data));
					var statusStatus = "";

					if (data.status == 1) {
						statusStatus = "Coming/Running";
					} else if (data.status == 0) {
						statusStatus = "Finished";
					}

					return '' + statusStatus + '';
				},
			}, {
				data : null,
				render : function(data, type, row) {
					return '<button class="btn btn-danger deletebtn">Delete</button>';
				},
			} ]
		});

		// delete buttons on exam row
		$('#view_exam tbody').on('click', '.deletebtn', function() {
			var table = $("#view_exam").DataTable();
			var data = table.row($(this).parents('tr')).data();
			console.log(data);
			alert(data['exam_id'] + "' id is: " + data['exam_id']);
		});
	}
</script>