<%@page import="org.viz.servlet.IndexServlet"%>
<%@page import="org.viz.main.Viz"%>
<%@page import="org.viz.javascript.ExportToJavascript"%>
<%@page import="java.util.Collections"%>
<%@page import="org.visminer.model.MetricValue"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
ExportToJavascript js = new ExportToJavascript();
PrintWriter writer = response.getWriter(); 
writer.println("<script type='text/javascript' src='js/jquery-1.11.0.min.js'></script>"); 
String charts = js.exportAllCharts((String)request.getAttribute("metricName").toString(),(String)request.getAttribute("metricDescription"),(String)request.getAttribute("values"),(String)request.getAttribute("greater"),(String)request.getAttribute("selectedChart"));
writer.println(charts);
%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualization</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/histogram.css">
<link rel="stylesheet" href="css/custom.css">
<script type='text/javascript' src='js/d3.v3.min.js'></script>
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
					<div class="inputs">
						<h4>Graphics:</h4>
						<select name="graphic">
							<option value="bubbleChart">Bubble Chart</option>
							<option value="histogramChart">Histogram Chart</option>
						</select>
					</div>
					<div>
						<div>
							<h4>List of implemented metrics:</h4>
						</div>
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
					</div>
				</section>
				<section id="chart" class="col-md-10"></section>
           </div>
    	</div>
    </div>
    <%@include file='footer.jsp' %>
</body>
</html>