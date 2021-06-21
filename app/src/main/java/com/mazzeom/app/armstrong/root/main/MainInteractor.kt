package com.mazzeom.app.armstrong.root.main

import android.annotation.SuppressLint
import android.util.Log
import com.jakewharton.rxrelay3.BehaviorRelay
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationInteractor
import com.mazzeom.app.armstrong.root.main.profile_tab.ProfileTabInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MainScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class MainInteractor(profile: ProfileDTO) : Interactor<MainInteractor.MainPresenter, MainRouter>() {
  var relay: BehaviorRelay<ProfileDTO> = BehaviorRelay.createDefault(profile)
  var currentTabId: Int = 0


  @Inject lateinit var presenter: MainPresenter

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    
    router.attachBottomNavigation()
    router.attachDailyTab()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  fun onChangeProfile(): Observable<ProfileDTO> {
    return Observable.create { emitter ->
      relay.subscribe {
        emitter.onNext(it)
      }
    }
  }

  inner class BottomNavigationListener: BottomNavigationInteractor.Listener {
    override fun onClickNavigationItem(tabId: Int) {
      if (currentTabId != tabId) {
        router.replaceNavigationTab(currentTabId, tabId)
        currentTabId = tabId
      }
    }
  }

  inner class ProfileTabListener: ProfileTabInteractor.Listener {
    override fun onUpateProfile(profile: ProfileDTO) {
      relay.accept(profile)
    }
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MainPresenter

  interface Listener {
    fun logout()
  }
}
