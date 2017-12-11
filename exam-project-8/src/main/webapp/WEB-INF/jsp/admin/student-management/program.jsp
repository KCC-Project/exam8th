<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="../shared/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="${cp}/home""><span class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black"> Education</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black"> Program</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right" data-toggle="modal"
						id="modal-box-vew-all-program">View All</button></a>
				<div class="pull-right" style="margin-right: 10px;">
					<button class="btn btn-info" id="add-program-modal">Add Program</button>
				</div>
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


	<table id="view_program" class="table table-hover table-striped table-responsive" cellspacing="0"
		width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Program Name</th>
				<th>Program Year</th>
				<th>Faculty Name</th>
				<th>Status</th>
				<th>Option</th>
			</tr>
		</thead>
	</table>

	<!-- This Form is for adding new Program  -->
	<form id="program-add-form" method="post" class="form-horizontal well" style="display: none;">

		<div class="form-group">
			<label class="col-md-3 control-label">Program name</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="program_name" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Program years</label>
			<div class="col-md-9">
				<input type="number" class="form-control" name="program_years" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Select Faculty</label>
			<div class="col-md-9">
				<select required class="form-control" id="f-faculty-box" name="faculty_id">
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Available</label>
			<div class="col-md-9">
				<label> Yes <input type="radio" value=1 name="status" checked required>
				</label> <label> No <input type="radio" value=0 name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="addProgram" class="btn btn-info btn-block">Save</button>
			</div>
		</div>
	</form>

	<!-- This Form is for Editing  Program  -->
	<form id="program-edit-form" method="post" class="form-horizontal well" style="display: none;">

		<div class="form-group">
			<label class="col-md-3 control-label">Program id</label>
			<div class="col-md-9">
				<input type="number" class="form-control" name="program_id" disabled="disabled" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Program name</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="program_name" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Program years</label>
			<div class="col-md-9">
				<input type="number" class="form-control" name="program_years" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Select Faculty</label>
			<div class="col-md-9">
				<select required class="form-control" id="fe-faculty-box" name="fe_faculty_id">
				</select>
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
				<button type="submit" id="editProgram" class="btn btn-info btn-block">Save</button>
			</div>
		</div>
	</form>


</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
<script>
    $(document).ready(function () {

        // for selecting faculty in faculty add model box
        load_faculty(event, "f-faculty-box");
        // for selecting faculty in faculty edit model box
        load_faculty(event, "fe-faculty-box");

        var url1 = window.context + "/ApiProgram/SearchProgram";
        var method1 = "POST";
        var url2 = window.context + "/ApiProgram/GetProgram";
        var method2 = "GET";
        var placeholder = "Program";
        select2Function(url1, url2, method1, method2, placeholder, loadProgramInformation);

        $("#modal-box-vew-all-program").click(function (event) {
            var url = window.context + "/ApiProgram/GetAllProgram";
            var method = "GET";
            var data = "";
            loadProgramInformation(url, method, data);
        });

    });

    // loading data into datatable
    function loadProgramInformation(url, method, data) {
        $('#view_program').DataTable({
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
                "data" : "program_id"
            }, {
                "data" : "program_name"
            }, {
                "data" : "program_years"
            }, {
                "data" : "faculty_id"
            }, {
                data : null,
                render : function (data, type, row) {
                    console.log(JSON.stringify(data));
                    var statusStatus = "";

                    if (data.status == 1) {
                        statusStatus = "Running";
                    } else if (data.status == 0) {
                        statusStatus = "Not Running";
                    }

                    return '' + statusStatus + '';
                },
            }, {
                data : null,
                render : function (data, type, row) {
                    return '<button class="btn btn-success editProg">Edit</button>';
                },
            } ]
        });

        // edit buttons on program row
        $(".editProg").click(function (event) {
            var table = $("#view_program").DataTable();
            var data = table.row($(this).parents('tr')).data();
            console.log(data);

            //$.when($.ajax(load_faculty("fe-faculty-box"))).done(function () {
            //   $("input[name=fe_faculty_id][value=" + data['faculty_id'] + "]").attr('selected', 'selected');
            //});

            // Populate the form fields
            $('#program-edit-form')
            .find('[name="program_id"]').val(data['program_id']).end()
            .find('[name="program_name"]').val(data['program_name']).end()
             .find('[name="program_years"]').val(data['program_years']).end();
            
            $("input[name=status][value=" + data['status'] + "]").prop('checked', true);

            //$("input[name=fe_faculty_id][value=" + data['faculty_id'] + "]").attr('selected', 'selected');
            //$('#fe-faculty-box').val(1).attr("selected", "selected");
            $('#fe-faculty-box option').each(function() {
            	//alert($(this).val());
  
                if($(this).val() == data['faculty_id']) {
                	//alert("inside");
                	//$('select option[value="1"]').attr("selected",true);
                	//$(this).prop("selected", true);
             
                }
            });
            bootbox.dialog({
                title : 'Edit the program',
                message : $('#program-edit-form'),
                show : false
            // We will show it manually later
            }).on('shown.bs.modal', function () {
                $('#program-edit-form').show() // Show the modal form
            }).on('hide.bs.modal', function (e) {
                // Bootbox will remove the modal (including the body which contains the login form)
                // after hiding the modal
                // Therefor, we need to backup the form
                $('#program-edit-form').hide().appendTo('body');
            }).modal('show');

        });
        // ---------------------------------------- edit btn function end --------------------
    }
    // ----------------------- datatable function edn --------------

    // modal popup for adding new Program data
    $("#add-program-modal").click(function (event) {

        load_faculty(event, "f-faculty-box");

        bootbox.dialog({
            title : 'Add New Program',
            message : $('#program-add-form'),
            show : false

        // We will show it manually later
        }).on('shown.bs.modal', function () {
            $('#program-add-form').show() // Show the modal form

        }).on('hide.bs.modal', function (e) {
            $('#program-add-form').hide().appendTo('body');
        }).modal('show');

    });

    $("#program-add-form").bootstrapValidator({
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
            program_name : {
                validators : {
                    stringLength : {
                        min : 3
                    },
                    notEmpty : {
                        message : "Please Enter Program Name"
                    }
                }
            },
            faculty_id : {
                validators : {
                    notEmpty : {
                        message : "Please Select Faculty"
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

    .on("success.form.bv", function (e) {

        // Prevent form submission
        e.preventDefault();

        var data = $('#program-add-form').serializeArray();
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
            url : window.context + "/ApiProgram/SaveProgram",
            method : "POST",
            dataType : 'json',
            data : formToJSON(),
			contentType : 'application/json',
            cache : true,
            success : function (data) {
                alert("Thanks for the submission!");
                $("#program-add-form")[0].reset();
            },
            error : function () {
                alert("Error...!!!");
            }
        });
        function formToJSON() {
			var data = JSON.stringify({
				"program_id" : $('#program-add-form').find('[name="program_id"]').val(),
				"program_name" : $('#program-add-form').find('[name="program_name"]').val(),
				"program_years" : $('#program-add-form').find('[name="program_years"]').val(),
				"status" : $('#program-add-form').find('[name="status"]:checked').val(),
				"faculty_id" : $('#program-add-form').find('[name="faculty_id"]').val(),
				

			});
			alert(data);
			return data;
		}
    });

    // form validator for program edit form
    $("#program-edit-form").bootstrapValidator({
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
            program_name : {
                validators : {
                    stringLength : {
                        min : 3
                    },
                    notEmpty : {
                        message : "Please Enter Program Name"
                    }
                }
            },
            faculty_id : {
                validators : {
                    notEmpty : {
                        message : "Please Select Faculty"
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

    .on("success.form.bv", function (e) {

        // Prevent form submission
        e.preventDefault();

        var data = $('#program-edit-form').serializeArray();
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
            url : window.context + "/ApiProgram/UpdateProgram",
            method : "PUT",
            dataType : 'json',
            data : formToJSON(),
			contentType : 'application/json',
            cache : true,
            success : function (data) {
                alert("Thanks for the submission!");
                $("#program-edit-form")[0].reset();
            },
            error : function () {
                alert("Error...!!!");
            }
        });
        function formToJSON() {
			var data = JSON.stringify({
				"program_id" : $('#program-edit-form').find('[name="program_id"]').val(),
				"program_name" : $('#program-edit-form').find('[name="program_name"]').val(),
				"program_years" : $('#program-edit-form').find('[name="program_years"]').val(),
				"status" : $('#program-edit-form').find('[name="status"]:checked').val(),
				"faculty_id" : $('#program-edit-form').find('[name="fe_faculty_id"]').val(),
				

			});
			alert(data);
			return data;
		}
    });
</script>