package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "JMV@xrms9";
	
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			return con;
		}catch(Exception e) {
			System.out.print(e.getMessage());
			return null;
		}
	}
	
	public void testConexao() {
		try {
			Connection con = conectar();
			System.out.print(con);
			con.close();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}		
	}
	
	public void insertContato(Beans obj) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("INSERT INTO agenda (NOME,TELEFONE,EMAIL) VALUES (");
			sql.append("'" + obj.getNome()).append("',");
			sql.append("'" + obj.getTelefone()).append("',");
			sql.append("'" + obj.getEmail()).append("');");
			this.conectar().prepareStatement(sql.toString()).executeUpdate();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public ArrayList<Beans> getContatos() {
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		ArrayList<Beans> contatos = new ArrayList<>();
		try {
			sql.append("SELECT * FROM agenda order by NOME");
			rs = this.conectar().prepareStatement(sql.toString()).executeQuery();
			while(rs.next()) {
				contatos.add(montarBean(rs));
			}
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return contatos;
	}
	
	public Beans getContato(Integer codigo) {
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("SELECT * FROM agenda where ");
			sql.append("ID = '").append(codigo + "'");
			rs = this.conectar().prepareStatement(sql.toString()).executeQuery();
			while(rs.next()) {
				return montarBean(rs);
			}
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
	
	public void excluirContato(Beans bean) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append("DELETE FROM agenda WHERE ID = '").append(bean.getId() + "'");
			this.conectar().prepareStatement(sql.toString()).executeUpdate();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public void atualizarContato(Beans bean) {
		StringBuilder sql = new StringBuilder();
		try {
			sql.append(" UPDATE agenda SET ");
			sql.append(" NOME = '").append(bean.getNome() + "',");
			sql.append(" TELEFONE = '").append(bean.getTelefone() + "',");
			sql.append(" EMAIL = '").append(bean.getEmail() + "'");
			sql.append("WHERE ID = '").append(bean.getId() + "'");
			this.conectar().prepareStatement(sql.toString()).executeUpdate();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	
	private Beans montarBean(ResultSet rs) {
		Beans ctt = new Beans();
		try {
			ctt.setId(rs.getString("ID"));
			ctt.setNome(rs.getString("NOME"));
			ctt.setTelefone(rs.getString("TELEFONE"));
			ctt.setEmail(rs.getString("EMAIL"));
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return ctt;
	}
	
	
	
}
