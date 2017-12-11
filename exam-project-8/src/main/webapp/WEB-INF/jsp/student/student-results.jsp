<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>


<jsp:include page="../student/student-header.jsp" />

<!-- Content Area -->
<div id="page-content-wrapper">

	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span class="glyphicon glyphicon-home"> Home </span></a></li>
		<li><a><span class="glyphicon glyphicon-user black"> View Results </span></a></li>
	</ol>

	<form  class="form-horizontal well">

		<div class="form-group">
			<label class="col-md-3 control-label">Results</label>
			<div class="col-md-9">
				<div class="col-md-4">
					Semester no.:<select class="form-control" id="s-semester-no" name="s_semester_no">
						<option value="" selected disabled>Select Semester</option>
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
				<div class="col-md-4">
					Exam_type:<select required class="form-control" id="exam-type-box" name="exam_type_id">
					</select>
				</div>
				<div class="col-md-4">
					<button type="button" id="searchResult" value="${studentID}" class="btn btn-info btn-block">Search Result</button>
					<%-- <button type="button" id="idstud"  class="btn btn-info btn-block hidden" >${studentID}</button> --%>
				</div>

			</div>
		</div>
	</form>


	<table id="view-student-exam" class="table table-hover table-striped table-responsive" cellspacing="0"
		width="100%">
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

</div>
<script>
var x = document.getElementById("searchResult").value;
var semester_no;
var exam_type_id;
var data;
var url;
var method;

    $(document).ready(function () {
  
    
        $.when($.ajax(load_all_examType("exam-type-box"))).done(function () {
            $("#exam-type-box").append("<option value='' selected disabled>Select Exam Type</option>");
        });
        
        $("#s-semester-no").change(function(event) {
        	var getid = event.target.id;
		
        	semester_no = $('#' + getid).find(":selected").val();
		});
        $("#exam-type-box").change(function(event) {
        	var getid = event.target.id;
		
        	exam_type_id = $('#' + getid).find(":selected").val();

             data = {
                "semester_no" : semester_no,
                "exam_type_id" : exam_type_id,
                "studentId" :x
            };
             url = window.context + "/ApiStudentsExams/loadResultExams";
           method = "POST";
            
		});
        $("#searchResult").click(function(event) {
     
            loadExamInformation(url, method, data);
		});

    });
/* 
    $("#search-result").bootstrapValidator({
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
            semester_no : {
                validators : {
                    notEmpty : {
                        message : "Please Select Semester"
                    }
                }
            },
            exam_type_id : {
                validators : {
                    notEmpty : {
                        message : "Please Select Exam Type"
                    }
                }
            }
        }

    })
    // on add new faculty submit form
    .on("success.form.bv", function (e) {

        // Prevent form submission
        e.preventDefault();

         semester_no = $('#sub-form').find('[name="s_semester_no"]').val();
         exam_type_id = $('#sub-form').find('[name="exam_type_id"]').val();
    


    }); */

    // Datatable for viewing student exam
    function loadExamInformation(url, method, data) {
alert(semester_no+ "  "+exam_type_id);
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
                "data" : "students_exams_id"
            }, {
                data : null,
                render : function (data, type, row) {
                	//alert(data);
                	console.log(data);
                    var full_name = "";
                    full_name += data.first_name + " ";
                    if (data.middle_name == undefined) {

                    } else {
                        full_name += data.middle_name + " ";
                    }
                    full_name += data.last_name;
                    // Combine the two data
                    return '' + full_name + '';
                },
            }, {
                "data" : "type_name"
            }, {
                "data" : "subjectname"
            }, {
                "data" : "semester_no"
            }, {
                "data" : "date"
            }, {
                data : null,
                render : function (data, type, row) {
                    return 'Full: ' + data.fullmarks + '\ Pass: ' + data.passmarks + '';
                },
            }, {
                data : null,
                render : function (data, type, row) {
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
                render : function (data, type, row) {
                    if (data.grade == "A") {
                        return '<p class="btn-success">' + data.grade + '</p>';
                    } else if (data.grade == "F") {
                        return '<p class="btn-danger">' + data.grade + '</p>';
                    } else {
                        return '<p class="btn-default">' + data.grade + '</p>';
                    }
                },
            }, {
                data : null,
                render : function (data, type, row) {
                    return '<button class="btn btn-danger editStuExam">Edit</button>';
                },
            } ]
        });

    }
</script>