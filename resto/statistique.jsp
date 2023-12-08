<%@ page import="java.util.*" %>
<%@ page import="resto.*" %>
<%@ page import="java.sql.*" %>

<%
	java.util.Date startDate = java.sql.Date.valueOf(request.getParameter("startDate"));
	java.util.Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));
    Facture f = new Facture();
    Vector general=f.statistique_detaille(startDate, endDate);
    Vector<Vector<Object>> benefices = f.calculerBeneficeBrutParMois();

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
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        header {
            background-color: #343a40;
            color: #ffffff;
            padding: 10px;
            text-align: center;
        }

        h2 {
            color: #343a40;
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            border-radius: 8px;
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

        footer {
            background-color: #343a40;
            color: #ffffff;
            text-align: center;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
	<h2>statistique du <% out.print(startDate); %> au <% out.print(endDate); %></h2>
	<table border="1">
	<tr>
		<td>nom produit</td>
		<td>quantite vendu</td>
		<td>prix de vente</td>
		<td>prix de revient</td>
		<td>prix de l intermediaire</td>
	</tr>
		<% for (int i=0;i<general.size() ;i++ ) { %>
	<tr>
			<td><% out.print( ( (Vector) general.elementAt(0)).elementAt(i)  );%></td>
			<td><% out.print( ( (Vector) general.elementAt(1)).elementAt(i)  );%></td>
			<td><% out.print( ( (Vector) general.elementAt(2)).elementAt(i)  );%></td>
			<td><% out.print( ( (Vector) general.elementAt(3)).elementAt(i)  );%></td>
			<td><% out.print( ( (Vector) general.elementAt(4)).elementAt(i)  );%></td>
	</tr>
	
	<%	} %>
	</table>

    <h2>satistique par mois </h2>
    <table border="1">
    <tr>
        <th>Mois</th>
        <th>Annee</th>
        <th>Benefice par produit</th>
    </tr>
    <%
        for (Vector<Object> benefice : benefices) {
    %>
    <tr>
        <td><%= benefice.get(0) %></td>
        <td><%= benefice.get(1) %></td>
        <td><%= benefice.get(2) %></td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>