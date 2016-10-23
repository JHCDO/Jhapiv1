package nl.jhcdo.jotihunt.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Model class that representing a Hint
 */
public class Hint {

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
     * Model class for the Hint response
     */
    public static class Container extends BaseModel {

        /**
         * The list of Hints.
         * */
        @SerializedName("data")
        protected ArrayList<Hint> hints = new ArrayList<>();

        /**
         * Gets the list of Hints.
         * */
        public ArrayList<Hint> getHints() {
            return hints;
        }
    }

}
