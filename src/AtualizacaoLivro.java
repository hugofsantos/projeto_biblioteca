import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AtualizacaoLivro extends JFrame {

	private JPanel contentPane;
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JTextField txtGenero;
	private JTextField txtAutor;
	private JComboBox<String> comboBox; 
	
	//Variaveis de texto
	private String isbn;
	private String titulo;
	private String autor;
	private String genero;
	/**
	 * Launch the application.
	 */
	DefaultComboBoxModel modelo;
	
	public AtualizacaoLivro(String isbn,String titulo,String autor,String genero) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		
		this.initComponents();
		
		modelo =(DefaultComboBoxModel) comboBox.getModel();
		this.atualizarLista();
		modelo.setSelectedItem(autor);
		
	}
	
	public void atualizarLista() {
		ConexaoTabelas bd = new ConexaoTabelas();
		modelo.removeAllElements();
		
		for(Autor a:bd.consultarAutor("")) {
			modelo.addElement(a.getNome());
		}
	}

	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setResizable(false);
		setTitle("Atualizar Livro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(10, 37, 472, 212);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsbn.setBounds(28, 11, 46, 14);
		panel.add(lblIsbn);
		
		txtIsbn = new JTextField();txtIsbn.setText(this.isbn);
		txtIsbn.setEditable(false);
		txtIsbn.setBounds(84, 8, 380, 20);
		panel.add(txtIsbn);
		txtIsbn.setColumns(10);
		
		txtTitulo = new JTextField();txtTitulo.setText(this.titulo);
		txtTitulo.setBounds(84, 39, 380, 20);
		panel.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTtulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTtulo.setBounds(28, 42, 46, 14);
		panel.add(lblTtulo);
		
		JLabel lblGnero = new JLabel("G\u00EAnero:");
		lblGnero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGnero.setHorizontalAlignment(SwingConstants.CENTER);
		lblGnero.setBounds(28, 70, 46, 14);
		panel.add(lblGnero);
		
		txtGenero = new JTextField();txtGenero.setText(this.genero);
		txtGenero.setBounds(84, 70, 380, 20);
		panel.add(txtGenero);
		txtGenero.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(28, 107, 46, 14);
		panel.add(lblAutor);
		
		txtAutor = new JTextField();
		txtAutor.setBounds(84, 104, 133, 20);
		panel.add(txtAutor);
		txtAutor.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setBounds(328, 103, 134, 22);
		panel.add(comboBox);
		
		JButton btnVoltarPadro = new JButton("Padr\u00E3o");
		btnVoltarPadro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTitulo.setText(titulo);
				txtGenero.setText(genero);
				modelo.setSelectedItem(autor);
			}
		});
		btnVoltarPadro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltarPadro.setBounds(171, 178, 91, 23);
		panel.add(btnVoltarPadro);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTitulo.setText("");
				txtGenero.setText("");
				txtAutor.setText("");
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpar.setBounds(272, 178, 91, 23);
		panel.add(btnLimpar);
		
		JButton btnModificar = new JButton("Salvar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexaoTabelas bd = new ConexaoTabelas();
				
				String isbn = txtIsbn.getText().trim();
				String titulo = txtTitulo.getText().trim();
				String autor = modelo.getSelectedItem().toString();
				String genero = txtGenero.getText().trim();
				
				if(titulo.isEmpty()||genero.isEmpty()||modelo.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null,"Verifique se os campos estão preenchidos!","ERRO",JOptionPane.ERROR_MESSAGE);
					return;
				}
				System.out.println(autor);
				bd.updateLivro(titulo, autor, genero,isbn);
				dispose();
				
			}
		});
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setBounds(373, 178, 91, 23);
		panel.add(btnModificar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexaoTabelas bd = new ConexaoTabelas();
				modelo.removeAllElements();
				
				for(Autor autor:bd.consultarAutor(txtAutor.getText().trim())) {
					modelo.addElement(autor.getNome());
				}
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.setBounds(227, 103, 91, 23);
		panel.add(btnPesquisar);
		
		JLabel lblModificarLivro = new JLabel("Modificar Livro");
		lblModificarLivro.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		lblModificarLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarLivro.setBounds(99, 12, 292, 14);
		contentPane.add(lblModificarLivro);
	}
}
