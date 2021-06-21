package com.mazzeom.app.armstrong.root.main

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), MainInteractor.MainPresenter {

	}
