package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday

import android.annotation.SuppressLint
import android.util.Log
import android.widget.LinearLayout
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
 * Coordinates Business Logic for [TuesdayScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class TuesdayInteractor : Interactor<TuesdayInteractor.TuesdayPresenter, TuesdayRouter>() {

  @Inject lateinit var presenter: TuesdayPresenter
  @Inject lateinit var profile: ProfileDTO
  @Inject lateinit var date: String
  val countItems: MutableList<LinearLayout> = mutableListOf()

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
  interface TuesdayPresenter {
    fun addCountItem(order: Int, count: Int, state: Int = TuesdayView.STATE_FETCHED)
    fun setCountItem(order: Int, count: Int, state: Int = TuesdayView.STATE_FETCHED)
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
                presenter.addCountItem(record.order, record.count, TuesdayView.STATE_FETCHED)
              }
            }
          }

          override fun onFailure(call: Call<GetRecordByProfileIdAndDateResponse>, t: Throwable) {
            Log.d("Tuesday", t.toString())
          }
        })
    }
  }
}
