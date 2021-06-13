package com.mazzeom.app.armstrong.root.main.daily_tab

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.mazzeom.app.armstrong.R
import io.reactivex.Observable

/**
 * Top level view for {@link DailyTabBuilder.DailyTabScope}.
 */
class DailyTabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), DailyTabInteractor.DailyTabPresenter {
    val tag = "DailyTabView"
    lateinit var dailyDateTextView: TextView
    lateinit var prevDateButton: LinearLayout
    lateinit var nextDateButton: LinearLayout

    override fun onFinishInflate() {
        super.onFinishInflate()
        dailyDateTextView = findViewById(R.id.dailyDateTextView)
        prevDateButton = findViewById(R.id.prevDateButton)
        nextDateButton = findViewById(R.id.nextDateButton)
    }

    override fun setFormattedDate(formattedDate: String) {
        dailyDateTextView.text = formattedDate
    }

    override fun onClickPrevDateButton(): Observable<Unit> {
        return Observable.create { emitter ->
            prevDateButton.setOnClickListener {
                emitter.onNext(Unit)
            }
        }
    }

    override fun onClickNextDateButton(): Observable<Unit> {
        return Observable.create { emitter ->
            nextDateButton.setOnClickListener {
                emitter.onNext(Unit)
            }
        }
    }
}
