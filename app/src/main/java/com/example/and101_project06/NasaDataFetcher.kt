package com.example.and101_project06

import android.content.Context
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.io.InputStream

class NasaDataFetcher(private val context: Context, private val nasaData: NasaData) : DataFetcher<InputStream> {

    private var call: Call? = null
    private var stream: InputStream? = null

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        val client = OkHttpClient() // Create an instance of OkHttpClient to make network requests
        val request = Request.Builder().url(nasaData.imageUrl).build()

        call = client.newCall(request)
        try {
            val response: Response = call!!.execute()
            if (response.isSuccessful) {
                stream = response.body?.byteStream()
                callback.onDataReady(stream) // Pass the InputStream to Glide
            } else {
                callback.onLoadFailed(IOException("Request failed with code: ${response.code}"))
            }
        } catch (e: IOException) {
            callback.onLoadFailed(e)
        }
    }

    override fun cleanup() {
        try {
            stream?.close()
        } catch (e: IOException) {
            // Ignore IOException
        }
    }

    override fun cancel() {
        call?.cancel()
    }

    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.REMOTE
    }
}