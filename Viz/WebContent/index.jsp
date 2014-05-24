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
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/d3.v3.min.js"></script>
<script type="text/javascript" src="js/functions.js"></script>
</head>
<body>
	<%@include file='top.jsp' %>
	<div class="container" id="content" style="margin-top: 80px;">
        <div class="row" id="top">
			<div class="col-md-12" style="margin-top: 0px;">
           		<div class="repository">LOCAL_REPOSITORY_PATH = '<%= request.getAttribute("LOCAL_REPOSITORY_PATH") %>'</div>
				<section id="metrics">
					<header>List of implemented metrics</header>
					<ol class="metrics">
					<c:set var='major' value="0" scope="request"></c:set>
			        <c:set var='locMetricValue' value="new MetricValue()"></c:set>
			        <c:forEach items="${metrics}" var="metric">
				        <li>${metric.name} - ${metric.description}</li>
				        <ul>
				        <c:if test="${metric.name == 'LOC'}">
				        	<c:set var="locMetricValue" scope="request" value="${metric.metricValues}"></c:set>
				        	<c:forEach items="${metric.metricValues}" var="metricValue">
				        		<c:if test="${metricValue.value > major}">
				        			<c:set var='major' scope="request" value="${metricValue.value}"></c:set>					        
				        		</c:if>
					        </c:forEach>
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
				</section>
				<hr/>
				<a href='treemap.do' class="btn btn-default" target="_blank">
					<span class="glyphicon glyphicon-plus-sign"></span> TreeMap Example
				</a>
           </div>
    	</div>
    </div>
    <%@include file='footer.jsp' %>
    <% 
    List<MetricValue> locMetricValue = (List<MetricValue>)request.getAttribute("locMetricValue");
    //int major = (int)request.getAttribute("major");
   	int [] metricsValues = new int[locMetricValue.size()];
    float [] values = new float[metricsValues.length];
    
    int i = 0;
   	for(MetricValue metricValue : locMetricValue){
   		values[i] = metricValue.getValue();
   		i++;
   	}
   	
   	PrintWriter writer = response.getWriter();
   	for(float x: values){
   		writer.println("<script>console.log('"+x+"');</script>");
   	}
   	%>
    <script type="text/javascript">
    	
		//Generate a Bates distribution of 10 random variables.
		var values = [ 0.2, 0.3, 0.4, 0.2, 0.4, 0, 1 ];

		//A formatter for counts.
		var formatCount = d3.format(",.0f");

		var margin = {
			top : 10,
			right : 30,
			bottom : 30,
			left : 30
		}, width = 960 - margin.left - margin.right, height = 500 - margin.top
				- margin.bottom;

		var x = d3.scale.linear().domain([ 0, 1 ]).range([ 0, width ]);

		//Generate a histogram using twenty uniformly-spaced bins.
		var data = d3.layout.histogram().bins(x.ticks(20))(values);

		var y = d3.scale.linear().domain([ 0, d3.max(data, function(d) {
			return d.y;
		}) ]).range([ height, 0 ]);

		var xAxis = d3.svg.axis().scale(x).orient("bottom");

		var svg = d3.select("body").append("svg").attr("width",
				width + margin.left + margin.right).attr("height",
				height + margin.top + margin.bottom).append("g").attr(
				"transform",
				"translate(" + margin.left + "," + margin.top + ")");

		var bar = svg.selectAll(".bar").data(data).enter().append("g").attr(
				"class", "bar").attr("transform", function(d) {
			return "translate(" + x(d.x) + "," + y(d.y) + ")";
		});

		bar.append("rect").attr("x", 1).attr("width", x(data[0].dx) - 1).attr(
				"height", function(d) {
					return height - y(d.y);
				});

		bar.append("text").attr("dy", ".75em").attr("y", 6).attr("x",
				x(data[0].dx) / 2).attr("text-anchor", "middle").text(
				function(d) {
					return formatCount(d.y);
				});

		svg.append("g").attr("class", "x axis").attr("transform",
				"translate(0," + height + ")").call(xAxis);
	</script>

</body>
</html>