package tech.thdev.kotlin_udemy_sample.view.image.adapter

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.kotlin_udemy_sample.view.image.adapter.holder.ImageViewHolder
import tech.thdev.kotlin_udemy_sample.data.PhotoItem
import tech.thdev.kotlin_udemy_sample.view.image.adapter.model.ImageViewAdapterContract
import java.util.*

/**
 * Created by tae-hwan on 10/29/16.
 */

class ImageAdapter(private val context: Context) :
        RecyclerView.Adapter<ImageViewHolder>(),
        ImageViewAdapterContract.View, ImageViewAdapterContract.Model {

    private val itemList: MutableList<PhotoItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
            ImageViewHolder(context, parent)

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Log.d("TAG", "onBindViewHolder position=" + position)
        holder.bindView(getItem(position), position)
    }

    // Item size를 return
    override fun getItemCount() = itemList.size

    /**
     * Item list의 item을 return
     */
    private fun getItem(position: Int) = itemList[position]

    override fun reload() {
        notifyDataSetChanged()
    }

    override fun addItem(item: PhotoItem) {
        itemList.add(item)
    }


}