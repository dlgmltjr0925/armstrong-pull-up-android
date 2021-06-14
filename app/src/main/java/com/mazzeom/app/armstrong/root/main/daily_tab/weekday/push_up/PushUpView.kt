package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.shapes.Shape
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mazzeom.app.armstrong.R
import org.xmlpull.v1.XmlPullParser
import java.lang.Exception

/**
 * Top level view for {@link PushUpBuilder.PushUpScope}.
 */
class PushUpView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle),
	PushUpInteractor.PushUpPresenter {

	companion object {
		val STATE_FETCH_INIT: Int = 0
		val STATE_FETCHING: Int = 1
		val STATE_FETCHED: Int = 2
	}

	lateinit var pushUpCountItem1: LinearLayout
	lateinit var pushUpCountItem2: LinearLayout
	lateinit var pushUpCountItem3: LinearLayout

	var textView: TextView? = null

	override fun onFinishInflate() {
		super.onFinishInflate()

		pushUpCountItem1 = findViewById(R.id.pushUpCountItem1)
		pushUpCountItem2 = findViewById(R.id.pushUpCountItem2)
		pushUpCountItem3 = findViewById(R.id.pushUpCountItem3)

		setItemStroke(pushUpCountItem1, R.color.fetch_init)
		setItemStroke(pushUpCountItem2, R.color.fetch_init)
		setItemStroke(pushUpCountItem3, R.color.fetch_init)
	}

	fun setItemStroke(item: LinearLayout, colorResourceId: Int) {
		(item.background as GradientDrawable).setStroke(
			dpToPx(3.toFloat(), context),
			ContextCompat.getColor(context, colorResourceId)
		)
	}

	override fun setPushUpCount(order: Int, count: Int, state: Int) {
		Log.d("setPushUpCount", "order: ${order}, count: ${count}")
		val pushUpCountItem: LinearLayout? = when (order) {
			1 -> pushUpCountItem1
			2 -> pushUpCountItem2
			3 -> pushUpCountItem3
			else -> null
		}
		var colorRecourceId = when(state) {
			STATE_FETCHING -> R.color.fetching
			STATE_FETCHED -> R.color.fetched
			else -> R.color.fetch_init
		}
		if (pushUpCountItem != null) {
			setItemStroke(pushUpCountItem, colorRecourceId)
			val textView = pushUpCountItem.findViewById<TextView>(R.id.countItemTextView)
			textView.text = count.toString()
		}
	}

	fun dpToPx(dp: Float, context: Context): Int {
		return (dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()
	}
}


