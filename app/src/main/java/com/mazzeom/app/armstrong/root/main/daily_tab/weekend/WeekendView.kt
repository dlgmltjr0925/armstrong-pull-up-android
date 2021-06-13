package com.mazzeom.app.armstrong.root.main.daily_tab.weekend

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * Top level view for {@link WeekendBuilder.WeekendScope}.
 */
class WeekendView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    LinearLayout(context, attrs, defStyle), WeekendInteractor.WeekendPresenter {

}
