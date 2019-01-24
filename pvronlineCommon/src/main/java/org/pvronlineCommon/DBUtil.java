package org.pvronlineCommon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private Properties prop;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void readProp() throws FileNotFoundException, IOException {
		prop = new Properties();
		String propfile = "database.properties";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream resStream = loader.getResourceAsStream(propfile)) {
			prop.load(resStream);
		}
	}

	public void openConnection() throws FileNotFoundException, IOException {
		readProp();
		String url = prop.getProperty("jdbc.url");
		String username = prop.getProperty("jdbc.username");
		String password = prop.getProperty("jdbc.password");
		String driver = "com.mysql.cj.jdbc.Driver";

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet readPreparedStatment(String query) throws SQLException, FileNotFoundException, IOException {
		try {
			openConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			throw e;
		}
		return resultSet;
	}

	public int insertPreparedStatement(String query) throws SQLException, FileNotFoundException, IOException {
		int rowAffected = 0;
		try {
			openConnection();
			preparedStatement = connection.prepareStatement(query);
			rowAffected = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			closeConnection();
		}
		return rowAffected;
	}

	public void closeConnection() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}
	}

}