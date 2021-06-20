package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday

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
 * Coordinates Business Logic for [ThursdayScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class ThursdayInteractor : Interactor<ThursdayInteractor.ThursdayPresenter, ThursdayRouter>() {

  @Inject lateinit var presenter: ThursdayPresenter
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
  interface ThursdayPresenter {
    fun addCountItem(order: Int, count: Int, state: Int = ThursdayView.STATE_FETCHED)
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
            records?.run {
              forEach { record ->
                presenter.addCountItem(record.order, record.count, ThursdayView.STATE_FETCHED)
              }
            }
          }

          override fun onFailure(call: Call<GetRecordByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("Thursday", t.toString())
          }
        })
    }
  }
}
