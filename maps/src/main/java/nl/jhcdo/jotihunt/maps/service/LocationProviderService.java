package nl.jhcdo.jotihunt.maps.service;

import android.app.Service;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Binder;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 12-10-2016
 * Provides a base for building a Service that requests location updates.
 */
public abstract class LocationProviderService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    /**
     * Defines a tag for this class.
     * */
    private static final String TAG = "LocationProviderService";

    /**
     * The GoogleApiClient for the connection.
     * */
    protected GoogleApiClient client;

    /**
     * The LocationRequest that will be issued when location updates are requested.
     * */
    protected LocationRequest request;

    /**
     * The value indicating if the Service is requesting location updates.
     * */
    protected boolean isRequesting = false;

    /**
     * The list of the LocationListeners.
     * */
    protected List<LocationListener> listeners = new ArrayList<>();

    /**
     * Initializes a new instance of LocationProviderService.
     * */
    public LocationProviderService() {
        request = new LocationRequest();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intialize();
    }

    /**
     * Builds the GoogleApiClient.
     * */
    private synchronized void intialize() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    /**
     * Starts requesting location updates.
     * This method requires one of the location permissions, you should check if they're granted and if not request them.
     * Invoking start() without permissions will not result in a SecurityException, but in a error message via Log.e().
     * */
    @SuppressWarnings("MissingPermission")
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    protected void start() {
        if(hasPermission()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
            isRequesting = true;
        } else {
            Log.e(TAG, "There's no location permission granted");
        }
    }

    /**
     * Stops requesting location updates.
     * */
    protected void stop() {
        LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        isRequesting = false;
    }

    /**
     * Restarts requesting location updates.
     * */
    @SuppressWarnings("MissingPermission")
    @RequiresPermission(
            anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
    )
    protected void restart() {
        stop();
        start();
    }

    /**
     * Checks if the FINE_LOCATION or the COARSE_LOCATION permission has been granted.
     * @return True if so, false if not.
     * */
    protected boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Invokes the LocationListeners with the given Location.
     *
     * @param location The Location to invoke the LocationListeners with.
     * */
    protected void invoke(Location location) {
        for (LocationListener listener : listeners) {
            if(listener != null) {
                listener.onLocationChanged(location);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        invoke(location);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection was suspended " + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, connectionResult.toString());
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 12-10-2016
     * Class that servers as bridge between the service and the bound client.
     */
    public class Api extends Binder {

        /**
         * Starts requesting location updates.
         * */
        @SuppressWarnings("MissingPermission")
        @RequiresPermission(
                anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
        )
        public void start() {
            start();
        }

        /**
         * Stops requesting location updates.
         * */
        public void stop() {
            stop();
        }

        /**
         * Gets the request.
         * */
        public LocationRequest getRequest() {
            return request;
        }

        /**
         * Restarts the location updates.
         * */
        @SuppressWarnings("MissingPermission")
        @RequiresPermission(
                anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}
        )
        public void restart() {
            restart();
        }

        /**
         * Adds a LocationListener.
         * */
        public void add(LocationListener listener) {
            listeners.add(listener);
        }
        /**
         * Removes a LocationListener.
         * */
        public void remove(LocationListener listener) {
            listeners.remove(listener);
        }
    }

}
