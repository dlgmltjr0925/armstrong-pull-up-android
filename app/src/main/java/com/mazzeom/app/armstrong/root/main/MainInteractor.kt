package com.mazzeom.app.armstrong.root.main

import android.util.Log
import com.mazzeom.app.armstrong.libs.api.response.ProfileDTO
import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationInteractor
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
  var currentTabId: Int = 0

  @Inject
  lateinit var presenter: MainPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    
    router.attachBottomNavigation()
    router.attachDailyTab()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  inner class BottomNavigationListener: BottomNavigationInteractor.Listener {
    override fun onClickNavigationItem(tabId: Int) {
      if (currentTabId != tabId) {
        router.replaceNavigationTab(currentTabId, tabId)
        currentTabId = tabId
      }
    }
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MainPresenter
}
