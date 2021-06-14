package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday

import android.annotation.SuppressLint
import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.response.GetRecordByProfileIdAndDateResponse
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpView
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Coordinates Business Logic for [MondayScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class MondayInteractor : Interactor<MondayInteractor.MondayPresenter, MondayRouter>() {

  @Inject lateinit var presenter: MondayPresenter
  @Inject lateinit var profile: ProfileDTO
  @Inject lateinit var date: String

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    GetPullUpRecords().start()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface MondayPresenter {
    fun setCountItem(order: Int, count: Int, state: Int = MondayView.STATE_FETCH_INIT)
  }

  inner class GetPullUpRecords: Thread() {
    override fun run() {
      val service = Api.create()
      service
        .getRecordByProfileIdAndDateRequest(profile.id, date)
        .enqueue(object: Callback<GetRecordByProfileIdAndDateResponse> {
          override fun onResponse(
            call: Call<GetRecordByProfileIdAndDateResponse>,
            response: Response<GetRecordByProfileIdAndDateResponse>
          ) {
            val records = response.body()?.records
            for (i in 0..4) {
              val order = i + 1
              var record = records?.firstOrNull { it.order == order}
              if (record == null) presenter.setCountItem(order, 0)
              else presenter.setCountItem(record.order, record.count, PushUpView.STATE_FETCHED)
            }
          }

          override fun onFailure(call: Call<GetRecordByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("DailyTab#PullUp", t.toString())
          }
        })
    }
  }
}
