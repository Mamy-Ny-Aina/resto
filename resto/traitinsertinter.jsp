<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="olona.*" %>
<%@ page import="java.sql.*" %>

<%

 String nom = request.getParameter("nom");
 String mdp = request.getParameter("mdp");
 String seuilStr = request.getParameter("seuil");
 String plafondStr = request.getParameter("plafond");

int seuil = Integer.parseInt(seuilStr);
int plafond = Integer.parseInt(plafondStr);
Intermediaire intermed=new Intermediaire(plafond,seuil,0,nom,mdp);
Intermediaire ii=new Intermediaire();
ii.insertIntermediaire(intermed);


%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tableau des intermédiaires</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #dee2e6;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #343a40;
            color: #ffffff;
        }

        tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        tr:hover {
            background-color: #e2e6ea;
        }
    </style>
</head>
<body>

    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Plafond</th>
            <th>Seuil</th>
            <th>Vola</th>
        </tr>

        <% 
            Intermediaire intermediaireObj = new Intermediaire();
            Vector<Intermediaire> intermediaires = intermediaireObj.getallIntermediaire();

            for (int i = 0; i < intermediaires.size(); i++) {
                Intermediaire inter = intermediaires.get(i);
        %>
                <tr>
                    <td><%= inter.getIdintermediaire() %></td>
                    <td><%= inter.getnom() %></td>
                    <td><%= inter.getPlafond() %></td>
                    <td><%= inter.getSeuil() %></td>
                    <td><%= inter.getVola() %></td>
                </tr>
        <%
            }
        %>
    </table>

</body>
</html>