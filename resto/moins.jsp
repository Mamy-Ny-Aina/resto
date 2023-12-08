<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="java.sql.*" %>

<%
  String idFactParameter = request.getParameter("idfact");
  String idProduitParameter = request.getParameter("idproduit");

  if (idFactParameter != null && idProduitParameter != null) {
    int idfact = Integer.parseInt(idFactParameter);
    int idproduit = Integer.parseInt(idProduitParameter);

    out.print(idfact);
    out.print(idproduit);

    Facture f = new Facture();
    f.moin_quantite(idfact, idproduit);
    response.sendRedirect("mycommande.jsp");
  } else {
    out.print("Erreur : Les paramètres idfact et idproduit sont null.");
  }
%>

