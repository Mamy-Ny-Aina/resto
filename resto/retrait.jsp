<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="olona.*" %>
<%@ page import="java.sql.*" %>

<%
 	String idInterStr = request.getParameter("idinter");
    String debutStr = request.getParameter("debut");
    String finStr = request.getParameter("fin");

    int idInter = Integer.parseInt(idInterStr);
    java.sql.Date debut = java.sql.Date.valueOf(debutStr);
    java.sql.Date fin = java.sql.Date.valueOf(finStr);


    Intermediaire intermediaire = new Intermediaire();
    intermediaire.insert_vola_intermediaire(idInter,debut,fin);
    out.print("nety");
    // response.sendRedirect(".jsp");


%>