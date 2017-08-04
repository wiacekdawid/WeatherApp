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

- Mockito
use to mocking in unit tests

Architecture
I've used MVVM pattern with data binding library. I still learn this architecture but
I really love it, I think it gives you much more posibilites than MVP although you have to be
carefull not to put to much logic into the view.

What to change?

unit tests to rxjava flow (repository)

change permission verification in weather activity

logic of date checking in local getting weather

divide view actions from logic in handler

add extra state in view informing about no location

add posibility to go to settings when there is no internet and location to change it


