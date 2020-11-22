
public class Livro {
	private String isbn;
	private String titulo;
	private String autor;
	private String genero;
	private String estado;
	
	
	
	public Livro(String isbn, String titulo, String autor, String genero, String estado) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.estado = estado;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public String getGenero() {
		return genero;
	}
	public String getEstado() {
		return estado;
	}

	@Override
	public String toString() {
		return this.isbn+"-"+this.titulo;
	}

	
	
}
