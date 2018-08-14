package manoj.com.networkpicassorecyclerview.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import manoj.com.networkpicassorecyclerview.AppController;
import manoj.com.networkpicassorecyclerview.R;
import manoj.com.networkpicassorecyclerview.adapter.DataAdapter;
import manoj.com.networkpicassorecyclerview.model.AboutCountryResponse;
import manoj.com.networkpicassorecyclerview.model.RowsItem;
import manoj.com.networkpicassorecyclerview.network.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    private RetrofitInterface retrofitInterface;
    private AboutCountryResponse aboutCountryResponse;
    private Unbinder unbinder;
    private AppController appController;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.error)
    TextView mError;
    @BindView(R.id.title)
    TextView toolbar;
    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        appController = AppController.create(this);
        initViews();
        requestAboutCountry();
    }

    //initialize views
    private void initViews() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestAboutCountry();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    //    request for the country details list
    private void requestAboutCountry() {
        retrofitInterface = appController.getRetrofitClient();
        retrofitInterface.getJSON().enqueue(new Callback<AboutCountryResponse>() {
            @Override
            public void onResponse(Call<AboutCountryResponse> call, Response<AboutCountryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mError.setVisibility(View.GONE);
                        aboutCountryResponse = new AboutCountryResponse();
                        aboutCountryResponse.setRows(response.body().getRows());
                        aboutCountryResponse.setTitle(response.body().getTitle());
                        setData();
                    }
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.error_desc), Toast.LENGTH_SHORT).show();
                    setErrorVisibility();
                }
            }

            @Override
            public void onFailure(Call<AboutCountryResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.network_error_desc), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.internal_error_desc), Toast.LENGTH_SHORT).show();
                }
                setErrorVisibility();
            }
        });
    }

    private void setErrorVisibility() {
        if (aboutCountryResponse == null) {
            mError.setVisibility(View.VISIBLE);
        } else {
            mError.setVisibility(View.GONE);
        }
    }


    //    initialize the view
    private void setData() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestAboutCountry();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        if (aboutCountryResponse != null && aboutCountryResponse.getTitle() != null)
            toolbar.setText(aboutCountryResponse.getTitle());
        if (aboutCountryResponse != null && aboutCountryResponse.getRows() != null && aboutCountryResponse.getRows().size() > 0) {
            List<RowsItem> list = new ArrayList();
            for (RowsItem item : aboutCountryResponse.getRows()) {
                if (item.getTitle() != null || item.getDescription() != null || item.getImageHref() != null) {
                    list.add(item);
                }
            }
            aboutCountryResponse.setRows(list);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);

            DataAdapter adapter = new DataAdapter(MainActivity.this, aboutCountryResponse);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
