package com.mazzeom.app.armstrong.root.sign_up

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.mazzeom.app.armstrong.R
import io.reactivex.Observable
import me.rajin.butterkt.ButterKt
import me.rajin.butterkt.bindView

/**
 * Top level view for {@link SignUpBuilder.SignUpScope}.
 */
class SignUpView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
	LinearLayout(context, attrs, defStyle), SignUpInteractor.SignUpPresenter {
	val backButton by bindView<ImageView>(R.id.backButton)
	val profileImageText by bindView<TextView>(R.id.profileImageText)
	val profileNickNameEditText by bindView<EditText>(R.id.profileNickNameEditText)
	val enrollButton by bindView<TextView>(R.id.enrollButton)

	override fun onFinishInflate() {
		super.onFinishInflate()
		ButterKt.bind(this)

		profileNickNameEditText.doOnTextChanged { text, _, _, _ ->
			val nickname = text.toString().trim()
			val length = nickname.length
			profileImageText.text = when(length) {
				0 -> ""
				2 -> nickname.slice(0..1).toUpperCase()
				else -> nickname[0].toString().toUpperCase()
			}

			if (length > 0 && !enrollButton.isVisible) {
				enrollButton.isVisible = true

			} else if (length == 0 && enrollButton.isVisible) {
				enrollButton.isVisible = false
			}
		}
	}

	override fun onClickEnrollButton(): Observable<String> {
		return Observable.create { emitter ->
			enrollButton.setOnClickListener {
				val nickname = profileNickNameEditText.text.toString().trim()
				emitter.onNext(nickname)
			}
		}
	}

	override fun onClickBackButton(): Observable<Unit> {
		return Observable.create { emitter ->
			backButton.setOnClickListener {
				emitter.onNext(Unit)
			}
		}
	}
}
