package org.pvronlineService.activity.bussines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.pvronlineCommon.DBUtil;
import org.pvronlineCommon.Encrypted;
import org.pvronlineModel.UserBean;

public class CreateUser {

	private DBUtil dbUtil = new DBUtil();
	private String encryptedUserName;
	private boolean userExist;
	private boolean mailIdExist;
	private UserBean newUser;

	public UserBean execute(UserBean user) {

		// validate(user);

		try {
			encryptedUserName = Encrypted.getEncryptedText(user.getUserName());
			userExist = checkForExistingUserName(encryptedUserName);
			mailIdExist = checkForExistingMailId(user.getMailid());
			createNewUser(user, encryptedUserName);
			newUser = fetchNewUser(encryptedUserName);
		} catch (NoSuchAlgorithmException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (userExist) {
			return null;
		}

		if (mailIdExist) {
			return null;
		}

		return newUser;
	}

	private UserBean fetchNewUser(String encryptedUserName) throws FileNotFoundException, SQLException, IOException {
		UserBean newUser = new UserBean();
		String query = "select * from pvruser where username = '" + encryptedUserName + "';";
		ResultSet resultSet = dbUtil.readPreparedStatment(query);
		while (resultSet.next()) {
			newUser.setFullName(resultSet.getString("fullname"));
			newUser.setMailid(resultSet.getString("mailid"));
			newUser.setCity(resultSet.getString("city"));
		}
		dbUtil.closeConnection();
		return newUser;
	}

	private void createNewUser(UserBean user, String encryptedUserName)
			throws FileNotFoundException, SQLException, IOException {
		String query = "insert into pvruser (username, fullname, city, citycode, mailid) values ('" + encryptedUserName
				+ "','" + user.getFullName() + "','" + user.getCity() + "','" + user.getCityCode() + "','"
				+ user.getMailid() + "')";
		dbUtil.insertPreparedStatement(query);
	}

	private boolean checkForExistingMailId(String email) throws FileNotFoundException, SQLException, IOException {
		String query = "select * from pvruser where mailid = '" + email + "';";
		ResultSet resultSet = dbUtil.readPreparedStatment(query);
		if (resultSet.next()) {
			dbUtil.closeConnection();
			return true;
		}
		dbUtil.closeConnection();
		return false;
	}

	private Boolean checkForExistingUserName(String encryptedUserName)
			throws FileNotFoundException, SQLException, IOException {
		String query = "select * from pvruser where username = '" + encryptedUserName + "';";
		ResultSet resultSet = dbUtil.readPreparedStatment(query);
		if (resultSet.next()) {
			dbUtil.closeConnection();
			return true;
		}
		dbUtil.closeConnection();
		return false;

	}

	private void validate(UserBean user) {
		if (user != null && user.getFullName() != null && user.getUserName() != null && user.getCity() != null
				&& user.getMailid() == null) {
			// TODO
		}
	}

}
