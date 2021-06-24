package com.mazzeom.app.armstrong.root.main.daily_tab.count_input

import android.app.Service
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Dimension
import com.mazzeom.app.armstrong.R
import io.reactivex.Observable
import me.rajin.butterkt.ButterKt
import me.rajin.butterkt.bindView

/**
 * Top level view for {@link CountInputBuilder.CountInputScope}.
 */
class CountInputView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), CountInputInteractor.CountInputPresenter {
	val countTextView by bindView<TextView>(R.id.countTextView)
	val numberPadGridLayout by bindView<GridLayout>(R.id.numberPadGridLayout)
	val cancelButton by bindView<TextView>(R.id.cancelButton)
	val okButton by bindView<TextView>(R.id.okButton)

	var numberTextViews: MutableList<TextView> = mutableListOf()
	lateinit var layoutInflater: LayoutInflater

	companion object {
		val numbers = arrayOf<String>("1", "2","3", "4", "5", "6", "7", "8", "9", "", "0", "bs")
	}

	override fun onFinishInflate() {
		super.onFinishInflate()
		ButterKt.bind(this)

		layoutInflater = context.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater

		for (number in numbers) {
			val numberButton = layoutInflater.inflate(R.layout.count_input_item, this, false) as LinearLayout
			val layoutParams = GridLayout.LayoutParams()
			layoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
			numberButton.layoutParams = layoutParams
			val numberTextView = numberButton.findViewById<TextView>(R.id.numberTextView)
			when(number) {
				"" -> {
					numberButton.removeAllViews()
				}
				"bs" -> {
					numberTextView.text = "<-"
					numberTextViews.add(numberTextView)
				}
				else -> {
					numberTextView.text = number.toString()
					numberTextViews.add(numberTextView)
				}
			}

			numberPadGridLayout.addView(numberButton)
		}
	}

	override fun onClickNumberButton(): Observable<String> {
		return Observable.create { emitter ->
			numberTextViews.forEach { numberTextView ->
				numberTextView.setOnClickListener {
					val text = numberTextView.text.toString()
					when (text) {
						"<-" -> emitter.onNext("bs")
						else -> emitter.onNext(text)
					}
				}
			}
		}
	}

	override fun setCountNumber(number: Int) {
		countTextView.text = number.toString()
	}
}
