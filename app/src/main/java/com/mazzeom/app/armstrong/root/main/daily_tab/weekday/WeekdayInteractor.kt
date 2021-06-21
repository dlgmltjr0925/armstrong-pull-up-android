package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.annotation.SuppressLint
import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.dto.RecordType
import com.mazzeom.app.armstrong.libs.api.response.GetRecordByProfileIdAndDateResponse
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday.FridayInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.DayOfWeek
import javax.inject.Inject
import kotlin.properties.Delegates

val categoryMap: Map<Number, Array<String>> = mapOf(
  2 to arrayOf("풀업 5세트", "최대 반복 횟수, 쉬는 시간 : 90초"),
  3 to arrayOf("피라미드 루틴", "세트당 1회씩 증가, 쉬는 시간: 횟수 x 10초"),
  4 to arrayOf("3가지 그립", "풀업, 친업, 와이드 풀업을 각 3세트 \n쉬는 시간: 60초"),
  5 to arrayOf("최대 세트수", "수요일 반복 횟수로 실패 세트가 나올때까지 실시 \n쉬는 시간: 60초"),
//  6 to arrayOf("복습", "루틴 중 가장 힘들었던 날의 루틴을 실시")
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
    setViewByDayOfWeek(dayOfWeek)
    val category = categoryMap[dayOfWeek]
    category?.run {
      presenter.setCategory(category)
    }
    when(dayOfWeek) {
      2 -> router.attachMondayRouter(profile, date)
      3 -> router.attachTuesdayRouter(profile, date)
      4 -> router.attachWednesdayRouter(profile, date)
      5 -> router.attachThursdayRouter(profile, date)
      6 -> GetPullUpRecords().start()
      else -> null
    }
  }

  override fun willResignActive() {
    super.willResignActive()
  }

  fun setViewByDayOfWeek(dayOfWeek: Number) {
    val category = categoryMap[dayOfWeek.toInt()]
    category?.run {
      presenter.setCategory(category)
    }
    when(dayOfWeek) {
      2 -> router.attachMondayRouter(profile, date)
      3 -> router.attachTuesdayRouter(profile, date)
      4 -> router.attachWednesdayRouter(profile, date)
      5 -> router.attachThursdayRouter(profile, date)
      6 -> GetPullUpRecords().start()
      else -> null
    }
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface WeekdayPresenter {
    fun setCategory(category: Array<String>)
  }

  inner class FridayListener: FridayInteractor.Listener {
    override fun onSelectRoutine(dayOfWeek: Int) {
      val category = categoryMap[dayOfWeek]
      category?.run {
        presenter.setCategory(category)
      }
      router.detachFridayRouter()
      when(dayOfWeek) {
        2 -> router.attachMondayRouter(profile, date)
        3 -> router.attachTuesdayRouter(profile, date)
        4 -> router.attachWednesdayRouter(profile, date)
        5 -> router.attachThursdayRouter(profile, date)
        6 -> GetPullUpRecords().start()
        else -> null
      }
    }
  }

  inner class GetPullUpRecords: Thread() {
    override fun run() {
      val service = Api.create()
      service
        .getRecordByProfileIdAndDateRequest(profile.id, date)
        .enqueue(object : Callback<GetRecordByProfileIdAndDateResponse> {
          override fun onResponse(
            call: Call<GetRecordByProfileIdAndDateResponse>,
            response: Response<GetRecordByProfileIdAndDateResponse>
          ) {
            val records = response.body()?.records
            if (records == null || records.isEmpty()) {
              presenter.setCategory(arrayOf("복습", "루틴 중 가장 힘들었던 날의 루틴을 실시"))
              router.attachFridayRouter()
            } else {
              val type = records[0].type
              dayOfWeek = when(type) {
                RecordType.MAX_COUNT -> 2
                RecordType.PYRAMID -> 3
                RecordType.THREE_GRIP -> 4
                RecordType.MAX_SET -> 5
                else -> 6
              }

              setViewByDayOfWeek(dayOfWeek)
            }
          }

          override fun onFailure(call: Call<GetRecordByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("Weekday#PullUp", t.toString())
          }
        })
    }
  }
}
