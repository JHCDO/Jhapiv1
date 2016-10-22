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
