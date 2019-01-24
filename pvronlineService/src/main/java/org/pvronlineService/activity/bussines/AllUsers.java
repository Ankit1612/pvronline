package org.pvronlineService.activity.bussines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.pvronlineCommon.DBUtil;
import org.pvronlineCommon.Encrypted;
import org.pvronlineModel.UserBean;

public class AllUsers {

	private DBUtil dbUtil = new DBUtil();
	private List<UserBean> allUsers;

	public List<UserBean> execute() {

		try {
			allUsers = getAllUers();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allUsers;
	}

	private List<UserBean> getAllUers() throws FileNotFoundException, SQLException, IOException {
		List<UserBean> allUsers = new ArrayList<>();
		String query = "select * from pvruser;";
		ResultSet resultSet = dbUtil.readPreparedStatment(query);
		while (resultSet.next()) {
			UserBean user = new UserBean();
			user.setFullName(resultSet.getString("fullname"));
			user.setMailid(resultSet.getString("mailid"));
			user.setCity(resultSet.getString("city"));
			allUsers.add(user);
		}
		dbUtil.closeConnection();
		return allUsers;
	}

}
