

<!-- Side Menu -->
    <div id="side-menu" class="sidenav">
      <ul>
        <li class="link active">
          <a href="${cp}/home" class="main-color-bg">
            <span class="glyphicon glyphicon-th" aria-hidden="true"></span>
            <span class="hidden-xs hidden-sm">Dashboard</span>
          </a>
        </li>

        <li class="link">
          <a href="#collapse-post" data-toggle="collapse"  aria-controls="collapse-post">
            <span class="glyphicon glyphicon-list-alt"></span>
            <span class="hidden-xs hidden-sm">Students</span>
            <span class="caret pull-right"></span>
          </a>
          <ul class="collapse collapseable" id="collapse-post">
          	<li><a href="${cp }/student/view" id="view-student">View</a></li>
            <li><a href="${cp }/student/addNew" id="add-student">Add New</a></li>
          </ul>
        </li>
        <li class="link">
          <a href="#collapse-comments" data-toggle="collapse"  aria-controls="collapse-comments">
            <span class="glyphicon glyphicon-pencil"></span>
            <span class="hidden-xs hidden-sm">Subject</span>
            <span class="caret pull-right"></span>
          </a>
          <ul class="collapse collapseable" id="collapse-comments">
            <li><a href="${cp }/subject/view" id="view-subject">View</a></li>
            <li><a href="${cp }/subject/addNew" id="add-subject">Add New</a></li>
          </ul>
        </li>

         <li class="link">
          <a href="#collapse-manage" data-toggle="collapse"  aria-controls="collapse-manage">
            <span class="glyphicon glyphicon-pencil"></span>
            <span class="hidden-xs hidden-sm">Education</span>
            <span class="caret pull-right"></span>
          </a>
          <ul class="collapse collapseable" id="collapse-manage">
            <li><a href="${cp }/faculty" id="Semester">Faculty</a></li>
            <li><a href="${cp }/program" id="courses">Program</a></li>
          </ul>
        </li>
        <!--====================================================================================  -->
          <li class="link">
          <a href="#collapse-exam" data-toggle="collapse"  aria-controls="collapse-exam">
            <span class="glyphicon glyphicon-pencil"></span>
            <span class="hidden-xs hidden-sm">Exam</span>
            <span class="caret pull-right"></span>
          </a>
          <ul class="collapse collapseable" id="collapse-exam">
            <li><a href="${cp }/addexam" id="addexam">Add Exam</a></li>
            <li><a href="${cp }/viewexam" id="manageexam">View Exam</a></li>
             <li><a href="${cp }/exam-type" id="exam-type">Exam-type</a></li>
             <li><a href="${cp }/student-exam" id="student-exam">Student-exam</a></li>
              <li><a href="${cp }/exam-routine" id="exam-routine">Routine</a></li>
              <li><a href="${cp }/viewResult" id="viewResult">View Result</a></li>
             
          </ul>
        </li>
        <!--====================================================================================  -->
           <!--====================================================================================  -->
          <li class="link">
          <a href="#collapse-report-graph" data-toggle="collapse"  aria-controls="collapse-exam">
            <span class="glyphicon glyphicon-pencil"></span>
            <span class="hidden-xs hidden-sm">Analysis</span>
            <span class="caret pull-right"></span>
          </a>
          <ul class="collapse collapseable" id="collapse-report-graph">
            <li><a href="${cp }/viewReportGraph" id="viewReportGraph">Graph</a></li>
            
          </ul>
        </li>
        <!--====================================================================================  -->
          <li class="link">
          <a href="#collapse-admin" data-toggle="collapse"  aria-controls="collapse-admin">
            <span class="glyphicon glyphicon-pencil"></span>
            <span class="hidden-xs hidden-sm">Setting</span>
            <span class="caret pull-right"></span>
          </a>
          <ul class="collapse collapseable" id="collapse-admin">
            <li><a href="${cp }/view-admin" id="view-admin">View-Admin</a></li>
             <li><a href="${cp }/parentsMode" id="view-admin">Parents Mode</a></li>
            
          </ul>
        </li>
      </ul>
    </div>
    <!-- -------------------------------------------------- -->