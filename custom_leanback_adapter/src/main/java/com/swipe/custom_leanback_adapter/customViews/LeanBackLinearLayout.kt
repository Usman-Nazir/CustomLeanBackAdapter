package com.swipe.custom_leanback_adapter.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class LeanBackLinearLayout : LinearLayout {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}

    override fun clearFocus() {
        if (parent != null)
            super.clearFocus()
    }
}