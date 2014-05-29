<%@page import="org.viz.servlet.IndexServlet"%>
<%@page import="org.viz.main.Viz"%>
<%@page import="org.viz.javascript.ExportToJavascript"%>
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
<script type='text/javascript' src='js/d3.v3.min.js'></script>
</head>
<body>
	<%@include file='top.jsp' %>
	<div class="container" id="content" style="margin-top: 80px;">
        <div class="row" id="top">
			<div class="col-md-12" style="margin-top: 0px;">
           		<div class="repository">LOCAL_REPOSITORY_PATH = '<%= request.getAttribute("LOCAL_REPOSITORY_PATH") %>'</div>
				<hr/>
				<section id="metrics" class="col-md-2">
					<header>
						<h4>
							<strong>List of implemented metrics</strong>
						</h4>
					</header>
					<ol class="listMetrics">
					<c:forEach items="${metrics}" var="metric">
				        <li><a href="index.do?m=${metric.name}" >${metric.name} - ${metric.description}</a></li>
				    </c:forEach>
					</ol>
					<div class="examples">
						<h4>Examples:</h4>
						<a href='treemap.do' class="btn btn-default" target="_blank">
							<span class="glyphicon glyphicon-plus-sign"></span> TreeMap Example
						</a>
					</div>
				</section>
				<section id="chart" class="col-md-10"></section>
           </div>
    	</div>
    </div>
    <%@include file='footer.jsp' %>
    <% 
    String greater = (String)request.getAttribute("greater");
    String values  = (String)request.getAttribute("values");
    
    ExportToJavascript js = new ExportToJavascript();
    //String histogram = js.generatorHistogram(values,greater,(String)request.getAttribute("metricName").toString(),(String)request.getAttribute("metricDescription"));
    PrintWriter writer = response.getWriter();
    //writer.println(histogram);
	writer.println(js.bubbleChart((String)request.getAttribute("metricName").toString(),(String)request.getAttribute("metricDescription")));
	%>
   	<script>$(document).ready(function(){ bubbleChart(); });</script>
</body>
</html>