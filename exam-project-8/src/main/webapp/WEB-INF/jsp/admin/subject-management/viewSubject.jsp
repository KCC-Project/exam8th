

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>

<jsp:include page="../shared/header.jsp" />

<!-- Content Area -->
<div id="page-content-wrapper">

	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span class="glyphicon glyphicon-home"> Home </span></a></li>
		<li><a><span class="glyphicon glyphicon-user black"> View Subject </span></a></li>
	</ol>


	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right" data-toggle="modal"
						data-target="#searchSubjectModal" id="modal-box">Filter</button></a>
				<div class="col-md-3" style="margin-left: -34px; /* border: 2px solid black; */ height: 37px;">
					<div class="form-group">
						<div class="input-group">
							<select class="form-control" id="sel1"></select> <span class="input-group-addon"> <i
								class="fa fa-search"></i>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!--=========================================================================================  -->
	<div class="modal fade" id="searchSubjectModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Subject Search</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<div class="col-sm-6">
								<div class="form-group " style="margin-bottom: 0px;">
									<select required class="form-control" id="p-faculty-box" name="faculty_id">
									</select>
								</div>
							</div>
							<div class="form-group col-sm-6" style="margin-bottom: 0px;">
								<select required class="form-control" id="p-program-box" name="program_id">
									<option value="" disabled selected>Select Program</option>
								</select>
							</div>
							<div class="col-sm-12 form-group">
								<h4 class="text-danger">The fields Below are Optional</h4>
							</div>

							<div class="row form-group">
								<div class="col-sm-4">
									<label class="control-label">Subject Name</label>
									<div class="input-group">
										<input name="s_subject_name" id="subject-name" placeholder="Enter Subject Name" class="form-control" type="text">
									</div>
								</div>

								<div class="col-sm-4">
									<label class=" control-label">Subject Code</label>
									<div class="input-group">
										<input name="s_subject_code" id="subject-code" placeholder="Enter Subject code" class="form-control" type="text">
									</div>
								</div>
								<div class="col-sm-4">
									<label>Semester no.: </label> <select class="form-control" id="s-semester-no" name="s_semester_no">
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

	<form id="subject-edit-form" method="post" class="form-horizontal well" style="display: none;">
		<div class="form-group">
			<label class="col-md-3 control-label">Subject ID</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="subject_id" disabled="disabled" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">Subject name</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="subject_name" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Subject code</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="subject_code" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Subject Credit</label>
			<div class="col-md-9">
				<div class="col-md-6">
					Theory_Credit<input type="text" class="form-control" name="theory_cr" />
				</div>
				<div class="col-md-6">
					Tutorial_Credit<input type="text" class="form-control" name="tutorial_cr" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Subject Marks</label>
			<div class="col-md-9">
				<div class="col-md-4">
					Internal_theory<input type="text" class="form-control" name="internal_theory" />
				</div>
				<div class="col-md-4">
					Internal_practical<input type="text" class="form-control" name="internal_practical" />
				</div>
				<div class="col-md-4">
					Final_theory<input type="text" class="form-control" name="final_theory" />
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-3 control-label">Syllabus File</label>
			<div class="col-md-9">
				<input type="file" class="form-control" name="syllabus_file" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Program</label>
			<div class="col-md-9">
				<select required class="form-control" id="all-program-box" name="program_id" required>
				</select>
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
				<button type="submit" id="updateSubject" class="btn btn-info btn-block">Save</button>
			</div>
		</div>
	</form>



	<table id="view_subject" class="table table-hover table-striped" cellspacing="0" width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Subject_name</th>
				<th>Subject_code</th>
				<th>Subject_credit</th>
				<th>Subject_marks</th>
				<th>Syllabus_file</th>
				<th>Program_id</th>
				<th>Semester_no</th>
				<th>Option</th>
			</tr>
		</thead>
	</table>

	<script>
        var programId = 0;

        $(document).ready(function () {
            
            load_all_program("all-program-box");

            // hiding table view
            //$("#view_subject").hide();
            $("#modal-box").click(function (event) {
                load_faculty(event, "p-faculty-box");
            });

            $("#p-faculty-box").change(function (event) {
                load_program(event, "p-program-box");
            });

            $("#searchbtnClicked").click(function (event) {
                load_subject(event);
            });

            // when view all subject button is clicked
            $("#view-all-subject").click(function () {
                if (confirm('Do you want to load All Subjects ? ')) {
                    var url = window.context + "/ApiSubject/GetAllSubject";
                    var method = "GET";
                    var data = "";
                    loadSubjectInformation(url, method, data);
                } else {
                    return false;
                }
            });

            //For select 2 initialization
            var url1 = window.context + "/ApiSubject/SearchSubject";
            var method1 = "POST";
            var url2 = window.context + "/ApiSubject/GetSubject";
            var method2 = "GET";
            var placeholder = "Subject Name";
            select2Function(url1, url2, method1, method2, placeholder, loadSubjectInformation);

        function load_subject(e) {
            //alert(programId);
            //alert(batchyear);
            $('input[type=number]').each(function() {
				var t = $(this);
				if (t.val() != 0) {
					//alert(t.val());
				} else {
					t.val('0');
				}
			});
            /*
            var program_id = $('#p-program-box').val();
            var subject_name = $('#subject-name').val();
            if (subject_name == ""){
            subject_name = null;
        }
            var subject_code = $('#subject-code').val();
            if (subject_code == ""){
                subject_code = null;
            }
            var semester_no = $('#s-semester-no').val();
            */
            
            function formToJSON() {
				var data = JSON.stringify({
					"semester_no" : $('#searchSubjectModal').find('[name="s_semester_no"]').val(),
					"subject_name" : $('#searchSubjectModal').find('[name="_subject_name"]').val(),
					"subject_code" : $('#searchSubjectModal').find('[name="s_subject_code"]').val(),
					"program_id" : $('#searchSubjectModal').find('[name="program_id"]').val(),

				});
				alert(data);
				return data;
			}
            var data = formToJSON();
            var url = window.context + "/ApiSubject/GetSubjectByParameters";
            var method = "POST";
           
            console.log(data);
            loadSubjectInformation(url, method, data);
        }

        // when load all subject is clicked
        function loadSubjectInformation(url, method, data) {
            // Initializing Datatable
            $('#view_subject').DataTable({
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
                    "contentType" : 'application/json',
                    "async" : false
                },
                "columns" : [ {
                    "data" : "subject_id"
                }, {
                    "data" : "subject_name"
                }, {
                    "data" : "subject_code"
                }, {
                    data : null,
                    render : function (data, type, row) {
                        console.log(data);
                        // Combine the two data
                        return 'Theory_credit: ' + data.theory_cr + '</br> Tutorial_credit: ' + data.tutorial_cr;
                    },
                }, {
                    data : null,
                    render : function (data, type, row) {
                        // Combine the two data
                        return 'Internal Theory: ' + data.internal_theory + '</br> Practical Marks: ' + data.internal_practical + '</br>  Final Theory: ' + data.final_theory;
                    },
                }, {
                    "data" : "syllabus_file",
                    render : function (data, type, row) {
                        // Combine the two data
                        return '<a href='+window.context +'file/'+data+'>view Syllabus</a>';
                    },
                }, {
                    "data" : "program_id",
                    render : function (data, type, row) {
                        return '<a href='+ window.context +'/program/'+data+'>Program_id: ' + data + '</a>';
                    },
                }, {
                    "data" : "semester_no"
                }, {
                    data : null,
                    render : function (data, type, row) {
                        return '<button class="btn btn-success editSub">Click!</button>';
                    },
                } ]
            });

            // edit buttons on subjects row
            $(".editSub").click(function (event) {
                var table = $("#view_subject").DataTable();
                var data = table.row($(this).parents('tr')).data();
                console.log(data);

                // Populate the form fields
                $('#subject-edit-form').find('[name="subject_id"]').val(data['subject_id']).end().find('[name="subject_name"]').val(data['subject_name']).end().find('[name="subject_code"]').val(data['subject_code']).end().find('[name="theory_cr"]').val(data['theory_cr']).end().find('[name="tutorial_cr"]').val(data['tutorial_cr']).end().find('[name="internal_theory"]').val(data['internal_theory']).end().find('[name="internal_practical"]').val(data['internal_practical']).end().find('[name="final_theory"]').val(data['final_theory']).end().find('[name="syllabus_file"]').val(data['syllabus_file']).end();

                $("input[name=status][value=" + data['status'] + "]").prop('checked', true);
                $("input[name=program_id][value=" + data['program_id'] + "]").attr('selected', 'selected');

                bootbox.dialog({
                    title : 'Edit the Subject',
                    message : $('#subject-edit-form'),
                    show : false
                // We will show it manually later
                }).on('shown.bs.modal', function () {
                    $('#subject-edit-form').show() // Show the modal form
                }).on('hide.bs.modal', function (e) {
                    // Bootbox will remove the modal (including the body which contains the login form)
                    // after hiding the modal
                    // Therefor, we need to backup the form
                    $('#subject-edit-form').hide().appendTo('body');
                }).modal('show');

            });

        }
        });
        
        // form validator for subject edit form
        $("#subject-edit-form").bootstrapValidator({
			// To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
			feedbackIcons : {
				valid : "glyphicon glyphicon-ok",
				invalid : "glyphicon glyphicon-remove",
				validating : "glyphicon glyphicon-refresh"
			},
			fields : {
				subject_name : {
					validators : {
						stringLength : {
							min : 2
						},
						notEmpty : {
							message : "Please Enter Subject Name"
						}
					}
				},
				subject_code : {
					validators : {
						stringLength : {
							min : 3
						},
						notEmpty : {
							message : "Please Enter Subject Code"
						}
					}
				},
				theory_cr : {
					validators : {
						stringLength : {
							max : 1
						},
						integer : {
							message : "Please Enter Number"
						}
					}
				},
				tutorial_cr : {
					validators : {
						stringLength : {
							max : 1
						},
						integer : {
							message : "Please Enter Number"
						}
					}
				},
				internal_theory : {
					validators : {
						stringLength : {
							max : 3
						},
						integer : {
							message : "Please Enter Number"
						}
					}
				},
				internal_practical : {
					validators : {
						stringLength : {
							max : 3
						},
						integer : {
							message : "Please Enter Number"
						}
					}
				},
				final_theory : {
					validators : {
						stringLength : {
							max : 3
						},
						integer : {
							message : "Please Enter Number"
						}
					}
				}
			}
		})

		.on("success.form.bv", function(e) {


			$('input[type=number]').each(function() {
				var t = $(this);
				if (t.val() != 0) {
					//alert(t.val());
				} else {
					t.val('0');
				}
			});
			
			// Prevent form submission
			e.preventDefault();

			var data = $('#subject-edit-form').serializeArray();
			console.log(data);

			$.ajax({
				url : window.context + "/ApiSubject/UpdateSubject",
				method : "PUT",
				dataType : 'json',
				data : formToJSON(),
				contentType : 'application/json',
				cache : true,
				success : function(data) {
					var message = "Subject has been added Successfully";
					$("#success_message").html(message);
					alert("Thanks for the submission!");
					$("#subject-edit-form")[0].reset();
				},
				error : function() {
					alert("Error...!!!");
				}
			});
			function formToJSON() {
				var data = JSON.stringify({
					"subject_id" : $('#subject-edit-form').find('[name="subject_id"]').val(),
					"semester_no" : $('#subject-edit-form').find('[name="semester_no"]').val(),
					"subject_name" : $('#subject-edit-form').find('[name="subject_name"]').val(),
					"subject_code" : $('#subject-edit-form').find('[name="subject_code"]').val(),
					"theory_cr" : $('#subject-edit-form').find('[name="theory_cr"]').val(),
					"tutorial_cr" : $('#subject-edit-form').find('[name="tutorial_cr"]').val(),
					"internal_theory" : $('#subject-edit-form').find('[name="internal_theory"]').val(),
					"internal_practical" : $('#subject-edit-form').find('[name="internal_practical"]').val(),
					"final_theory" : $('#subject-edit-form').find('[name="final_theory"]').val(),
					"syllabus_file" : $('#subject-edit-form').find('[name="syllabus_file"]').val(),
					"status" : $('#subject-edit-form').find('[name="status"]:checked').val(),
					"program_id" : $('#subject-edit-form').find('[name="program_id"]').val(),

				});
				alert(data);
				return data;
			}
		});

    </script>


</div>

<jsp:include page="../shared/footer.jsp" />