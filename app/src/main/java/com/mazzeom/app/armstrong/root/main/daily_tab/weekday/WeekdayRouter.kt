package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.view.View
import android.widget.FrameLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday.MondayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday.MondayRouter
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
    pushUpBuilder: PushUpBuilder,
    mondayBuilder: MondayBuilder
) : ViewRouter<WeekdayView, WeekdayInteractor>(view, interactor, component) {
    val pushUpBuilder = pushUpBuilder
    var mondayBuilder = mondayBuilder

    var pushUpRouter: PushUpRouter? = null
    var mondayRouter: MondayRouter? = null

    var pushUpFrameLayout: FrameLayout? = null
    var pullUpFrameLayout: FrameLayout? = null

    fun attachPushUpRouter(profile: ProfileDTO, date: String) {
        pushUpRouter = pushUpBuilder.build(view, profile, date)
        attachChild(pushUpRouter!!)
        if (pushUpFrameLayout == null) pushUpFrameLayout = view.findViewById(R.id.pushUpFrameLayout)
        pushUpFrameLayout!!.addView(pushUpRouter!!.view)
    }

    fun attachMondayRouter(profile: ProfileDTO, date: String) {
        mondayRouter = mondayBuilder.build(view, profile, date)
        attachChild(mondayRouter!!)
        if (pullUpFrameLayout == null) pullUpFrameLayout = view.findViewById(R.id.pullUpFrameLayout)
        pullUpFrameLayout!!.addView(mondayRouter!!.view)
    }
}
