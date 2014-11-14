<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bigws.server.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ToDO List Manager</title>
</head>
<body>
	<STYLE>
#s1 {
	margin-top: 20pt;
	margin-left: 10pt;
	float: left
}

#s2 {
	margin-top: 20pt;
	margin-right: 400pt;
	float: right
}

#s3 {
	padding-top: 150pt;
	padding-left: 10pt
}
</STYLE>
	<section id="s1">
		<fieldset>
			<legend>
				<h1>Introducir Tarea</h1>
			</legend>
			<form method="post" action="Opciones">
				<table>
					<tr>
						<td>Nombre de la tarea:</td>
						<td><input type=text name=nom></td>
					</tr>
					<tr>
						<td>Contexto:</td>
						<td><input type=text name=context></td>
					</tr>
					<tr>
						<td>Proyecto:</td>
						<td><input type=text name=pro></td>
					</tr>
					<tr>
						<td>Prioridad:</td>
						<td><input type=number name=prior></td>
						<td>(Hueco en blanco se asumirá como prioridad 0)</td>
					</tr>
				</table>
				<input type = "hidden" value="add" name="option">
				<input type="submit" value="Aceptar">
			</form>
		</fieldset>
	</section>
	<section id="s2">
		<fieldset>
			<legend>
				<h1>Eliminar Tarea</h1>
			</legend>
			<form method="post" action="Opciones">
				<table>
					<tr>
						<td>Id de tarea :</td>
						<td><input type=number name=id></td>
					</tr>
				</table>
				<input type = "hidden" value="remove" name="option">
				<input type="submit" value="Eliminar">
			</form>
		</fieldset>
		<fieldset>
			<legend>
				<h1>Eliminar Lista</h1>
			</legend>
			<form method="post" action="Opciones">
				<input type = "hidden" value="removeAll" name="option">
				<input type="submit" value="Eliminar Todas">
			</form>
		</fieldset>
	</section>
	<section id="s3">
		<fieldset>
			<legend>
				<h1>Listado Tareas</h1>
			</legend>
			<%
				ToDoServicesService tdss = new ToDoServicesService();
				ToDoServices tds = tdss.getToDoServicesPort();
				String list = tds.listTasks();
			%>
			<%=list%>
		</fieldset>
	</section>
</body>
</html>