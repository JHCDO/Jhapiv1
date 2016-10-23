package nl.jhcdo.jotihunt.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 11-10-2016
 * Model class for the Bericht response, see the <a href="http://www.jotihunt.net/RESTInterface.html">API</a> for more info.
 */
public class Bericht extends BaseModel {

    /**
     * The list of Content of the Bericht.
     * */
    @SerializedName("data")
    protected ArrayList<Content> contents;

    /**
     * Gets the list of the Content objects inside the Bericht.
     * */
    public ArrayList<Content> getContents() {
        return contents;
    }

    /**
     * Checks if the Bericht has Content.
     * */
    public boolean hasContent() {
        return !contents.isEmpty();
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 11-10-2016
     * Model class for the Content inside the Bericht.
     */
    public static class Content {
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
         * The content of the Content.
         * */
        @SerializedName("inhoud")
        protected String content;

        /**
         * Gets the content of the Content.
         * */
        public String getContent() {
            return content;
        }
    }
}
