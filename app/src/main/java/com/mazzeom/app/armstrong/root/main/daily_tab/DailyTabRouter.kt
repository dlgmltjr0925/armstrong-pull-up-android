package com.mazzeom.app.armstrong.root.main.daily_tab

import android.view.View
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.WeekdayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.WeekdayRouter
import com.mazzeom.app.armstrong.root.main.daily_tab.weekend.WeekendBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekend.WeekendRouter

import com.uber.rib.core.ViewRouter
import java.time.DayOfWeek

/**
 * Adds and removes children of {@link DailyTabBuilder.DailyTabScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class DailyTabRouter(
    view: DailyTabView,
    interactor: DailyTabInteractor,
    component: DailyTabBuilder.Component,
    weekendBuilder: WeekendBuilder,
    weekdayBuilder: WeekdayBuilder
) : ViewRouter<DailyTabView, DailyTabInteractor>(view, interactor, component) {
    val weekendBuilder = weekendBuilder
    val weekdayBuilder = weekdayBuilder

    var weekendRouter: WeekendRouter? = null
    var weekdayRouter: WeekdayRouter? = null

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

    /**
     * profileId : Int
     * date: yyyyMMdd
     */
    fun attachWeekday(profile: ProfileDTO, date: String, dayOfWeek: Int) {
        weekdayRouter = weekdayBuilder.build(view, profile, date, dayOfWeek)
        attachChild(weekdayRouter!!)
        view.addView(weekdayRouter!!.view)
    }

    fun detachWeekday() {
        if (weekdayRouter != null) {
            detachChild(weekdayRouter!!)
            view.removeView(weekdayRouter!!.view)
            weekdayRouter = null
        }
    }
}
