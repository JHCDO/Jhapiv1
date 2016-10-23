package nl.jhcdo.jotihunt.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Model class that represents the Nieuws.
 */
public class Nieuws extends BaseModel {

    /**
     * The ID of the Content.
     * */
    @SerializedName("ID")
    protected String id;

    /**
     * Gets the ID of the Content.
     * */
    public String getId() {
        return id;
    }

    /**
     * The title of the Content.
     * */
    @SerializedName("titel")
    protected String title;

    /**
     * Gets the title of the Content.
     * */
    public String getTitle() {
        return title;
    }

    /**
     * The date of the Content.
     * */
    @SerializedName("datum")
    protected long date;

    /**
     * Gets the date of the Content.
     * */
    public Date getDate() {
        return new Date(date * 1000);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Model class for the Nieuws response.
     */
    public static class Container extends BaseModel {
        /**
         * The list of Nieuws items.
         * */
        @SerializedName("data")
        protected ArrayList<Nieuws> items = new ArrayList<>();

        /**
         * Get the Nieuws items.
         * */
        public ArrayList<Nieuws> getItems() {
            return items;
        }

    }
}
