import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConectorBD {
	private String driver = "com.mysql.jdbc.Driver";
	private String banco = "bdBiblioteca";
	private String host = "localhost";
	private String conex = "jdbc:mysql://"+host+":3306/"+banco;
	private String user = "root";
	private String senha = "";
	
	Connection conexao;
	PreparedStatement estado;
	
	public void conectar() {
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(conex,user,senha);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Erro ao carregar driver","ERRO",JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao conectar","ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void desconectar() {
		try {
			estado.close();
			conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao fechar conexão!","ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
