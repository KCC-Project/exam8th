<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="../shared/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${ empty sessionScope.adminUserName }">
	<c:redirect url="/index " />
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

	<form id="exam-edit-form" method="post" class="form-horizontal well"
		style="display: none;">
		<div class="form-group">
			<label class="col-md-3 control-label">Exam Id</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="exam_id"
					disabled="disabled" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Subject </label>
			<div class="col-md-9">
				<select required class="form-control" id="subject_box1"
					name="subject_box1">
					<option selected>Select Program</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Exam Type </label>
			<div class="col-md-9">
				<select required class="form-control" id="exam_type_box1"
					name="exam_type_id">
					<option selected>Select Exam Type</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Exam Date</label>
			<div class="col-md-9">
				<input type="date" class="form-control" name="exam_date" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Time</label>
			<div class="col-md-9">
				<div class="col-md-6">
					From:<input type="time" class="form-control" name="time_from" />
				</div>
				<div class="col-md-6">
					To:<input type="time" class="form-control" name="time_to" />
				</div>

			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Marks</label>
			<div class="col-md-9">
				<div class="col-md-6">
					Full:<input type="number" class="form-control" name="full_marks" />
				</div>
				<div class="col-md-6">
					Pass:<input type="number" class="form-control" name="pass_marks" />
				</div>

			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">Finished/Not</label>
			<div class="col-md-9">
				<label> Yes <input type="radio" value=0 name="status"
					checked>
				</label> <label> No <input type="radio" value=1 name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="updateexam" class="btn btn-info btn-block">Update</button>
			</div>
		</div>
	</form>


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
		
		// check if the url comes from dashboard
    	var url_string = window.location.href;
    	var url = new URL(url_string);
    	var dash = url.searchParams.get("dash");
    	
    	if(dash==1){
    		//console.log(dash);
    		var url = window.context + "/ApiExam/GetRunningExam";
        	var method = "GET";
        	var data = "";
        	loadRunningExam(url, method, data);
    	}
    	function loadRunningExam(url, method, data) {
    		
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
    					return data.program_name + ' ' + '/ ' +data.semester_no;
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
    			},{
    				data : null,
    				render : function(data, type, row) {
    					return 'Active';
    				},
    			},{
    				data : null,
    				render : function(data, type, row) {
    					return '-----';
    				},
    			}]
    		});

    	}
    	// -------------------------------

		load_all_examType("exam_type_box1");

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
			var url = window.context + "/ApiSubject/GetSubjectByProgram/" + programId;
			var method = "GET";
			var data = "";
			search_subject(data, "subject_box1", url, method);
			//load_subject(event, "p-subject-box");
		});

		$("#p-semester-box").change(function(event) {
			var getid = event.target.id;
			semesterNo = $('#' + getid).find(":selected").text();
			var url = window.context + "/ApiSubject/GetSubjectByParameters/" + programId + "/" + semesterNo;
			var method = "GET";
			var data = "";
			search_subject(data, "p-subject-box", url, method);

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
			//alert("mm examTypeId = " + examTypeId + " " + examTypeName + " " + subjectId);
			var url = window.context + "/ApiExam/GetExamByExamTypeAndSubjectId/" + examTypeId + "/" + subjectId;
			var method = "GET";
			var data = "";

			loadExamInformation(url, method, data);
		});

		$("#exam-edit-form").bootstrapValidator({
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphicon-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {
				subject_box1 : {
					validators : {
						notEmpty : {
							message : "Please Select Subject"
						}
					}
				},
				exam_type_id : {
					validators : {
						notEmpty : {
							message : "Please Select Exam Type"
						}
					}
				},
				s_semester_no : {
					validators : {
						notEmpty : {
							message : "Please Select Semester"
						}
					}
				},

				exam_date : {
					validators : {
						notEmpty : {
							message : "Please Select Exam Date"
						}
					}
				},
				status : {
					validators : {
						notEmpty : {
							message : "Please Select Avilability"
						}
					}
				}
			}

		})
		// on clicking update for faculty edit form
		.on("success.form.bv", function(e) {

			// Prevent form submission
			e.preventDefault();

			$.ajax({
				url : window.context + "/ApiExam/UpdateExam",
				method : "PUT",
				dataType : 'json',
				contentType : 'application/json',
				data : formToJSON(),
				cache : true,
				async : false,
				success : function(data) {

					alert("Thanks for the submission!");
					//$("#exam-edit-form")[0].reset();
					$('#view_exam').DataTable().ajax.reload();

				},
				error : function() {
					alert("Error...!!!");
				}
			});

			function formToJSON() {
				var data = JSON.stringify({
					"exam_id" : $('#exam-edit-form').find('[name="exam_id"]').val(),
					"examtype" : {
						"exam_type_id" : $('#exam-edit-form').find('[name="exam_type_id"]').val(),
					},
					"subject" : {
						"subject_id" : $('#exam-edit-form').find('[name="subject_box1"]').val(),
					},
					"exam_date" : $('#exam-edit-form').find('[name="exam_date"]').val(),
					"full_marks" : $('#exam-edit-form').find('[name="full_marks"]').val(),
					"pass_marks" : $('#exam-edit-form').find('[name="pass_marks"]').val(),
					"time_from" : $('#exam-edit-form').find('[name="time_from"]').val(),
					"time_to" : $('#exam-edit-form').find('[name="time_to"]').val(),
					"status" : $('#exam-edit-form').find('[name="status"]:checked').val(),
				});
				//alert(data);
				return data;
			}

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
			"order" : [ [ 0, "desc" ] ],
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

					return programeName + ' / ' + data.subject.semester_no;
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
					return '<button class="btn btn-success deletebtn">Select</button>';
				},
			} ]
		});

		// edit buttons on row
		$("#view_exam").on('click','.deletebtn',function(event) {
			var table = $("#view_exam").DataTable();
			var data = table.row($(this).parents('tr')).data();
			console.log(data);

			// Populate the form fields
			$('#exam-edit-form').find('[name="exam_id"]').val(data['exam_id']).end().find('[name="exam_date"]').val(data['exam_date']).end().find('[name="full_marks"]').val(data['full_marks']).end().find('[name="pass_marks"]').val(data['pass_marks']).end().find('[name="time_from"]').val(data['time_from']).end().find('[name="time_to"]').val(data['time_to']).end()
			$("input[name=status][value=" + data['status'] + "]").prop('checked', true);

			$('#exam_type_box1 option').each(function() {
				if ($(this).val() == data.examtype.exam_type_id) {
					$(this).prop("selected", true);
				}
			});
			$('#subject_box1 option').each(function() {
				if ($(this).val() == data.subject.subject_id) {
					$(this).prop("selected", true);
				}
			});
			bootbox.dialog({
				title : 'Edit the Exam',
				message : $('#exam-edit-form'),
				show : false
			// We will show it manually later
			}).on('shown.bs.modal', function() {
				$('#exam-edit-form').show() // Show the modal form
			}).on('hide.bs.modal', function(e) {
				// Bootbox will remove the modal (including the body which contains the login form)
				// after hiding the modal
				// Therefor, we need to backup the form
				$('#exam-edit-form').hide().appendTo('body');
			}).modal('show');

		});
	}
</script>