<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%
	String dados = (String) session.getAttribute("dados");
%>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="js/functions.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "treemap" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		// Create and populate the data table.
		var data = google.visualization.arrayToDataTable(
<%=dados%>
	);

		// Create and draw the visualization.
		var tree = new google.visualization.TreeMap(document
				.getElementById('chart_div'));
		tree.draw(data, {
			minColor : '#f00',
			midColor : '#ddd',
			maxColor : '#0d0',
			headerHeight : 15,
			fontColor : 'black',
			showScale : true
		});
	}
</script>
</head>

<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="alert alert-info">
				<span class="glyphicon glyphicon-thumbs-up"></span> <strong>DICA:</strong>
				Clique com o botão esquerdo do mouse para visualizar os nós filhos<br />
				Clique com o botão direito do mouse para voltar ao nó pai do nó
				selecionado.
			</div>
			<div id="chart_div" style="width: 900px; height: 500px;"></div>
		</div>
	</div>
</body>
</html>