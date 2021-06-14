package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday

import android.app.Service
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.flexbox.FlexboxLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.utils.Dimension
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpView


/**
 * Top level view for {@link MondayBuilder.MondayScope}.
 */
class MondayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), MondayInteractor.MondayPresenter {

	companion object {
		val STATE_FETCH_INIT = 0
		val STATE_FETCHING = 1
		val STATE_FETCHED = 2
	}

	lateinit var pullUpFlexboxLayout: FlexboxLayout
	var countItems: MutableList<LinearLayout> = mutableListOf()

	override fun onFinishInflate() {
		super.onFinishInflate()

		pullUpFlexboxLayout = findViewById(R.id.pullUpFlexboxLayout)
		val layoutInflater = context.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater

		for (i in 0..4) {
			val countItem = layoutInflater.inflate(R.layout.count_item, this, false) as LinearLayout
			countItems.add(countItem)
			pullUpFlexboxLayout.addView(countItem)
		}
	}

	override fun setCountItem(order: Int, count: Int, state: Int) {
		val countItem = countItems[order - 1]
		val textView = countItem.findViewById<TextView>(R.id.countItemTextView)
		textView.text = count.toString()
		val colorResourceId = when (state) {
			STATE_FETCHING -> R.color.fetching
			STATE_FETCHED -> R.color.fetched
			else -> R.color.fetch_init
		}
		(countItem.background as GradientDrawable).setStroke(
			Dimension.dpToPx(3.toFloat(), context),
			ContextCompat.getColor(context, colorResourceId)
		)
	}
}
