<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@page language="java" %>
<%@page errorPage=""%>
<%
	String protocolo = request.getScheme() + "://";
	String dominio = request.getServerName();
	String puerto = ":" + request.getServerPort();
	
	if(request.getServerPort() == 80){
		puerto = "";
	}
	
	if(!dominio.equalsIgnoreCase("localhost")){
		//protocolo = "https://";
	}
	
	String ruta_raiz_app = protocolo + dominio + puerto + request.getContextPath();
	
	response.sendRedirect(ruta_raiz_app + "/jsp/empresas/gloria/login.jsp");
	
%>
<html>
<meta charset="UTF-8">
<title>Everis</title>
<body>
	<h3>Procesando...</h3>
</body>
</html>

<!DOCTYPE html>
<html>
<head>