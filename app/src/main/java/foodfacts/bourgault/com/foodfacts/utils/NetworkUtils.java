package foodfacts.bourgault.com.foodfacts.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import io.reactivex.Observable;

/**
 * Utility methods for the network.
 */
public class NetworkUtils {

    @SuppressWarnings("WeakerAccess")
    public static boolean isNetworkAvailable(Context context) {
        String service = Context.CONNECTIVITY_SERVICE;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(service);
        NetworkInfo activeNetworkInfo = null;
        if (manager != null) {
            activeNetworkInfo = manager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Observable<Boolean> isNetworkAvailableObservable(Context context) {
        return Observable.just(NetworkUtils.isNetworkAvailable(context));
    }

}
