<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="java.sql.*" %>

<%
int idfacture = Integer.parseInt(request.getParameter("idfacture"));
out.print(idfacture);

Produit p = new Produit();
Vector allproduct = p.selectProduit();

int intermediaire = Integer.parseInt(request.getParameter("intermediaire"));

for (int i = 0; i < allproduct.size(); i++) {
    String quantiteParam = request.getParameter("quantite_" + i);
    String idProduitParam = request.getParameter("id_" + i);

    if (quantiteParam != null && idProduitParam != null) {
        int quantite = Integer.parseInt(quantiteParam);
        int idproduit = Integer.parseInt(idProduitParam);

        // out.print("id intermediaire: " + intermediaire);
        // out.print("+++");
        // out.print("id produit: " + idproduit);
        // out.print("***");
        // out.print("quantite: " + quantite);

        Produit leproduit = p.produit_base(idproduit);
        int realid = leproduit.getIdproduit();

        if (quantite >= 1 && intermediaire >= 7) {
            Facture f = new Facture();
            java.util.Date d = f.datezao();
            Facture facture = new Facture(idfacture, realid, intermediaire, quantite, d);
            f.insertFacture(facture);
        }
    }
}

response.sendRedirect("menu.jsp");
%>
