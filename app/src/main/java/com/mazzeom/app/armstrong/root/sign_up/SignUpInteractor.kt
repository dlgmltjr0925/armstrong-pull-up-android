package com.mazzeom.app.armstrong.root.sign_up

import android.annotation.SuppressLint
import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.request.PostProfileRequestBody
import com.mazzeom.app.armstrong.libs.api.response.PostProfileResponse
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SignUpScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SignUpInteractor : Interactor<SignUpInteractor.SignUpPresenter, SignUpRouter>() {

  @Inject
  lateinit var presenter: SignUpPresenter
  @Inject
  lateinit var listener: Listener

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter
      .onClickBackButton()
      .subscribe {
        listener.goBack()
      }

    presenter
      .onClickEnrollButton()
      .subscribe { nickname ->
        PostProfile(nickname).start()
      }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface SignUpPresenter {
    fun onClickBackButton(): Observable<Unit>
    fun onClickEnrollButton(): Observable<String>
  }

  interface Listener {
    fun goBack()
    fun signUp(profile: ProfileDTO)
  }

  inner class PostProfile(nickname: String): Thread() {
    val nickname = nickname
    override fun run() {
      val service = Api.create()
      val parameters = PostProfileRequestBody(nickname)
      service
        .postProfileRequest(parameters)
        .enqueue(object : Callback<PostProfileResponse> {
          override fun onResponse(call: Call<PostProfileResponse>, response: Response<PostProfileResponse>) {
            val profile = response.body()?.profile
            if (profile != null) {
              listener.signUp(profile)
            }
          }

          override fun onFailure(call: Call<PostProfileResponse>, t: Throwable) {
            Log.d("SignUpInteractor", t.toString())
          }
        })
    }
  }
}