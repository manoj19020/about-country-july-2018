package manoj.com.networkpicassorecyclerview.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Manoj.Kumar04 on 7/29/2018.
 */

public class RetrofitClient {
    private static Retrofit retrofitClient = null;

    public static RetrofitInterface getClient(String baseURL) {
        if (retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient.create(RetrofitInterface.class);
    }
}
