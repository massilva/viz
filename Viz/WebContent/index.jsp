<%@page import="java.util.Collections"%>
<%@page import="org.visminer.model.MetricValue"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualization</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/histogram.css">
<link rel="stylesheet" href="css/custom.css">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/functions.js"></script>
</head>
<body>
	<%@include file='top.jsp' %>
	<div class="container" id="content" style="margin-top: 80px;">
        <div class="row" id="top">
			<div class="col-md-12" style="margin-top: 0px;">
           		<div class="repository">LOCAL_REPOSITORY_PATH = '<%= request.getAttribute("LOCAL_REPOSITORY_PATH") %>'</div>
				<hr/>
				<section id="metrics" class="col-md-2">
					<header><strong>List of implemented metrics</strong></header>
					<ol class="listMetrics">
					<c:set var='locMetricValue' value="new MetricValue()"></c:set>
			        <c:forEach items="${metrics}" var="metric">
				        <li>${metric.name} - ${metric.description}</li>
				        <ul>
				        <c:if test="${metric.name == 'LOC'}">
				        	<c:set var="locMetricValue" scope="request" value="${metric.metricValues}"></c:set>
			        		<c:set var="metricName" scope="request" value="${metric.name}	"></c:set>
			        		<c:set var="metricDescription" scope="request" value="${metric.description}"></c:set>
			        	</c:if>
				        <%-- <c:forEach items="${metric.metricValues}" var="metricValue">
				        	<c:if test="${metricValue.file != null}">
				        		<li>${metricValue.file.path}</li>
				        	</c:if>
				        	<li>${metricValue.value}</li>
				        </c:forEach> --%>
				        </ul>
					</c:forEach>
					</ol>
					<a href='treemap.do' class="btn btn-default" target="_blank">
						<span class="glyphicon glyphicon-plus-sign"></span> TreeMap Example
					</a>
				</section>
				<section id="loc" class="col-md-10"></section>
           </div>
    	</div>
    </div>
    <%@include file='footer.jsp' %>
    <% 
    List<MetricValue> locMetricValue = (List<MetricValue>) request.getAttribute("locMetricValue");
    int greater = 0;
   	int [] metricsValues = new int[locMetricValue.size()];
    double [] values = new double[metricsValues.length];
    
    MetricValue mv = new MetricValue();
    PrintWriter writer = response.getWriter();
    
    for(MetricValue metricValue : locMetricValue){
    	//verifies that the value of metricValue is greater than the last value is set higher and the file exists because of the LOC TAG
    	if(metricValue.getValue() > greater && metricValue.getFile() != null){
			greater = metricValue.getValue();
   		}
    }
    
    writer.println("<script type='text/javascript' src='js/d3.v3.min.js'></script>");
   	String script = "<script>function histogram(){\n";
	script += "var greater = "+greater+";\n";
   	script += "var metricValue = [";
   	int i = 0;
   	for(MetricValue metricValue : locMetricValue){
   		values[i] = (metricValue.getValue() / greater);
   		script += metricValue.getValue();
   		if(i != locMetricValue.size() - 1)
   			script += ",";
   		i++;
   	}
   	script += "];\n";
   	script += "var values = []\n";
   	script += "for(var i = 0; i < metricValue.length; i++){\n"
   		   	 +"		values[i] = metricValue[i];\n"
   		   	 +"}\n";
   	script	+= "//A formatter for counts.\n"
		+"var formatCount = d3.format(',.0f');\n"
		+"var margin = {\n"
		+"	top : 10,\n"
		+"	right : 30,\n"
		+"	bottom : 30,\n"
		+"	left : 30\n"
		+"}, width = 960 - margin.left - margin.right, height = 500 - margin.top\n"
		+"		- margin.bottom;\n"
		+"var x = d3.scale.linear().domain([ 0, "+greater+"]).range([ 0, width ]);\n"
		+"\n"
		+"//Generate a histogram using twenty uniformly-spaced bins.\n"
		+"var data = d3.layout.histogram().bins(x.ticks(20))(values);\n"
		+"var y = d3.scale.linear().domain([ 0,d3.max(data, function(d) {\n"
		+"	return d.y;\n"
		+"}) ]).range([height, 0 ]);\n"
		+"\n"
		+"var xAxis = d3.svg.axis().scale(x).orient('bottom');\n"
		+"\n"
		+"var strong = document.createElement('strong');\n"
		+"var t = document.createTextNode('Histogram of Metric "+request.getAttribute("metricName")+" - "+request.getAttribute("metricDescription")+"');\n"
		+"strong.appendChild(t);\n"
		+"var loc = document.getElementById('loc');\n"
		+"loc.appendChild(strong);\n"
		+"var svg = d3.select('#loc').append('svg').attr('width',\n"
		+"	width + margin.left + margin.right).attr('height',\n"
		+"	height + margin.top + margin.bottom).append('g').attr(\n"
		+"	'transform',\n"
		+"	'translate(' + margin.left + ',' + margin.top + ')');\n"
		+"var bar = svg.selectAll('.bar').data(data).enter().append('g').attr(\n"
		+"'class', 'bar').attr('transform', function(d) {\n"
		+"		return 'translate(' + x(d.x) + ',' + y(d.y) + ')';\n"
		+"	});\n"
		+"bar.append('rect').attr('x', 1).attr('width', x(data[0].dx) - 1).attr(\n"
		+"'height', function(d) {\n"
		+"	return height - y(d.y);\n"
		+"});\n"
		+"bar.append('text').attr('dy', '.75em').attr('y', 6).attr('x',\n"
		+"	x(data[0].dx) / 2).attr('text-anchor', 'middle').text(\n"
		+"		function(d) {\n"
		+"			return formatCount(d.y);\n"
		+"		});\n"
		+"svg.append('g').attr('class', 'x axis').attr('transform',\n"
		+"	'translate(0,' + height + ')').call(xAxis);\n"
		+"}</script>";
   	writer.println(script);
   	%>
   	<script>$(document).ready(function(){ histogram(); });</script>
</body>
</html>