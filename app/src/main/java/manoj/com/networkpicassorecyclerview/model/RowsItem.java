package manoj.com.networkpicassorecyclerview.model;

import com.google.gson.annotations.SerializedName;

public class RowsItem{

	@SerializedName("imageHref")
	private String imageHref;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	public void setImageHref(String imageHref){
		this.imageHref = imageHref;
	}

	public String getImageHref(){
		return imageHref;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}