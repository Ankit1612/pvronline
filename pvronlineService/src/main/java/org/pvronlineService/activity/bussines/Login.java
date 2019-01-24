package org.pvronlineService.activity.bussines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.pvronlineCommon.DBUtil;
import org.pvronlineCommon.Encrypted;
import org.pvronlineModel.AuthBean;

public class Login {

	private DBUtil dbUtil = new DBUtil();
	private String userName;
	private String password;
	private String encryptedUserName;
	private String encryptedPassword;
	private String fullName;

	public String execute(AuthBean login) {
		dbUtil = new DBUtil();
		userName = login.getUserName();
		password = login.getPassword();

		// validate(userName, password);

		try {
			encryptedUserName = Encrypted.getEncryptedText(userName);
			encryptedPassword = Encrypted.getEncryptedText(password);
			fullName = fetchFromDB(encryptedUserName, encryptedPassword);
		} catch (NoSuchAlgorithmException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fullName;

	}

	private String fetchFromDB(String encryptedUserName, String encryptedPassword)
			throws FileNotFoundException, SQLException, IOException {
		String query = "select fullname from pvrauth where username ='" + encryptedUserName + "' AND password = '"
				+ encryptedPassword + "';";
		String fullname = "";
		ResultSet resultSet = dbUtil.readPreparedStatment(query);
		while (resultSet.next()) {
			fullname = resultSet.getString("fullname");
		}
		dbUtil.closeConnection();
		return fullname;
	}

	public void validate(String userName, String password) {
		if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {

		}
	}

}
