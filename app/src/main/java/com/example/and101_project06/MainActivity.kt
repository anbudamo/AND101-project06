package com.example.and101_project06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var nasaItems: MutableList<NasaData>
    private lateinit var rvNasa: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNasa = findViewById(R.id.nasa_list)
        nasaItems = mutableListOf()

        fetchApodData()
    }

    // APOD- Astronomy Picture Of the Day
    private fun fetchApodData() {
        val client = AsyncHttpClient()

        client["https://api.nasa.gov/planetary/apod?api_key=NzmWTsbM4lJMr4gGc3Bsv93wfx5pZD0LaSIcKdOi&count=10", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("NASA", "response successful")
                val nasaImageArray = json.jsonArray
                for (i in 0 until nasaImageArray.length()) {
                    val imageUrl = nasaImageArray.getJSONObject(i).getString("url")
                    val title = nasaImageArray.getJSONObject(i).getString("title")
                    val explanation = nasaImageArray.getJSONObject(i).getString("explanation")
                    val date = nasaImageArray.getJSONObject(i).getString("date")
                    nasaItems.add(NasaData(imageUrl, title, explanation, date))
                }

                val adapter = NasaAdapter(nasaItems)
                rvNasa.adapter = adapter
                rvNasa.layoutManager = LinearLayoutManager(this@MainActivity)
                rvNasa.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("NASA", errorResponse)
            }
        }]
    }
}