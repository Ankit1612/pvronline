package org.pvronlineService.activity.bussines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.pvronlineCommon.DBUtil;
import org.pvronlineCommon.Encrypted;
import org.pvronlineModel.AuthBean;

public class SignUp {

	private DBUtil dbUtil = new DBUtil();
	private String fullName;
	private String userName;
	private String password;
	private String encryptedUserName;
	private String encryptedPassword;
	private int status;

	public int execute(AuthBean signUpDetails) {

		fullName = signUpDetails.getFullName();
		userName = signUpDetails.getUserName();
		password = signUpDetails.getPassword();

		try {
			encryptedUserName = Encrypted.getEncryptedText(userName);
			encryptedPassword = Encrypted.getEncryptedText(password);
			status = insertToDB(fullName, encryptedUserName, encryptedPassword);

		} catch (NoSuchAlgorithmException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return status;

	}

	private int insertToDB(String fullName, String encryptedUserName, String encryptedPassword)
			throws FileNotFoundException, SQLException, IOException {
		String emptyQuery = "truncate table pvrauth;";
		String query = "insert into pvrauth (username, fullname, password) values ('" + encryptedUserName + "','"
				+ fullName + "','" + encryptedPassword + "');";
		dbUtil.insertPreparedStatement(emptyQuery);
		int rowAffected = dbUtil.insertPreparedStatement(query);
		return rowAffected;
	}
}
