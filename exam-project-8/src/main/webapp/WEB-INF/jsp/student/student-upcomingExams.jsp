<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	isELIgnored="false"%>


<jsp:include page="../student/student-header.jsp" />

<!-- Content Area -->
<div id="page-content-wrapper">

	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span class="glyphicon glyphicon-home"> Home </span></a></li>
		<li><a><span class="glyphicon glyphicon-user black"> Upcoming Exams </span></a></li>
	</ol>


	<table id="upcoming-exam" class="table table-hover table-striped table-responsive" cellspacing="0"
		width="100%">
		<thead>
			<tr class="info">

				<th>Id</th>
				<th>Subject Name</th>
				<th>Exam Type</th>
				<th>Program/Semester</th>
				<th>Date</th>
				<th>F.M</th>
				<th>P.M</th>
				<th>Start Time</th>
				<th>End Time</th>
			</tr>
		</thead>
	</table>


</div>
<script>
    $(document).ready(function () {
        //search from student_exam table, (session.student_id)
        // main thing here in upcoming exam is we have to display all the data, whose date is greater than current date
        var data = {
            "s_id" : 1,
        };
        var url = window.context + "/ApiStudentsExams/SearchStudentExams";
        var method = "POST";
        loadUpcomingExamInformation(url, method, data);

    });

    function loadUpcomingExamInformation(url, method, data) {
        $('#view_exam').DataTable({
            destroy : true,
            paging : true,
            searching : true,
            "processing" : true,
            "serverSide" : false,
            "order" : [ [ 0, "desc" ] ],
            "ajax" : {
                "url" : url,
                "type" : method,
                "data" : data,
                "dataSrc" : "",
                "dataType" : "json",
                "async" : false
            },
            "columns" : [ {
                "data" : "exam_id"
            }, {
                data : null,
                render : function (data, type, row) {
                    return subjectname;
                },
            }, {
                data : null,
                render : function (data, type, row) {
                    return examTypeName;
                },
            }, {
                data : null,
                render : function (data, type, row) {
                    return programeName + ' / ' + semesterNo;
                },
            }, {
                "data" : "exam_date"
            }, {
                "data" : "full_marks"
            }, {
                "data" : "pass_marks"
            }, {
                "data" : "time_from"
            }, {
                "data" : "time_to"
            } ]
        });

    }
</script>