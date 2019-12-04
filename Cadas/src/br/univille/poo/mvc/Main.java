package br.univille.poo.mvc;

import br.univille.poo.mvc.control.CadastroGameControl;
import br.univille.poo.mvc.model.GameModel;
import br.univille.poo.mvc.view.CadastroGameView;

public class Main {

	public static void main(String[] args) {
		GameModel p = new GameModel();
		p.setConsole("Ex: Nintendo");
		p.setDescricao("Ex: Aventura");
		p.setNome("Mega Man");
		p.setId(123);
		
		CadastroGameControl control = new CadastroGameControl(new CadastroGameView(),p);
		control.exibirTela();
	}

}
