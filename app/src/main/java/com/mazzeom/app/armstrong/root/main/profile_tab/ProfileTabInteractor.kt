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
  @Inject lateinit var onChangeProfile: Observable<ProfileDTO>
  @Inject lateinit var profileTabListener: Listener

  lateinit var profile: ProfileDTO

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    onChangeProfile.subscribe {
      this.profile = it
      presenter.setNickname(it.nickname)
    }

    presenter.onClickSave().subscribe { nickname ->
      Log.d("UpdateProfile", nickname)
      val profile = ProfileDTO(this.profile.id, nickname)
      UpdateProfile(profile).start()
    }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  interface Listener {
    fun onUpateProfile(profile: ProfileDTO)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface ProfileTabPresenter {
    fun setNickname(nickname: String)
    fun onClickSave(): Observable<String>
  }


  inner class UpdateProfile(profile: ProfileDTO): Thread() {
    val profile = profile
    override fun run() {
      Log.d("UpdateProfile", "run")
      val service = Api.create()
      service
        .putProfileRequest(PutProfileRequestBody(profile.id, profile.nickname))
        .enqueue(object: Callback<PutProfileResponse> {
          override fun onResponse(
            call: Call<PutProfileResponse>,
            response: Response<PutProfileResponse>
          ) {
            Log.d("UpdateProfile", response?.toString())
            var profile = response?.body()?.profile
            profile?.let {
              profileTabListener.onUpateProfile(profile)
            }
          }

          override fun onFailure(call: Call<PutProfileResponse>, t: Throwable) {
            Log.d("UpdateProfile", "onFailure")
          }
        })
    }
  }

}
