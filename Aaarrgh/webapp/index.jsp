<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>

	<div class="alert">
		${message}
	</div>
	<form action="login/auth.do" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="inputUsername">Nombre de usuario</label>
			<div class="controls">
				<input type="text" name="nombre" id="inputUsername" placeholder="jack">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputPassword">Contraseña</label>
			<div class="controls">
				<input type="password" name="pass" id="inputPassword" placeholder="Contraseña">
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">Ingresar</button>
			</div>
		</div>
	</form>
</body>
</html>