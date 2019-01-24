package org.pvronlineService.activity.bussines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.pvronlineCommon.DBUtil;
import org.pvronlineModel.CityBean;
import org.pvronlineModel.MoviesBean;
import org.pvronlineModel.PvrDetailsBean;
import org.pvronlineModel.TheaterBean;

public class PvrDetails {

	private DBUtil dbUtil = new DBUtil();
	private List<PvrDetailsBean> pvrDetailsBean;

	public List<PvrDetailsBean> execute() {
		try {
			pvrDetailsBean = getPvrDetails();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pvrDetailsBean;
	}

	public List<PvrDetailsBean> getPvrDetails() throws FileNotFoundException, SQLException, IOException {
		List<PvrDetailsBean> pvrDetails = new ArrayList<>();
		PvrDetailsBean pvrDetailsBean = new PvrDetailsBean();
		String query = "select city, theater, movie  from pvrcity c, pvrtheater t, pvrmovie m where c.citycode = t.citycode AND t.theatercode = m.theatercode;";
		ResultSet resultSet = dbUtil.readPreparedStatment(query);
		while (resultSet.next()) {
			pvrDetailsBean.setCity(resultSet.getString("city"));
			pvrDetailsBean.setTheater(resultSet.getString("theater"));
			pvrDetailsBean.setMovie(resultSet.getString("movie"));
			pvrDetails.add(pvrDetailsBean);
		}
		dbUtil.closeConnection();
		return pvrDetails;
	}

}
