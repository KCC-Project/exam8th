<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

</div>

<script>
	
$(document).ready(function () {
	var scienceTotal;
	var managementTotal;
	var artsTotal;
	var lawTotal;
	
	//fail total
	var science_fail;
	var management_fail;
	var arts_fail;
	var law_fail;
	
$.ajax({
	url : window.context + "/ApiViewController/FailRatioCountView",
	method : "",
	dataType : 'json',
	contentType : 'application/json',
	data : "",
	cache : true,
	async:true,
	success : function(data) {
		
		console.log(JSON.stringify(data));
		
		scienceTotal=data[0].science_totalstudent
		managementTotal=data[0].management_totalstudent
		artsTotal=data[0].arts_totalstudent
		lawTotal=data[0].law_totalstudent
		
		science_fail = data[0].science_fail_totalstudent;
		management_fail = data[0].management_fail_totalstudent;
		arts_fail = data[0].arts_fail_totalstudent;
		law_fail = data[0].law_fail_totalstudent;
		
		maxFailSubject();
	
	},
error : function() {
alert("Error...!!!");
}
});





// Load google charts
google.charts.load('current', {
	'packages' : [ 'corechart' ]
});
google.charts.setOnLoadCallback(drawChartTotal);
google.charts.setOnLoadCallback(drawChartFail);

// Draw the chart and set the chart values
function drawChartTotal() {
	var data = google.visualization.arrayToDataTable([
			[ 'Task', 'Hours per Day' ], [ 'Science and Tech', scienceTotal ], [ 'Management', managementTotal ],
			[ 'Arts', artsTotal ], [ 'Law', lawTotal ] ]);

	// Optional; add a title and set the width and height of the chart
	
	
	var options = {
		'width' : 590,
		'height' : 300
		
	};

	// Display the chart inside the <div> element with id="piechart"
	var chart = new google.visualization.PieChart(document
			.getElementById('piechart1'));
	
	chart.draw(data, options);
}



function drawChartFail() {
	var data = google.visualization.arrayToDataTable([
			[ 'Task', 'Hours per Day' ], [ 'Science and Tech', (science_fail/scienceTotal)*100 ], [ 'Management', (management_fail/managementTotal)*100 ],
			[ 'Arts', (arts_fail/artsTotal)*100 ], [ 'Law', (law_fail/lawTotal)*100 ] ]);

	// Optional; add a title and set the width and height of the chart
	
	
	var options = {
		'width' : 480,
		'height' : 300
		
	};

	// Display the chart inside the <div> element with id="piechart"
	var chart = new google.visualization.PieChart(document
			.getElementById('piechart2'));
	
	chart.draw(data, options);
}

function drawChartMaxFailSubject() {
	var data = google.visualization.arrayToDataTable([
		[ 'Task', 'Hours per Day' ], [ 'Science and Tech', (science_fail/scienceTotal)*100 ], [ 'Management', (management_fail/managementTotal)*100 ],
		[ 'Arts', (arts_fail/artsTotal)*100 ], [ 'Law', (law_fail/lawTotal)*100 ] ]);

	alert("data = "+ data);
	// Optional; add a title and set the width and height of the chart
	
	
	var options = {
		'width' : 400,
		'height' : 300
		
	};

	// Display the chart inside the <div> element with id="piechart"
	var chart = new google.visualization.PieChart(document
			.getElementById('piechart3'));
	
	chart.draw(data, options);
}

function maxFailSubject(){
	var url = window.context + "/ApiViewController/MaxFailFubjectView";
	var method ="GET";
	var data ="";
	
	$('#max_fail_subject').DataTable({
		destroy : true,
		paging : true,
		searching : true,
		"processing" : true,
		"serverSide" : false,
		"order" : [ [ 0, "asc" ] ],
		"ajax" : {
			"url" : url,
			"type" : method,
			"data" : data,
			"dataSrc" : "",
			"dataType" : "json",
			"async" : true
		},
		"columns" : [ {
			data : null,
			render : function(data, type, row,i) {
				return (i.row)+1;
			},
		}, {
			data : null,
			render : function(data, type, row) {

				return data.subject_name;
			},
		}, {
			data : null,
			render : function(data, type, row) {
				return data.total_failed;
			},
		}, {
			data : null,
			render : function(data, type, row) {
				return data.program_name;
			},
		} ]
	});
	
};
});

</script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
</body>  

</html>