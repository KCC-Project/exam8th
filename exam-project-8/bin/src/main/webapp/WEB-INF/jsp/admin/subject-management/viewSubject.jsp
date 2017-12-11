

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>


<jsp:include page="../shared/header.jsp" />

<!-- Content Area -->
<div id="page-content-wrapper">

	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span class="glyphicon glyphicon-home"> Home </span></a></li>
		<li><a><span class="glyphicon glyphicon-user black"> View Subject </span></a></li>
	</ol>

	<div class="panel-group">
		<div class="panel panel-default">
			<div class="panel-heading main-color-bg">
				View Subject
				<div class="filter-area pull-right">
					<button type="button" class="btn btn-info" id="filter-box">
						<span class="glyphicon glyphicon-search"> Filter </span>
					</button>
					<button class="btn btn-warning" id=view-all-subject>View All Subject</button>
				</div>
			</div>

			<div class="panel-body">

				<div class="modal fade" id="search_subject_modal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-body">

								<form class="well form-horizontal" action=" " method="post" id="subject_search_form">
									<fieldset>

										<!-- Form Name -->
										<legend>Search Subject!</legend>

										<div class="row form-group">
											<label class="col-sm-4 control-label">Program Name: </label>
											<div class="col-sm-8">
												<div class="input-group">
													<span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span> <select
														class="form-control col-sm-12" id="s-program-box" name="s_program_box">

													</select>
												</div>
											</div>
										</div>

										<div class="col-sm-12 form-group">
											<div class="alert alert-info">The fields Below are Optional</div>
										</div>

										<div class="row form-group">
											<div class="col-sm-6">
												<label class="control-label">Subject Name</label>
												<div class="inputGroupContainer">
													<div class="input-group">
														<span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span> <input
															name="s_subject_name" placeholder="Enter Subject Name" class="form-control"
															type="text">
													</div>
												</div>
											</div>

											<div class="col-sm-6">
												<label class=" control-label">Subject Code</label>
												<div class=" inputGroupContainer">
													<div class="input-group">
														<span class="input-group-addon"><i class="glyphicon glyphicon-book"></i></span> <input
															name="s_subject_code" placeholder="Enter Subject code" class="form-control"
															type="text">
													</div>
												</div>
											</div>
										</div>

										<div class="col-sm-12 form-group">
											<label>Semester no.: </label> <select class="form-control" id="s-semester-no"
												name="s_semester_no">
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

										<!-- Button -->
										<div class="form-group">
											<label class="col-md-4 control-label"></label>
											<div class="col-md-4">
												<button type="submit" class="btn btn-warning" id="search_submit">
													Submit <span class="glyphicon glyphicon-send"></span>
												</button>
											</div>
										</div>

									</fieldset>
								</form>


							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
				</div>



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
					<tfoot>
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
					</tfoot>
				</table>
			</div>
		</div>
	</div>

	<script>
        $(document).ready(function () {

            // hiding table view
            $("#view_subject").hide();

            // Showing modal when filter button is clicked
            $('#filter-box').click(function () {
                $('#search_subject_modal').modal('show');
                // initilizing modal with form validators
                initialize_modal();
                load_program('s-program-box');
            });

            // when view all subject button is clicked
            $("#view-all-subject").click(function () {
                if (confirm('Do you want to load All Subjects ? ')) {
                    $("#view_subject").show();
                    load_all_subjects();
                } else {
                    return false;
                }
            });

            function load_program(target) {
                $('#' + target).select2({
                    width : "100%",
                    placeholder : "Select Program",
                    tags : true,
                    multiple : true,
                    ajax : {
                        url : window.context + "/ApiProgram/GetAllProgram",
                        dataType : "json",
                        type : "GET",
                        data : function (params) {
                            var queryParameters = {
                                term : params.term
                            };
                            return queryParameters;
                        },
                        processResults : function (data) {
                            return {
                                results : $.map(data, function (item) {
                                    return {
                                        id : item.program_id,
                                        text : item.program_name
                                    };
                                })
                            };
                        }
                    }
                });
            }

            function initialize_modal() {
                $("#subject_search_form").bootstrapValidator({
                    // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
                    feedbackIcons : {
                        valid : "glyphicon glyphicon-ok",
                        invalid : "glyphicon glyphicon-remove",
                        validating : "glyphicon glyphicon-refresh"
                    },
                    fields : {
                        s_subject_name : {
                            validators : {
                                stringLength : {
                                    min : 2
                                },
                            }
                        },
                        subject_code : {
                            validators : {
                                stringLength : {
                                    min : 3
                                },
                            }
                        },
                        s_program_box : {
                            validators : {
                                stringLength : {
                                    max : 1,
                                    message : "You can select only one program"
                                },
                                notEmpty : {
                                    message : "Please Enter Program"
                                }
                            }
                        }
                    }
                })
               
                .on('success.form.fv', function(e) {
                    // Prevent form submission
                    e.preventDefault();

                   alert("wsda");

                });
                
            }

            // when load all subject is clicked
            function load_all_subjects() {
                // Initializing Datatable
                $('#view_subject').DataTable({
                    "processing" : true,
                    "serverSide" : true,
                    "ajax" : {
                        "url" : window.context + "/ApiSubject/GetAllSubject",
                        "type" : "GET",
                        "dataSrc" : "",
                        "contentType" : "application/json",
                        "dataType" : "json"
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
                            return '<button class="btn btn-success editBtns">Click!</button>';
                        },
                    } ]
                });

                // edit buttons on subjects row
                $('#view_subject tbody').on('click', '.editBtns', function () {
                    var table = $("#view_subject").DataTable();
                    var data = table.row($(this).parents('tr')).data();
                    console.log(data);
                    alert(data['subject_name'] + "' id is: " + data['subject_id']);
                });

            }

        });
    </script>


</div>

<jsp:include page="../shared/footer.jsp" />