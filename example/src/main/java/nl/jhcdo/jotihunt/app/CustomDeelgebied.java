package nl.jhcdo.jotihunt.app;

import android.content.Context;
import android.graphics.Color;

import nl.jhcdo.jotihunt.Team;
import nl.jhcdo.jotihunt.maps.Deelgebied;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Example of a CustomDeelgebied
 */
public class CustomDeelgebied extends Deelgebied {

    protected int color;

    public CustomDeelgebied(Team team) {
        super(team);

        switch (team) {
            case ALPHA:
                color = Color.argb(255, 255, 0, 0);
                break;
            case BRAVO:
                color = Color.argb(255, 0, 255, 0);
                break;
            case CHARLIE:
                color = Color.argb(255, 0, 0, 255);
                break;
            case DELTA:
                color = Color.argb(255, 0, 255, 255);
                break;
            case ECHO:
                color = Color.argb(255, 255, 0, 255);
                break;
            case FOXTROT:
                color = Color.argb(255, 255, 162, 0);
                break;
            case XRAY:
                color = Color.argb(255, 0, 0, 0);
                break;
        }
    }

    public int getColor() {
        return color;
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
