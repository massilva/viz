<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
	<%@include file='stylesheet.jsp' %>
	<%@include file='script.jsp' %>
	<!--Load the AJAX API-->
    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
    google.load("visualization", "1", {packages:["corechart"]});
    google.setOnLoadCallback(drawChart);
    
    function drawChart() {
   		var data = new google.visualization.DataTable();
		data.addColumn('string','ID'); //id bubble
      	data.addColumn('number','Closed'); //x
		data.addColumn('number','Opened'); //y
		data.addColumn('string','Label'); //name legend
		data.addColumn('number','Total');
      
      
      var repository_json = JSON.parse('${repository_json}');
      var labelIssues_json = JSON.parse('${labelIssues_json}');
      var i;

      for(i = 0; i<labelIssues_json.length; i++) {
       
          data.addRow( [labelIssues_json[i].label.substring(0, 3).toUpperCase(), 
                        labelIssues_json[i].closed,
                        labelIssues_json[i].opened, labelIssues_json[i].label,
                        labelIssues_json[i].closed + labelIssues_json[i].opened] );
          
      }

      var options = {
        title: 'CORRELATION BETWEEN CLOSED AND OPENED ISSUES GROUPING BY LABEL.\n'+
               'Repository id: '+repository_json.idGit,
        hAxis: {title: 'Closed'},
        vAxis: {title: 'Opened'},
        bubble: {textStyle: {fontSize: 8}},
        width: 900,
        height: 700,
        titleTextStyle: {fontSize: 14}
      };

      var chart = new google.visualization.BubbleChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }
    </script>
  </head>

<body>
	<%@include file='top.jsp' %>
	<div id="content" class="container">
		<div id="chart_div"></div>
	</div>
    <%@include file='footer.jsp' %>
</body>
</html>