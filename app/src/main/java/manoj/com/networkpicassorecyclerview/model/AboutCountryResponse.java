package manoj.com.networkpicassorecyclerview.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AboutCountryResponse {

	@SerializedName("title")
	private String title;

	@SerializedName("rows")
	private List<RowsItem> rows;

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setRows(List<RowsItem> rows){
		this.rows = rows;
	}

	public List<RowsItem> getRows(){
		return rows;
	}
}