package com.mazzeom.app.armstrong.root.main.daily_tab

import android.annotation.SuppressLint
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * Coordinates Business Logic for [DailyTabScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class DailyTabInteractor : Interactor<DailyTabInteractor.DailyTabPresenter, DailyTabRouter>() {

  @Inject lateinit var presenter: DailyTabPresenter
  @Inject lateinit var onChangeProfile: Observable<ProfileDTO>

  lateinit var profile: ProfileDTO
  var currentDate = Date()

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    onChangeProfile.subscribe { profile ->
      this.profile = profile
    }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface DailyTabPresenter
}
