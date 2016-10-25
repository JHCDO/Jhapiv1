package nl.jhcdo.jotihunt.maps.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 4-9-2016
 * Class that helps starting/binding a Service.
 */
public class ServiceManager<S extends Service, B extends Binder> implements ServiceConnection {

    /**
     * Defines a tag for this class.
     * */
    private static final String TAG = "ServiceManager";

    /**
     * The binder.
     * */
    protected IBinder binder;

    /**
     * The type of the Service.
     * */
    protected Class<S> type;

    /**
     * The value indicating if the serice is bound.
     * */
    protected boolean bound = false;

    /**
     * Gets the value indicating if the service is bound.
     * */
    public boolean isBound() {
        return bound;
    }

    /**
     * Initializes a new instance of ServiceManager.
     * */
    public ServiceManager(Class<S> service) {
        type = service;
    }

    /**
     * The list of Callback listeners.
     * */
    protected ArrayList<Callback<B>> callbacks = new ArrayList<>();

    /**
     * Adds a Callback.
     * */
    public void add(Callback<B> callback) {
        this.callbacks.add(callback);
    }

    /**
     * Removes a Callback.
     * */
    public void remove(Callback<B> callback) {
        this.callbacks.remove(callback);
    }

    /**
     * Starts and binds the service.
     *
     * @param context The Context is used for starting/binding the service.
     * */
    public void bind(Context context) {
        bind(context, Mode.StartAndBind);
    }

    /**
     * Binds the service.
     *
     * @param context The Context is used for starting/binding the service.
     * @param mode The Mode wherein the service should be started.
     * */
    public void bind(Context context, Mode mode) {
        Intent intent = new Intent(context, type);
        switch (mode) {
            case StartAndBind:
                context.startService(intent);
                context.bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case Bind:
                context.bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
        }
    }

    /**
     * Unbinds from the service.
     *
     * @param context The Context is used for unbinding the service.
     * */
    @SuppressWarnings("unchecked")
    public void unbind(Context context) {
        Callback<B> callback;
        for(int i = 0; i < callbacks.size(); i++) {
            callback = callbacks.get(i);
            if(callback != null) {
                callback.onPreUnbind((B)binder);
            }
        }
        context.unbindService(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        bound = true;
        binder = iBinder;
        Callback<B> callback;
        for(int i = 0; i < callbacks.size(); i++) {
            callback = callbacks.get(i);
            if(callback != null) {
                callback.onBind((B)iBinder);
            }
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(TAG, "Disconnected from " + componentName);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 4-9-2016
     * Defines a callback for when the service is connected.
     */
    public interface Callback<B extends Binder> {
        void onBind(B binder);
        void onPreUnbind(B binder);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 4-9-2016
     * Defines modes for starting/binding the service.
     */
    public enum Mode {

        /**
         * The Service keeps running after the ServiceManager unbinds.
         * */
        StartAndBind,

        /**
         * The Service will be destroyed by the system, if there was not already a Service running by startService().
         * */
        Bind
    }

}
