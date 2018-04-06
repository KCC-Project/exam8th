<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="../shared/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black"> view</span></a></li>

	</ol>
	<!--=============================================Main Content===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 10px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right" data-toggle="modal"
						data-target="#searchStudentModal" id="modal-box">Filter</button></a>
						<button type="button" class="btn btn-warning pull-right" id="promote-btn" style="margin-right:1%;">Update Semester 
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> </button>
				<div class="col-xs-3" style="margin-left: -34px; /* border: 2px solid black; */ height: 37px;">
					<div class="form-group">
						<div class="input-group">
							<select class="form-control" id="sel1" ></select> <span class="input-group-addon"> <i
								class="fa fa-search"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="col-xs-7 " id="sembtn"></div>
			</div>
		</div>
	</div>

<div class="table table-responsive">
	<table class="table table-hover table-striped" id="view_student" width="100%">
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
</div>

	<!--=========================================================================================  -->
	<div class="modal fade" id="searchStudentModal" role="dialog">
		<div class="modal-dialog modal-md">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Students Search</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12 text-center">
							<!-- 
							<div class="col-sm-4">
								<div class="form-group " style="margin-bottom: 0px;">
									<select required class="form-control" id="p-faculty-box" name="faculty_id">
									</select>
								</div>
							</div>   -->
							<div class="form-group col-sm-6" style="margin-bottom: 0px;">
								<select required class="form-control" id="p-program-box" name="program_id">
									<option value="" disabled selected>Select Programme</option>
								</select>
							</div>
							<div class="form-group col-sm-6" style="margin-bottom: 0px;">
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
	
		<!--=========================================================================================  -->

				<div id="promoteBox" class="danger" style="display:none;">
					<div class="row">
						<div class="col-sm-12" id="promote-inputs">
							<div class="form-group col-sm-6" style="margin-bottom: 0px;">
								<select required class="form-control" id="pr-program-box" name="pr_program_id">
									<option value="" disabled selected>Select Programme</option>
								</select>
							</div>
							<div class="form-group col-sm-6" style="margin-bottom: 0px;">
								<select required class="form-control" name="pr_batch_id" id="pr-batch-box">
									<option value="" disabled selected>Select Batch</option>
								</select>
							</div>
							<div class="form-group col-sm-12 text-center" style="margin-top: 1rem;">
								<select class="form-control" name="pr_increment_value">
									<option value="0" selected disabled>Select Semester</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9 (Pass Out)</option>
								</select>							
							</div>

						</div>
					</div>
					<br>
					<div class="form-group text-center">
						<button class="btn btn-warning" id="promoteClicked">Promote Semester</button>
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
				<label> Male <input type="radio" value=1 name="gender">
				</label> <label> Female <input type="radio" value=0 name="gender">
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
					<option  selected >Select Program</option>
					</select>
				</div>
				<div class="col-md-6">
				<select class="form-control" id="s-semester-no" name="s_semester_no">
						<option value="0" selected disabled>Select Semester</option>
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
				<label> Yes <input type="radio" value=1 name="status" checked>
				</label> <label> No <input type="radio" value=0 name="status">
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
var idOfSelectedIteam=0;

//id for auto selecting program when update is cliced
var programId2=0;

//for auto updating program id from student
var studentProgramId2=0;

var batchyear2=0;
var enrolldate2=0;
var studentid2=0;

    $(document).ready(function () {
    	
    	// check if the url comes from dashboard
    	var url_string = window.location.href;
    	var url = new URL(url_string);
    	var dash = url.searchParams.get("dash");
    	
    	if(dash==1){
    		//console.log(dash);
    		var url = window.context + "/ApiStudent/GetAllStudent";
        	var method = "GET";
        	var data = "";
    		loadStudentInformation(url, method, data);
    	}
    	// ----------------------------------
        load_all_program("all-program-box");
    	
    	// ------------------ promote students input modal ----------------------------
        load_all_program("pr-program-box");
        $("#pr-program-box").change(function (event) {
            load_batch_year(event, "pr-batch-box");
        });
        $("#promote-btn").click(function (event) {
        	bootbox.dialog({
                title : 'Update Student Semester',
                message : $('#promoteBox'),
                show : false
            // We will show it manually later
            }).on('shown.bs.modal', function () {
                $('#promoteBox').show() // Show the modal form
            }).on('hide.bs.modal', function (e) {
                // Bootbox will remove the modal (including the body which contains the form)
                // after hiding the modal
                // Therefor, we need to backup the form
                $('#promoteBox').hide().appendTo('body');
            }).modal('show');
        });
        
        $("#promoteClicked").click(function (event) {
        	var program_id = $("#promote-inputs").find('[name="pr_program_id"]').val();
        	var batch_year = $("#promote-inputs").find('[name="pr_batch_id"]').val();
        	var program_name = $( "#pr-program-box option:selected" ).text();
        	var increment_value = $("#promote-inputs").find('[name="pr_increment_value"]').val();
        	if(!program_id || !batch_year || !increment_value){
        		alert("Please select both Program & Batch_year & semester and Try Again !");
        	}
        	else {
				// promote student semester
				var batch = prompt("Update semester of: "+program_name+" ("+ batch_year +") students", "Enter Batch_year here to confirm");
				if(batch == batch_year){
	        		$.ajax({
	        			 url : window.context + "/ApiStudent/UpdateSemester",
	        	            method : "POST",
	        	            data: { program_id: program_id, batch_year: batch_year, increment_value: increment_value }, // parameters
	        	            dataType: "text",
	        	            async: false,
	        	            cache : true,
	            			success : function(data) {
	            				alert(data);
	            				 var url = window.context + "/ApiStudent/SearchStudentsByProgram/"+program_id+"/"+batch_year;
	            		         var method = "GET";
	            		         var data="";
	            		         loadStudentInformation(url,method,data);
	            			},
	            			error : function() {
	            				alert("Error...!!!, Something went wrong with the server");
	            			}
	            		});
	        	} else { alert ("Wrong Input"); }
        	}
        });
        // ----------------------------------------------------------------------------

        /*
        $("#modal-box").click(function (event) {
            load_faculty(event, "p-faculty-box");
        });
        $("#p-faculty-box").change(function (event) {
            load_program(event, "p-program-box");
        });
        */
        load_all_program("p-program-box");
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

        function load_student(e, target) {
            //var data = 'programId='+ programId  + '&batchyear='+ batchyear;
            var url = window.context + "/ApiStudent/SearchStudentsByProgram/"+programId+"/"+batchyear;
            var method = "GET";
            var data="";
            loadStudentInformation(url,method,data);
        }
        
        function loadStudentInformation(url, method, data) {
            // Initializing Datatable
            //alert("from load ="+data);
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
                        console.log("data = "+JSON.stringify(data));
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
                    data : null,
                    render : function (data, type, row) {
                        // Combine the two data
                        if (data.status == 1) {
                            return 'Active/NotOut';
                        } else {
                            return '<button class="btn btn-danger">PassOut/Left</button>';
                        }
                    },
                },{
                    data : null,
                    render : function (data, type, row) {
                        return '<button value='+data.s_id+' class="btn btn-success editStud">Edit</button>';
                    },
                } ]
            });

            // edit buttons on students row
            $("#view_student").on('click','.editStud',function(event) {
                var table = $("#view_student").DataTable();
                var data = table.row($(this).parents('tr')).data();
                console.log("data of row = "+JSON.stringify(data));
            
                //initializing selected student id to get student program
                idOfSelectedIteam=data.s_id;
               
                // Populate the form fields
                $('#edit-student-form').find('[name="s_id"]').val(data['s_id']).end()
                .find('[name="first_name"]').val(data['first_name']).end()
                  .find('[name="middle_name"]').val(data['middle_name']).end()
                .find('[name="last_name"]').val(data['last_name']).end()
                .find('[name="username"]').val(data['username']).end()
                .find('[name="password"]').val(data['password']).end()
                .find('[name="email"]').val(data['email']).end()
                .find('[name="date_of_birth"]').val(data['date_of_birth']).end()
                .find('[name="phone"]').val(data['phone']).end()
                .find('[name="address"]').val(data['address']).end()
                .find('[name="image"]').val(data['image']).end();

                $("input[name=gender][value=" + data['gender'] + "]").prop('checked', true);
                $("input[name=status][value=" + data['status'] + "]").prop('checked', true);
               // $("input[name=s_semester_no][value=" + data['current_semester'] + "]").attr('selected', 'selected');
                $('#s-semester-no option').each(function() {
                	//alert($(this).val());
      
                    if($(this).val() == data['current_semester']) {
                    	//alert("inside");
                    	//$('select option[value="1"]').attr("selected",true);
                    	$(this).prop("selected", true);
                 
                    }
                });
                
                
                //loading student program to find which student belong to which program and to select that program in 
                //update automatically
                loadStudentProgram();
               
                function loadStudentProgram() {
                	
            		$.ajax({
            			url : window.context + "/ApiStudentsProgram/GetStudentsProgramByStudentId/"+idOfSelectedIteam,
            			method : "GET",
            			cache : true,
            			 async: false,
            			  dataType : 'json',
            			
            			success : function(data) {
            			//alert("from stud ="+JSON.stringify(data));
            			programId2=data[0].program.program_id;
            			studentProgramId2=data[0].student_program_id;
            			//alert(programId2);
            			
            			 batchyear2=data[0].batch_year;
            			enrolldate2=data[0].enroll_date;
            			 studentid2=data[0].student.s_id;
            			//alert(studentid2);
            			//alert(studentProgramId2);
            			},
            			error : function() {
            				alert("Error...!!!");
            			}
            		});
            	}
              
                //auto selecting program 
                $('#all-program-box option').each(function() {
                    if($(this).val() == programId2) {
                    	$(this).prop("selected", true);
                    }
                });
                
                
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
                
                bootbox.modal('hide');

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
            async:false,
            cache : true,
            success : function (data) {
                var message = "Student has been updated Successfully";
               $("#success_message").html(message);
                //alert("Thanks for the submission!");
               // $("#edit-student-form")[0].reset();
               $('#view_student').DataTable().ajax.reload();
               updateStudentProgram();
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
				"gender" : $('#edit-student-form').find('[name="gender"]:checked').val(),
				"date_of_birth" : $('#edit-student-form').find('[name="date_of_birth"]').val(),
				"phone" : $('#edit-student-form').find('[name="phone"]').val(),
				"address" : $('#edit-student-form').find('[name="address"]').val(),
				"image" : $('#edit-student-form').find('[name="image"]').val(),
				"current_semester" : $('#edit-student-form').find('[name="s_semester_no"]').val(),
				"status" : $('#edit-student-form').find('[name="status"]:checked').val(),	

			});
			return data;
		}
        function updateStudentProgram(){
         $.ajax({
            url : window.context + "/ApiStudentsProgram/UpdateStudentsProgram",
            method : "PUT",
            dataType : 'json',
            contentType : 'application/json',
            data: formToJSONProgram(),
            async:false,
            cache : true,
            success : function (data) {
                var message = "Student has been updated Successfully";
                $("#success_message").html(message);
                alert("Thanks for the submission!");
                $("#edit-student-form")[0].reset();
            },
            error : function () {
                alert("Error...!!!");
            }
        });  
         }
        //formToJSONProgram();
        function formToJSONProgram() {
			var data = JSON.stringify({

				"student_program_id":studentProgramId2,
				"batch_year":batchyear2,
				"enroll_date":enrolldate2,
				"status":$('#edit-student-form').find('[name="status"]:checked').val(),	
				"student":{"s_id":studentid2,},
				"program":{
					"program_id":$('#edit-student-form').find('[name="e_program_id"]').val(),
					},
					
			});
			//alert(data);
			return data;
		}
    });
</script>

</div>

<jsp:include page="../shared/footer.jsp" />