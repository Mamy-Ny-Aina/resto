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

        form {
            width: 80%;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        p {
            margin: 10px 0;
        }

        input[type="text"],
        input[type="password"],
        input[type="number"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            margin-top: 5px;
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
	<form method="get" action="traitinsertinter.jsp">
		<p>nom: <input type="text" name="nom"> </p>
		<p>mdp: <input type="text" name="mdp"> </p>
		<p>seuil: <input type="number" name="seuil"> </p>
		<p>plafond: <input type="number" name="plafond"> </p>
		<input type="submit" value="INSERTION INTERMEDIAIRE">
	</form>
	<form method="get" action="aboutinter.jsp">
		<p>id intermediaire:  <input type="number" name="idinter"> </p>
		<p>debut:  <input type="date" name="debut"> </p>
		<p>fin:  <input type="date" name="fin"> </p>
		<input type="submit" name="afficher">
	</form>
</body>
</html>