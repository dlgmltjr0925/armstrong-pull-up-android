package com.mazzeom.app.armstrong.root.main.profile_tab

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout

/**
 * Top level view for {@link ProfileTabBuilder.ProfileTabScope}.
 */
class ProfileTabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), ProfileTabInteractor.ProfileTabPresenter
