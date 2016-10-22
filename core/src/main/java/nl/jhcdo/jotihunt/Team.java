package nl.jhcdo.jotihunt;

import com.google.gson.annotations.SerializedName;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Defines the enumeration for a Team.
 */

public enum Team {

    /**
     * The Team Alpha.
     * */
    @SerializedName("Alpha")
    ALPHA("Alpha"),

    /**
     * The Team Bravo.
     * */
    @SerializedName("Bravo")
    BRAVO("Bravo"),

    /**
     * The Team Charlie.
     * */
    @SerializedName("Charlie")
    CHARLIE("Charlie"),

    /**
     * The Team Delta.
     * */
    @SerializedName("Delta")
    DELTA("Delta"),

    /**
     * The Team Echo.
     * */
    @SerializedName("Echo")
    ECHO("Echo"),

    /**
     * The Team Foxtrot.
     * */
    @SerializedName("Foxtrot")
    FOXTROT("Foxtrot"),

    /**
     * The Team Xray, this team is a fictional team for when a extra team is added.
     * */
    @SerializedName("Xray")
    XRAY("Xray");

    /**
     * The name of the Team.
     * */
    private final String name;

    /**
     * Initializes a new instance of Team.
     * */
    Team(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the Team.
     * */
    public String getName() {
        return name;
    }

    /**
     * Parses a Team from a string.
     * */
    public static Team parse(String name) {
        switch (name) {
            case "Alpha":
                return ALPHA;
            case "Bravo":
                return BRAVO;
            case "Charlie":
                return CHARLIE;
            case "Delta":
                return DELTA;
            case "Echo":
                return ECHO;
            case "Foxtrot":
                return FOXTROT;
            case "Xray":
                return XRAY;

            case "alpha":
                return ALPHA;
            case "bravo":
                return BRAVO;
            case "charlie":
                return CHARLIE;
            case "delta":
                return DELTA;
            case "echo":
                return ECHO;
            case "foxtrot":
                return FOXTROT;
            case "xray":
                return XRAY;

            case "a":
                return ALPHA;
            case "b":
                return BRAVO;
            case "c":
                return CHARLIE;
            case "d":
                return DELTA;
            case "e":
                return ECHO;
            case "f":
                return FOXTROT;
            case "x":
                return XRAY;

            case "A":
                return ALPHA;
            case "B":
                return BRAVO;
            case "C":
                return CHARLIE;
            case "D":
                return DELTA;
            case "E":
                return ECHO;
            case "F":
                return FOXTROT;
            case "X":
                return XRAY;

            default:
                return null;
        }
    }
}
