# Restaurant Search Android Application

A simple android app written in Kotlin and using the Yelp Fusion API, as part of a take home assessment 

## Getting Started

1. Import this GitHub project using Android Studio

2. Get your Yelp API key from creating an app on [their website](https://www.yelp.com/developers/v3/manage_app)

3. Add the Yelp API key to app/res/values/strings.xml

```
 <string name="api_key">ADD API KEY HERE</string>
```
4. Run the app

<img src="/images/home.png" width="300px">

## Using the App

As per the requirements, this is a simple android application that shows the top 10 restaurant results when you enter a keyword into the search box. Click "sort" to reorder the restaurants alphabetically by name. 

<img src="/images/search.png" width="300px">

Tap on a restaurant name to see more information about it. You can also return to the search page from here. 

<img src="/images/info.png" width="300px">

## Notes on the implementation 

- As this is application is part of an assessment, the Yelp API Query is hardcoded to search in one specific geographic location only (the assessment requires a query based on a search ter only). 

- I utilized [JieHeng's Yelp Fusion API client library](https://github.com/JieHeng/yelp-fusion-android) for making the API calls in this project. This choice was made because this provided a clean and readable way to make API calls using the Yelp Fusion API. 

- Due to the time constraints and this being my first time working with Kotlin, I am aware that the application is not perfect with regards to the grid display and asynchronousy of the results returned (sometimes you need to tap outside or backspace before the results show).  

- Due to the nature of this application being part of an assessment, I made the UI very simple so I could focus on other aspects such as documentation and overall cleanliness. 
