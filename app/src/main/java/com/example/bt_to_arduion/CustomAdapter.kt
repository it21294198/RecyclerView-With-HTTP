package com.example.bt_to_arduion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(private val dataset: List<Todo>, private val clickListener: MainActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // ViewHolder class to hold references to the views in each list item
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // Inflating the layout for each item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        // Return the size of the dataset
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Set the text for each item in the dataset
        val position = dataset[position]
        (holder as ViewHolder).textView.text = position.title

        val currentImageUrl = position.url
        if (holder is ViewHolder) {
            // Assuming imageView is the ImageView in your ViewHolder
            Glide.with(holder.imageView.context)
                .load(currentImageUrl)
                .into(holder.imageView)
        }
        holder.imageView.setOnClickListener{
            clickListener.onItemClick(position.id)
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(position: Int)
}
