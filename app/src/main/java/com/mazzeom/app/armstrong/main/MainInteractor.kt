package com.mazzeom.app.armstrong.main

import com.mazzeom.app.armstrong.libs.api.response.ProfileDTO
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MainScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class MainInteractor(profile: ProfileDTO) : Interactor<MainInteractor.MainPresenter, MainRouter>() {
  var profile = profile

  @Inject
  lateinit var presenter: MainPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter.setProfileNickname(profile.nickname)
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MainPresenter {
    fun setProfileNickname(nickname: String)
  }
}
