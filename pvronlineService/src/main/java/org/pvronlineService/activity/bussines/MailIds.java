package org.pvronlineService.activity.bussines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.pvronlineCommon.DBUtil;

public class MailIds {
	
	private DBUtil dbUtil = new DBUtil();
	private List<String> mailIds;
	
	public List<String> execute(int movieCode){
		try {
			mailIds = getMailIds(movieCode);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailIds;
	}
	
	public List<String> getMailIds(int movieCode) throws SQLException, FileNotFoundException, IOException {
		List<String> mailIds = new ArrayList<>();
		String query = "select mailid from pvruser where city in (select distinct city from (pvrmovie m join pvrtheater t on m.theatercode=t.theatercode) join pvrcity c on t.citycode=c.citycode where m.moviecode="+movieCode+")";
		ResultSet resultSet = dbUtil.readPreparedStatment(query);
		while (resultSet.next()) {
			mailIds.add(resultSet.getString("mailid"));
		}
		dbUtil.closeConnection();
		return mailIds;
	}

}
