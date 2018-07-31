package manoj.com.networkpicassorecyclerview;

import android.app.Application;
import android.content.Context;

import manoj.com.networkpicassorecyclerview.network.RetrofitClient;
import manoj.com.networkpicassorecyclerview.network.RetrofitInterface;
import manoj.com.networkpicassorecyclerview.utils.Constants;

/**
 * Created by Manoj.Kumar04 on 7/29/2018.
 */
public class AppController extends Application {
    private AppController appController;
    private static RetrofitInterface retrofitInterface;

    private static AppController getAppController(Context context) {
        return (AppController) context.getApplicationContext();
    }

    /**
     * Create app controller.
     *
     * @param context the context
     * @return the app controller
     */
    public static AppController create(Context context) {
        return AppController.getAppController(context);
    }

    /**
     * Gets retrofit client.
     *
     * @return the retrofit client
     */
    public static RetrofitInterface getRetrofitClient() {
        if (retrofitInterface == null) {
            retrofitInterface = RetrofitClient.getClient(Constants.BASE_URL);
        }
        return retrofitInterface;
    }

}
