import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ConexaoTabelas extends ConectorBD{
	
	// --------------- LIVRO ----------------
	
	public ArrayList<Livro> consultarLivro(String texto,int opcao){
		ArrayList<Livro> lista = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE titulo LIKE ?";
		this.conectar();
		
		switch(opcao) {
		case 0:
			
			break;
		case 1:
			sql = "SELECT * FROM livro WHERE nome_autor LIKE ?";
			break;
		case 2:
			sql = "SELECT * FROM livro WHERE genero LIKE ?";
			break;
		default:
			JOptionPane.showMessageDialog(null,"Erro ao procurar o parâmetro da pesquisa!","ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,'%'+texto+'%');
			ResultSet resultado = estado.executeQuery();
			
			while(resultado.next()) {
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("nome_autor");
				String genero = resultado.getString("genero");
				String estado = resultado.getString("estado");
				
				lista.add(new Livro(isbn,titulo,autor,genero,estado));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao procurar livro!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
		return lista;
	}
	
	public Livro consultarLivroEspecifico(String chave) {
		Livro livro = null;
		String sql = "SELECT * FROM livro WHERE isbn=?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,chave);
			ResultSet resultado = estado.executeQuery();
			
			while(resultado.next()) {
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("nome_autor");
				String genero = resultado.getString("genero");
				String estado = resultado.getString("estado");
				
				livro = new Livro(isbn,titulo,autor,genero,estado);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao procurar livro!","ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
		return livro;
	}
	
	
	public void updateEstado(String isbn,String disponibilidade) {
		String sql="UPDATE livro SET estado = ? WHERE isbn = ?";
		this.conectar();
		
		try {
			estado=conexao.prepareStatement(sql);
			estado.setString(1,disponibilidade);
			estado.setString(2,isbn);
			estado.execute();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao ajustar estado do livro","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
	}
	
	public ArrayList<Livro> consultarLivrosDisponiveis(String texto){
		ArrayList<Livro> lista = new ArrayList<>();
		String sql = "SELECT * FROM livro WHERE estado =? AND titulo LIKE ? ORDER BY titulo";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,"disponível");
			estado.setString(2,'%'+texto+'%');
			ResultSet resultado = estado.executeQuery();
			
			while(resultado.next()) {
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("titulo");
				String autor = resultado.getString("nome_autor");
				String genero = resultado.getString("genero");
				String estado = resultado.getString("estado");
				
				lista.add(new Livro(isbn,titulo,autor,genero,estado));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao atualizar lista de livros disponíveis!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
		return lista;
	}
	
	public void updateLivro(String titulo,String autor,String genero,String isbn){
		String sql = "UPDATE livro SET titulo=?,nome_autor=?,genero=? WHERE isbn = ?";
		this.conectar();
		
		try {
			estado=conexao.prepareStatement(sql);
			estado.setString(1,titulo);
			estado.setString(2,autor);
			estado.setString(3,genero);
			estado.setString(4,isbn);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Livro modificado de sucesso!","Modificado",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao modificar livro!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
	}
	
	public void insertLivro(String isbn,String titulo,String autor,String genero) {
		String sql = "INSERT INTO livro(isbn,titulo,nome_autor,genero) VALUES(?,?,?,?)";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,isbn);
			estado.setString(2,titulo);
			estado.setString(3,autor);
			estado.setString(4,genero);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Livro registrado com sucesso!","Registrado!",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao cadastrar livro!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
	}
	
	public boolean deleteLivro(String isbn) {
		String sql = "DELETE FROM livro WHERE isbn = ?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,isbn);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Livro removido com sucesso!","Removido",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao remover livro! Verifique se o livro está emprestado antes de removê-lo dos registros!","ERRO",JOptionPane.ERROR_MESSAGE);
			return false;
		}finally {
			this.desconectar();
		}
		
		return true;
	}
	
	//------------------AUTOR---------------------------
	
	public ArrayList<Autor> consultarAutor(String texto){
		ArrayList<Autor> lista = new ArrayList<>();
		String sql = "SELECT * FROM autor WHERE nome LIKE ?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,'%'+texto+'%');
			ResultSet resultado = estado.executeQuery();
			
			while(resultado.next()) {
				String nome = resultado.getString("nome");
				String sexo = resultado.getString("sexo");
				
				lista.add(new Autor(nome,sexo));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao procurar autor!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
		return lista;
	}
	
	public void updateAutor(String nome,String sexo) {
		String sql="UPDATE autor SET sexo=? WHERE nome=?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(2,nome);
			estado.setString(1,sexo);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Autor modificado com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao modificar autor!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
	}
	
	public void insertAutor(String nome,String sexo) {
		String sql = "INSERT INTO autor VALUES(?,?)";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,nome);
			estado.setString(2,sexo);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Autor cadastrado com sucesso!","Autor Cadastrado!",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao cadastrar autor!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
	}
	
	public boolean deleteAutor(String nome) {
		String sql = "DELETE FROM autor WHERE nome = ?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,nome);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Autor removido com sucesso!","Removido!",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao remover autor!Verifique se não têm nenhum livro vinculado a ele!","ERRO",JOptionPane.ERROR_MESSAGE);
			return false;
		}finally {
			this.desconectar();
		}
		
		return true;
	}
	
	//-----------------------EMPRÉSTIMO--------------------------
	
	public ArrayList<Emprestimo> consultarEmprestimo(String texto, int opcao){
		ArrayList<Emprestimo> lista = new ArrayList<>();
		String sql = "SELECT * FROM emprestimo WHERE leitor LIKE ?";
		this.conectar();
		
		switch(opcao) {
		case 0:
			
			break;
		case 1:// LEMBRAR DE MODIFICAR DEPOIS
			sql = "SELECT * FROM emprestimo WHERE isbn_livro LIKE ?";
			break;
		default:
			JOptionPane.showMessageDialog(null,"Erro ao procurar parâmetro de pesquisa!","ERRO",JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,'%'+texto+'%');
			ResultSet resultado = estado.executeQuery();
			
			while(resultado.next()) {
				int id = resultado.getInt("id");
				String leitor = resultado.getString("leitor");
				String livro = resultado.getString("isbn_livro");
				String telefone = resultado.getString("telefone");
				String endereco = resultado.getString("endereco_leitor");
				String emprestimo = resultado.getString("data_emprestimo");
				String devolucao = resultado.getString("data_devolucao");
				
				lista.add(new Emprestimo(id,leitor,livro,telefone,endereco,emprestimo,devolucao));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao procurar registro de empréstimo!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
			
		}
		
		return lista;
	}
	
	public void insertEmprestimo(String leitor,String isbn,String telefone,String endereco) {
		String sql = "INSERT INTO emprestimo(leitor,isbn_livro,telefone,endereco_leitor) VALUES(?,?,?,?)";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,leitor);
			estado.setString(2,isbn);
			estado.setString(3,telefone);
			estado.setString(4,endereco);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Empréstimo registrado com sucesso!","Empréstimo registrado!",JOptionPane.INFORMATION_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao registrar empréstimo!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
	}
	
	public boolean deleteEmprestimo(int id) {
		String sql = "DELETE FROM emprestimo WHERE id = ?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setInt(1,id);
			estado.execute();
			
			
			JOptionPane.showMessageDialog(null,"Empréstimo removido do histórico com sucesso!","REMOVIDO",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao remover empréstimo do histórico!","ERRO",JOptionPane.ERROR_MESSAGE);
			return false;
		}finally {
			this.desconectar();
		}
		
		return true;
	}
	
	public void executarDevolucao(int id) {
		String sql = "UPDATE emprestimo SET data_devolucao = CURRENT_TIMESTAMP WHERE id = ?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setInt(1,id);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Livro devolvido com sucesso!","ERRO",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao devolver livro!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
		
	}
	
	public void updateEmprestimo(int id,String leitor,String endereco,String telefone,String isbn) {
		String sql = "UPDATE emprestimo SET leitor=?,endereco_leitor=?,telefone=?,isbn_livro=? WHERE id=?";
		this.conectar();
		
		try {
			estado = conexao.prepareStatement(sql);
			estado.setString(1,leitor);
			estado.setString(2,endereco);
			estado.setString(3,telefone);
			estado.setString(4,isbn);
			estado.setInt(5,id);
			estado.execute();
			
			JOptionPane.showMessageDialog(null,"Registro de empréstimo modificado com sucesso!","Modificado",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao modificar registro de empréstimo!","ERRO",JOptionPane.ERROR_MESSAGE);
		}finally {
			this.desconectar();
		}
	}
	
	
	

}
