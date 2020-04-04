package com.example.uicomponents.category

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.uicomponents.R
import com.example.uicomponents.helpers.loadImageFromUrl
import kotlinx.android.synthetic.main.category_view.view.*

class CategoryView:ConstraintLayout
{
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)

    }

    private fun init(context: Context) {
        View.inflate(context, R.layout.category_view, this)
    }

    fun setCover(res:Any){
        cover.loadImageFromUrl(res)
    }

    fun setTitle(text:String){
        titleTv.text = text
    }
}