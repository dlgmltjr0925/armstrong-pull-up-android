package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday

import android.annotation.SuppressLint
import android.util.Log
import com.mazzeom.app.armstrong.libs.api.Api
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.libs.api.response.GetRecordByProfileIdAndDateResponse
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Coordinates Business Logic for [WednesdayScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class WednesdayInteractor : Interactor<WednesdayInteractor.WednesdayPresenter, WednesdayRouter>() {

  @Inject lateinit var presenter: WednesdayPresenter
  @Inject lateinit var profile: ProfileDTO
  @Inject lateinit var date: String

  @SuppressLint("MissingSuperCall")
  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)

    GetPullUpRecords().start()
  }

  override fun willResignActive() {
    super.willResignActive()
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface WednesdayPresenter {
    fun setCountItem(order: Int, count: Int, state: Int = WednesdayView.STATE_FETCH_INIT)
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
            records?.forEach {
              presenter.setCountItem(it.order, it.count, WednesdayView.STATE_FETCHED)
            }
          }

          override fun onFailure(call: Call<GetRecordByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("Wednesday#PullUp", t.toString())
          }
        })
    }
  }
}
