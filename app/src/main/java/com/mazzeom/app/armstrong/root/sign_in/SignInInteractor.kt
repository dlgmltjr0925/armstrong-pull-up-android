package com.mazzeom.app.armstrong.root.sign_in

import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.response.GetProfileResponse
import com.mazzeom.app.armstrong.libs.api.response.ProfileDTO
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Coordinates Business Logic for [SignInScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class SignInInteractor : Interactor<SignInInteractor.SignInPresenter, SignInRouter>() {

  @Inject lateinit var listener: Listener
  @Inject lateinit var presenter: SignInPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    GetProfiles().start()

    presenter
      .onClickProfile()
      .subscribe { profile ->
        listener.login(profile)
      }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface SignInPresenter {
    fun setProfiles(profiles: Array<ProfileDTO>)
    fun onClickProfile(): Observable<ProfileDTO>
  }

  interface Listener {
    fun login(profile: ProfileDTO)
  }

  inner class GetProfiles: Thread() {
    override fun run() {
      val service = Api.create()
      service.getProfileRequest().enqueue(object : Callback<GetProfileResponse> {
        override fun onResponse(
          call: Call<GetProfileResponse>,
          response: Response<GetProfileResponse>
        ) {
          val profiles = response.body()?.profiles
          presenter.setProfiles(profiles!!)
        }

        override fun onFailure(call: Call<GetProfileResponse>, t: Throwable) {
          Log.d("SignInInteractor", t.toString())
        }
      })
    }
  }
}
