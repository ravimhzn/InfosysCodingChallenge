package com.ravimhzn.infosyscodingapplication.ui.viemodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.ravimhzn.infosyscodingapplication.ui.model.Row
import javax.inject.Inject

class AboutRecyclerAdapterViewModel @Inject constructor() :
    ViewModel() {
    val _title = MutableLiveData<String>()
    val _description = MutableLiveData<String>()
    val _imageUrl = MutableLiveData<String>()

    fun bind(row: Row) {
        _title.value = row.title
        _description.value = row.description
        _imageUrl.value = row.imageHref
    }

    companion object {

        /**
         * This methods should not have any return type, = declaration would make it return that object declaration.
         */
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(
            view: ImageView,
            url: String
        ) {
            if (!url.isNullOrEmpty()) {
                Glide.with(view.context).load(url).into(view)
            }
        }
    }
}