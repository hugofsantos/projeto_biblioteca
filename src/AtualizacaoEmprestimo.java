import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AtualizacaoEmprestimo extends JFrame {

	private JPanel contentPane;
	private JTextField txtLeitor;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtLivro;
	private JComboBox<Livro> comboBox;

	/**
	 * Launch the application.
	 */
	private int id;
	private String leitor;
	private String endereco;
	private String telefone;
	private Livro livro;
	
	DefaultComboBoxModel<Livro> modelo;
	
	public AtualizacaoEmprestimo(int id,String leitor,String endereco,String telefone,Livro livro) {
		this.id = id;
		this.leitor=leitor;
		this.endereco=endereco;
		this.telefone=telefone;
		this.livro = livro;
		
		this.initComponents();
		modelo = (DefaultComboBoxModel) comboBox.getModel();
		
		
		this.atualizarLista("");
		modelo.setSelectedItem(this.livro);
	}
	
	public void atualizarLista(String texto) {
		ConexaoTabelas bd = new ConexaoTabelas();
		
		modelo.removeAllElements();
		for(Livro l:bd.consultarLivrosDisponiveis(texto)) {
			modelo.addElement(l);
		}
	}
	
	public void initComponents() {
		setResizable(false);
		setTitle("Modificar Emprestimo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 31, 424, 199);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLeitor = new JLabel("Leitor:");
		lblLeitor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLeitor.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeitor.setBounds(10, 11, 46, 14);
		panel.add(lblLeitor);
		
		txtLeitor = new JTextField();txtLeitor.setText(this.leitor);
		txtLeitor.setBounds(76, 8, 311, 20);
		panel.add(txtLeitor);
		txtLeitor.setColumns(10);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		lblEndereo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndereo.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblEndereo.setBounds(10, 36, 56, 14);
		panel.add(lblEndereo);
		
		txtEndereco = new JTextField();txtEndereco.setText(this.endereco);
		txtEndereco.setBounds(76, 33, 311, 20);
		panel.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefone.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefone.setBounds(10, 61, 56, 14);
		panel.add(lblTelefone);
		
		txtTelefone = new JTextField();txtTelefone.setText(this.telefone);
		txtTelefone.setBounds(76, 58, 311, 20);
		panel.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblLivro = new JLabel("Livro:");
		lblLivro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLivro.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivro.setBounds(10, 103, 46, 14);
		panel.add(lblLivro);
		
		txtLivro = new JTextField();
		txtLivro.setBounds(76, 100, 97, 20);
		panel.add(txtLivro);
		txtLivro.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = txtLivro.getText().trim();
				
				atualizarLista(texto);
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnPesquisar.setBounds(183, 99, 97, 23);
		panel.add(btnPesquisar);
		
		comboBox = new JComboBox<Livro>();
		comboBox.setBounds(290, 99, 97, 22);
		panel.add(comboBox);
		
		JButton btnPadro = new JButton("Padr\u00E3o");
		btnPadro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLeitor.setText(leitor);
				txtEndereco.setText(endereco);
				txtTelefone.setText(telefone);
				txtLivro.setText("");modelo.setSelectedItem(livro);
			}
		});
		btnPadro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPadro.setBounds(128, 165, 91, 23);
		panel.add(btnPadro);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLeitor.setText("");
				txtEndereco.setText("");
				txtTelefone.setText("");
				txtLeitor.setText("");
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpar.setBounds(229, 165, 91, 23);
		panel.add(btnLimpar);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexaoTabelas bd = new ConexaoTabelas();
				
				String leitor = txtLeitor.getText().trim();
				String endereco = txtEndereco.getText().trim();
				String telefone = txtTelefone.getText().trim();
				// LEMBRAR DE AJUSTAR DISPONIBILIDADE DO LIVRO AO TROCAR LIVRO EMPRESTADO
				String isbn = ((Livro) modelo.getSelectedItem()).getIsbn();
				
				if(leitor.isEmpty()||endereco.isEmpty()||telefone.isEmpty()||modelo.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null,"Preencha todos os campos!","ERRO",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				bd.updateEmprestimo(id,leitor,endereco, telefone, isbn);
				
				if(((Livro) modelo.getSelectedItem())!=livro) {
					bd.updateEstado(livro.getIsbn(),"Disponível");
					bd.updateEstado(isbn,"Emprestado");
				}
				
				dispose();
			}
		});
		btnNewButton.setBounds(330, 165, 91, 23);
		panel.add(btnNewButton);
		
		JLabel lblModificarEmprstimo = new JLabel("Modificar Empr\u00E9stimo");
		lblModificarEmprstimo.setFont(new Font("Verdana", Font.BOLD, 14));
		lblModificarEmprstimo.setBounds(138, 11, 215, 14);
		contentPane.add(lblModificarEmprstimo);
	}

}
