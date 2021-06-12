package com.mazzeom.app.armstrong.root.main.profile_tab

import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.request.PutProfileRequestBody
import com.mazzeom.app.armstrong.libs.api.response.GetProfileResponse
import com.mazzeom.app.armstrong.libs.api.response.PutProfileResponse
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ProfileTabScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class ProfileTabInteractor : Interactor<ProfileTabInteractor.ProfileTabPresenter, ProfileTabRouter>() {

  @Inject lateinit var presenter: ProfileTabPresenter
  @Inject lateinit var profile: ProfileDTO

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.setInitialNickname(profile.nickname)

    presenter.onClickSave().subscribe { nickname ->
      if (profile.nickname != nickname) {
        var profile = ProfileDTO(this.profile.id, this.profile.nickname)
        UpdateProfile(profile).start()
      }
    }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface ProfileTabPresenter {
    fun setInitialNickname(nickname: String)
    fun onClickSave(): Observable<String>
  }


  inner class UpdateProfile(profile: ProfileDTO): Thread() {
    override fun run() {
      val service = Api.create()
      service
        .putProfileRequest(PutProfileRequestBody(profile.id, profile.nickname))
        .enqueue(object: Callback<PutProfileResponse> {
          override fun onResponse(
            call: Call<PutProfileResponse>,
            response: Response<PutProfileResponse>
          ) {
            Log.d("UpdateProfile", response?.body()?.profile.toString())
          }

          override fun onFailure(call: Call<PutProfileResponse>, t: Throwable) {
            Log.d("UpdateProfile", "onFailure")
          }
        })
    }
  }
}
