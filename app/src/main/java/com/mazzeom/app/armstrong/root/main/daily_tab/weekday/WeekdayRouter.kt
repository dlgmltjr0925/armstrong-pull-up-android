package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.view.View
import android.widget.FrameLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpRouter

import com.uber.rib.core.ViewRouter
import javax.inject.Inject

/**
 * Adds and removes children of {@link WeekdayBuilder.WeekdayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class WeekdayRouter(
    view: WeekdayView,
    interactor: WeekdayInteractor,
    component: WeekdayBuilder.Component,
    pushUpBuilder: PushUpBuilder
) : ViewRouter<WeekdayView, WeekdayInteractor>(view, interactor, component) {
    val pushUpBuilder = pushUpBuilder
    var pushUpRouter: PushUpRouter? = null
    var pushUpFrameLayout: FrameLayout? = null

    fun attachPushUpRouter(profile: ProfileDTO, date: String) {
        pushUpRouter = pushUpBuilder.build(view, profile, date)
        attachChild(pushUpRouter!!)
        if (pushUpFrameLayout == null) pushUpFrameLayout = view.findViewById(R.id.pushUpFrameLayout)
        pushUpFrameLayout!!.addView(pushUpRouter!!.view)
    }
}
