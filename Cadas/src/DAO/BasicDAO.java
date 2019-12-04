package DAO;

import java.sql.Connection;

public class BasicDAO {

	public Connection getConnection() {
		return ConnectionFactory.getInstance().getConnection();
	}
}