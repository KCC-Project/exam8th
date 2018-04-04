<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ empty sessionScope.studentUserName }">
	<c:redirect url="/index " />
</c:if>
<jsp:include page="../student/student-header.jsp" />

<!-- Content Area -->
<div id="page-content-wrapper">

	<ol class="breadcrumb">
		<li><a href="${cp}/home"><span
				class="glyphicon glyphicon-home"> Home </span></a></li>
		<li><a><span class="glyphicon glyphicon-user black">
					View Subject </span></a></li>
	</ol>


	<div class="box box-default with-border">
		<div>
			<div style="margin: 0px; padding-left: 20px; height: 35px;">
				<div class="col-xs-12 text-center" id="sembtn"></div>
			</div>
		</div>
	</div>

	<table id="view_subject" class="table table-hover table-striped"
		cellspacing="0" width="100%">
		<thead>
			<tr class="info">
				<th>#</th>
				<th>Subject name</th>
				<th>Code</th>
				<th>Credit</th>
				<th>Marks</th>
				<th>Syllabus_file</th>
				<th>Program</th>
				<th>Semester</th>
			</tr>
		</thead>
	</table>



	<script>
     

	
        $(document).ready(function () {
        	
      
        	var url = window.context + "/ApiStudent/GetStudent/"+${ studentID };
			var method = "GET";
			var data="";
		
			loadStudentInfo(url,method,data);
			
			function loadStudentInfo(url, method, data) {
				var count;
				var current_sem;
				var student_id;
				var programId;

				$.ajax({
					url : url,
					method : method,
					dataType : 'json',
					contentType : 'application/json',
					data : data,
					cache : true,
					success : function(data) {
						$("#sembtn").empty();
						var content = '';
						for (var i = 1; i <= data[0].current_semester; i++) {

							content += '<input style="margin-right:10px;" id='+i+'  ids='+data[0].s_id+' type="button"  class="btn btn-default btnSelected" values='
																							+ data[0].s_id
																							+ ' value='
																							+i
																							+ '>';
							count = i;

						}
						current_sem = data[0].current_semester;
						student_id = data[0].s_id;

						studentFullName = data[0].first_name + " " + data[0].middle_name + " " + data[0].last_name;

						$("#sembtn").append(content);
						var color = current_sem;
						//this if is to show defult current semester result
						$.ajax({
							url : window.context + "/ApiStudentsProgram/GetStudentsProgramByStudentId/"+${ studentID },
							method : method,
							dataType : 'json',
							contentType : 'application/json',
							data : data,
							cache : true,
							async:false,
							success : function(data) {
								programId=data[0].program.program_id;
					},
					error : function() {
						alert("Error...!!!");
					}
				});
			
							if (count == current_sem) {
							$('#' + current_sem + '').addClass('btn btn-primary');
							var url = window.context + "/ApiSubject/GetSubjectByParameters/"+programId+"/"+ ${ currentSemester };
							var method = "GET";
							var data = "";
							loadSubjectInformation(url, method, data);
						} 

						//this is run when we click specific semester and change color of button also
						$("input").click(function(event) {
							studentId = $(this).attr('ids');
							var idForSelectionColor = event.target.id;
							$('#' + idForSelectionColor + '').addClass('btn btn-primary');
							var temp = color;
							if (temp != 0) {
								$('#' + temp + '').removeClass('btn btn-primary');
								$('#' + temp + '').addClass('btn btn-default');
							}
							color = idForSelectionColor;
							//alert("idForSelectionColor = "+idForSelectionColor);
							//alert(" temp= "+temp);
							var semesterNo = event.target.value;
							var url = window.context + "/ApiSubject/GetSubjectByParameters/"+programId+"/"+ semesterNo;
					
							var method = "GET";
							var data = "";
							loadSubjectInformation(url, method, data);

						});
					},
					error : function() {
						alert("Error...!!!");
					}
				});
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
              "async":true
                },
                "columns" : [ {
    				data : null,
    				render : function(data, type, row,i) {
    					return (i.row)+1;
    				},
    			}, {
                    "data" : "subject_name"
                }, {
                    "data" : "subject_code"
                }, {
                    data : null,
                    render : function (data, type, row) {
                        console.log("return data = "+JSON.stringify(data));
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
                    "data" : null,
                    render : function (data, type, row) {
                    	//alert(data.program.program_name);
                        return '<a href='+ window.context +'/program/'+data+'>' + data.program.program_name + '</a>';
                    },
                }, {
                    "data" : "semester_no"
                } ]
            });

  

        }
        });

    </script>


</div>
