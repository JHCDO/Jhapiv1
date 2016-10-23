package nl.jhcdo.jotihunt.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 23-10-2016
 * Model for each API call.
 */

public class BaseModel {

    /**
     * The last update of the Bericht.
     * */
    @SerializedName("last_update")
    protected int last;

    /**
     * Returns the date indicating the last time the Bericht was updated.
     * */
    public Date getLastUpdate() {
        return new Date(last * 1000);
    }
}
