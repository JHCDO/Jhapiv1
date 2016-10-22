package nl.jhcdo.jotihunt.net.data.structures;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 11-10-2016
 * Description...
 */
public class Bericht {

    /**
     *
     * */
    @SerializedName("last_update")
    protected int last;

    /**
     * The list of Content of the Bericht.
     * */
    @SerializedName("data")
    protected ArrayList<Content> contents;

    public ArrayList<Content> getContents() {
        return contents;
    }

    /**
     * Checks if the Bericht has Content.
     * */
    public boolean hasContent() {
        return !contents.isEmpty();
    }

    public static class Content {
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

        @SerializedName("inhoud")
        protected String content;

        public String getContent() {
            return content;
        }
    }
}
