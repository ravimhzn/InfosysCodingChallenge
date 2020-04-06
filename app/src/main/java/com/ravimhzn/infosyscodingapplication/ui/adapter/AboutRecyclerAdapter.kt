package com.ravimhzn.infosyscodingapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.databinding.CustomListViewBinding
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import com.ravimhzn.infosyscodingapplication.ui.viemodels.AboutRecyclerAdapterViewModel

class AboutRecyclerAdapter : RecyclerView.Adapter<AboutRecyclerAdapter.AboutViewHolder>() {

    private lateinit var arrRow: List<Row>

    fun setCountryInfo(arrRow: List<Row>) {
        if (arrRow != null) {
            this.arrRow = arrRow
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutViewHolder {
        val binding: CustomListViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.custom_list_view, parent, false
        )
        return AboutViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::arrRow.isInitialized) arrRow.size else 0
    }

    override fun onBindViewHolder(holder: AboutViewHolder, position: Int) {
        holder.bind(arrRow[position])
    }

    class AboutViewHolder(private val binding: CustomListViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = AboutRecyclerAdapterViewModel()

        fun bind(row: Row) {
            viewModel.bind(row)
            binding.viewModel = viewModel
        }
    }
}