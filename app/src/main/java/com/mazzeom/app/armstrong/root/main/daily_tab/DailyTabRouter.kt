package com.mazzeom.app.armstrong.root.main.daily_tab

import android.view.View
import com.mazzeom.app.armstrong.root.main.daily_tab.weekend.WeekendBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekend.WeekendRouter

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link DailyTabBuilder.DailyTabScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class DailyTabRouter(
    view: DailyTabView,
    interactor: DailyTabInteractor,
    component: DailyTabBuilder.Component,
    weekendBuilder: WeekendBuilder
) : ViewRouter<DailyTabView, DailyTabInteractor>(view, interactor, component) {
    val weekendBuilder = weekendBuilder
    var weekendRouter: WeekendRouter? = null

    fun attachWeekend() {
        weekendRouter = weekendBuilder.build(view)
        attachChild(weekendRouter!!)
        view.addView(weekendRouter!!.view)
    }

    fun detachWeekend() {
        if (weekendRouter != null) {
            detachChild(weekendRouter!!)
            view.removeView(weekendRouter!!.view)
            weekendRouter = null
        }
    }
}
