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

class MainActivity : AppCompatActivity() {

    private val url : String = "https://api.myjson.com/bins/1b9u6m"

    override fun onCreate(savedInstanceState: Bundle?) {

        //Thread policy for internet access
        val SDK_INT = android.os.Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        //CreateDemoData()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //kotlin typecasting of view
        val button = findViewById(R.id.button) as Button
        var tv = findViewById(R.id.mytext) as TextView
        val progress_bar = findViewById(R.id.progressBar) as ProgressBar
        progress_bar.visibility = View.INVISIBLE

        //on button click event
        button.setOnClickListener {
            progress_bar.visibility = View.VISIBLE

            // Kotlin + HttpURLConnection
            var text : String? = null
            try{
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.connect()
                println(connection.responseCode)
                println(connection.getHeaderField("Content-Type"))

                text = connection.inputStream.use { conn ->
                    conn.reader().use { reader -> reader.readText() }
                }

            } catch (ex : Exception) {
                tv.setText(ex.toString())
            }

            // Json
            val catType = object : TypeToken<List<Cat>>() {}.type
            val cats = Gson().fromJson<List<Cat>>(text, catType)
            tv.text = cats.joinToString()

            progress_bar.visibility = View.GONE
        }
    }

    private fun createDemoData(){
        val list = listOf<Cat>(
            Cat("Mika", "Black-White", 3, "Fluffy"),
            Cat("Puma", "Tiger-look", 8, "ShortHair")
        )
        val demoString : String = Gson().toJson(list)
    }
}
