<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualization</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>
	<%@include file='top.jsp' %>
    <div class="container" id="content" style="margin-top: 80px;">
        <div class="row" id="top">
			<div class="col-md-12">
				<h1>Welcome</h1>
			</div>
			<div class="col-md-12" style="margin-top: 0px;">
           		Viz is a visualization software based into VisMiner API.
           </div>
           <div class="col-md-12">
				<section class="actions">
					<a href="index.do#createTable">Visualization into new repository</a>;
					<a href="index.do">Visualization into old repository</a>;
				</section>
			</div>
    	</div>
    </div>
    <%@include file='footer.jsp' %>
</body>
</html>