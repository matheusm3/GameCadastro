package br.univille.poo.mvc.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DAO.GameDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.univille.poo.mvc.util.Observer;
import br.univille.poo.mvc.util.Subject;

public class GameModel implements Subject {
	
	private int id;
	private String nome;
	private String console;
	private String descricao;
	private List<Observer> list;
	private GameDAO gameDAO = new GameDAO();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Observer> getList() {
		return list;
	}

	public void setList(List<Observer> list) {
		this.list = list;
	}

	
	public GameModel() {
		list = new ArrayList<Observer>();
	}
	
	public void novoRegistro() {
		id = 0;
		nome = "";
		console = "";
		descricao = "";
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return "GameModel [id=" + id + ", nome=" + nome + ", console=" + console + ", descricao=" + descricao + "]";
	}
	
	
	@Override
	public void attach(Observer o) {
		list.add(o);
	}

	@Override
	public void detach(Observer o) {
		list.remove(o);
	}

	@Override
	public void notifyObservers() {
		// Avisa cada um dos observadores
		for(Observer o : list) {
			// Atualiza a informacao no observador
			o.update(this, this);
		}
	}

	public void salvar() throws Exception {
		
		if(console == null || console.isEmpty()) {
			throw new Exception("CPF é inválido");
		}
		if(nome == null || nome.isEmpty()) {
			throw new Exception("Nome inválido");
		}
		if(descricao == null || descricao.isEmpty()) {
			throw new Exception("Descricao inválida");
		}
		//Novo registro
		if(id == 0) {
			// Gera um id
			id = (new Random()).nextInt(100);
			this.gameDAO.insert(this);

		}else {
			this.gameDAO.update(this);
		}
		notifyObservers();
	}
	
	public void deletar() {
        this.gameDAO.delete(this.id);
        
		novoRegistro();
		notifyObservers();
	}

	
	

}
