package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null; // primeiro se cria um objeto est�tico do tipo Connection

	public static Connection getConnection() { // m�todo para criar a conex�o
		if (conn == null) {
			try {
				Properties props = loadProperties(); // objeto do tipo Properties props recebe o m�todo loadProperties()
				String url = props.getProperty("dburl"); // uma string url recebe o m�todo auxiliar getProperty do
															// objeto props, o m�todo carrega a linha "dburl" presente
															// no db.properties
				conn = DriverManager.getConnection(url, props); // objeto conn � instanciado com o m�todo getConnection
																// da classe auxiliar DriverManager, que recebe url e
																// props como argumentos.
			} catch (SQLException e) { // tratamento da exce��o
				throw new DbException(e.getMessage());
			}
		}
		return conn; // retorna o objeto conn ao m�todo
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadProperties() { // m�todo para carregar as propriedades do db.properties
		try (FileInputStream fs = new FileInputStream("db.properties")) { // FileInputStream para ler o documento
			Properties props = new Properties(); // objeto do tipo Properties instanciado.
			props.load(fs); // objeto fs (db.properties) � passado como argumento para o m�todo load, do
							// objeto props
			return props; // retorna props ao m�todo loadProperties
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
