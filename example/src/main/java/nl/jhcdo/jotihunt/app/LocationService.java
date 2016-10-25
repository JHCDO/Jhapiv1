package nl.jhcdo.jotihunt.app;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.android.gms.location.LocationRequest;

import nl.jhcdo.jotihunt.maps.service.LocationProviderService;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 12-10-2016
 * Example for extending the LocationProviderSerivce.
 */
public class LocationService extends LocationProviderService {

    /**
     * Binder object
     * */
    private final Api binder = new Api();

    /**
     * Refresh rate for something.
     * */
    protected float refresh = 1000;

    /**
     * Initializes a new instance of LocationService.
     * */
    public LocationService() {
        super();
        request.setInterval(1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setFastestInterval(1000);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onLocationChanged(Location location) {
        /**
         * Invokes the listeners.
         * */
        super.onLocationChanged(location);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        /**
         * Starts the location updates.
         * This method requires one of the location permissions, you should check if they're granted and if not request them.
         * Invoking start() without permissions will not result in a SecurityException, but in a error message via Log.e().
         * */
        start();
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 12-10-2016
     * Example for creating you're on API on top of the LocationProviderService.Api
     */
    public class Api extends LocationProviderService.Api {

        /**
         * Example of the custom API.
         * */
        public void setRefresh(float value) {
            refresh = value;
        }
    }

}
