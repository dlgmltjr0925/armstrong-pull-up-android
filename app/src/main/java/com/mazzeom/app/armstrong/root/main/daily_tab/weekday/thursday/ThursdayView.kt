package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday

import android.app.Service
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.utils.Dimension
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday.TuesdayView

/**
 * Top level view for {@link ThursdayBuilder.ThursdayScope}.
 */
class ThursdayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), ThursdayInteractor.ThursdayPresenter {
	companion object {
		val STATE_FETCHING = 1
		val STATE_FETCHED = 2
	}

	lateinit var pullUpGridLayout: GridLayout
	lateinit var layoutInflater: LayoutInflater
	val countItems: MutableList<LinearLayout> = mutableListOf()

	override fun onFinishInflate() {
		super.onFinishInflate()

		pullUpGridLayout = findViewById(R.id.pullUpGridLayout)
		layoutInflater = context.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater
	}

	fun setItemStroke(countItem: LinearLayout, state: Int) {
		val countItemLinearLayout = countItem.findViewById<LinearLayout>(R.id.countItemLinearLayout)
		val colorResourceId = when (state) {
			TuesdayView.STATE_FETCHING -> R.color.fetching
			else -> R.color.fetched
		}

		(countItemLinearLayout.background as GradientDrawable).setStroke(
			Dimension.dpToPx(3, context),
			ContextCompat.getColor(context, colorResourceId)
		)
	}

	override fun addCountItem(order: Int, count: Int, state: Int) {
		val countItem = layoutInflater.inflate(R.layout.count_item, this, false) as LinearLayout

		val layoutParams = GridLayout.LayoutParams()
		layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
		layoutParams.bottomMargin = Dimension.dpToPx(20, context)
		countItem.layoutParams = layoutParams
		setItemStroke(countItem, state)
		val countItemTextView = countItem.findViewById<TextView>(R.id.countItemTextView)
		countItemTextView.text = count.toString()

		countItems.add(countItem)
		pullUpGridLayout.addView(countItem)
	}
}
