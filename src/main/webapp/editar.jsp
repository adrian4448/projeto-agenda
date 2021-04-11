<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Beans"%>

<% Beans contato = (Beans) request.getAttribute("contato"); %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda</title>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="icon" href="images/smartphone.png">
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">
		<table>
			<tr>
				<td>
					<input type="text" id="caixa3" name="codigo" readonly value="<% out.print(contato.getId()); %>">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" class="caixa1" name="nome" value="<% out.print(contato.getNome()); %>">
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="text" class="caixa2" name="fone" value="<% out.print(contato.getTelefone()); %>">
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="text" class="caixa1" name="email" value="<% out.print(contato.getEmail()); %>">
				</td>
			</tr>
		</table>
		<input type="button" class="botao1" value="Salvar" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>