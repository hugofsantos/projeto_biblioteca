
public class Emprestimo {
	private int id;
	private String leitor;
	private String livro;
	private String telefone;
	private String endereco;
	private String emprestimo;
	private String devolucao;
	
	public Emprestimo(int id,String leitor, String livro, String telefone, String endereco, String emprestimo,String devolucao) {
		super();
		this.id = id;
		this.leitor = leitor;
		this.livro = livro;
		this.telefone = telefone;
		this.endereco = endereco;
		this.emprestimo = emprestimo;
		this.devolucao = devolucao;
	}
	
	public int getId() {
		return id;
	}

	public String getLeitor() {
		return leitor;
	}

	public String getLivro() {
		return livro;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getEmprestimo() {
		return emprestimo;
	}

	public String getDevolucao() {
		return devolucao;
	}
	
	
	
	
}
