<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

</div>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
	// Load google charts
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	// Draw the chart and set the chart values
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ], [ 'Science and Tech', 100 ], [ 'Management', 80 ],
				[ 'Arts', 40 ], [ 'Education', 20 ], [ 'Law', 15 ] ]);

		// Optional; add a title and set the width and height of the chart
		
		
		var options = {
			'width' : 618,
			'height' : 300
			
		};

		// Display the chart inside the <div> element with id="piechart"
		var chart = new google.visualization.PieChart(document
				.getElementById('piechart1'));
		
		chart.draw(data, options);
	}
</script>


<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
	// Load google charts
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	// Draw the chart and set the chart values
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Task', 'Hours per Day' ], [ 'Science and Tech', 100 ], [ 'Management', 80 ],
				[ 'Arts', 40 ], [ 'Education', 20 ], [ 'Law', 15 ] ]);

		// Optional; add a title and set the width and height of the chart
		
		
		var options = {
			'width' : 420,
			'height' : 300
			
		};

		// Display the chart inside the <div> element with id="piechart"
		var chart = new google.visualization.PieChart(document
				.getElementById('piechart2'));
		
		chart.draw(data, options);
	}
</script>

</body>  

</html>