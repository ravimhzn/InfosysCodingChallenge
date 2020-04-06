package com.ravimhzn.infosyscodingapplication.utils.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.ravimhzn.infosyscodingapplication.R
import com.ravimhzn.infosyscodingapplication.extension.inflateCustomLayout
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import org.jetbrains.anko.textColor

/**
 * Simple Toolbar which is customizable
 */
class AppToolbar(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    init {
        inflateCustomLayout(R.layout.custom_toolbar)
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.AppToolbar, 0, 0
        ).apply {
            try {
                titleLabel.text = getString(R.styleable.AppToolbar_title)
                val color = getColor(
                    R.styleable.AppToolbar_color,
                    ContextCompat.getColor(context, R.color.white)
                )
                titleLabel.textColor = color
            } finally {
                recycle()
            }
        }
    }
}