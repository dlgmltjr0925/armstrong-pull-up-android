package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.annotation.SuppressLint
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import java.time.DayOfWeek
import javax.inject.Inject
import kotlin.properties.Delegates

val categoryMap: Map<Number, Array<String>> = mapOf(
  2 to arrayOf("풀업 5세트", "최대 반복 횟수, 쉬는 시간 : 90초"),
  3 to arrayOf("피라미드 루틴", "세트당 1회씩 증가, 쉬는 시간: 횟수 x 10초"),
  4 to arrayOf("3가지 그립", "풀업, 친업, 와이드 풀업을 각 3세트 \n쉬는 시간: 60초"),
  5 to arrayOf("최대 세트수", "수요일 반복 횟수로 실패 세트가 나올때까지 실시 \n쉬는 시간: 60초"),
  6 to arrayOf("복습", "루틴 중 가장 힘들었던 날의 루틴을 실시")
)

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
    val category = categoryMap[dayOfWeek]
    category?.run {
      presenter.setCategory(category)
    }
    when(dayOfWeek) {
      2 -> router.attachMondayRouter(profile, date)
      3 -> router.attachTuesdayRouter(profile, date)
      4 -> router.attachWednesdayRouter(profile, date)
      5 -> router.attachThursdayRouter(profile, date)
      else -> null
    }
  }

  override fun willResignActive() {
    super.willResignActive()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface WeekdayPresenter {
    fun setCategory(category: Array<String>)
  }
}
