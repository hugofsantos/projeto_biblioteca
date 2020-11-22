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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AtualizacaoAutor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JComboBox<String> comboBox;
	
	//Propriedades
	private String nome;
	private String sexo;
	
	
	DefaultComboBoxModel modelo;
	
	public AtualizacaoAutor(String nome,String sexo) {
		this.nome = nome;
		this.sexo = sexo;
		
		this.initComponents();
		modelo = (DefaultComboBoxModel) comboBox.getModel();
		modelo.setSelectedItem(sexo);
	}
	
	
	
	public void initComponents() {
		setTitle("Modificar Autor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 25, 424, 155);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(21, 11, 46, 14);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setEditable(false);txtNome.setText(this.nome);
		txtNome.setBounds(72, 8, 309, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexo.setBounds(21, 54, 46, 14);
		panel.add(lblSexo);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Feminino"}));
		comboBox.setBounds(72, 50, 114, 22);
		panel.add(comboBox);
		
		JButton btnPadro = new JButton("Padr\u00E3o");
		btnPadro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText(nome);
				modelo.setSelectedItem(sexo);
			}
		});
		btnPadro.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPadro.setBounds(222, 121, 91, 23);
		panel.add(btnPadro);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConexaoTabelas bd = new ConexaoTabelas();
				
				String nome = txtNome.getText().trim();
				String sexo = (modelo.getSelectedItem()).toString();
				
				if(nome.isEmpty()||modelo.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null,"Preencha todos os campos!","ERRO",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				bd.updateAutor(nome, sexo);
				dispose();
			}
		});
		btnNewButton.setBounds(323, 121, 91, 23);
		panel.add(btnNewButton);
		
		JLabel lblModificarAutor = new JLabel("Modificar Autor");
		lblModificarAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificarAutor.setFont(new Font("Verdana", Font.BOLD, 14));
		lblModificarAutor.setBounds(113, 0, 210, 14);
		contentPane.add(lblModificarAutor);
	}
}
