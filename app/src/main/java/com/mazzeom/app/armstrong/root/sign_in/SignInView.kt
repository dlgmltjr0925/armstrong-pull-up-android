package com.mazzeom.app.armstrong.root.sign_in

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.AttributeSet
import android.util.Log
import android.widget.GridView
import android.widget.LinearLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.uber.rib.core.Initializer
import io.reactivex.Observable
import me.rajin.butterkt.ButterKt
import me.rajin.butterkt.bindView

/**
 * Top level view for {@link SignInBuilder.SignInScope}.
 */
class SignInView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle),
    SignInInteractor.SignInPresenter {
    private val profileGridView by bindView<GridView>(R.id.profileGridView);
    private var profiles: Array<ProfileDTO> = arrayOf()

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKt.bind(this)
    }

    override fun setProfiles(profiles: Array<ProfileDTO>) {
        this.profiles = profiles
        val profileAdapter = ProfileAdapter(context, profiles)
        profileGridView.adapter = profileAdapter
    }

    override fun onClickProfile(): Observable<ProfileDTO> {
        return Observable.create { emitter ->
            profileGridView.setOnItemClickListener { parent, view, position, id ->
                val profile = profileGridView.adapter.getItem(position) as ProfileDTO
                Log.d("onClickProfile", profile.toString())
                emitter.onNext(profile)
            }
        }
    }

    override fun onClickDeleteProfile(): Observable<ProfileDTO> {
        return Observable.create { emitter ->
            profileGridView.setOnItemLongClickListener { parent, view, position, id ->
                val profile = this.profiles[position]
                val builder = androidx.appcompat.app.AlertDialog.Builder(context).apply {
                    setTitle("${profile.nickname}을(를) 삭제하시겠습니까?")
                    setPositiveButton("삭제", DialogInterface.OnClickListener { dialog, which ->
                        emitter.onNext(profile)
                    })
                    setNegativeButton("취소", DialogInterface.OnClickListener { dialog, which ->

                    })
                }
                builder.show()
                true
            }
        }
    }
}
