package com.mazzeom.app.armstrong.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.mazzeom.app.armstrong.R

/**
 * Top level view for {@link MainBuilder.MainScope}.
 */
class MainView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), MainInteractor.MainPresenter {
    private lateinit var profileNickname: TextView

    override fun setProfileNickname(nickname: String) {
        profileNickname = findViewById(R.id.profileNickName)
        profileNickname.text = nickname
    }
}
