package br.univille.poo.mvc.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.univille.poo.mvc.control.CadastroGameControl;
import br.univille.poo.mvc.model.GameModel;
import br.univille.poo.mvc.util.Observer;
import br.univille.poo.mvc.util.Subject;

public class CadastroGameView extends JFrame implements Observer{

	private GameModel model;
	private CadastroGameControl control;
	
	private JTextField codigoTextField;
	private JTextField nomeTextField;
	private JTextField consoleTextField;
	private JTextField descricaoTextField;
	private JButton    salvarButton;
	private JButton    cancelarButton;
	private JButton    novoButton;
	private JButton    deletarButton;
	private JLabel statusLabel;
	
	public CadastroGameView() {
		setSize(400,280);
		setTitle("Cadastro de Game");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildLayout();
	}
	
	public void setModel(GameModel model) {
		this.model = model;
	}
	
	public void setControl(CadastroGameControl control) {
		this.control = control;
	}

	private void buildLayout() {
		JPanel root = new JPanel();
		root.setLayout(new BorderLayout());
		
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(BorderFactory.createCompoundBorder(
				
				  BorderFactory.createEmptyBorder(20, 20, 0, 20)
				, BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Detalhes do Game")
						,BorderFactory.createEmptyBorder(10, 10, 10, 10))));

		codigoTextField = new JTextField(20);
		codigoTextField.setEnabled(false);
		nomeTextField = new JTextField(20);
		consoleTextField = new JTextField(20);
		descricaoTextField = new JTextField(20);
		
		salvarButton = new JButton("Salvar");
		salvarButton.setToolTipText("Armazenar no banco");
		salvarButton.addActionListener(e -> salvar());
		
		cancelarButton = new JButton("Cancelar");
		novoButton = new JButton("Novo");
		novoButton.setToolTipText("Criar novo registro");
		novoButton.addActionListener(e -> novo());
		deletarButton = new JButton("Deletar");
		deletarButton.setToolTipText("Excluir registro");
		deletarButton.setEnabled(true);
		deletarButton.addActionListener(e -> deletar());
		
		
		JLabel l = new JLabel("Código",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(codigoTextField);
		
		l = new JLabel("Nome",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(nomeTextField);
		l = new JLabel("Console",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(consoleTextField);
		l = new JLabel("Descrição",JLabel.RIGHT);
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(descricaoTextField);
		l = new JLabel();
		l.setPreferredSize(new Dimension(60,16));
		panel.add(l);
		panel.add(novoButton);
		panel.add(salvarButton);
		panel.add(deletarButton);
		
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 10));
		statusLabel = new JLabel("Cadastre um novo registro de pessoa");
		statusPanel.add(statusLabel);
		
		
		root.add(statusPanel,BorderLayout.SOUTH);
		root.add(panel,BorderLayout.CENTER);
		
		add(root);
	}
	
	private void exibirDados() {
		codigoTextField.setText(Long.toString(model.getId()));
		nomeTextField.setText(model.getNome());
		descricaoTextField.setText(model.getConsole());
		consoleTextField.setText(model.getDescricao());
	}
	
	
	private void salvar() {
		control.salvar();
	}
	
	private void deletar() {
		control.deletar();
	}
	
	private void novo() {
		control.novo();
	}

	public JButton getBotaoDeletar() {
		return deletarButton;
	}

	public void setMensagemStatusBar(String text) {
		statusLabel.setText(text);
	}

	@Override
	public void update(Subject s, Object o) {
		model = (GameModel) o;
		exibirDados();
	}
	
	public String getCpf() {
		return consoleTextField.getText();
	}
	
	public String getEmail() {
		return descricaoTextField.getText();
	}
	
	public String getNome() {
		return nomeTextField.getText();
	}
	
}
