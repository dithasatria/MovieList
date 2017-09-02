package com.example.android.movielist.model.cast;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CastItem implements Parcelable {

	@SerializedName("cast_id")
	private int castId;

	@SerializedName("character")
	private String character;

	@SerializedName("gender")
	private int gender;

	@SerializedName("credit_id")
	private String creditId;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_path")
	private String profilePath;

	@SerializedName("id")
	private int id;

	@SerializedName("order")
	private int order;

	public void setCastId(int castId){
		this.castId = castId;
	}

	public int getCastId(){
		return castId;
	}

	public void setCharacter(String character){
		this.character = character;
	}

	public String getCharacter(){
		return character;
	}

	public void setGender(int gender){
		this.gender = gender;
	}

	public int getGender(){
		return gender;
	}

	public void setCreditId(String creditId){
		this.creditId = creditId;
	}

	public String getCreditId(){
		return creditId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setProfilePath(String profilePath){
		this.profilePath = profilePath;
	}

	public String getProfilePath(){
		return profilePath;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setOrder(int order){
		this.order = order;
	}

	public int getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"CastItem{" + 
			"cast_id = '" + castId + '\'' + 
			",character = '" + character + '\'' + 
			",gender = '" + gender + '\'' + 
			",credit_id = '" + creditId + '\'' + 
			",name = '" + name + '\'' + 
			",profile_path = '" + profilePath + '\'' + 
			",id = '" + id + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}

	public CastItem() {
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.castId);
		dest.writeString(this.character);
		dest.writeInt(this.gender);
		dest.writeString(this.creditId);
		dest.writeString(this.name);
		dest.writeString(this.profilePath);
		dest.writeInt(this.id);
		dest.writeInt(this.order);
	}

	protected CastItem(Parcel in) {
		this.castId = in.readInt();
		this.character = in.readString();
		this.gender = in.readInt();
		this.creditId = in.readString();
		this.name = in.readString();
		this.profilePath = in.readString();
		this.id = in.readInt();
		this.order = in.readInt();
	}

	public static final Parcelable.Creator<CastItem> CREATOR = new Parcelable.Creator<CastItem>() {
		@Override
		public CastItem createFromParcel(Parcel source) {
			return new CastItem(source);
		}

		@Override
		public CastItem[] newArray(int size) {
			return new CastItem[size];
		}
	};
}