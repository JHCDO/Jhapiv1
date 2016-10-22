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
 * Description...
 */

public class LocationService extends LocationProviderService {

    private final Api binder = new Api();

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
        start();
    }


    public class Api extends LocationProviderService.Api {

    }

}
