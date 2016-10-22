package nl.jhcdo.jotihunt.net.data.structures;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Description...
 */
public class Nieuws {

    @SerializedName("last_update")
    protected int last;

    /**
     * The list of Nieuws items.
     * */
    @SerializedName("data")
    protected ArrayList<Item> items = new ArrayList<>();

    /**
     * Get the Nieuws items.
     * */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Class that represents one item in the Nieuws object.
     */
    public static class Item {

        @SerializedName("ID")
        protected String id;

        public String getId() {
            return id;
        }

        @SerializedName("titel")
        protected String title;

        public String getTitle() {
            return title;
        }

        @SerializedName("datum")
        protected String date;

        public String getDate() {
            return date;
        }
    }
}
