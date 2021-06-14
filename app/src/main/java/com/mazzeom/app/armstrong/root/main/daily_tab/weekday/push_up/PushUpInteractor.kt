package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up

import android.annotation.SuppressLint
import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.response.GetPushUpByProfileIdAndDateResponse
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Coordinates Business Logic for [PushUpScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class PushUpInteractor : Interactor<PushUpInteractor.PushUpPresenter, PushUpRouter>() {

  @Inject lateinit var presenter: PushUpPresenter
  @Inject lateinit var profile: ProfileDTO
  @Inject lateinit var date: String


  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    GetPushUpRecords().start()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface PushUpPresenter {
    fun setPushUpCount(order: Int, count: Int, state: Int = PushUpView.STATE_FETCH_INIT)
  }

  inner class GetPushUpRecords: Thread() {
    override fun run() {
      val service = Api.create()
      service
        .getPushUpByProfileIdAndDateRequest(profile.id, date)
        .enqueue(object : Callback<GetPushUpByProfileIdAndDateResponse> {
          override fun onResponse(
            call: Call<GetPushUpByProfileIdAndDateResponse>,
            response: Response<GetPushUpByProfileIdAndDateResponse>
          ) {
            val records = response.body()?.records
            for (i in 1..3) {
              var record = records?.firstOrNull { it.order == i}
              if (record == null) presenter.setPushUpCount(i, 0)
              else presenter.setPushUpCount(record.order, record.count, PushUpView.STATE_FETCHED)
            }
          }

          override fun onFailure(call: Call<GetPushUpByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("DailyTab#PushUp", t.toString())
          }
        })
    }
  }
}
