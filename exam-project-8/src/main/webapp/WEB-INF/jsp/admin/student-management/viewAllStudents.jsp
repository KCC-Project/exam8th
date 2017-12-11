<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="../shared/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="admin-dashboard.jsp"><span class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black"> view</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right" data-toggle="modal"
						data-target="#searchStudentModal" id="modal-box">Filter</button></a>
				<div class="col-xs-3" style="margin-left: -34px; /* border: 2px solid black; */ height: 37px;">
					<div class="form-group">
						<div class="input-group">
							<select class="form-control" id="sel1"></select> <span class="input-group-addon"> <i
								class="fa fa-search"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="col-xs-7 " id="sembtn"></div>
			</div>
		</div>
	</div>


	<table id="view_student" class="table table-hover table-striped table-responsive" cellspacing="0"
		width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Image</th>
				<th>Name</th>
				<th>Username</th>
				<th>Gender</th>
				<th>DOB</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Email</th>
				<th>current Semester</th>
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
					<h4 class="modal-title">Students Search</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="col-sm-4">
								<div class="form-group " style="margin-bottom: 0px;">
									<select required class="form-control" id="p-faculty-box" name="faculty_id">
									</select>
								</div>
							</div>
							<div class="form-group col-sm-4" style="margin-bottom: 0px;">
								<select required class="form-control" id="p-program-box" name="program_id">
									<option value="" disabled selected>Select Programme</option>
								</select>
							</div>
							<div class="form-group col-sm-4" style="margin-bottom: 0px;">
								<select required class="form-control" name="batch_id" id="p-batch-box">
									<option value="" disabled selected>Select Batch</option>
								</select>
							</div>

						</div>
					</div>
					<br>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success" id="searchbtnClicked" data-dismiss="modal">Search</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<form id="edit-student-form" method="post" class="form-horizontal well" style="display: none;">
		<div class="form-group">
			<label class="col-md-3 control-label">Student Id</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="s_id" disabled="disabled" />
			</div>
		</div>
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
				<label> Male <input type="radio" value=0 name="gender">
				</label> <label> Female <input type="radio" value=1 name="gender">
				</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">Address</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="address" />
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
				<input type="date" class="form-control" name="date_of_birth" />
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
					<select required class="form-control" id="all-program-box" name="e_program_id">
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
				<label> Yes <input type="radio" value=0 name="status">
				</label> <label> No <input type="radio" value=1 name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="updateStudent" class="btn btn-info btn-block">Update</button>
			</div>
		</div>
	</form>

<!--=========================================================================================  -->

<script>
    var programId = 0;
    var batchyear = 0;

    $(document).ready(function () {

        load_all_program("all-program-box");

        $("#modal-box").click(function (event) {
            load_faculty(event, "p-faculty-box");
        });
        $("#p-faculty-box").change(function (event) {
            load_program(event, "p-program-box");
        });
        $("#p-program-box").change(function (event) {
            load_batch_year(event, "p-batch-box");
        });
        $("#searchbtnClicked").click(function (event) {
            load_student(event, "student-batch-box");
        });
        $("#student-batch-box").change(function (event) {
            var getid = event.target.id;
            var id = $('#' + getid).find(":selected").val();
            loadStudentInformation(id);
        });
        $("#p-batch-box").change(function (event) {
            var getid = event.target.id;
            var id = $('#' + getid).find(":selected").val();
            batchyear = id;
        });

        //For select 2 initialization
        var url1 = window.context + "/ApiStudent/SearchStudent";
        var method1 = "POST";
        var url2 = window.context + "/ApiStudent/GetStudent";
        var method2 = "GET";
        var placeholder = "Student";
        select2Function(url1, url2, method1, method2, placeholder, loadStudentInformation);

        function loadStudentInformation(url, method, data) {
            // Initializing Datatable
            $('#view_student').DataTable({
                destroy : true,
                paging : true,
                searching : true,
                "processing" : true,
                "serverSide" : false,
                "ajax" : {
                    "url" : url,
                    "type" : method,
                    "data" : data,
                    "dataSrc" : "",
                    "dataType" : "json",
                    "async" : false
                },
                "columns" : [ {
                    "data" : "s_id"
                }, {
                    "data" : "first_name"
                }, {
                    data : null,
                    render : function (data, type, row) {
                        console.log(JSON.stringify(data));
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
                    "data" : "username"
                }, {
                    data : null,
                    render : function (data, type, row) {
                        // Combine the two data
                        if (data.gender == 1) {
                            return 'Male';
                        } else {
                            return 'Female';
                        }
                    },
                }, {
                    "data" : "date_of_birth"
                }, {
                    "data" : "address"
                }, {
                    "data" : "phone"
                }, {
                    "data" : "email"
                }, {
                    "data" : "current_semester"
                }, {
                    "data" : "status"
                }, {
                    data : null,
                    render : function (data, type, row) {
                        return '<button class="btn btn-success editStud">Edit</button>';
                    },
                } ]
            });

            // edit buttons on students row
            $(".editStud").click(function (event) {
                var table = $("#view_student").DataTable();
                var data = table.row($(this).parents('tr')).data();
                console.log(data);

                // Populate the form fields
                $('#edit-student-form').find('[name="s_id"]').val(data['s_id']).end().find('[name="first_name"]').val(data['first_name']).end().find('[name="last_name"]').val(data['last_name']).end().find('[name="username"]').val(data['username']).end().find('[name="password"]').val(data['password']).end().find('[name="email"]').val(data['email']).end().find('[name="date_of_birth"]').val(data['date_of_birth']).end().find('[name="phone"]').val(data['phone']).end().find('[name="address"]').val(data['address']).end().find('[name="image"]').val(data['image']).end();

                $("input[name=gender][value=" + data['gender'] + "]").prop('checked', true);
                $("input[name=status][value=" + data['status'] + "]").prop('checked', true);
                $("input[name=s_semester_no][value=" + data['semester_no'] + "]").attr('selected', 'selected');

                bootbox.dialog({
                    title : 'Edit the Student',
                    message : $('#edit-student-form'),
                    show : false
                // We will show it manually later
                }).on('shown.bs.modal', function () {
                    $('#edit-student-form').show() // Show the modal form
                }).on('hide.bs.modal', function (e) {
                    // Bootbox will remove the modal (including the body which contains the login form)
                    // after hiding the modal
                    // Therefor, we need to backup the form
                    $('#edit-student-form').hide().appendTo('body');
                }).modal('show');

            });

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
            success : function (data) {
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

                var uniqueYear = duplicateYear.filter(function (x, i, a) {
                    return a.indexOf(x) == i;
                });

                for (var i = 0; i < uniqueYear.length; i++) {
                    //batchyear = uniqueYear[i];
                    content += '<option value='+uniqueYear[i] +'>' + uniqueYear[i] + '</option>';
                }

                $('#' + target).html(content);
            },
            error : function () {
                alert("Error...!!!");
            }
        });
    }

    function load_student(e, target) {
        //alert(programId);
        //alert(batchyear);
        var url = window.context + "/ApiStudent/SearchStudentsByProgram";
        var method = "POST";
        //var data = "{'programId':'" + programId + "','batchyear':'" + batchyear + "'}";
        var data = {
            "programId" : programId,
            "batchyear" : batchyear
        };
        loadStudentInformation(url, method, data);
    }
    
    
    // form validator for student edit form
    $("#edit-student-form").bootstrapValidator({
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

        var data = $('#edit-student-form').serializeArray();
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
            url : window.context + "/ApiStudent/UpdateStudent",
            method : "PUT",
            dataType : 'json',
            contentType : 'application/json',
            data: formToJSON(),
            
            cache : true,
            success : function (data) {
                var message = "Student has been added Successfully";
                $("#success_message").html(message);
                alert("Thanks for the submission!");
                $("#edit-student-form")[0].reset();
            },
            error : function () {
                alert("Error...!!!");
            }
        }); 
        
        function formToJSON() {
			var data = JSON.stringify({
				"s_id" : $('#edit-student-form').find('[name="s_id"]').val(),
				"first_name" : $('#edit-student-form').find('[name="first_name"]').val(),
				"middle_name" : $('#edit-student-form').find('[name="middle_name"]').val(),
				"last_name" : $('#edit-student-form').find('[name="last_name"]').val(),
				"username" : $('#edit-student-form').find('[name="username"]').val(),
				"password" : $('#edit-student-form').find('[name="password"]').val(),
				"email" : $('#edit-student-form').find('[name="email"]').val(),
				"gender" : $('#edit-student-form').find('[name="gender"]').val(),
				"date_of_birth" : $('#edit-student-form').find('[name="date_of_birth"]').val(),
				"phone" : $('#edit-student-form').find('[name="phone"]').val(),
				"address" : $('#edit-student-form').find('[name="address"]').val(),
				"image" : $('#edit-student-form').find('[name="image"]').val(),
				"current_semester" : $('#edit-student-form').find('[name="current_semester"]').val(),
				"status" : $('#edit-student-form').find('[name="status"]:checked').val(),	

			});
			alert(data);
			return data;
		}
    });
</script>

</div>

<jsp:include page="../shared/footer.jsp" />