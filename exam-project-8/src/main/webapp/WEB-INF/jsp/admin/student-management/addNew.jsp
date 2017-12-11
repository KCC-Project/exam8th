
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
<jsp:include page="../shared/header.jsp" />

<div class="alert alert-success" role="alert" id="success_message"></div>

<!-- Content Area -->
<div id="page-content-wrapper">


	<!-- Content Area -->
	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span class="glyphicon glyphicon-home"> Home </span></a></li>
		<li><a><span class="glyphicon glyphicon-user black"> Add-Students </span></a></li>
	</ol>

	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading main-color-bg">Add Student</div>
			<div class="panel-body">

				<form id="add-student-form" method="post" class="form-horizontal well">

					<div class="form-group">
						<label class="col-md-3 control-label">Student Name</label>
						<div class="col-md-9">
							<div class="col-md-4">
								First_Name:<input type="text" class="form-control" name="first_name" />
							</div>
							<div class="col-md-4">
								Middle_Name<input type="text" class="form-control" name="middle_name" />
							</div>
							<div class="col-md-4">
								Last_Name<input type="text" class="form-control" name="last_name" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Gender</label>
						<div class="col-md-9">
							<label> Male <input type="radio" value=0 name="gender" checked>
							</label> <label> Female <input type="radio" value=1 id="gender" name="gender" >
							</label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">Address</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="address"  />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Phone</label>
						<div class="col-md-9">
							<input type="number" class="form-control" name="phone" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Date_Of_Birth</label>
						<div class="col-md-9">
							<input type="date" class="form-control" id="date_of_birth" name="date_of_birth" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Login Info</label>
						<div class="col-md-9">
							<div class="col-md-6">
								Username<input type="text" class="form-control" name="username" />
							</div>
							<div class="col-md-6">
								Password<input type="password" class="form-control" name="password" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Email</label>
						<div class="col-md-9">
							<input type="email" class="form-control" name="email" />
						</div>
					</div>
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
						<label class="col-md-3 control-label">Image</label>
						<div class="col-md-9">
							<input type="file" class="form-control" name="image" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Available</label>
						<div class="col-md-9">
							<label> Yes <input type="radio" value=0 name="status" checked>
							</label> <label> No <input type="radio" value=1 name="status">
							</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<button type="submit" id="addStudent" class="btn btn-info btn-block" >Add Student</button>
						</div>
					</div>
				</form>



			</div>
		</div>
	</div>
</div>

<script>
var semesterno;
var p_id;
    $(document).ready(function () {
    	
        $.when( $.ajax(load_all_program("all-program-box")) ).done(function() {
           	$("#all-program-box").append("<option value='' selected disabled>Select Program</option>");
    		});
        
        
        $("#s-semester-no").change(function(event) {
			var getid = event.target.id;
			semesterno = $('#' + getid).find(":selected").val();
			//load_subject(event, "p-subject-box");
		});
        $("#all-program-box").change(function(event) {
			var getid = event.target.id;
			p_id = $('#' + getid).find(":selected").val();
			//load_subject(event, "p-subject-box");
		});
        $("#add-student-form").bootstrapValidator({
            // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
            feedbackIcons : {
                valid : "glyphicon glyphicon-ok",
                invalid : "glyphicon glyphicon-remove",
                validating : "glyphicon glyphicon-refresh"
            },
            fields : {
                first_name : {
                    validators : {
                        stringLength : {
                            min : 2
                        },
                        notEmpty : {
                            message : "Please Enter Student First Name"
                        }
                    }
                },
                last_name : {
                    validators : {
                        stringLength : {
                            min : 2
                        },
                        notEmpty : {
                            message : "Please Enter Student Last Name"
                        }
                    }
                },
                gender : {
                    validators : {
                        notEmpty : {
                            message : "Please Select Gender"
                        }
                    }
                },
                date_of_birth : {
                    validators : {
                        notEmpty : {
                            message : "Please Enter Date of birth"
                        }
                    }
                },
                address : {
                    validators : {
                        stringLength : {
                            min : 2
                        },
                        notEmpty : {
                            message : "Please Enter address"
                        }
                    }
                }
            }
        })

        .on("success.form.bv", function (e) {

            // Prevent form submission
            e.preventDefault();

            var data = $('#add-student-form').serializeArray();
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
                url : window.context + "/ApiStudent/SaveStudent",
                method : "POST",
                dataType : 'json',
                contentType : 'application/json',
                data: formToJSON(),
                
                cache : true,
                async: false,
                success : function (data) {
                    var message = "Student has been added Successfully";
                    $("#success_message").html(message);
                    alert("Thanks for the submission!");
                    $("#add-student-form")[0].reset();
                },
                error : function () {
                    alert("Error...!!!");
                }
            }); 
             saveStudentProgram();
            function formToJSON() {
				var data = JSON.stringify({
					"s_id" : $('#add-student-form').find('[name="s_id"]').val(),
					"first_name" : $('#add-student-form').find('[name="first_name"]').val(),
					"middle_name" : $('#add-student-form').find('[name="middle_name"]').val(),
					"last_name" : $('#add-student-form').find('[name="last_name"]').val(),
					"username" : $('#add-student-form').find('[name="username"]').val(),
					"password" : $('#add-student-form').find('[name="password"]').val(),
					"email" : $('#add-student-form').find('[name="email"]').val(),
					"gender" : $('#add-student-form').find('[name="gender"]:checked').val(),
					"date_of_birth" : $('#add-student-form').find('[name="date_of_birth"]').val(),
					"phone" : $('#add-student-form').find('[name="phone"]').val(),
					"address" : $('#add-student-form').find('[name="address"]').val(),
					"image" : $('#add-student-form').find('[name="image"]').val(),
					"current_semester" : semesterno,
					"status" : $('#add-student-form').find('[name="status"]:checked').val(),	

				});
				//alert(data);
				return data;
			}
            function saveStudentProgram() {
            	var utc = new Date().toJSON().slice(0,10).replace(/-/g,'-');
            	//alert(utc);
            	var today = new Date();
            	var yyyy = today.getFullYear();
            	//alert(yyyy);
        		$.ajax({
        			url : window.context + "/ApiStudentsProgram/GetStudentProgramInfoTOSave",
        			method : "POST",
        			cache : true,
        			 async: false,
        			data : {
        				p_id : p_id,
        				enroll_date : utc,
        				batch	: yyyy

        			},
        			success : function(data) {
        				alert("sucess in insert studet program");
        			
        			},
        			error : function() {
        				alert("Error...!!!");
        			}
        		});
        	}
        });
     
    });
</script>

<jsp:include page="../shared/footer.jsp" />