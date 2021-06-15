package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.mazzeom.app.armstrong.R

/**
 * Top level view for {@link WeekdayBuilder.WeekdayScope}.
 */
class WeekdayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), WeekdayInteractor.WeekdayPresenter {

	lateinit var categoryTextView: TextView
	lateinit var explainTextView: TextView

	override fun onFinishInflate() {
		super.onFinishInflate()

		categoryTextView = findViewById(R.id.categoryTextView)
		explainTextView = findViewById(R.id.explainTextView)
	}

	override fun setCategory(category: Array<String>) {
		categoryTextView.text = category[0]
		explainTextView.text = category[1]
	}
}
