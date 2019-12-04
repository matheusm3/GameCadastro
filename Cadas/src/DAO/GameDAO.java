package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.univille.poo.mvc.model.*;

public class GameDAO extends BasicDAO {
    public void insert(GameModel game) {
    	
        String sql = "INSERT INTO game(id, nome, console, descricao) VALUES (?, ?, ?, ?)";

        try {
        	try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
        		p.setInt(1, game.getId());
                p.setString(2, game.getNome());
        		p.setString(3, game.getConsole());
                p.setString(4, game.getDescricao());
                p.execute();
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void update(GameModel game) {
    	
        String sql = "UPDATE game SET nome = ?, console = ?, descricao = ? WHERE id = ?";

        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                p.setString(1, game.getNome());
                p.setString(2, game.getConsole());
                p.setString(3, game.getDescricao());
                p.setInt(4, game.getId());
                p.execute();
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete(int id) {
    	
        String sql = "DELETE FROM game WHERE id = ?";

        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                p.setInt(1, id);
                p.execute();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GameModel getById(int id) {
    	
        String sql = "SELECT id, nome, console, descricao FROM game WHERE id = ?";

        GameModel game = null;
        
        try {
            try (Connection conn = getConnection();
                 PreparedStatement p = conn.prepareStatement(sql)) {
                p.setInt(1, id);
                ResultSet resultSet = p.executeQuery();
                if (resultSet.next()) {
                    game = new GameModel();
                    game.setId(resultSet.getInt(1));
                    game.setNome(resultSet.getString(2));
                    game.setConsole(resultSet.getString(3));
                    game.setDescricao(resultSet.getString(4));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return game;
    }

    public List<GameModel> get() {
        
    	String sql = "SELECT id, nome, console, descricao FROM game";
        
        List<GameModel> gamezinhos = new ArrayList<>();
        
        try {
        	
            try (Connection conn = getConnection();
                PreparedStatement p = conn.prepareStatement(sql)) {
                ResultSet resultSet = p.executeQuery();
                
                while (resultSet.next()) {
                	
                	GameModel game = new GameModel();
                    game.setId(resultSet.getInt(1));
                    game.setNome(resultSet.getString(2));
                    game.setConsole(resultSet.getString(3));
                    game.setDescricao(resultSet.getString(4));
                    gamezinhos.add(game);
                
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return gamezinhos;
    }

}