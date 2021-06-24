package com.mazzeom.app.armstrong.root.main.daily_tab.count_input

import android.annotation.SuppressLint
import android.util.Log
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [CountInputScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class CountInputInteractor : Interactor<CountInputInteractor.CountInputPresenter, CountInputRouter>() {

  @Inject
  lateinit var presenter: CountInputPresenter

  var count: Int = 0

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    presenter
      .onClickNumberButton()
      .subscribe {
        handleClickNumberButton(it)
      }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  fun handleClickNumberButton(strNumber: String) {
    when (strNumber) {
      "bs" -> {
        if (count < 10) count = 0
        else count /= 10
      }
      else -> {
        count = count * 10 + strNumber.toInt()
        if (count > 9999) count = 9999
      }
    }
    presenter.setCountNumber(count)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface CountInputPresenter {
    fun onClickNumberButton(): Observable<String>
    fun setCountNumber(number: Int)
  }
}
