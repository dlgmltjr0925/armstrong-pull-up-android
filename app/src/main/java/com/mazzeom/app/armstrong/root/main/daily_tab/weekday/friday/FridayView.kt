package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday

import android.app.Service
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.mazzeom.app.armstrong.R
import io.reactivex.Observable

/**
 * Top level view for {@link FridayBuilder.FridayScope}.
 */
class FridayView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), FridayInteractor.FridayPresenter {

	val dayOfWeeks: Array<String> = arrayOf("월", "화", "수", "목")
	val routines: Array<String> = arrayOf("풀업 5세트", "피라미드 루틴", "3그립", "최대 세트수")
	val routineButtons: MutableList<LinearLayout> = mutableListOf()

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
			routineButtons.add(routineButton)

			this.addView(item)
		}
	}

	override fun onClickRoutine(): Observable<Int> {
		return Observable.create { emitter ->
			for(i in 0..3) {
				val routineButton = routineButtons[i]
				routineButton.setOnClickListener {
					emitter.onNext(i + 2)
				}
			}
		}
	}
}
