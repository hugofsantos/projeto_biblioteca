import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rarys
 */
public class RegistroEmprestimo extends javax.swing.JFrame {

    /**
     * Creates new form RegistroEmprestimo
     */
	DefaultComboBoxModel modelo;
	
    public RegistroEmprestimo() {
        initComponents();
        
        modelo = (DefaultComboBoxModel) jComboBox1.getModel();
        this.atualizarItens();
    }
    
    public void atualizarItens() {
    	ConexaoTabelas bd = new ConexaoTabelas();
    	modelo.removeAllElements();
    	
    	for(Livro l:bd.consultarLivrosDisponiveis("")) {
    		modelo.addElement(l);
    	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLeitor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jButton1 = new javax.swing.JButton();
        jButton1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConexaoTabelas bd = new ConexaoTabelas();
        		String leitor = txtLeitor.getText().trim();
        		String telefone = txtTelefone.getText().trim();
        		String endereco = txtEndereco.getText().trim();
        		String livro = ((Livro) modelo.getSelectedItem()).getIsbn();
        		
        		if(leitor.isEmpty()||modelo.getSelectedItem()==null) {
        			JOptionPane.showMessageDialog(null,"Verifique se todos os campos est�o preenchidos!","ERRO",JOptionPane.INFORMATION_MESSAGE);
        			return;
        		}
        		
        		bd.insertEmprestimo(leitor, livro, telefone, endereco);
        		bd.updateEstado(livro,"Emprestado");
        		System.out.println(livro);
        	}
        });
        jButton2 = new javax.swing.JButton();
        jButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtLeitor.setText("");
        		txtTelefone.setText("");
        		txtEndereco.setText("");
        		txtLivro.setText("");
        	}
        });
        txtEndereco = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("REGISTRO DE EMPR�STIMO");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nome do leitor:");

        jLabel3.setText("Endere�o:");

        jButton1.setText("Cadastrar");

        jButton2.setText("Limpar");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Telefone:");

        jLabel5.setText("Livro:");
        
        txtLivro = new JTextField();
        txtLivro.setColumns(10);
        
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConexaoTabelas bd = new ConexaoTabelas();
        		String texto = txtLivro.getText().trim();
        		
        		modelo.removeAllElements();
        		
        		for(Livro l:bd.consultarLivrosDisponiveis(texto)) {
        			modelo.addElement(l);
        		}
        		
        	}
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        			.addContainerGap())
        		.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel2)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);
        GroupLayout gl_jPanel2 = new GroupLayout(jPanel2);
        gl_jPanel2.setHorizontalGroup(
        	gl_jPanel2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(10)
        			.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        			.addGap(4)
        			.addComponent(txtLeitor, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(22)
        			.addComponent(jLabel3)
        			.addGap(18)
        			.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(10)
        			.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
        			.addGap(4)
        			.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(222)
        			.addComponent(jButton2)
        			.addGap(6)
        			.addComponent(jButton1))
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(34)
        			.addComponent(jLabel5)
        			.addGap(28)
        			.addComponent(txtLivro)
        			.addGap(18)
        			.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(122)
        			.addComponent(btnPesquisar)
        			.addContainerGap(173, Short.MAX_VALUE))
        );
        gl_jPanel2.setVerticalGroup(
        	gl_jPanel2.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_jPanel2.createSequentialGroup()
        			.addGap(11)
        			.addGroup(gl_jPanel2.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel2.createSequentialGroup()
        					.addGap(3)
        					.addComponent(jLabel1))
        				.addComponent(txtLeitor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(21)
        			.addGroup(gl_jPanel2.createParallelGroup(Alignment.BASELINE)
        				.addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel3))
        			.addGap(18)
        			.addGroup(gl_jPanel2.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel2.createSequentialGroup()
        					.addGap(3)
        					.addComponent(jLabel4))
        				.addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGroup(gl_jPanel2.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_jPanel2.createParallelGroup(Alignment.LEADING)
        					.addGroup(gl_jPanel2.createSequentialGroup()
        						.addGap(22)
        						.addComponent(jLabel5))
        					.addGroup(gl_jPanel2.createSequentialGroup()
        						.addGap(19)
        						.addComponent(txtLivro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(gl_jPanel2.createSequentialGroup()
        					.addGap(18)
        					.addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnPesquisar)
        			.addGap(33)
        			.addGroup(gl_jPanel2.createParallelGroup(Alignment.LEADING)
        				.addComponent(jButton2)
        				.addComponent(jButton1)))
        );
        jPanel2.setLayout(gl_jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<Livro> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtLeitor;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtTelefone;
    private JTextField txtLivro;
}
