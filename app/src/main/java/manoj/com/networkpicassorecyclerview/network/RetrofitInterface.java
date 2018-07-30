package manoj.com.networkpicassorecyclerview.network;

import manoj.com.networkpicassorecyclerview.model.AboutCountryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Manoj.Kumar04 on 7/28/2018.
 */

public interface RetrofitInterface {
    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<AboutCountryResponse> getJSON();
}
