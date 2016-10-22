package nl.jhcdo.jotihunt;


import nl.jhcdo.jotihunt.net.data.structures.Bericht;
import nl.jhcdo.jotihunt.net.data.structures.Hint;
import nl.jhcdo.jotihunt.net.data.structures.Nieuws;
import nl.jhcdo.jotihunt.net.data.structures.Opdracht;
import nl.jhcdo.jotihunt.net.data.structures.Scorelijst;

import nl.jhcdo.jotihunt.net.data.structures.Status;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 *
 */
public final class Jotihunt {

    /**
     * Gets a instance of the Jotihunt Api.
     * */
    public static Api getApiInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jotihunt.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }

    /**
     * @author Dingenis Sieger Sinke
     * @version 1.0
     * @since 8-10-2016
     * Defines the Jotihunt Api.
     */
    public interface Api {

        /**
         * Gets a call for retrieing the Status.Container, which contains the status of the vossen.
         * */
        @GET("/api/1.0/vossen")
        Call<Status.Container> getVossen();

        /**
         * Gets a call for retrieving the Opdracht.
         * */
        @GET("/api/1.0/opdracht")
        Call<Opdracht> getOpdracht();

        /**
         * Gets a call for retrieving the Hint.
         * */
        @GET("/api/1.0/hint")
        Call<Hint> getHint();

        /**
         * Gets a call for retrieving the Nieuws.
         * */
        @GET("/api/1.0/nieuws")
        Call<Nieuws> getNieuws();

        /**
         * Gets a call for retrieving the Scorelijst.
         * */
        @GET("/api/1.0/scorelijst")
        Call<Scorelijst> getScorelijst();

        /**
         * Gets a call for retrieving a Bericht.
         * */
        @GET("/api/1.0/{type}/{id}")
        Call<Bericht> getBericht(@Path("type") String type, @Path("id") String id);
    }
}
