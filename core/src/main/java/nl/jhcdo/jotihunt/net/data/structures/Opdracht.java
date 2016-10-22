package nl.jhcdo.jotihunt.net.data.structures;

import com.google.gson.annotations.SerializedName;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Description...
 */

public class Opdracht {

    @SerializedName("ID")
    protected String id;

    @SerializedName("titel")
    protected String title;

    @SerializedName("datum")
    protected String date;

    @SerializedName("eindtijd")
    protected String until;

    @SerializedName("maxpunten")
    protected int max;

}
