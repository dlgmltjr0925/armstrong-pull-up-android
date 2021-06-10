package com.mazzeom.app.armstrong.sign_in

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView
import android.widget.LinearLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.response.ProfileDTO
import com.uber.rib.core.Initializer
import io.reactivex.Observable

/**
 * Top level view for {@link SignInBuilder.SignInScope}.
 */
class SignInView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle), SignInInteractor.SignInPresenter {
    private lateinit var profileGridView: GridView;
    private var profiles: Array<ProfileDTO> = arrayOf()

    @Initializer
    override fun onFinishInflate() {
        super.onFinishInflate()
        profileGridView = findViewById(R.id.profileGridView)
    }

    override fun setProfiles(profiles: Array<ProfileDTO>) {
        this.profiles = profiles
        val profileAdapter = ProfileAdapter(context, profiles)
        profileGridView.adapter = profileAdapter
    }

    override fun onClickProfile(): Observable<ProfileDTO> {
        return Observable.create {
            profileGridView.setOnItemClickListener { parent, view, position, id ->
                it.onNext(this.profiles[position])
            }
        }
    }
}
