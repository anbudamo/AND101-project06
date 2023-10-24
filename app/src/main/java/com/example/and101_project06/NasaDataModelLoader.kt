package com.example.and101_project06

import android.content.Context
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.signature.ObjectKey
import java.io.InputStream

class NasaDataModelLoader(private val context: Context) : ModelLoader<NasaData, InputStream> {

    override fun handles(model: NasaData): Boolean {
        return true
    }

    override fun buildLoadData(model: NasaData, width: Int, height: Int, options: Options): ModelLoader.LoadData<InputStream>? {
        return ModelLoader.LoadData(ObjectKey(model), NasaDataFetcher(context, model))
    }
}
