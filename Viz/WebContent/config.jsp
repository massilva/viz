<%@page import="org.viz.main.Viz"%>
<%@page import="java.util.Collections"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visminer Web - Configuration</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<script type='text/javascript' src='js/jquery-1.11.0.min.js'></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<%@include file='top.jsp'%>
	<div class="container" id="content" style="margin-top: 80px;">
		<div class="row" id="top">
			<form method="post" action="index.cfg">
				<div class="alert alert-warning">
					<span class="legend">Fields with <span class="required">*</span>
						is required.
					</span>
				</div>
				<div class="col-md-12">
					<div class="col-md-6 well" style="margin-top: 0px;">
						<h3>Local Repository Properties</h3>
						<hr />
						<div class="form-group">
							<label>LOCAL REPOSITORY PATH</label> <input type="text"
								name="local_repository_path" class="form-control"
								placeholder="example: /home/visminer/workspace/visminer/.git"
								required="required" />
						</div>
						<div class="form-group">
							<label>LOCAL REPOSITORY NAME</label> <input type="text"
								name="local_repository_name" class="form-control"
								placeholder="example: Visminer-web" required="required" />
						</div>
						<div class="form-group">
							<label>LOCAL REPOSITORY OWNER</label> <input type="text"
								name="local_repository_owner" class="form-control"
								placeholder="example: Visminer-web" required="required" />
						</div>
						<div class="form-group">
							<label>NEW REPOSITORY ?</label>
							<div>
								<label><input type="radio" name="new_repository"
									value="1" checked='checked' />Yes</label> <label><input
									type="radio" name="new_repository" value="0" />No</label>
							</div>
						</div>
					</div>
					<div class="col-md-5 well col-md-offset-1" style="margin-top: 0px;">
						<h3>Persistence Properties</h3>
						<hr />
						<div class="form-group">
							<label>JDBC USER</label> <input type="text" name="jdbc_user"
								class="form-control" placeholder="example: root"
								required="required" />
						</div>
						<div class="form-group">
							<label>JDBC PASSWORD</label> <input type="password"
								name="jdbc_password" class="form-control"
								placeholder="example: 12345" required="required" />
						</div>
					</div>
				</div>
				<div class="col-md-12">

					<div class="col-md-6 well" style="margin-top: 0px;">
						<h3>Remote Repository GitHub Properties</h3>
						<hr />
						<div class="form-group">
							<label>REMOTE REPOSITORY LOGIN</label> <input type="text"
								name="remote_repository_login" class="form-control"
								placeholder="example: visminer" />
						</div>
						<div class="form-group">
							<label>REMOTE REPOSITORY PASSWORD</label> <input type="password"
								name="remote_repository_password" class="form-control" />
						</div>
					</div>
				</div>
				<div class="col-md-12 text-right" style="margin-top: 0px;">
					<hr />
					<button type="submit" class="btn btn-primary">Save</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file='footer.jsp'%>
</body>
</html>