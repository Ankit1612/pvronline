package org.pvronlineModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class TheaterBean {
	
	@JsonProperty("movies")
	private List<MoviesBean> movies;
	
	@JsonProperty("name")
	private String theaterName;

	public List<MoviesBean> getMovies() {
		return movies;
	}

	public void setMovies(List<MoviesBean> movies) {
		this.movies = movies;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	@Override
	public String toString() {
		return "TheaterBean [movies=" + movies + ", theaterName=" + theaterName + "]";
	}

}
