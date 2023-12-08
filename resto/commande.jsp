<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="java.sql.*" %>
<% 
	Facture f=new Facture();
    int idfacture=f.last_facture_id();
    idfacture=idfacture;
    out.print(idfacture);
    java.util.Date d = f.datezao();
    Facture facture=new Facture(idfacture,19,7,0,d);
    f.insertFacture(facture);

    response.sendRedirect("menu.jsp");
%>