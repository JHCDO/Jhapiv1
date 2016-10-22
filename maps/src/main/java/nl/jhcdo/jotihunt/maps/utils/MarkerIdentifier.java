package nl.jhcdo.jotihunt.maps.utils;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 17-9-2016
 * Class for making it easy to store data with a Marker.
 * By using this system you can associated your data with the MarkerOptions before the Marker.setTag().
 * This class might be unnecessary later on when issue https://code.google.com/p/gmaps-api-issues/issues/detail?id=10306 is resolved.
 */
public class MarkerIdentifier {

    /**
     * The properties of the MarkerIdentifier.
     * */
    protected HashMap<String, String> properties = new HashMap<>();

    /**
     * Gets the properties of the MarkerIdentifier.
     * */
    public HashMap<String, String> getProperties() {
        return properties;
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 17-9-2016
     * Builder for the MarkerIdentifier.
     */
    public static class Builder {

        /**
         * Buffer for building the MarkerIdentifier.
         * */
        MarkerIdentifier identifier = new MarkerIdentifier();

        /**
         * Add a property.
         * */
        public Builder add(String key, String value) {
            identifier.properties.put(key, value);
            return this;
        }

        /**
         * Remove a property.
         * */
        public Builder remove(String key) {
            identifier.properties.remove(key);
            return this;
        }

        /**
         * Create the Identifier.
         * */
        public MarkerIdentifier create() {
            return identifier;
        }
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * Parses a MarkerIdentifier from the given String value.
     *
     * @param value The String value to parse from.
     * @return A MarkerIdentifier, returns null if it doesn't succeed.
     * */
    public MarkerIdentifier parse(String value) {
        if(value != null && !value.isEmpty()) {
            return new Gson().fromJson(value, MarkerIdentifier.class);
        }
        return null;
    }

}
