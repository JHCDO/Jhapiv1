package nl.jhcdo.jotihunt.app;

import android.app.Application;


/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 10-10-2016
 * Example Application class
 */
public class JotihuntTest extends Application {

    /**
     * Instance of JotihuntTest.
     * */
    public static JotihuntTest instance;

    /**
     * Gets the instance of JotihuntTest.
     * */
    public static JotihuntTest getInstance() {
        return instance;
    }

    /**
     * The adapter.
     * */
    private CustomDeelgebied.CustomAdapter adapter;

    /**
     * Get the Adapter from the instance.
     * */
    public static CustomDeelgebied.CustomAdapter getDeelgebiedAdapter() {
        return instance.adapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        /**
         * Create the adapter.
         * */
        adapter = new CustomDeelgebied.CustomAdapter(this);

        /**
         * Create a proxy for each team.
         * */
        adapter.proxyAll();

    }
}
