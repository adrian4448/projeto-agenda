<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%@ page import="model.Beans"%>
    <%@ page import="java.util.ArrayList"%>
<%
	ArrayList<Beans> contatos = (ArrayList<Beans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="icon" href="images/smartphone.png">
<title>Agenda de contatos</title>
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="botao1">Novo Contato</a>
	<a href="report" class="botao1">Relatorio</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th colspan="2">Opções</th>
			</tr>
		</thead>
		<tbody>
			<% for(Beans b : contatos) { %>
				<tr>
					<td> <%=b.getId()%> </td>
					<td> <%=b.getNome()%> </td>
					<td> <%=b.getTelefone()%> </td>
					<td> <%=b.getEmail()%> </td>
					<td><a class="botao1" href="select?codigo=<%= b.getId()%>">Editar</a></td>
					<td><a class="botao1 delete" href="delete?codigo=<%= b.getId()%>">Excluir</a></td>
				</tr>
			<% } %>	
		</tbody>
	</table>
</body>
</html>