package nl.jhcdo.jotihunt.app;

import android.content.Context;
import android.graphics.Color;

import nl.jhcdo.jotihunt.Team;
import nl.jhcdo.jotihunt.maps.Deelgebied;


/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Description...
 */

public class CustomDeelgebied extends Deelgebied {

    protected int color;

    public CustomDeelgebied(Team team) {
        super(team);

        switch (team) {
            case ALPHA:
                color = Color.RED;
                break;
            case BRAVO:
                color = Color.GREEN;
                break;
        }
    }

    public static class CustomFactory extends Deelgebied.AbstractFactory<CustomDeelgebied> {

        public CustomFactory(Context context) {
            super(context);
        }

        @Override
        public CustomDeelgebied create(Team team) {
            CustomDeelgebied deelgebied = new CustomDeelgebied(team);
            deelgebied.load(context);
            return deelgebied;
        }
    }

    public static class CustomAdapter extends ProxyAdapter<CustomDeelgebied> {
        public CustomAdapter(Context context) {
            super(new CustomFactory(context));
        }
    }
}
