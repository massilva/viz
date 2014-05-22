<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualization</title>
<link rel="stylesheet" href="css/bootstrap.css">
<script type="text/javascript" src="js/functions.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
</head>
<body>
	<!--  FORM -->
	<!-- 
<form class="form-inline" method="post" action="treemap.do">
	<div id="grupo_0" class="panel panel-default">
		<div class="panel-heading">
		  <h2 class="panel-title">
		  	<input class="form-control" name="treeMap[0][nome]" value="Grupo 1">
		  </h2>
		</div>
		<div id="filho_grupo_0" class="panel-body">
			<div id="filho_0_0" class="col-md-12 form-group">
				<label for="id" class="control-label">ID:</label>
				<input type="text" name="treeMap[0][0][id]" id="id" class="form-control">
				<label for="size" class="control-label">Tamanho:</label>
				<input type="text" name="treeMap[0][0][size]" id="size" class="form-control" placeholder="Somente número">
				<label for="color" class="control-label">Cor:</label>
				<input type="text" name="treeMap[0][0][color]" id="color" class="form-control" placeholder="Somente número">
				<button type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-plus-sign"></span> Nó
				</button>
			</div>
		</div>
		<div class="panel-footer">
			<button onclick="addFilho('grupo_0');" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-plus-sign"></span> Nó
			</button>
		</div>
		<input id="f_0" type="hidden" name="f[0]" value="1"/>
	</div>
	<div>
		<input type="submit" value="Enviar"/>
	</div>
</form>
 -->
	<div class="repository"><%= request.getAttribute("LOCAL_REPOSITORY_PATH") %></div>
	<a href='treemap.do' " class="btn btn-default"> <span
		class="glyphicon glyphicon-plus-sign"></span> TreeMap
	</a>
</body>
</html>