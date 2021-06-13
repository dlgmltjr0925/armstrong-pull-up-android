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
    }

    presenter.onClickPrevDateButton().subscribe {
      updateDate(-1)
    }

    presenter.onClickNextDateButton().subscribe {
      updateDate(1)
    }

    updateDate(0)
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  fun updateDate(amount: Int) {
    if (amount != 0) calendar.add(Calendar.DATE, amount)
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 1: sun, ... , 7: sat

    presenter.setFormattedDate(displayFormatter.format(calendar.time))
    GetPushUpRecords().start()
    GetPullUpRecords().start()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface DailyTabPresenter {
    fun onClickPrevDateButton(): Observable<Unit>
    fun onClickNextDateButton(): Observable<Unit>
    fun setFormattedDate(formattedDate: String)
  }

  inner class GetPushUpRecords: Thread() {
    override fun run() {
      val service = Api.create()
      service
        .getPushUpByProfileIdAndDateRequest(profile.id, apiFormatter.format(calendar.time))
        .enqueue(object : Callback<GetPushUpByProfileIdAndDateResponse> {
          override fun onResponse(
            call: Call<GetPushUpByProfileIdAndDateResponse>,
            response: Response<GetPushUpByProfileIdAndDateResponse>
          ) {
            val records = response.body()?.records
            Log.d("DailyTab#PushUp", "size: ${records?.size}")
          }

          override fun onFailure(call: Call<GetPushUpByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("DailyTab#PushUp", t.toString())
          }
        })
    }
  }

  inner class GetPullUpRecords: Thread() {
    override fun run() {
      val service = Api.create()
      service
        .getRecordByProfileIdAndDateRequest(profile.id, apiFormatter.format(calendar.time))
        .enqueue(object: Callback<GetRecordByProfileIdAndDateResponse> {
          override fun onResponse(
            call: Call<GetRecordByProfileIdAndDateResponse>,
            response: Response<GetRecordByProfileIdAndDateResponse>
          ) {
            val records = response.body()?.records
            Log.d("DailyTab#PullUp", "size: ${records?.size}")
          }

          override fun onFailure(call: Call<GetRecordByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("DailyTab#PullUp", t.toString())
          }
        })
    }
  }
}
