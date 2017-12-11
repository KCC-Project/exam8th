<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="../shared/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" scope="application">${pageContext.request.contextPath}</c:set>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
          
<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="${cp}/home""><span class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black"> Education</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black"> Faculty</span></a></li>

	</ol>

	<!-- 
	<form id="add-exam-by-semester-form" method="post" class="form-horizontal well">
		<h2>Add Exam for Particular Program-> Semester</h2>
		<div class="form-group">
			<label class="col-md-3 control-label">Program</label>
			<div class="col-md-9">
				<div class="col-md-6">
					<select required class="form-control" id="all-program-box" name="program_id">
					</select>
				</div>
				<div class="col-md-6">
					Semester no.:<select class="form-control" id="s-semester-no" name="s_semester_no">
						<option value="" selected>Select Semester</option>
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
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="addExamBySemester" class="btn btn-info btn-block">Add Exam By Semester</button>
			</div>
		</div>
	</form>
	
	 -->

	<form id="add-exam-form" method="post" class="form-horizontal well">

		<div class="form-group">
			<label class="col-md-3 control-label">Exam For</label>
			<div class="col-md-9">
				<div class="col-md-6">
					Program<select required class="form-control" id="all-program-box" name="program_id">
					</select>
				</div>
				<div class="col-md-6">
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
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Exam Info</label>
			<div class="col-md-9">
				<div class="col-md-6">
					Subject_Name<select required class="form-control" id="subject-box" name="subject_id">
					</select>
				</div>
				<div class="col-md-6">
					Exam_type:<select required class="form-control" id="exam-type-box" name="exam_type_id">
					</select>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Date</label>
			<div class="col-md-9">
				<input type="date" class="form-control" name="exam_date" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Marks</label>
			<div class="col-md-9">
				<div class="col-md-6">
					Full Marks<input type="number" class="form-control" name="full_marks" />
				</div>
				<div class="col-md-6">
					Pass Marks<input type="number" class="form-control" name="pass_marks" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Time</label>
			<div class="col-md-9">
				<div class="col-md-6">
					Time From<input type="time" class="form-control" name="time_from" />
				</div>
				<div class="col-md-6">
					Time To<input type="time" class="form-control" name="time_to" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Available</label>
			<div class="col-md-9">
				<label> Yes <input type="radio" value="0" name="status" checked required>
				</label> <label> No <input type="radio" value="1" name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="addExam" class="btn btn-info btn-block">Save</button>
			</div>
		</div>
	</form>



	<script>
	var a_program_id;
	var semester_no;
	var examTypeId;
        $(document).ready(function () {

            $.when($.ajax(load_all_program("all-program-box"))).done(function () {
                $("#all-program-box").append("<option value='' selected disabled>Select Program</option>");
            });

            $.when($.ajax(load_all_examType("exam-type-box"))).done(function () {
                $("#exam-type-box").append("<option value='' selected disabled>Select Exam Type</option>");
            });

            $("#exam-type-box").change(function(event) {
            	var getid = event.target.id;
    		
            	examTypeId = $('#' + getid).find(":selected").val();
    		});
            $("#s-semester-no").change(function (event) {
                 a_program_id = $('#add-exam-form').find('[name="program_id"]').val();
                 semester_no = $('#add-exam-form').find('[name="s_semester_no"]').val();

                if (a_program_id != "") {
                    var data = {
                        "programId" : a_program_id,
                        "semester_no" : semester_no
                    };
                    search_subject(data, "subject-box");
                }

            });

        });
        // ------------------------------- document.ready end --------------------------------

        // form validator for add-faculty form
        $("#add-exam-by-semester-form").bootstrapValidator({
            feedbackIcons : {
                valid : "glyphicon glyphicon-ok",
                invalid : "glyphicon glyphicon-remove",
                validating : "glyphicon glyphicon-refresh"
            },
            fields : {
                program_id : {
                    validators : {
                        notEmpty : {
                            message : "Please Select Program"
                        }
                    }
                },
                semester_no : {
                    validators : {
                        notEmpty : {
                            message : "Please Select Semester"
                        }
                    }
                },
                exam_date : {
                    validators : {
                        notEmpty : {
                            message : "Please Enter Exam Date"
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
        // on add new faculty submit form
        .on("success.form.bv", function (e) {

            // Prevent form submission
            e.preventDefault();

            var data = $('#add-exam-by-semester-form').serializeArray();
            console.log(data);
            $('input[type=number]').each(function () {
                var t = $(this);
                if (t.val() != 0) {
                    //alert(t.val());
                } else {
                    t.val('0');
                }
            });
            $.ajax({
                url : window.context + "/ApiExam/SaveExam",
                method : "POST",
                dataType : 'json',
                contentType : 'application/json',
                data : formToJSON(),
                cache : true,
                success : function (data) {
                    alert("Thanks for the submission!");
                    $("#add-exam-by-semester-form")[0].reset();

                },
                error : function () {
                    alert("Error...!!!");
                }
            });
            function formToJSON() {
                var data = JSON.stringify({
                    "faculty_id" : $('#add-exam-by-semester-form').find('[name="faculty_id"]').val(),
                    "faculty_name" : $('#add-exam-by-semester-form').find('[name="faculty_name"]').val(),
                    "status" : $('#add-exam-by-semester-form').find('[name="status"]:checked').val(),

                });
                alert(data);
                return data;
            }
        });

        // form validator for edit-faculty form
        $("#add-exam-form").bootstrapValidator({
            feedbackIcons : {
                valid : "glyphicon glyphicon-ok",
                invalid : "glyphicon glyphicon-remove",
                validating : "glyphicon glyphicon-refresh"
            },
            fields : {
                program_id : {
                    validators : {
                        notEmpty : {
                            message : "Please Select Program"
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
                subject_id : {
                    validators : {
                        notEmpty : {
                            message : "Please Select Subject"
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
        .on("success.form.bv", function (e) {

            // Prevent form submission
            e.preventDefault();

            var data = $('#add-exam-form').serializeArray();
            console.log(data);

            $.ajax({
                url : window.context + "/ApiExam/SaveExam",
                method : "POST",
                dataType : 'json',
                contentType : 'application/json',
                data : formToJSON(),
                cache : true,
                async: false,
                success : function (data) {

                    alert("Thanks for the submission!");
                    $("#add-exam-form")[0].reset();
                    $('#add-exam-form').DataTable().ajax.reload();
                    
                },
                error : function () {
                    alert("Error...!!!");
                }
            });
            saveInfo();
            function formToJSON() {
                var data = JSON.stringify({
                    "exam_type_id" : $('#add-exam-form').find('[name="exam_type_id"]').val(),
                    "subject_id" : $('#add-exam-form').find('[name="subject_id"]').val(),
                    "exam_date" : $('#add-exam-form').find('[name="exam_date"]').val(),
                    "full_marks" : $('#add-exam-form').find('[name="full_marks"]').val(),
                    "pass_marks" : $('#add-exam-form').find('[name="pass_marks"]').val(),
                    "time_from" : $('#add-exam-form').find('[name="time_from"]').val(),
                    "time_to" : $('#add-exam-form').find('[name="time_to"]').val(),
                    "status" : $('#add-exam-form').find('[name="status"]:checked').val(),
                });
                alert(data);
                return data;
            }
            function saveInfo() {
            	//alert(a_program_id);
            	//alert(semester_no);
            	//alert(examTypeId);
        		$.ajax({
        			url : window.context + "/ApiStudentsExams/GetRequiredInfoTOSave",
        			method : "POST",
        			cache : true,
        			 async: false,
        			data : {
        				a_program_id : a_program_id,
        				semester_no : semester_no,
        				examTypeId	: examTypeId

        			},
        			success : function(data) {
        				alert("sucess");
        			
        			},
        			error : function() {
        				alert("Error...!!!");
        			}
        		});
        	}
        
        });
    </script>

</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />