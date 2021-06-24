package com.mazzeom.app.armstrong.root.main.profile_tab

import android.app.Service
import android.content.Context
import android.inputmethodservice.InputMethodService
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.uber.rib.core.Initializer
import io.reactivex.Emitter
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Top level view for {@link ProfileTabBuilder.ProfileTabScope}.
 */
class ProfileTabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), ProfileTabInteractor.ProfileTabPresenter {
    lateinit var profileNickNameEditText: EditText
    lateinit var profileImageText: TextView
    lateinit var profileSaveButton: TextView
    lateinit var inputMethodManager: InputMethodManager
    lateinit var logoutButton: TextView

    @Initializer
    override fun onFinishInflate() {
        super.onFinishInflate()
        profileNickNameEditText = findViewById(R.id.profileNickNameEditText)
        profileImageText = findViewById(R.id.profileImageText)
        profileSaveButton = findViewById(R.id.profileSaveButton)
        inputMethodManager = context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
        logoutButton = findViewById(R.id.logoutTextView)

        profileNickNameEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
            }
        }

        profileNickNameEditText.doOnTextChanged { text, start, before, count ->
            val nickname = text.toString().trim()
            profileImageText.text = when(nickname.length) {
                0 -> ""
                2 -> nickname.slice(0..1).toUpperCase()
                else -> nickname[0].toString().toUpperCase()
            }
        }
    }

    override fun setNickname(nickname: String) {
        if (profileNickNameEditText.text.toString() == "") profileNickNameEditText.setText(nickname)
    }

    override fun onClickSave(): Observable<String> {
        return Observable.create { emitter ->
            profileSaveButton.setOnClickListener {
                if (profileNickNameEditText.isFocused) profileNickNameEditText.clearFocus()
                emitter.onNext(profileNickNameEditText.text.toString())
            }
        }
    }

    override fun onClickLogout(): Observable<String> {
        return Observable.create { emitter ->
            logoutButton.setOnClickListener {
                emitter.onNext("")
            }
        }
    }
}
