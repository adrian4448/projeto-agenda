package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.Beans;
import model.Dao;

@WebServlet(urlPatterns = { "/Controller",  "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    Dao dao = new Dao();
	
	public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("/main".equals(request.getServletPath())) {
			contatos(request, response);
		}else if("/insert".equals(request.getServletPath())) {
			novoContato(request, response);
		}else if("/select".equals(request.getServletPath())) {
			listarContato(request, response);	
		}else if("/update".equals(request.getServletPath())) {
			atualizarContato(request, response);
		}else if("/delete".equals(request.getServletPath())) {
			deletarContato(request, response);
		}else if("/report".equals(request.getServletPath())) {
			gerarRelatorio(request, response);
		}else {
			response.sendRedirect("index.html");
		}
	}
	
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Beans> contatos = dao.getContatos();
		request.setAttribute("contatos", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");
		Beans obj = new Beans(nome, fone, email);
		dao.insertContato(obj);
		response.sendRedirect("main");
	}
	
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codigo = Integer.parseInt(request.getParameter("codigo"));
		Beans contato = dao.getContato(codigo);
		request.setAttribute("contato", contato);
		RequestDispatcher rq = request.getRequestDispatcher("editar.jsp");
		rq.forward(request, response);
	}
	
	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		String email = request.getParameter("email");
		Beans obj = new Beans(nome, fone, email);
		obj.setId(codigo);
		dao.atualizarContato(obj);
		response.sendRedirect("main");
	}
	
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codigo = Integer.parseInt(request.getParameter("codigo"));
		Beans contato = dao.getContato(codigo);
		dao.excluirContato(contato);
		response.sendRedirect("main");
	}
	
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		try {
			//tipo do conteudo
			response.setContentType("apllication/pdf");
			
			//nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			
			//criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			//abrir o documento -> conteudo
			documento.open();
			documento.add(new Paragraph("Lista de contatos: "));
			documento.add(new Paragraph(" "));
			//Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			popularTabelaPDF(tabela);
			documento.add(tabela);
			documento.close();
		}catch(Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
	
	private void popularTabelaPDF(PdfPTable tabela) {
		//Cabeçalho
		PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
		PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
		PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
		tabela.addCell(col1);
		tabela.addCell(col2);
		tabela.addCell(col3);
		//popular a tabela com os contatos
		ArrayList<Beans> contatos = dao.getContatos();
		for(Beans contato : contatos) {
			tabela.addCell(contato.getNome());
			tabela.addCell(contato.getTelefone());
			tabela.addCell(contato.getEmail());
		}
	}
	
}
