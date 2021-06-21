package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday

import android.annotation.SuppressLint
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import java.time.DayOfWeek
import javax.inject.Inject

/**
 * Coordinates Business Logic for [FridayScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class FridayInteractor : Interactor<FridayInteractor.FridayPresenter, FridayRouter>() {

  @Inject lateinit var presenter: FridayPresenter
  @Inject lateinit var listener: Listener

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter
      .onClickRoutine()
      .subscribe { dayOfWeek ->
        listener.onSelectRoutine(dayOfWeek)
      }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface FridayPresenter {
    fun onClickRoutine(): Observable<Int>
  }

  interface Listener {
    fun onSelectRoutine(dayOfWeek: Int)
  }
}
