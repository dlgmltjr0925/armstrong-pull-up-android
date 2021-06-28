package com.mazzeom.app.armstrong.root.main.daily_tab

import android.annotation.SuppressLint
import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.response.GetPushUpByProfileIdAndDateResponse
import com.mazzeom.app.armstrong.libs.api.response.GetRecordByProfileIdAndDateResponse
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response
import java.time.DayOfWeek

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
  var calendar = Calendar.getInstance()
  val apiFormatter = SimpleDateFormat("yyyyMMdd")
  val displayFormatter = SimpleDateFormat("yyyy년 MM월 dd일 (E)")

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    onChangeProfile.subscribe { profile ->
      this.profile = profile
      updateDate(0)
    }

    presenter.onClickPrevDateButton().subscribe {
      updateDate(-1)
    }

    presenter.onClickNextDateButton().subscribe {
      updateDate(1)
    }
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  fun detachRouter(dayOfWeek: Int) {
    when(dayOfWeek) {
      2 -> router.detachWeekday()
      3 -> router.detachWeekday()
      4 -> router.detachWeekday()
      5 -> router.detachWeekday()
      6 -> router.detachWeekday()
      else -> router.detachWeekend()
    }
  }

  fun attachRouter(date: String, dayOfWeek: Int) {
    when(dayOfWeek) {
      2 -> router.attachWeekday(profile, date, dayOfWeek)
      3 -> router.attachWeekday(profile, date, dayOfWeek)
      4 -> router.attachWeekday(profile, date, dayOfWeek)
      5 -> router.attachWeekday(profile, date, dayOfWeek)
      6 -> router.attachWeekday(profile, date, dayOfWeek)
      else -> router.attachWeekend()
    }
  }

  fun updateDate(amount: Int) {
    val prevDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    if (amount != 0) calendar.add(Calendar.DATE, amount)
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 1: sun, ... , 7: sat

    detachRouter(prevDayOfWeek)
    attachRouter(apiFormatter.format(calendar.time), dayOfWeek)

    presenter.setFormattedDate(displayFormatter.format(calendar.time))
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface DailyTabPresenter {
    fun onClickPrevDateButton(): Observable<Unit>
    fun onClickNextDateButton(): Observable<Unit>
    fun setFormattedDate(formattedDate: String)
  }
}
