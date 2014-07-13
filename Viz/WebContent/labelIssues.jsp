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

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      
      function drawChart() {
    	   //var jsonObject = JSON.parse(jsonText);
        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        
        var json = JSON.parse('${labelIssues_json}');
        var repository_json = JSON.parse('${repository_json}');
        var i;
        
        for(i = 0; i<json.length; i++) {
        	
            data.addRow( [json[i].label, json[i].quantity] );
            
        }
        
        // Set chart options
        var options = {'title':'TYPES OF ISSUES.\n Repository id: '+repository_json.idGit,
                       'width':900,
                       'height':800,
                       'is3D': true};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);

      }
    </script>
  </head>

<body>
	<%@include file='top.jsp' %>
	<div id="content" class="container">
		<div class="row" id="top">
			<div class="col-md-12" style="margin-top: 0px;">
           		<section class="col-md-12">
           			<div id="chart_div"></div>
				</section>
			</div>
		</div>
	</div>
    <%@include file='footer.jsp' %>
</body>
</html>