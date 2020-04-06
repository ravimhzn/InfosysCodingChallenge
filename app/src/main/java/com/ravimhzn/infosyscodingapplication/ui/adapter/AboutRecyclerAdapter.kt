package com.ravimhzn.infosyscodingapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.extension.setImageUrl
import com.ravimhzn.infosyscodingapplication.ui.model.Row

class AboutRecyclerAdapter : RecyclerView.Adapter<AboutRecyclerAdapter.AboutViewHolder>() {

    private var arrRow: List<Row> = ArrayList()

    fun setCountryInfo(arrRow: List<Row>) {
        if (arrRow != null) {
            this.arrRow = arrRow
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        return AboutViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_list_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arrRow.size
    }

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.bind(arrRow[position])
    }

    class AboutViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var title: TextView =
            itemView.findViewById(R.id.tvTitle)

        private var description: TextView =
            itemView.findViewById(R.id.tvDescription)

        private var imageView: ImageView =
            itemView.findViewById(R.id.imageNews)

        fun bind(row: Row) {
            row?.let {
                title.text = it.title
                description.text = it.description
                imageView.setImageUrl(it.imageHref)
            }
        }
    }
}