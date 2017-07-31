Libraries

- Timber
to log actions in application

- Dagger
to dependency injection, most popular library for this, it's much easier to write test

- Retrofit
I use retrofit to consuming API, the most popular library and very easy to use,
you can easily use it with RxJava

- RxJava2
I use RxJava to pass information between different layers of my application.

- Glide
to download graphics

- Stetho
mainly for database and network inspection directly on the device, it can also help
with layout with hierarchy viewer

- LeakCanary
to inspect memory leaks

- Realm database
very fast database, good documentation, a lot of posibilities e.g. adapters to work with listView
and recyclerView

Architecture
I've used MVVM pattern with data binding library. I still learn this architecture but
I really love it, I think it gives you much more posibilites than MVP although you have to be
carefull not to put to much logic into the view.

What to change?

add mockito to unit tests

activity_weather.xml - I just add all the neccesaries Views, it need some work to be better

data layer - WeatherRepository - create interface to it and unit tests, think about one method
instead of two combining local and remote data due to given conditions

ui layer - WeatherViewHandler - create unit tests to it (check all conditions when we change
the viewmodel)

api layer - add interceptor to api key, add celcius parameter to query degrees


