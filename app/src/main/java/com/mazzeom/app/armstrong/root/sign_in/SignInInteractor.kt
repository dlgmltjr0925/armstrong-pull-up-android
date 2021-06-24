package com.mazzeom.app.armstrong.root.sign_in

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.response.GetProfileResponse
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.request.DeleteProfileRequestBody
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

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    GetProfiles().start()

    presenter
      .onClickProfile()
      .subscribe { profile ->
        Log.d("onClickProfile", profile.toString())
        if (profile.id == 0) {
          listener.signUp()
        } else {
          listener.login(profile)
        }
      }

    presenter
      .onClickDeleteProfile()
      .subscribe { profile ->
        DeleteProfileById(profile).start()
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
    fun onClickDeleteProfile(): Observable<ProfileDTO>
  }

  interface Listener {
    fun login(profile: ProfileDTO)
    fun signUp()
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

  inner class DeleteProfileById(profile: ProfileDTO): Thread() {
    val profile = profile
    override fun run() {
      val service = Api.create()
      service.deleteProfileRequest(profile.id).enqueue(object : Callback<HashMap<String, Any>> {
        override fun onResponse(call: Call<HashMap<String, Any>>, response: Response<HashMap<String, Any>>) {
          GetProfiles().start()
        }

        override fun onFailure(call: Call<HashMap<String, Any>>, t: Throwable) {
          Log.d("DeleteProfileById", t.toString())
        }
      })
    }
  }

}
