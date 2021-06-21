package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday

import android.app.Service
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.mazzeom.app.armstrong.R

/**
 * Top level view for {@link FridayBuilder.FridayScope}.
 */
class FridayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), FridayInteractor.FridayPresenter {

	val dayOfWeeks: Array<String> = arrayOf("월", "화", "수", "목")
	val routines: Array<String> = arrayOf("풀업 5세트", "피라미드 루틴", "3그립", "최대 세트수")

	override fun onFinishInflate() {
		super.onFinishInflate()

		val layoutInflater = context.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater

		for (i in 0..3) {
			val item = layoutInflater.inflate(R.layout.friday_routine_item, this, false) as LinearLayout

			val dayOfWeekTextView = item.findViewById<TextView>(R.id.dayOfWeekTextView)
			dayOfWeekTextView.text = dayOfWeeks[i]

			val routineTextView = item.findViewById<TextView>(R.id.routineTextView)
			routineTextView.text = routines[i]

			val routineButton = item.findViewById<LinearLayout>(R.id.routineButton)
			routineButton.setOnClickListener {
				Log.d("FridayView", "${routines[i]}")
			}

			this.addView(item)
		}
	}
}
