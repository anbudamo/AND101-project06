package com.example.and101_project06

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NasaAdapter (private val nasaItems: List<NasaData>) : RecyclerView.Adapter<NasaAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nasaImage: ImageView
        init {
            // Find our RecyclerView item's ImageView for future use
            nasaImage = view.findViewById(R.id.nasa_image)
        }

        val titleTextView: TextView
        init {
            titleTextView = view.findViewById(R.id.title_textView)
        }

        val explanationTextView: TextView
        init {
            explanationTextView = view.findViewById(R.id.explanation_textView)
        }

        val dateTextView: TextView
        init {
            dateTextView = view.findViewById(R.id.date_textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nasa_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = nasaItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nasaItem = nasaItems[position]
        holder.titleTextView.text = nasaItem.title
        holder.explanationTextView.text = nasaItem.explanation
        holder.dateTextView.text = nasaItem.date

        Glide.with(holder.itemView)
            .load(nasaItem.imageUrl)
            .centerCrop()
            .into(holder.nasaImage)

        holder.itemView.setOnClickListener() {
            Toast.makeText(holder.itemView.context, "Clicked: ${nasaItem.title}", Toast.LENGTH_SHORT).show()
        }
    }

}