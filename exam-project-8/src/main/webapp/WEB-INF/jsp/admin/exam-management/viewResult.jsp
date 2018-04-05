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
		<li><a href="${cp}/home"><span
				class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Exam</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Student Exam</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right"
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

				</div>
				<div class="col-xs-7 " id="sembtn"></div>
			</div>
		</div>
	</div>


	<table id="view-student-exam"
		class="table table-hover table-striped table-responsive"
		cellspacing="0" width="100%">
		<thead>
			<tr class="info">

				<th>Id</th>
				<th>Name</th>
				<th>Exam Type</th>
				<th>Subject Name</th>
				<th>Semester</th>
				<th>Date</th>
				<th>Full / Pass Marks</th>
				<th>Attendance</th>
				<th>Obtain Marks</th>
				<th>Grade</th>
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
							<div class="form-group col-sm-4" style="margin-bottom: 0px;">
								<select required class="form-control" name="batch_id"
									id="p-batch-box">
									<option value="" disabled selected>Select Batch</option>
								</select>
							</div>
							<div class="form-group col-sm-4" style="margin-bottom: 0px;">
								<select required class="form-control" name="subject_id"
									id="p-subject-box">
									<option value="" disabled selected>Select Subject</option>
								</select>
							</div>
							<div class="form-group col-sm-4" style="margin-bottom: 0px;">
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



	<form id="studentedit" method="post" class="form-horizontal well"
		style="display: none;">
		<div class="form-group">
			<label class="col-md-3 control-label">Student-Exam Detail</label>
			<div class="col-md-9">
				<div class="col-md-4">
					Student_ID<input type="text" class="form-control" name="s_id"
						disabled="disabled" />
				</div>
				<div class="col-md-4">
					Exam_Id<input type="text" class="form-control" name="exam_id"
						disabled="disabled" />
				</div>
				<div class="col-md-4">
					Student_exam_id<input type="text" class="form-control"
						name="student_exam_id" disabled="disabled" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">Attendance</label>
			<div class="col-md-9">
				</label> <label> Present <input type="radio" value=1
					name="attendance"> <label> Absent <input
						type="radio" value=0 name="attendance">

				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">Obtained_marks</label>
			<div class="col-md-9">
				<input type="number" class="form-control" name="obtained_marks" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Grade</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="grade" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Available</label>
			<div class="col-md-9">
				<label> Yes <input type="radio" value=1 name="status">
				</label> <label> No <input type="radio" value=0 name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="updateStudentExam"
					class="btn btn-info btn-block">Update</button>
			</div>
		</div>
	</form>


	<script>
		//dont khow why this var use
		var semesterNo;

		var semesterNo1;
		var programeName;
		var programId;
		var batchyear;
		var examTypeName;
		var examtypeId;
		var subjectId;
		var subjectName;

		var idOfSelectedIteam = 0;

		var studentId = 0;
		var studentFullName;

		$(document).ready(function() {

			//For select 2 initialization
			var url1 = window.context + "/ApiStudent/SearchStudent";
			var method1 = "POST";
			var url2 = window.context + "/ApiStudent/GetStudent";
			var method2 = "GET";
			var placeholder = "Student";
			select2Function(url1, url2, method1, method2, placeholder, loadStudentInfo);

			$("#modal-box").click(function(event) {
				$("#sembtn").empty();
				load_faculty(event, "p-faculty-box");
			});
			$("#p-faculty-box").change(function(event) {
				load_program(event, "p-program-box");
			});
			$("#p-semester-box").change(function(event) {
				var getid = event.target.id;
				semesterNo1 = $('#' + getid).find(":selected").text();
				semesterNo = $('#' + getid).find(":selected").text();
				var url = window.context + "/ApiSubject/GetSubjectByParameters/" + programId + "/" + semesterNo;
				var method = "GET";
				var data = "";
				search_subject(data, "p-subject-box", url, method);
			});
			$("#p-program-box").change(function(event) {
				var getid = event.target.id;
				programeName = $('#' + getid).find(":selected").text();
				programId = $('#' + getid).find(":selected").val();
				load_batch_year(event, "p-batch-box");

			});
			$("#p-batch-box").change(function(event) {
				var getid = event.target.id;
				var id = $('#' + getid).find(":selected").val();
				batchyear = $('#' + getid).find(":selected").text();
				load_exam_type(event, "p-Exam-Type-box");
			});
			$("#p-subject-box").change(function(event) {
				var getid = event.target.id;
				subjectName = $('#' + getid).find(":selected").text();
				subjectId = $('#' + getid).find(":selected").val();

			});

			$("#p-Exam-Type-box").change(function(event) {
				var getid = event.target.id;
				examtypeId = $('#' + getid).find(":selected").val();
				examTypeName = $('#' + getid).find(":selected").text();
			});

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
						"async" : false
					},
					"columns" : [ {
						data : null,
						render : function(data, type, row) {
							console.log("res = " + JSON.stringify(data));
							//alert(data.students_exams_id);
							return data.students_exams_id;
						},
					}, {
						data : null,
						render : function(data, type, row) {
							//alert(data.name);

							if (data.name != null) {
								//this is from seach filter
								return data.name;
							} else {
								//this is from searchbox
								return studentFullName;
							}

						},
					}, {
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
					}, {
						data : null,
						render : function(data, type, row) {
							return '<button value='+data.students_exams_id+' class="btn btn-success editStud">Edit</button>';
						},
					} ]
				});

				// edit buttons on students row
				$("#view-student-exam").on('click','.editStud',function(event) {
					var table = $("#view-student-exam").DataTable();
					var data = table.row($(this).parents('tr')).data();
					console.log(data);

					// Populate the form fields
					$('#studentedit').find('[name="s_id"]').val(data['s_id']).end().find('[name="student_exam_id"]').val(data['students_exams_id']).end().find('[name="exam_id"]').val(data['exam_id']).end().find('[name="obtained_marks"]').val(data['obtained_marks']).end().find('[name="grade"]').val(data['grade']).end()

					$("input[name=attendance][value=" + data['attendance_status'] + "]").prop('checked', true);
					$("input[name=status][value=" + data['status'] + "]").prop('checked', true);

					bootbox.dialog({
						title : 'Edit the Student',
						message : $('#studentedit'),
						show : false
					// We will show it manually later
					}).on('shown.bs.modal', function() {
						$('#studentedit').show() // Show the modal form
					}).on('hide.bs.modal', function(e) {
						// Bootbox will remove the modal (including the body which contains the login form)
						// after hiding the modal
						// Therefor, we need to backup the form
						$('#studentedit').hide().appendTo('body');
					}).modal('show');

				});
			}

			$("#searchbtnClicked").click(function(event) {

				var url = window.context + "/ApiStudentsExams/GetResultSearch";
				var method = "POST";

				var data = {
					semesterNo : semesterNo1,
					programeName : programeName,
					programId : programId,
					batchyear : batchyear,
					examTypeName : examTypeName,
					examtypeId : examtypeId,
					subjectId : subjectId,
					subjectName : subjectName,
				};
				//alert(JSON.stringify(data));
				loadsExamInformation(url, method, data);

			});

		});

		// form validator for student edit form
		$("#studentedit").bootstrapValidator({
			// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphicon-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			attendance_status : {
				first_name : {
					validators : {
						notEmpty : {
							message : "Please Select Attendance Status"
						}
					}
				},
				obtained_marks : {
					validators : {
						notEmpty : {
							message : "Please Enter Obtained Marks"
						},
						Integer : {
							message : "Please Enter Valid Number"
						}
					}
				},
				status : {
					validators : {
						notEmpty : {
							message : "Please Select Status"
						}
					}
				}
			}
		})

		.on("success.form.bv", function(e) {

			// Prevent form submission
			e.preventDefault();

			var data = $('#studentedit').serializeArray();
			console.log(data);

			$('input[type=number]').each(function() {
				var t = $(this);
				if (t.val() != 0) {
					//alert(t.val());
				} else {
					t.val('0');
				}
			});

			$.ajax({
				url : window.context + "/ApiStudentsExams/UpdateStudentsExam",
				method : "PUT",
				dataType : 'json',
				contentType : 'application/json',
				data : formToJSON(),

				cache : true,
				success : function(data) {
					var message = "Student has been added Successfully";
					alert("Thanks for the submission!");
					$("#studentedit")[0].reset();
					$('#view-student-exam').DataTable().ajax.reload();
				},
				error : function() {
					alert("Error...!!!");
				}
			});

			function formToJSON() {
				var data = JSON.stringify({
					"students_exams_id" : $('#studentedit').find('[name="student_exam_id"]').val(),
					"student" : {
						"s_id" : $('#studentedit').find('[name="s_id"]').val(),
					},
					"exam" : {
						"exam_id" : $('#studentedit').find('[name="exam_id"]').val(),
					},
					"attendance_status" : $('#studentedit').find('[name="attendance"]:checked').val(),
					"obtained_marks" : $('#studentedit').find('[name="obtained_marks"]').val(),
					"grade" : $('#studentedit').find('[name="grade"]').val(),
					"status" : $('#studentedit').find('[name="status"]:checked').val(),

				});
				//alert(data);
				return data;
			}
		});

		function load_batch_year(e, target) {
			var getid = e.target.id;
			var id = $('#' + getid).find(":selected").val();
			programId = id;
			$.ajax({
				url : window.context + "/ApiStudentsProgram/GetStudentsProgramByProgramId/" + id,
				method : "GET",
				dataType : 'json',
				cache : true,
				success : function(data) {
					//console.log("simple data=" + data);
					//console.log("batch size=" + JSON.stringify(data));
					var lengt = data.length;
					var duplicateYear = [];
					var content = '';
					content += "<option selected='true' > Select Batch </option>"
					for (var i = 0; i < data.length; i++) {

						duplicateYear[i] = data[i].batch_year;
						var batch_yearDate = data[i].batch_year;
						var batch_yearId = data[i].student_program_id;
						//console.log("batch_yearDate name ="	+ batch_yearDate);

					}

					var uniqueYear = duplicateYear.filter(function(x, i, a) {
						return a.indexOf(x) == i;
					});

					for (var i = 0; i < uniqueYear.length; i++) {
						//batchyear = uniqueYear[i];
						content += '<option value='+uniqueYear[i] +'>' + uniqueYear[i] + '</option>';
					}

					$('#' + target).html(content);
				},
				error : function() {
					alert("Error...!!!");
				}
			});
		}

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
	</script>

</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />