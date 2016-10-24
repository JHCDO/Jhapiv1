# Jhapiv1
Project containing all the Jotihunt API V1 libaries and the example project

# Installation
--------
##Core
Add the following line in your Gradle app file.

```
compile 'nl.jhcdo.jotihunt:jotihunt-core:1.0'
```
##Maps
You can add the Maps library aswell, it adds Deelgebieden and map/location utils. Use the following line in your Gradle app file.

```
compile 'nl.jhcdo.jotihunt:jotihunt-maps:1.0'
```
##UI
You can also add the UI library, it provides views and fragments for displaying data

```
compile 'nl.jhcdo.jotihunt:jotihunt-ui:1.0'
```
# Usage
--------

##Networking
```
Jotihunt.Api api = Jotihunt.getApiInstance();
api.getNieuws().enqueue(new Callback<Nieuws>() {
  @Override
  public void onResponse(Call<Nieuws> call, Response<Nieuws> response) {
    Nieuws nieuws = response.body();
    for(Nieuws.Item item : nieuws.getItems()) {
      Log.i("test", item.getTitle());
    }    
  }

  @Override
  public void onFailure(Call<Nieuws> call, Throwable t) {
   
  }
});
```

##Deelgebieden

###Normal
```
Deelgebied.Adapter<Deelgebied> adapter = Deelgebied.getDefaultAdapter(this);
Deelgebied deelgebied = adapter.get(Team.ALPHA);
ArrayList<LatLng> coordinates = deelgebied.getCoordinates();
```

###Custom
Do you want to extend the Deelgebied class? That is possible, you can create a custom Deelgebied like down here below.
```
/**
 * @author Dingenis Sieger Sinke
 * @version 1.0
 * @since 8-10-2016
 * Example of a CustomDeelgebied 
 */
public class CustomDeelgebied extends Deelgebied {

    protected int color;

    public CustomDeelgebied(Team team) {
        super(team);

        switch (team) {
            case ALPHA:
                color = Color.argb(255, 255, 0, 0);
                break;
            case BRAVO:
                color = Color.argb(255, 0, 255, 0);
                break;
            case CHARLIE:
                color = Color.argb(255, 0, 0, 255);
                break;
            case DELTA:
                color = Color.argb(255, 0, 255, 255);
                break;
            case ECHO:
                color = Color.argb(255, 255, 0, 255);
                break;
            case FOXTROT:
                color = Color.argb(255, 255, 162, 0);
                break;
            case XRAY:
                color = Color.argb(255, 0, 0, 0);
                break;
        }
    }
    
    public int getColor() {
      return color;
    }

    public static class CustomFactory extends Deelgebied.AbstractFactory<CustomDeelgebied> {

        public CustomFactory(Context context) {
            super(context);
        }

        @Override
        public CustomDeelgebied create(Team team) {
            CustomDeelgebied deelgebied = new CustomDeelgebied(team);
            deelgebied.load(context);
            return deelgebied;
        }
    }

    public static class CustomAdapter extends ProxyAdapter<CustomDeelgebied> {
        public CustomAdapter(Context context) {
            super(new CustomFactory(context));
        }
    }
}
```
You can see the CustomAdapter class extends from the ProxyAdapter class, the ProxyAdapter will generate a proxy deelgebied when you request it via get(). You can proxy each Deelgebied immedietly by using proxyAll() on the ProxyAdapter.

Use the custom adapter to get the custom Deelgebied.
```
CustomDeelgebied.CustomAdapter adapter = new CustomDeelgebied.CustomAdapter(this);
CustomDeelgebied custom = adapter.get(Team.ALPHA);
int color = custom.getColor();
```
