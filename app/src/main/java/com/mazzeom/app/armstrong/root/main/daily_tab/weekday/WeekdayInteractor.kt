package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.annotation.SuppressLint
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import java.time.DayOfWeek
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Coordinates Business Logic for [WeekdayScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class WeekdayInteractor : Interactor<WeekdayInteractor.WeekdayPresenter, WeekdayRouter>() {

  @Inject lateinit var presenter: WeekdayPresenter
  @Inject lateinit var date: String
  @Inject lateinit var profile: ProfileDTO
  @Inject lateinit var dayOfWeek: Number

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    router.attachPushUpRouter(profile, date)
    when(dayOfWeek) {
      2 -> router.attachMondayRouter(profile, date)
//      3 -> router.attachTuesdayRouter(profile, date)
      else -> null
    }
  }

  override fun willResignActive() {
    super.willResignActive()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface WeekdayPresenter
}
