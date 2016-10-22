package nl.jhcdo.jotihunt.maps;


import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import nl.jhcdo.jotihunt.Team;


/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Defines a Deelgebied.
 */
public class Deelgebied {

    /**
     * The Team of the Deelgebied
     * */
    protected final Team team;

    /**
     * Gets the Team of the Deelgebied
     * */
    public Team getTeam() {
        return team;
    }

    /**
     * The coordinates of the Deelgebied.
     * */
    protected ArrayList<LatLng> coordinates = new ArrayList<>();

    /**
     * Gets the coordinates of the Deelgebied.
     * */
    public ArrayList<LatLng> getCoordinates() {
        return coordinates;
    }

    /**
     * Initializes a new instance of Deelgebied.
     * */
    public Deelgebied(Team team) {
        this.team = team;
    }

    /**
     * Loads in the coordinates of the Deelgebied.
     * */
    protected void load(Context context) {
        Resources resources = context.getResources();
        InputStream stream;
        switch (team) {
            case ALPHA:
                stream = resources.openRawResource(R.raw.alpha);
                break;
            case BRAVO:
                stream = resources.openRawResource(R.raw.bravo);
                break;
            case CHARLIE:
                stream = resources.openRawResource(R.raw.charlie);
                break;
            case DELTA:
                stream = resources.openRawResource(R.raw.delta);
                break;
            case ECHO:
                stream = resources.openRawResource(R.raw.echo);
                break;
            case FOXTROT:
                stream = resources.openRawResource(R.raw.foxtrot);
                break;
            case XRAY:
                stream = null;
                break;
            default:
                stream = null;
                break;
        }

        if(stream != null) {
            BufferedReader r = new BufferedReader(new InputStreamReader(stream));
            StringBuilder total = new StringBuilder();
            String line;
            try {
                while ((line = r.readLine()) != null) {
                    total.append(line).append('\n');
                }
            } catch (IOException e)
            {
                Log.e("Deelgebied", "Error occurred while reading stream", e);
            }
            coordinates = new Gson().fromJson(total.toString(), new TypeToken<ArrayList<LatLng>>() {} .getType());
        }

    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Defines a Factory for creating a Deelgebied.
     */
    public interface Factory<D extends Deelgebied> {
        /**
         * Creates a instance of D for the given Team.
         *
         * @param team The Team to create a Deelgebied for.
         * @return A instance of D where D extends Deelgebied.
         * */
        D create(Team team);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Defines a Adapter for getting a Deelgebied.
     */
    public interface Adapter<D extends Deelgebied> {
        /**
         * Returns a instance of D for the given Team.
         *
         * @param team The Team to create a Deelgebied for.
         * @return A instance of D where D extends Deelgebied.
         * */
        D get(Team team);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * The AbstractFactory already implements the Factory interface and houses a reference to a Context so that the load() can be called.
     */
    public abstract static class AbstractFactory<D extends Deelgebied> implements Factory<D> {
        protected Context context;

        /**
         * Initializes a new instance of AbstractFactory.
         *
         * @param context The Context later used for loading in a Deelgebied.
         * */
        public AbstractFactory(Context context) {
            this.context = context;
        }

    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * The ProxyAdapter implements the Adapter interface and keeps a proxy for each Deelgebied what has been requested. You can
     */
    public static class ProxyAdapter<D extends Deelgebied> implements Adapter<D> {

        /**
         * The Factory that will be used to create the proxies.
         * */
        protected Factory<D> factory;

        /**
         * The HashMap with the proxies.
         * */
        protected HashMap<Team, D> proxies = new HashMap<>();

        /**
         * Initializes a new instance of ProxyAdapter.
         *
         * @param factory The Factory that will be used to create the proxies.
         * */
        public ProxyAdapter(Factory<D> factory) {
            this.factory = factory;
        }

        /**
         * Creates a proxy for each Team.
         * */
        public D proxy(Team team) {
            D d = factory.create(team);
            proxies.put(team, d);
            return d;
        }

        /**
         * Proxies all the teams.
         * */
        public void proxyAll() {
            for(Team team : Team.values()) {
                proxy(team);
            }
        }

        /**
         * Gets a Deelgebied.
         * */
        public D get(Team team) {
            if(proxies.containsKey(team)) {
                return proxies.get(team);
            } else {
                return proxy(team);
            }
        }

        /**
         * Clears all the proxies.
         * */
        public void clear() {
            proxies.clear();
        }
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * The DefaultFactory you can use if you want plain the plain Deelgebied object.
     */
    public static class DefaultFactory extends AbstractFactory<Deelgebied> {

        /**
         * Initializes a new instance of DefaultFactory.
         *
         * @param context The Context later used for loading in a Deelgebied.
         * */
        public DefaultFactory(Context context) {
            super(context);
        }

        /**
         * Creates a instance of Deelgebied for the given team.
         *
         * @param team The Team to create a Deelgebied for.
         * @return A instance of Deelgebied.
         * */
        @Override
        public Deelgebied create(Team team) {
            Deelgebied deelgebied = new Deelgebied(team);
            deelgebied.load(context);
            return deelgebied;
        }
    }

    /**
     * Gets the default adapter, this if for when you just want to use the plain Deelgebied object.
     *
     * @param context The Context used to create the Factory.
     * @return The default adapter.
     * */
    public static Adapter<Deelgebied> getDefaultAdapter(Context context) {
        return new ProxyAdapter<>(new DefaultFactory(context));
    }
}
