package com.mazzeom.app.armstrong.root.main.profile_tab

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Top level view for {@link ProfileTabBuilder.ProfileTabScope}.
 */
class ProfileTabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), ProfileTabInteractor.ProfileTabPresenter {
    lateinit var profileNickNameEditText: EditText
    lateinit var profileImageText: TextView
    lateinit var profileSaveButton: TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        profileNickNameEditText = findViewById(R.id.profileNickNameEditText)
        profileImageText = findViewById(R.id.profileImageText)
        profileSaveButton = findViewById(R.id.profileSaveButton)

        profileNickNameEditText.doOnTextChanged { text, start, before, count ->
            val nickname = text.toString()
            profileImageText.text = when(nickname.length) {
                0 -> ""
                2 -> nickname.slice(0..1).toUpperCase()
                else -> nickname[0].toString().toUpperCase()
            }
        }
    }

    override fun setNickname(nickname: String) {
        profileNickNameEditText.setText(nickname)
    }

    override fun onClickSave(): Observable<String> {
        return Observable.create { emitter ->
            profileSaveButton.setOnClickListener {
                emitter.onNext(profileNickNameEditText.text.toString())
            }
        }
    }
}
