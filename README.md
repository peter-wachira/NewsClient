# Pixel News
#### November 29th 2021
#### By **Peter Wachira**


# NewsApp
This is my simple app demonstration the use of clean architecture. It gets data from the [News API](https://newsapi.org/) and entertains the user using
clean architecture and MVVM.


![](screenshots/img.png)


## Prerequisite

minSdkVersion -> 26

Gradle build system


## Set up project

Head over to the News api above and get your own API-KEY as well.

To add the News API key, go to your gradle.properties file, then add this line: 

```MY_KEY="b7122b5c5f8948eda9715867b6240ce6"```

Run the project. :)


## TOC

- [Architecture](#architecture)
- [APK](#apk)
- [Flow](#flow)
- [Libraries](#libraries)
- [Extras](#extras)
- [Contacts](#Contacts)


## Architecture

### Implemented using Clean Architecture
The following diagram shows the structure of this project with 3 layers:
- Presentation
- Domain
- Data

<br>
<p align="center">
  <img src="https://github.com/peter-wachira/TMDBClient/blob/master/diagram.png" width="750"/>
</p>
<br>

### Communication between layers

1. UI calls method from ViewModel.
2. ViewModel executes Use case.
3. Each Repository returns data from a Data Source (Cached or Remote).
4. Information flows back to the UI where we display the list of posts.




The App is not organized into multiple modules but follows the same principles of
the Presentation, Domain, and Data Layers.
The presentation layer handles the UI work with the logic contained in the **ViewModel**.
The UI uses a **LiveData** object from the ViewModel and observes it using the **Observer Pattern**.
A ListAdapter handles the actual displaying of the news. Data over the network is retrieved using
**retrofit** and **coroutines** to handle background work asynchronously. Additionally, note that
the ViewModel uses the **viewModelScope** to launch the coroutines while Fragments use the **viewLifeCycleOwner**
to observe data.
The data layer uses the recommended **Repository Pattern** to make the network calls and store the data using
**Room DB**.


## APK

Link to APK file [APK](https://drive.google.com/file/d/13s8Y3qZtsgUET6sStKSO9eXaKiJnpl-7/view?usp=sharing ) 

## Flow

 **Landing screen**

  Once the app is launched, the user has the option of choosing where to navigate within the app.

 **Search Screen**

 This screen is accessible from all the screens and allows the user to search for news articles.
 
## Libraries

This app will make use of the following libraries:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes and is lifecycle-aware
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Declaratively bind observable data to UI elements
  - [Navigation](https://developer.android.com/guide/navigation/) - Handle everything needed for in-app navigation
  - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Manage your Android background jobs
  - [Room DB](https://developer.android.com/topic/libraries/architecture/room) - Fluent SQLite database access
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging) - Load and display small chunks of data at a time
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Notify views when underlying database changes
- [Retrofit](https://square.github.io/retrofit/) - type safe http client with coroutines support
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back
- [Dagger2](https://github.com/google/dagger) - A fast dependency injector for Android and Java.
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logging HTTP request related data.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines
- [Material Design](https://material.io/develop/android/docs/getting-started/) - build awesome beautiful UIs.🔥🔥
- [Firebase](https://firebase.google.com/) - Backend As A Service for faster mobile development.
  - [Crashylitics](https://firebase.google.com/docs/crashlytics) - Provide Realtime crash reports from users end.
- [Like Button](https://github.com/jd-alexander/LikeButton) - Twitter's heart animation for Android
- [Lottie](https://github.com/airbnb/lottie-android) - Render awesome After Effects animations natively on Android and iOS, Web, and React Native
- [Glide](https://github.com/bumptech/glide) - Hassle-free image loading
- [Timber](https://github.com/JakeWharton/timber) - A logger with a small, extensible API which provides utility on top of Android's normal Log class.


## Extras


#### CI-Pipeline

[Github Actions CI](https://github.com/features/actions/) is used for Continuous Integration every time an update is made
to the repo. The configuration is in the .develop.yml .master.yml .branch.yml*** files

#### Code Analysis and test coverage

This code uses [Codacy](https://www.codacy.com/) for analysing the quality of the code, which is
always going to be A :)

```
  MIT License
  
  Copyright (c) 2019 PeteHack
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.

```


## Contacts

```bash

You can reach me via my personal email pwachira900@gmail.com or my website for contibutions or reuse


