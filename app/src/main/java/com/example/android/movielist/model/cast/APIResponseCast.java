package com.example.android.movielist.model.cast;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponseCast{

	@SerializedName("cast")
	private List<CastItem> cast;

	@SerializedName("id")
	private int id;

	public void setCast(List<CastItem> cast){
		this.cast = cast;
	}

	public List<CastItem> getCast(){
		return cast;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"APIResponseCast{" + 
			"cast = '" + cast + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

	public APIResponseCast() {
	}
}