package com.mazzeom.app.armstrong.root.sign_in.new_profile

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Top level view for {@link NewProfileBuilder.NewProfileScope}.
 */
class NewProfileView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle), NewProfileInteractor.NewProfilePresenter
