<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="java.sql.*" %>

<%
	int idfact = Integer.parseInt(request.getParameter("idfact"));
	out.print(idfact);
	int idproduit = Integer.parseInt(request.getParameter("idproduit"));
	out.print(idproduit);


%>