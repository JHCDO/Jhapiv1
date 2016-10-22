package nl.jhcdo.jotihunt.app;

import android.app.Application;


/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 10-10-2016
 * Description...
 */

public class JotihuntTest extends Application {

    public static JotihuntTest instance;

    public static JotihuntTest getInstance() {
        return instance;
    }

    private CustomDeelgebied.CustomAdapter adapter;

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
