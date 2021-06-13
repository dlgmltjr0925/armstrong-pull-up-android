package com.mazzeom.app.armstrong.root.main.daily_tab.push_up

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * Top level view for {@link PushUpBuilder.PushUpScope}.
 */
class PushUpView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), PushUpInteractor.PushUpPresenter
