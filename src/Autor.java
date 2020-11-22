
public class Autor {
	private String nome;
	private String sexo;
	
	public Autor(String nome, String sexo) {
		this.nome = nome;
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public String getSexo() {
		return sexo;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}
