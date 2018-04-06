<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" scope="application">${pageContext.request.contextPath}</c:set>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${ empty sessionScope.adminUserName }">
	<c:redirect url="/index " />
</c:if>

<jsp:include page="../shared/header.jsp" />

<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span
				class="glyphicon glyphicon-home"> Home</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Setting</span></a></li>
		<li><a><span class="glyphicon glyphicon-education black">
					Parents mode</span></a></li>

	</ol>
	<!--=============================================Main Containt===============================  -->
	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">



				<div>
					<h3 style="color: #3c8dbc">

						<center>Parents Request For Student Achievements And
							Reports</center>

					</h3>
				</div>
			</div>
		</div>
	</div>
	<br>

	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; height: 35px;">
				<div>
					<h3 style="color: #3c8dbc">
						<label class="switch pull-right" style="margin-top: -3px">
							<input type="checkbox" checked> <span
							class="slider round" onclick="loadExamInfoDataTemp();"></span>
						</label>
						<center id="newHeading"></center>
					</h3>
				</div>
			</div>
		</div>
	</div>

<div class="table table-responsive">
	<table class="table table-hover table-striped" id="parents_mode" width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Student Name</th>
				<th>Parent Name</th>
				<th>Relation</th>
				<th>Parents Number</th>
				<th>Status</th>
				<th>Permission</th>
			</tr>
		</thead>
	</table>
</div>

</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
<script>
	function permissionNotGrantedList() {

		var url = window.context + "/ApiStudentParent/GetStudentParentByStatus/0";
		var method = "GET";
		var data = "";
		loadAllParentsRequest(url, method, data);

	}

	function permissionGrantedList() {

		var url = window.context + "/ApiStudentParent/GetStudentParentByStatus/1";
		var method = "GET";
		var data = "";
		loadAllParentsRequest(url, method, data);

	}
	$(document).ready(function() {

		var url = window.context + "/ApiStudentParent/GetStudentParentByStatus/0";
		var method = "GET";
		var data = "";
		var from="newrequest";
		loadAllParentsRequest(url, method, data,from);
		$("#newHeading").html("<center><b>New Request</b></center>");

	});

	var count = 2;
	function loadExamInfoDataTemp() {
		var status;
		var from;
		if (count % 2 === 0) {

			status = 1;

			$("#newHeading").html("<center><b>Granted List</b></center>");
			
			from="not";

		} else {

			status = 0;

			$("#newHeading").html("<center><b>New Request</b></center>");
			
			from="newrequest";
		}

		var url = window.context + "/ApiStudentParent/GetStudentParentByStatus/" + status;
		var method = "GET";
		var data = "";
		loadAllParentsRequest(url, method, data,from);

		count++;
	}

	function loadAllParentsRequest(url, method, data,from) {
		var table = $('#parents_mode').DataTable({
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

			},
			"initComplete" : function(settings, json) {

				//parent_badge
				if (from=="newrequest") {
					if (table.data().count() != 0) {
						alert("Total number of new request = " + table.data().count());
					}
				}
			},

			"columns" : [ {
				"data" : "sp_info_id"
			}, {
				data : null,
				render : function(data, type, row) {
					console.log(JSON.stringify(data));

					return data.student_id.first_name + " " + data.student_id.middle_name + " " + data.student_id.last_name;
				},
			}, {
				"data" : "fullname"
			}, {
				"data" : "relation"
			}, {
				"data" : "primary_contact"
			}, {
				data : null,
				render : function(data, type, row) {

					if (data.status == 1) {
						return '  <button type="button" class="btn btn-primary">Granted</button>';
					} else {
						return '  <button type="button" class="btn btn-warning">Not Granted</button>';
					}

				},
			}, {
				data : null,
				render : function(data, type, row) {
					return '<button class="btn btn-primary editGrant">Grant</button>&nbsp;&nbsp;<button class="btn btn-success editNo">Delete</button>';
				},
			} ]
		});
		$("#parents_mode").on('click', '.editGrant', function(event) {
			var table = $("#parents_mode").DataTable();
			var data = table.row($(this).parents('tr')).data();

			var id = data.sp_info_id;
			var url = window.context + "/ApiStudentParent/UpdateStudentParentById/" + id;
			var method = "PUT";
			var data = "";

			RequestStatus(data, method, url, permissionNotGrantedList, "Permission Granted");
		});
		$("#parents_mode").on('click', '.editNo', function(event) {
			var table = $("#parents_mode").DataTable();
			var data = table.row($(this).parents('tr')).data();

			var id = data.sp_info_id;
			var url = window.context + "/ApiStudentParent/DeleteStudentParent/" + id;
			var method = "DELETE";
			var data = "";

			RequestStatus(data, method, url, permissionGrantedList, "Request Invalid So Request Delete Sucessfull");

		});
	}

	function RequestStatus(data, method, url, fxn, msg) {

		$.ajax({
			url : url,
			method : method,
			data : data,
			cache : true,
			success : function(data) {
				alert(msg);
				$('#parents_mode').DataTable().ajax.reload();
				fxn();
			},

		});

	}
</script>