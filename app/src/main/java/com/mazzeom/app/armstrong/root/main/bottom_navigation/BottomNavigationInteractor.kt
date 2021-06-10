package com.mazzeom.app.armstrong.root.main.bottom_navigation

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [BottomNavigationScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class BottomNavigationInteractor : Interactor<BottomNavigationInteractor.BottomNavigationPresenter, BottomNavigationRouter>() {

  @Inject lateinit var listener: Listener
  @Inject lateinit var presenter: BottomNavigationPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter
      .onClickNavigationItem()
      .subscribe {
        listener.onClickNavigationItem(it)
      }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface BottomNavigationPresenter {
    fun onClickNavigationItem(): Observable<Int>
  }

  interface Listener {
      fun onClickNavigationItem(tabId: Int)
  }
}
