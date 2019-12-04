package br.univille.poo.mvc.control;

import br.univille.poo.mvc.model.GameModel;
import br.univille.poo.mvc.view.CadastroGameView;
import DAO.GameDAO;

public class CadastroGameControl {
	
	private CadastroGameView view;
	private GameModel model;
	
	public CadastroGameControl(CadastroGameView view, GameModel model) {
		this.view = view;
		this.model = model;
		model.attach(view);
		view.setModel(model);
		view.setControl(this);
	}
	
	public void exibirTela() {
		view.show();
		model.notifyObservers();
	}

	public void deletar() {
		model.deletar();
		view.setMensagemStatusBar("Registro deletado.");
		view.getBotaoDeletar().setEnabled(false);
	}

	public void novo() {
		model.novoRegistro();
		view.getBotaoDeletar().setEnabled(false);
		view.setMensagemStatusBar("Registro novo.");
	}
	
	public void salvar() {
		model.setConsole(view.getEmail());
		model.setDescricao(view.getCpf());
		model.setNome(view.getNome());
		try {
			model.salvar();
			view.setMensagemStatusBar("Registro salvo com sucesso.");
			view.getBotaoDeletar().setEnabled(true);
		}catch(Exception e) {
			e.printStackTrace();
			view.setMensagemStatusBar("Erro: "+e.getMessage());
		}
	}

}
