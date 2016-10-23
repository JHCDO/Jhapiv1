package nl.jhcdo.jotihunt.net.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import nl.jhcdo.jotihunt.Team;


/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 14-10-2016
 * Defines a class for enumerating the status of a vossen team.
 */
public class Status {

    /**
     * The team associated with the Status.
     * */
    @SerializedName("team")
    protected Team team;

    /**
     * Gets the team associated with the Status.
     * */
    public Team getTeam() {
        return team;
    }

    /**
     * The code that represents the availability of the vossen.
     * */
    @SerializedName("status")
    protected Code code;

    /**
     * Gets the code that represents the availability of the vossen.
     * */
    public Code getCode() {
        return code;
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 14-10-2016
     * Defines codes for the availability of the vossen.
     */
    public enum Code {

        /**
         * Defines the code RED, with this code the vossen may not be hunted.
         * */
        @SerializedName("rood")
        RED,

        /**
         * Defines the code ORANGE, with this code the vossen will go to code RED in a half hour.
         * */
        @SerializedName("oranje")
        ORANGE,

        /**
         * Defines the code GREEN, with this code vossen may be hunted.
         * */
        @SerializedName("groen")
        GREEN
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 14-10-2016
     * Container for the Status of each team.
     */
    public static class Container extends BaseModel {

        /**
         * The list of Statuses.
         * */
        @SerializedName("data")
        protected ArrayList<Status> states = new ArrayList<>();

        /**
         * Gets the list of Statuses.
         * */
        public ArrayList<Status> getStates() {
            return states;
        }

        /**
         * Gets the Status by the given Team.
         *
         * @param team The Team associated with the Status.
         * @return The Status associated with the Team, returns null if none.
         * */
        public Status get(Team team) {
            Status current;
            for(int t = 0; t < states.size(); t++) {
                current = states.get(t);

                if(current != null) {
                    if(current.team == team) {
                        return current;
                    }
                }
            }
            return null;
        }
    }
}
