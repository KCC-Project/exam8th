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
		<li><a><span class="glyphicon glyphicon-education black"> Faculty</span></a></li>

	</ol>

	<div class="pull-right">
		<button class="btn btn-info" id="add-faculty-modal">Add Faculty</button>
	</div>

	<table id="view_faculty" class="table table-hover table-striped table-responsive" cellspacing="0"
		width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Faculty_name</th>
				<th>Status</th>
				<th>Option</th>
			</tr>
		</thead>
	</table>

	<div class="modal fade" id="faculty-edit-modal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Edit Faculty</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<h4 class="faculty_id">Id: {id} - {faculty_name}</h4>
					</div>

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
								<input name="s_subject_name" placeholder="Enter Subject Name" class="form-control" type="text">
							</div>
						</div>

						<div class="col-sm-4">
							<label class=" control-label">Subject Code</label>
							<div class="input-group">
								<input name="s_subject_code" placeholder="Enter Subject code" class="form-control" type="text">
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
				<br>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-success" id="updateClicked" data-dismiss="modal">Update</button>
				</div>
			</div>
		</div>
	</div>

	<form id="faculty-add-form" method="post" class="form-horizontal well" style="display: none;">

		<div class="form-group">
			<label class="col-md-3 control-label">Faculty name</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="faculty_name" required />
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
				<button type="submit" id="addFaculty" class="btn btn-info btn-block">Save</button>
			</div>
		</div>
	</form>

	<form id="faculty-edit-form" method="post" class="form-horizontal well" style="display: none;">
		<div class="form-group">
			<label class="col-md-3 control-label">Faculty id</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="faculty_id" disabled="disabled" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Faculty name</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="faculty_name" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Available</label>
			<div class="col-md-9">
				<label> Yes <input type="radio" value=0 name="status" checked required>
				</label> <label> No <input type="radio" value=1 name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="editFaculty" class="btn btn-info btn-block">Update</button>
			</div>
		</div>
	</form>


</div>

</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
<script>
var table;   
$(document).ready(function () {

        var url = window.context + "/ApiFaculty/GetAllFaculty";
        var method = "GET";
        var data = "";
        loadFacultyInformation(url, method, data);

        // loading data into datatable
        function loadFacultyInformation(url, method, data) {
        table=    $('#view_faculty').DataTable({
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
                    "data" : "faculty_id"
                }, {
                    "data" : "faculty_name"
                }, {
                    "data" : "status"
                }, {
                    data : null,
                    render : function (data, type, row) {
                        return '<button class="btn btn-success editFac">Edit</button>';
                    },
                } ]
            });

            // edit buttons on subjects row
            $(".editFac").click(function (event) {
                var table = $("#view_faculty").DataTable();
                var data = table.row($(this).parents('tr')).data();
                console.log(data);

                // Populate the form fields
                $('#faculty-edit-form').find('[name="faculty_id"]').val(data['faculty_id']).end().find('[name="faculty_name"]').val(data['faculty_name']).end()
                $("input[name=status][value=" + data['status'] + "]").prop('checked', true);

                //$("input[name=program_id][value=" + data['program_id'] + "]").attr('selected', 'selected');

                bootbox.dialog({
                    title : 'Edit the Faculty',
                    message : $('#faculty-edit-form'),
                    show : false
                // We will show it manually later
                }).on('shown.bs.modal', function () {
                    $('#faculty-edit-form').show() // Show the modal form
                }).on('hide.bs.modal', function (e) {
                    // Bootbox will remove the modal (including the body which contains the login form)
                    // after hiding the modal
                    // Therefor, we need to backup the form
                    $('#faculty-edit-form').hide().appendTo('body');
                }).modal('show');

            });
            // ---------------------------------------- edit btn function end --------------------

        }
        // ----------------------- datatable function edn --------------

    });
    // ------------------------------- document.ready end --------------------------------

    // modal popup for adding new faculty data
    $("#add-faculty-modal").click(function (event) {
        bootbox.dialog({
            title : 'Add New Faculty',
            message : $('#faculty-add-form'),
            show : false

        // We will show it manually later
        }).on('shown.bs.modal', function () {
            $('#faculty-add-form').show() // Show the modal form

        }).on('hide.bs.modal', function (e) {
            $('#faculty-add-form').hide().appendTo('body');
        }).modal('show');

    });
    // --------------------------------

    // form validator for add-faculty form
    $("#faculty-add-form").bootstrapValidator({
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
            faculty_name : {
                validators : {
                    stringLength : {
                        min : 3
                    },
                    notEmpty : {
                        message : "Please Enter Faculty Name"
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

        var data = $('#faculty-add-form').serializeArray();
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
            url : window.context + "/ApiFaculty/SaveFaculty",
            method : "POST",
            dataType : 'json',
        	contentType : 'application/json',
        	data : formToJSON(),
            cache : true,
            success : function (data) {
                alert("Thanks for the submission!");
                $("#faculty-add-form")[0].reset();
                $('#view_faculty').DataTable().ajax.reload();
           
             
            },
            error : function () {
                alert("Error...!!!");
            }
        });
        function formToJSON() {
			var data = JSON.stringify({
				"faculty_id" : $('#faculty-add-form').find('[name="faculty_id"]').val(),
				"faculty_name" : $('#faculty-add-form').find('[name="faculty_name"]').val(),
				"status" : $('#faculty-add-form').find('[name="status"]:checked').val(),
				

			});
			alert(data);
			return data;
		}
    });

    // form validator for edit-faculty form
    $("#faculty-edit-form").bootstrapValidator({
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
            faculty_name : {
                validators : {
                    stringLength : {
                        min : 3
                    },
                    notEmpty : {
                        message : "Please Enter Faculty Name"
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

        var data = $('#faculty-edit-form').serializeArray();
        console.log(data);

        $.ajax({
            url : window.context + "/ApiFaculty/UpdateFaculty",
            method : "PUT",
            dataType : 'json',
            contentType : 'application/json',
        	data : formToJSON(),
            cache : true,
            success : function (data) {
            	
                alert("Thanks for the submission!");
                $("#faculty-edit-form")[0].reset();
                $('#view_faculty').DataTable().ajax.reload();
              
            },
            error : function () {
                alert("Error...!!!");
            }
        });
        function formToJSON() {
			var data = JSON.stringify({
				"faculty_id" : $('#faculty-edit-form').find('[name="faculty_id"]').val(),
				"faculty_name" : $('#faculty-edit-form').find('[name="faculty_name"]').val(),
				"status" : $('#faculty-edit-form').find('[name="status"]:checked').val(),
				

			});
			alert(data);
			return data;
		}
    });
</script>