<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>


<jsp:include page="../student/student-header.jsp" />

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
					<form id="sub-form">
						<div class="form-group col-sm-6" style="margin-bottom: 0px;">
							<select required class="form-control" id="all-program-box" name="program_id">
							</select>
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
					</form>
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
		</tr>
	</thead>
</table>





<script>
    $(document).ready(function () {
        
        // set default student semester number here from session

        $.when($.ajax(load_all_program("all-program-box"))).done(function () {
            $("#all-program-box").append("<option value='' selected disabled>Select Program</option>");
        });

        $("#searchbtnClicked").click(function (event) {
           event.preventDefault(); 
           
           var a_program_id = $('#sub-form').find('[name="program_id"]').val();
           var semester_no = $('#sub-form').find('[name="s_semester_no"]').val();

           if (a_program_id != "") {
               var data = {
                   "programId" : a_program_id,
                   "semester_no" : semester_no
               };
               var url = window.context + "/ApiSubject/GetSubjectByParameters";
               var method = "POST";
               loadSubjectInformation(url, method, data);
           }
            
        });

        var url = window.context + "/ApiSubject/GetSubjectByParameters";
        var method = "POST";
        var data = {
            "programId" : 1,
            "semester_no" : 8
        };
        loadSubjectInformation(url, method, data);
    });

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
            } ]
        });
    }
</script>