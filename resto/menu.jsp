<%@ page import="resto.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="connexion.*" %>

<%
Produit p = new Produit();
Vector allproduct = p.selectProduit();

Facture f = new Facture();
int idfacture = f.last_facture_id();
idfacture = idfacture+1;
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
            padding: 0;
            text-align: center;
        }

        h2 {
            color: #333;
            margin-top: 20px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            text-align: left;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        input[type="number"] {
            width: 60px;
            padding: 8px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        label {
            display: block;
            margin-top: 10px;
            color: #333;
        }

        a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #4CAF50;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Liste des Produits :</h2>

    <form method="post" action="traitmenu.jsp">
        <table border="1">
            <tr>
                <th>Nom du Produit</th>
                <th>Prix</th>
                <th>Quantite</th>
            </tr>
            <% for (int i = 0; i < allproduct.size(); i++) { %>
                <tr>
                    <td><%= ((Produit) allproduct.elementAt(i)).getNomproduit() %></td>
                    <td><%= ((Produit) allproduct.elementAt(i)).getPrix() %></td>
                    <td><input type="number" name="quantite_<%= i %>" placeholder="0" value="0"></td>
                    <input type="hidden" name="id_<%= i %>" value="<%= i %>">
                </tr>
            <% } %>
        </table>

        <label for="intermediaire">Intermediaire:</label>
        <input type="number" name="intermediaire" placeholder="0" value="7">

        <input type="hidden" name="idfacture" value="<%= idfacture %>">
        <input type="submit" value="Ajouter">
    </form>

    <a href="mycommande.jsp?idfacture=<%= idfacture %>">voir mes commande</a>
    <a href="acceuil.jsp">retour a la page d'accueil</a>
</body>
</html>
