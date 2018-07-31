package manoj.com.networkpicassorecyclerview.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import manoj.com.networkpicassorecyclerview.AppController;
import manoj.com.networkpicassorecyclerview.model.AboutCountryResponse;
import manoj.com.networkpicassorecyclerview.adapter.DataAdapter;
import manoj.com.networkpicassorecyclerview.R;
import manoj.com.networkpicassorecyclerview.network.RetrofitInterface;
import manoj.com.networkpicassorecyclerview.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private AppController appController;
    private AboutCountryResponse aboutCountryResponse = new AboutCountryResponse();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAboutCountry();
        initViews();
    }

    private void requestAboutCountry() {
//        appController = AppController.create(this);
        retrofitInterface = AppController.getRetrofitClient();

        retrofitInterface.getJSON().enqueue(new Callback<AboutCountryResponse>() {
            @Override
            public void onResponse(Call<AboutCountryResponse> call, Response<AboutCountryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        aboutCountryResponse.setRows(response.body().getRows());
                        aboutCountryResponse.setTitle(response.body().getTitle());
                        initViews();
                    }
                }else{
                    Utility.validationDialog(MainActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.error_desc));
                }
            }

            @Override
            public void onFailure(Call<AboutCountryResponse> call, Throwable t) {
                Utility.validationDialog(MainActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.error_desc));
            }
        });
    }

    private void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestAboutCountry();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        TextView toolbar = (TextView) findViewById(R.id.title);
        if (aboutCountryResponse != null && aboutCountryResponse.getTitle() != null)
            toolbar.setText(aboutCountryResponse.getTitle());
        if (aboutCountryResponse != null && aboutCountryResponse.getRows() != null && aboutCountryResponse.getRows().size() > 0) {
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

//        ArrayList androidVersions = prepareData();
            DataAdapter adapter = new DataAdapter(MainActivity.this, aboutCountryResponse);
            recyclerView.setAdapter(adapter);
        }
    }

}
