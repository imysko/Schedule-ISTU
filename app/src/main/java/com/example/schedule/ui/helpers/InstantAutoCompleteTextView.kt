package com.example.timetable.ui.helpers

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet

class InstantAutoCompleteTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatAutoCompleteTextView(context, attrs) {

    override fun enoughToFilter(): Boolean = true

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused && adapter != null)
            showDropDown()
    }
}