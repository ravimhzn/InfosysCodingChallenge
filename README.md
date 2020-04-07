# InfosysCodingChallenge

## App Goal:
A list of the information about a country "Canada" in this case.
The main objective of this application is to keep the data in our local database so that
we could display even when there is no internet. Currently this app has only two screens/ fragments as demanded by coding challenge.

## Project specifications

Project features:

* 100% [Kotlin](https://kotlinlang.org/)
* [Android Jetpack](https://developer.android.com/jetpack) (ViewModel, LiveData, Lifecycle)
* Clean architecture
* Data Binding
* Reactive Programming (RxJava and LiveData)
* Dagger2 Dependency injection (service locator)
* JUnit4 and Mockito Test

## Project Used:
* Android Studio 3.6.1
* Kotlin Version 1.3.71.

## Libraries used

<img src="screenshots/Infosys.gif" width="336" align="right" hspace="20">

* Google + JetBrains (_Pretty standard default stack nowadays_)
    * [Kotlin](https://kotlinlang.org/)
        * [Idiomatic Kotlin] (https://kotlinlang.org/docs/reference/idioms.html)
        * [Collection] (https://kotlinlang.org/docs/reference/collections-overview.html)
    * [RxJava](https://github.com/ReactiveX/RxAndroid)
    * [RxAndroid](https://github.com/ReactiveX/RxAndroid)
    * [Jetpack](https://developer.android.com/jetpack)
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
        * [Data Binding](https://developer.android.com/topic/libraries/data-binding)
        * [Navigation Component] (https://developer.android.com/guide/navigation/navigation-getting-started)
* Dependency injection (Dagger2 is easy to use library for managing dependencies, it is perfect for small/mid size projects_)
    * [Dagger2](https://github.com/google/dagger)
* Retrofit2 (Retrofit is a de-facto standard nowadays)
    * [Retrofit](https://square.github.io/retrofit/)
* Tests (I used Mockito kotlin by nhraaman. It's pretty Standard. I'm comfortable with Mockk too. Also found recently about Kakao, which provides a nice DSL over Espresso, which makes writing and reading UI tests more pleasant_)
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit5](https://junit.org/junit5/))
    * [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
* Gradle (_Nice way to keep all library dependencies in one centralised place_)
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)


## Design patterns:
### MVVM:
MVVM stands for “Model View ViewModel”, and it’s a software architecture often used by Apple developers to replace MVC. Model-View-ViewModel (MVVM) is a structural design pattern that separates objects into three distinct groups:
- Models hold application data. They’re usually structs or simple classes.
- Views display visual elements and controls on the screen. They’re typically - subclasses of UIView.
- View models transform model information into values that can be displayed on a view. They’re usually classes, so they can be passed around as references.

<img src="screenshotss/MVVM.jpeg">


 ## TODO:
 - Improve UI for good user interface, Keep the interface simple.
 - Cover edge case in UI test
 - Use RxAndroid in this project. Would like to move to LiveData completely. It looks more clean to me that way.
 - Continuous integration (_Setup basic integration, it runs static analysis tools, unit tests, and assembles builds. Was thinking to run UI tests as well, but the setup is a little bit tricky and due to time constraints, have to postpone the idea_)
    * [Travis CI](https://travis-ci.org/)
 - Add more description fragment
