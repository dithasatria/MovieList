package com.example.android.movielist.model.trailer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponseTrailerMovie{

	@SerializedName("id")
	private int id;

	@SerializedName("results")
	private List<ResultsItemTrailer> results;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setResults(List<ResultsItemTrailer> results){
		this.results = results;
	}

	public List<ResultsItemTrailer> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"APIResponseTrailerMovie{" + 
			"id = '" + id + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}

	public APIResponseTrailerMovie() {
	}
}