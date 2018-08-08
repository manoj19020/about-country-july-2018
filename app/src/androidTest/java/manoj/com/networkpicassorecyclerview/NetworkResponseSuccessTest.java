package manoj.com.networkpicassorecyclerview;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import manoj.com.networkpicassorecyclerview.model.AboutCountryResponse;
import manoj.com.networkpicassorecyclerview.network.RetrofitClient;
import manoj.com.networkpicassorecyclerview.network.RetrofitInterface;
import manoj.com.networkpicassorecyclerview.utils.Constants;
import manoj.com.networkpicassorecyclerview.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Manoj.Kumar04 on 8/4/2018.
 */
@RunWith(AndroidJUnit4.class)
public class NetworkResponseSuccessTest {
    private RetrofitInterface retrofitInterface;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        //setup
    }

    @Test
    public void testNetworkCall() throws Exception {
        retrofitInterface = RetrofitClient.getClient(Constants.BASE_URL);

        retrofitInterface.getJSON().enqueue(new Callback<AboutCountryResponse>() {
            @Override
            public void onResponse(Call<AboutCountryResponse> call, Response<AboutCountryResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        assertEquals("200", response.code());
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<AboutCountryResponse> call, Throwable t) {
                assertNull(t);
            }
        });
    }
}
