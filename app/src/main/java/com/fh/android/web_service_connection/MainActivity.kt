package com.fh.android.web_service_connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import models.Cat
import java.net.HttpURLConnection
import java.net.URL
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    // Provide your api link here:
    private val url : String = "https://api.myjson.com/bins/1b9u6m"

    private var btLoad : Button? = null
    private var btReset : Button? = null
    private var tvResult : TextView? = null
    private var progressBar : ProgressBar? = null

    var dataLoaded:Boolean by Delegates.observable(false){
            _, _, dataLoaded ->
        btReset?.isEnabled = dataLoaded
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread policy for internet access
        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load UI elements
        btLoad = findViewById(R.id.btLoad)
        btReset = findViewById(R.id.btReset)
        tvResult = findViewById(R.id.mytext)
        progressBar = findViewById(R.id.progressBar)

        // Prepare UI
        tvResult?.text = ""
        progressBar?.visibility = View.INVISIBLE

        dataLoaded = false

        // Set EventHandler
        btLoad?.setOnClickListener { onLoadData() }
        btReset?.setOnClickListener{ onReset() }
    }

    private fun onLoadData(){
        btLoad?.isEnabled = false
        progressBar?.visibility = View.VISIBLE

        // TODO: Make call in co-routine
        var resultText: String = loadApiData()
        tvResult?.text = resultText

        progressBar?.visibility = View.INVISIBLE
        btLoad?.isEnabled = true
    }

    private fun loadApiData() : String {
        return try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.connect()

            val jsonString = connection.inputStream.use { conn ->
                conn.reader().use { reader -> reader.readText() }
            }
            var deserializedString = deserializeJsonString(jsonString)
            dataLoaded = true

            deserializedString
        } catch (ex : Exception) {
            "Error while accessing API $url. The following error occurred: $ex"
        }
    }

    private fun deserializeJsonString(resultText : String) : String{
        return try {
            val catType = object : TypeToken<List<Cat>>() {}.type
            val cats = Gson().fromJson<List<Cat>>(resultText, catType)

            cats.joinToString("\n")

        } catch(ex : java.lang.Exception){
            "Unable to deserialize Json. The following error occurred: $ex"
        }
    }

    private fun onReset(){
        tvResult?.text = ""
        dataLoaded = false
    }
}
