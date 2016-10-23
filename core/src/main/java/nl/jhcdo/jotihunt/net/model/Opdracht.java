package nl.jhcdo.jotihunt.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Model class that represents the Opdracht.
 */
public class Opdracht  {

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
     * The expire date of the Opdracht.
     * */
    @SerializedName("eindtijd")
    protected long until;

    /**
     * Gets the expire date of the Opdracht.
     * */
    public Date getExpireDate() {
        return new Date(until * 1000);
    }

    /**
     * The max amount of points that you can get of this Opdracht.
     * */
    @SerializedName("maxpunten")
    protected int max;

    /**
     * Gets the max amount of points you can get of this Opdracht.
     * */
    public int getMaxPoints() {
        return max;
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Model for the Opdracht response.
     */
    public static class Container extends BaseModel {

        /**
         * The list of Opdrachten.
         * */
        @SerializedName("data")
        protected ArrayList<Opdracht> opdrachten = new ArrayList<>();

        /**
         * Gets the list of Opdrachten.
         * */
        public ArrayList<Opdracht> getOpdrachten() {
            return opdrachten;
        }
    }

}
