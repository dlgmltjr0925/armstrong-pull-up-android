package com.mazzeom.app.armstrong.root.main.daily_tab

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.mazzeom.app.armstrong.R
import com.uber.rib.core.Initializer

/**
 * Top level view for {@link DailyTabBuilder.DailyTabScope}.
 */
class DailyTabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), DailyTabInteractor.DailyTabPresenter {
    val tag = "DailyTabView"
    lateinit var dailyText: TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(tag, "onFinishInflate")
        dailyText = findViewById(R.id.dailyText)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(tag, "onAttachedToWindow")
    }
}
