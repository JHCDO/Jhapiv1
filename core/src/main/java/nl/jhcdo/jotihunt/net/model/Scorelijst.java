package nl.jhcdo.jotihunt.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import nl.jhcdo.jotihunt.Team;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Description...
 */
public class Scorelijst extends BaseModel {

    /**
     * The entries of the Scorelijst.
     * */
    protected ArrayList<Entry> entries = new ArrayList<>();

    /**
     * Gets the Scorelijst entries.
     * */
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    /**
     * Sorts the Scorelijst on the given Comparator.
     *
     * @param comparator The Comparator used to sort the Scorelijst.
     * */
    public void sort(Comparator<Entry> comparator) {
        Collections.sort(entries, comparator);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Model class for a Entry in the Scorelijst.
     */
    public static class Entry {

        @SerializedName("plaats")
        protected int place;

        @SerializedName("groep")
        protected String group;

        @SerializedName("woonplaats")
        protected String residence;

        @SerializedName("regio")
        protected Team team;

        @SerializedName("hunts")
        protected int hunts;

        @SerializedName("tegenhunts")
        protected int contrahunts;

        @SerializedName("opdrachten")
        protected int opdrachten;

        @SerializedName("fotoopdrachten")
        protected int foto_opdrachten;

        @SerializedName("hints")
        protected int hints;

        @SerializedName("totaal")
        protected int total;
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Comparator for sorting the entries in a descending way on the place member.
     */
    public static final Comparator<Entry> PLACE_DESCENDING_COMPARATOR = new Comparator<Entry>() {
        @Override
        public int compare(Entry t1, Entry t2) {
            return t1.place - t2.place;
        }
    };

}
