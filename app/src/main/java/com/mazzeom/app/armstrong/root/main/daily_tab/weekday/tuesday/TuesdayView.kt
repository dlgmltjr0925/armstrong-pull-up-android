package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday

import android.app.Service
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginRight
import com.google.android.flexbox.FlexboxLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.utils.Dimension

/**
 * Top level view for {@link TuesdayBuilder.TuesdayScope}.
 */
class TuesdayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), TuesdayInteractor.TuesdayPresenter {
	companion object {
		val STATE_FETCHING = 1
		val STATE_FETCHED = 2
	}

	lateinit var pullUpFlexboxLayout: FlexboxLayout
	lateinit var layoutInflater: LayoutInflater
	val countItems: MutableList<LinearLayout> = mutableListOf()

	override fun onFinishInflate() {
		super.onFinishInflate()

		pullUpFlexboxLayout = findViewById(R.id.pullUpFlexboxLayout)
		layoutInflater = context.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater
	}

	fun setStroke(countItem: LinearLayout, state: Int) {
		val colorResourceId = when (state) {
			STATE_FETCHING -> R.color.fetching
			else -> R.color.fetched
		}

		(countItem.background as GradientDrawable).setStroke(
			Dimension.dpToPx(3.toFloat(), context),
			ContextCompat.getColor(context, colorResourceId)
		)
	}

	override fun addCountItem(order: Int, count: Int, state: Int) {
		var countItem = layoutInflater.inflate(R.layout.count_item, this, false) as LinearLayout

		var layoutParams = countItem.layoutParams as LinearLayout.LayoutParams

		layoutParams.setMargins(0, 0, Dimension.dpToPx(20, context), Dimension.dpToPx(20, context))
		countItem.layoutParams = layoutParams

		val textView = countItem.findViewById<TextView>(R.id.countItemTextView)
		textView.text = count.toString()

		countItems.add(countItem)
		pullUpFlexboxLayout.addView(countItem)
	}

	override fun setCountItem(order: Int, count: Int, state: Int) {
		TODO("Not yet implemented")
	}
}
