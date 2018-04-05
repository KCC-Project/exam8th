<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" scope="application">${pageContext.request.contextPath}</c:set>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
          
<jsp:include page="../shared/header.jsp" />

<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="${cp}/home" ><span
				class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Setting</span></a></li>
					<li><a><span class="glyphicon glyphicon-education black">
					Admin</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<a><button type="button" class="btn btn-info pull-right"
						data-toggle="modal" id="modal-box-vew-all-admin">View
						All</button></a>
						<div class="pull-right" style="margin-right: 10px;">
					<button class="btn btn-info" id="add-admin-modal">Add Admin</button>
				</div>
				<div class="col-xs-3"
					style="margin-left: -34px; /* border: 2px solid black; */ height: 37px;">
					<div class="form-group">
						<div class="input-group" id="sel1div">
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


	<table id="view_admin"
		class="table table-hover table-striped table-responsive"
		cellspacing="0" width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Name</th>
				<th>Status</th>
				<th>Email</th>
				<th>Option</th>
			</tr>
		</thead>
	</table>

<!-- This Form is for adding new admin  -->
	<form id="admin-add-form" method="post" class="form-horizontal well" style="display: none;">

		<div class="form-group">
			<label class="col-md-3 control-label"> Username</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="admin_username" required autofocus/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Password</label>
			<div class="col-md-9">
				<input type="password" class="form-control" name="password" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Email</label>
			<div class="col-md-9">
				<input type="email" class="form-control" name="email" required />
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
				<button type="submit" id="addAdmin" class="btn btn-info btn-block">Save</button>
			</div>
		</div>
	</form>

	<!-- This Form is for Editing  Admin  -->
	<form id="admin-edit-form" method="post" class="form-horizontal well" style="display: none;">

		<div class="form-group">
			<label class="col-md-3 control-label">Admin id</label>
			<div class="col-md-9">
				<input type="number" class="form-control" name="admin_id" disabled="disabled" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Username</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="admin_username" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Password</label>
			<div class="col-md-9">
				<input type="password" class="form-control" name="password" required />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">Email</label>
			<div class="col-md-9">
				<input type="text" class="form-control" name="email" required />
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
				<button type="submit" id="editAdmin" class="btn btn-info btn-block">Update</button>
			</div>
		</div>
	</form>
	<!--=========================================================================================  -->
	
</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
<script>
	$(document).ready(function() {
		
		var url1=window.context + "/ApiAdmin/SearchAdmin";
		var method1="POST";
		var url2=window.context + "/ApiAdmin/GetAdmin";
		var method2="GET";
		var placeholder="Admin";
		select2Function(url1,url2,method1,method2,placeholder,loadAdminInformation);
		
	
		$("#modal-box-vew-all-admin").click(function(event) {
			var url = window.context + "/ApiAdmin/GetAllAdmin";
			var method = "GET";
			var data = "";
			loadAdminInformation(url, method, data);
		});
	});

	

	function loadAdminInformation(url, method, data) {
		$('#view_admin').DataTable({
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
				"data" : "admin_id"
			}, {
				"data" : "admin_username"
			}, {
				data : null,
				render : function(data, type, row) {
					console.log(JSON.stringify(data));
					var statusStatus = "";

					if (data.status == 1) {
						statusStatus = "Active";
					} else if (data.status == 0) {
						statusStatus = "Not Active";
					}

					return '' + statusStatus + '';
				},
			},{
				"data" : "email"
			},
			{
				data : null,
				render : function(data, type, row) {
					return '<button class="btn btn-success editAdmin">Edit</button>';
				},
			} ]
		});
		// edit buttons on admin row
        $(".editAdmin").click(function (event) {
            var table = $("#view_admin").DataTable();
            var data = table.row($(this).parents('tr')).data();
            console.log(data);

            // Populate the form fields
            $('#admin-edit-form')
            .find('[name="admin_id"]').val(data['admin_id']).end()
            .find('[name="admin_username"]').val(data['admin_username']).end()
             .find('[name="email"]').val(data['email']).end()
             .find('[name="password"]').val(data['password']).end();
            
            $("input[name=status][value=" + data['status'] + "]").prop('checked', true);

            bootbox.dialog({
                title : 'Edit the Admin',
                message : $('#admin-edit-form'),
                show : false
            // We will show it manually later
            }).on('shown.bs.modal', function () {
                $('#admin-edit-form').show() // Show the modal form
            }).on('hide.bs.modal', function (e) {
                // Bootbox will remove the modal (including the body which contains the login form)
                // after hiding the modal
                // Therefor, we need to backup the form
                $('#admin-edit-form').hide().appendTo('body');
            }).modal('show');

        });
	}
	
	 // ----------------------- datatable function edn --------------

    // modal popup for adding new admin
    $("#add-admin-modal").click(function (event) {

        bootbox.dialog({
            title : 'Add New Admin',
            message : $('#admin-add-form'),
            show : false

        // We will show it manually later
        }).on('shown.bs.modal', function () {
            $('#admin-add-form').show() // Show the modal form

        }).on('hide.bs.modal', function (e) {
            $('#admin-add-form').hide().appendTo('body');
        }).modal('show');

    });
	 
    $("#admin-add-form").bootstrapValidator({
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
        	admin_username : {
                validators : {
                    stringLength : {
                        min : 3
                    },
                    notEmpty : {
                        message : "Please Enter Admin Username"
                    }
                }
            },
            password : {
                validators : {
                    stringLength : {
                        min : 8
                    },
                    notEmpty : {
                        message : "Please Enter Minimum 8 Character Password"
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

    }).on("success.form.bv", function (e) {

        // Prevent form submission
        e.preventDefault();
   
        $.ajax({
            url : window.context + "/ApiAdmin/SaveAdmin",
            method : "POST",
            dataType : 'json',
            data : formToJSON(),
			contentType : 'application/json',
            cache : true,
            success : function (data) {
                alert("Thanks for the submission!");
                $("#admin-add-form")[0].reset();
            },
            error : function () {
                alert("Error...!!!");
            }
        }); 
        function formToJSON() {
			var data = JSON.stringify({				
				"admin_username" : $('#admin-add-form').find('[name="admin_username"]').val(),
				"password" : $('#admin-add-form').find('[name="password"]').val(),
				"email" : $('#admin-add-form').find('[name="email"]').val(),
				"status" : $('#admin-add-form').find('[name="status"]:checked').val(),
			});
			//alert(data);
			return data;
		}
    });
    
    //for update admin 
    $("#admin-edit-form").bootstrapValidator({
        feedbackIcons : {
            valid : "glyphicon glyphicon-ok",
            invalid : "glyphicon glyphicon-remove",
            validating : "glyphicon glyphicon-refresh"
        },
        fields : {
        	admin_username : {
                validators : {
                    stringLength : {
                        min : 3
                    },
                    notEmpty : {
                        message : "Please Enter Admin Username"
                    }
                }
            },
            password : {
                validators : {
                    stringLength : {
                        min : 8
                    },
                    notEmpty : {
                        message : "Please Enter Minimum 8 Character Password"
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

    }) .on("success.form.bv", function (e) {

        // Prevent form submission
        e.preventDefault();

    	
        $.ajax({
            url : window.context + "/ApiAdmin/UpdateAdmin",
            method : "PUT",
            dataType : 'json',
            data : formToJSON(),
			contentType : 'application/json',
            cache : true,
            success : function (data) {
                alert("Thanks for the submission!");
                $("#admin-edit-form")[0].reset();
                $('#view_admin').DataTable().ajax.reload();
            },
            error : function () {
                alert("Error...!!!");
            }
        }); 
        function formToJSON() {
			var data = JSON.stringify({
				"admin_id" : $('#admin-edit-form').find('[name="admin_id"]').val(),
				"admin_username" : $('#admin-edit-form').find('[name="admin_username"]').val(),
				"password" : $('#admin-edit-form').find('[name="password"]').val(),
				"email" : $('#admin-edit-form').find('[name="email"]').val(),
				"status" : $('#admin-edit-form').find('[name="status"]:checked').val(),
				

			});
			//alert(data);
			return data;
		}
    });
</script>