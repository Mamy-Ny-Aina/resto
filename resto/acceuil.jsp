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

        h1 {
            color: #333;
            margin-top: 50px;
        }

        form {
            margin-top: 20px;
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
    </style>
</head>
<body>
	<h1> MIAM RESTO </h1>
	<form method="get" action="menu.jsp">
		<input type="submit" value="nouvelle facture">
	</form>
	<form method="get" action="intermediaire.jsp">
		<input type="submit" value="intermediaire">
	</form>
	<form method="get" action="beforeStat.jsp">
		<input type="submit" value="benefice du restaurant">
	</form>
</body>
</html>