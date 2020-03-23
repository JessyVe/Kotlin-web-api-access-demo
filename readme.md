# Load json-data from web service
**EDUCATION Repository**
This Repository acts as a showcase for the how to connect to a web api and work with the received json data within a Kotlin-based Andriod app.


## Adding depenedencies
1. Your application needs to be able to access the internet. To make this possible add the following line into the **manifest-file** of your application.
	```xml
	<uses-permission android:name="android.permission.INTERNET"/>
	```

2. For working with json-data this application uses the Google Gson library. The following dependency needs to be add into your build script (this example uses **gradle** as build tool).
	```
	dependencies{  
	    implementation 'com.google.code.gson:gson:2.8.2'  
	}
	```
	NOTE: Try using the newest available gson version.

## Demo data
For testing, a demo-json file is provide in 	```..\resources```

## References
- [Call webservice kotlin android](https://www.iotwebplanet.com/call-webservice-kotlin-android/) (last seen 23-03-2020)
- [Kotlin json convert data class](https://www.baeldung.com/kotlin-json-convert-data-class) (last seen 23-03-2020)
- [Most elegant way of using Gson + Kotlin with default values and null safety](https://proandroiddev.com/most-elegant-way-of-using-gson-kotlin-with-default-values-and-null-safety-b6216ac5328c) (last seen 23-03-2020)
