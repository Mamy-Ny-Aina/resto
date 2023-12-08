<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="java.sql.*" %>
<% 
	int idfact = Integer.parseInt(request.getParameter("idfacture"));
	idfact=idfact-1;
	    Facture f=new Facture();
        Produit p=new Produit();
        Vector idcommande=f.id_commande(idfact);
        Vector nomcommande=p.nom_commande(idcommande);
        Vector allfacture=f.selectFacture(idfact);
        double total=f.vola_total_facture(idfact);

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title></title>
	<style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: left;
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        a {
            text-decoration: none;
            color: #3498db;
            margin-right: 10px;
        }

        a:hover {
            color: #2980b9;
        }

        .total {
            font-weight: bold;
            font-size: 18px;
        }

        .total-row {
            background-color: #ecf0f1;
        }
    </style>
</head>
<body>
	 <h2>mes commande :</h2>

    <table border="1">
            <tr>
                <th>Nom du Produit</th>
                <th>Quantite</th>
                <th>Intermediaire</th>
            </tr>
			<% for (int i = 0; i < nomcommande.size(); i++) { %>
			    <form method="get" action="traitmenu.jsp">
			        <tr>
			            <td><% out.print(nomcommande.elementAt(i)); %></td>
			            <td><% out.print(((Facture) allfacture.elementAt(i)).getquantite()); %></td>
			            <td><% out.print(((Facture) allfacture.elementAt(i)).getidintermediaire()); %></td>
			            <td> <a href="plus.jsp?idproduit=<% out.print(idcommande.elementAt(i) +"&idfact=" +idfact); %>">plus</a> ou <a href="moins.jsp?idproduit=<% out.print(idcommande.elementAt(i) +"&idfact=" +idfact); %>">moins</a></td>
			        </tr>
			    </form>
			<% } %>
			<tr class="total-row">
            <td colspan="3" class="total">TOTAL :</td>
            <td class="total"><% out.print(total); %></td>
        </tr>
    </table>
</body>
</html>