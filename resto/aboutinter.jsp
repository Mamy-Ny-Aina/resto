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
    Vector<Vector<Object>> intermediaireDetails = intermediaire.getIntermediaireDetails(idInter, debut, fin);
    Double soldeactuel = intermediaire.vola_intermediaire(idInter, debut, fin);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Intermediaire Details</title>
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
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
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

        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #333;
            background-color: #4CAF50;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            color: white;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Intermediaire Details</h2>
    <table border="1">
        <tr>
            <th>Facture ID</th>
            <th>Date Facture</th>
            <th>Produit</th>
            <th>Quantite</th>
            <th>Statut</th>
            <th>Benfice par produit</th>
        </tr>
        <% for (int i = 0; i < intermediaireDetails.size(); i++) { %>
            <tr>
                <% Vector<Object> row = intermediaireDetails.elementAt(i); %>
                <% for (int j = 0; j < row.size(); j++) { %>
                    <td><%= row.elementAt(j) %></td>
                <% } %>
            </tr>
        <% } %>
        <tr>
            <td>total</td>
            <td><%= soldeactuel %></td>
        </tr>
    </table>
    <a href="retrait.jsp?idinter=<%= idInter %>&debut=<%= debutStr %>&fin=<%= finStr %>">faire le retrait</a>

</body>
</html>
