package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday

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
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday.MondayView

/**
 * Top level view for {@link WednesdayBuilder.WednesdayScope}.
 */
class WednesdayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), WednesdayInteractor.WednesdayPresenter {

	companion object {
		val STATE_FETCH_INIT = 0
		val STATE_FETCHING = 1
		val STATE_FETCHED = 2
	}

	lateinit var layoutInflater: LayoutInflater
	lateinit var pullUpGridLayout: GridLayout
	val countItems: MutableList<LinearLayout> = mutableListOf()

	override fun onFinishInflate() {
		super.onFinishInflate()

		pullUpGridLayout = findViewById(R.id.pullUpGridLayout)
		layoutInflater = context.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater

		for (i in 0..8) {
			val countItem =  layoutInflater.inflate(R.layout.count_item, this, false) as LinearLayout
			val layoutParams = GridLayout.LayoutParams()
			layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
			layoutParams.height = Dimension.dpToPx(80, context)
			countItem.layoutParams = layoutParams
			val countItemTextView = countItem.findViewById<TextView>(R.id.countItemTextView)
			countItemTextView.text = "0"
			countItems.add(countItem)
			pullUpGridLayout.addView(countItem)
		}
	}

	fun setItemStroke(countItem: LinearLayout, state: Int) {
		val countItemLinearLayout = countItem.findViewById<LinearLayout>(R.id.countItemLinearLayout)
		val colorResourceId = when (state) {
			MondayView.STATE_FETCHING -> R.color.fetching
			MondayView.STATE_FETCHED -> R.color.fetched
			else -> R.color.fetch_init
		}
		(countItemLinearLayout.background as GradientDrawable).setStroke(
			Dimension.dpToPx(3.toFloat(), context),
			ContextCompat.getColor(context, colorResourceId)
		)
	}

	override fun setCountItem(order: Int, count: Int, state: Int) {
		val countItem = countItems[order - 1]
		setItemStroke(countItem, state)
		val countItemTextView = countItem.findViewById<TextView>(R.id.countItemTextView)
		countItemTextView.text = count.toString()
	}
}
