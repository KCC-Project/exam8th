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
		<li><a href="${cp}/home" ><span
				class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Exam</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Exam-Type</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right"
						data-toggle="modal" id="modal-box-vew-all-exam-type">View
						All</button></a>
				<div class="pull-right"
					style="padding-left: 20px; margin-right: 10px;">
					<button class="btn btn-info" id="add-exam_type-modal">Add
						Exam Type</button>
				</div>
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


<div class="table table-responsive">
	<table class="table table-hover table-striped" id="view_exam_Type" width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Name</th>
				<th>Status</th>
				<th>Option</th>
			</tr>
		</thead>
	</table>
</div>

	<form id="examType-add-form" method="post" class="form-horizontal well"
		style="display: none;">

		<div class="form-group">
			<label class="col-md-4 control-label">Exam Type name</label>
			<div class="col-md-8">
				<input type="text" class="form-control" name="exam_type_name"
					required autofocus/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">Active</label>
			<div class="col-md-8">
				<label> Yes <input type="radio" value="1" name="status"
					checked required>
				</label> <label> No <input type="radio" value="0" name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="addExamType"
					class="btn btn-info btn-block">Save</button>
			</div>
		</div>
	</form>
	<form id="examType-edit-form" method="post"
		class="form-horizontal well" style="display: none;">
		<div class="form-group">
			<label class="col-md-3 control-label">Exam Type Id</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="exam_type_id"
					disabled="disabled" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Exam Type Name</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="exam_type_name"
					required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Active</label>
			<div class="col-md-9">
				<label> Yes <input type="radio" value=1 name="status"
					checked required>
				</label> <label> No <input type="radio" value=0 name="status">
				</label>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-12">
				<button type="submit" id="editExamType"
					class="btn btn-info btn-block">Update</button>
			</div>
		</div>
	</form>
	<!--=========================================================================================  -->

</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
<script>
	//modal popup for adding new faculty data
	$("#add-exam_type-modal").click(function(event) {
		bootbox.dialog({
			title : 'Add New Exam Type',
			message : $('#examType-add-form'),
			show : false

		// We will show it manually later
		}).on('shown.bs.modal', function() {
			$('#examType-add-form').show() // Show the modal form

		}).on('hide.bs.modal', function(e) {
			$('#examType-add-form').hide().appendTo('body');
		}).modal('show');

	});

	//form validator for add-Exam type form
	$("#examType-add-form").bootstrapValidator({
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphicon-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			exam_type_name : {
				validators : {
					stringLength : {
						min : 3
					},
					notEmpty : {
						message : "Please Enter Exam Type Name"
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
	.on("success.form.bv", function(e) {

		// Prevent form submission
		e.preventDefault();

		$.ajax({
			url : window.context + "/ApiExam_type/SaveExam_type",
			method : "POST",
			dataType : 'json',
			contentType : 'application/json',
			data : formToJSON(),
			cache : true,
			success : function(data) {
				alert("Thanks for the submission!");
				$("#examType-add-form")[0].reset();
				$('#view_exam_Type').DataTable().ajax.reload();

			},
			error : function() {
				alert("Error...!!!");
			}
		});
		function formToJSON() {
			var data = JSON.stringify({
				"type_name" : $('#examType-add-form').find('[name="exam_type_name"]').val(),
				"status" : $('#examType-add-form').find('[name="status"]:checked').val(),

			});
			//alert(data);
			return data;
		}
	});
	// --------------------------------
	//form validator for edit-Exam type form
	$("#examType-edit-form").bootstrapValidator({
		feedbackIcons : {
			valid : "glyphicon glyphicon-ok",
			invalid : "glyphicon glyphicon-remove",
			validating : "glyphicon glyphicon-refresh"
		},
		fields : {
			exam_type_name : {
				validators : {
					stringLength : {
						min : 3
					},
					notEmpty : {
						message : "Please Enter Exam Type Name"
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
	.on("success.form.bv", function(e) {

		// Prevent form submission
		e.preventDefault();

		$.ajax({
			url : window.context + "/ApiExam_type/UpdateExam_type",
			method : "PUT",
			dataType : 'json',
			contentType : 'application/json',
			data : formToJSON(),
			cache : true,
			success : function(data) {
				alert("Thanks for the submission!");
				$("#examType-edit-form")[0].reset();
				$('#view_exam_Type').DataTable().ajax.reload();

			},
			error : function() {
				alert("Error...!!!");
			}
		});
		function formToJSON() {
			var data = JSON.stringify({
				"exam_type_id" : $('#examType-edit-form').find('[name="exam_type_id"]').val(),
				"type_name" : $('#examType-edit-form').find('[name="exam_type_name"]').val(),
				"status" : $('#examType-edit-form').find('[name="status"]:checked').val(),

			});
			//alert(data);
			return data;
		}
	});
	/*------------------------------------------------------------------------------  */
	$(document).ready(

	function() {

		var url1 = window.context + "/ApiExam_type/SearchExam-Type";
		var method1 = "POST";
		var url2 = window.context + "/ApiExam_type/GetExam_type";
		var method2 = "GET";
		var placeholder = "Exam Type";
		select2Function(url1, url2, method1, method2, placeholder, loadExamTypeInformation);

		$("#modal-box-vew-all-exam-type").click(function(event) {
			var url = window.context + "/ApiExam_type/GetAllExam_type";
			var method = "GET";
			var data = "";
			loadExamTypeInformation(url, method, data);
		});
	});

	function loadExamTypeInformation(url, method, data) {
		//alert(url+"  "+method+  +data);
		$('#view_exam_Type').DataTable({
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
				"data" : "exam_type_id"
			}, {
				"data" : "type_name"
			}, {
				data : null,
				render : function(data, type, row) {
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
				render : function(data, type, row) {
					return '<button class="btn btn-success editBtns">Edit</button>';
				},
			} ]
		});

		// edit buttons on subjects row
		$("#view_exam_Type").on('click','.editBtns',function(event) {
			var table = $("#view_exam_Type").DataTable();
			var data = table.row($(this).parents('tr')).data();
			console.log(data);

			// Populate the form fields
			$('#examType-edit-form').find('[name="exam_type_id"]').val(data['exam_type_id']).end().find('[name="exam_type_name"]').val(data['type_name']).end()
			$("input[name=status][value=" + data['status'] + "]").prop('checked', true);

			//$("input[name=program_id][value=" + data['program_id'] + "]").attr('selected', 'selected');

			bootbox.dialog({
				title : 'Edit the Exam Type',
				message : $('#examType-edit-form'),
				show : false
			// We will show it manually later
			}).on('shown.bs.modal', function() {
				$('#examType-edit-form').show() // Show the modal form
			}).on('hide.bs.modal', function(e) {
				// Bootbox will remove the modal (including the body which contains the login form)
				// after hiding the modal
				// Therefor, we need to backup the form
				$('#examType-edit-form').hide().appendTo('body');
			}).modal('show');

		});
	}
</script>