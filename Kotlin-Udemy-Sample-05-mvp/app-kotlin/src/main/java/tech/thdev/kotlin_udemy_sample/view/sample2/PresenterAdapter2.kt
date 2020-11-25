package tech.thdev.kotlin_udemy_sample.view.sample2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.thdev.kotlin_udemy_sample.R
import java.util.ArrayList

class PresenterAdapter2(private val context: Context) : RecyclerView.Adapter<PresenterAdapter2.PresenterViewHolder2>() {
    private val itemList = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresenterViewHolder2 {
        return PresenterViewHolder2(parent)
    }

    override fun onBindViewHolder(holder: PresenterViewHolder2, position: Int) {
        holder.bindView(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(index: Int) {
        itemList.add(index)
    }

    inner class PresenterViewHolder2(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(
        R.layout.item_sample, parent, false)) {

        private val textView by lazy {
            itemView?.findViewById(R.id.text) as TextView
        }

        fun bindView(index: Int) {
            textView.text = "index=$index"
        }
    }




}