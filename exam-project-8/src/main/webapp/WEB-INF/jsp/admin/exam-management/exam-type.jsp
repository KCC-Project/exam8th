<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="../shared/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <c:if test = "${ empty sessionScope.adminUserName }">
         <c:redirect url = "/index "/>
      </c:if>
          
<div id="page-content-wrapper">
	<ol class="breadcrumb">
		<li><a href="admin-dashboard.jsp"><span
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


	<table id="view_exam_Type"
		class="table table-hover table-striped table-responsive"
		cellspacing="0" width="100%">
		<thead>
			<tr class="info">
				<th>Id</th>
				<th>Name</th>
				<th>Status</th>
				<th>Option</th>
			</tr>
		</thead>
	</table>


	<!--=========================================================================================  -->
	
</div>
<!--=========================================================================================  -->
<jsp:include page="../shared/footer.jsp" />
<script>
	$(document).ready(function() {
		
		
		var url1=window.context + "/ApiExam_type/SearchExam-Type";
		var method1="POST";
		var url2= window.context + "/ApiExam_type/GetExam_type";
		var method2="GET";
		var placeholder="Exam Type";
		select2Function(url1,url2,method1,method2,placeholder,loadExamTypeInformation);
		
		
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
		$('#view_exam_Type tbody').on('click', '.editBtns', function() {
			var table = $("#view_exam_Type").DataTable();
			var data = table.row($(this).parents('tr')).data();
			console.log(data);
			alert(data['type_name'] + "' id is: " + data['exam_type_id']);
		});
	}
</script>