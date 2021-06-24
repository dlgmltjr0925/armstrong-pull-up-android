package com.mazzeom.app.armstrong.root.main.daily_tab

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.daily_tab.count_input.CountInputBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.count_input.CountInputRouter
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
    weekdayBuilder: WeekdayBuilder,
    countInputBuilder: CountInputBuilder
) : ViewRouter<DailyTabView, DailyTabInteractor>(view, interactor, component) {
    val weekendBuilder = weekendBuilder
    val weekdayBuilder = weekdayBuilder
    val countInputBuilder = countInputBuilder

    var weekendRouter: WeekendRouter? = null
    var weekdayRouter: WeekdayRouter? = null
    var countInputRouter: CountInputRouter? = null

    var recordContainer: LinearLayout? = null
    var dailyTabContainer: FrameLayout? = null

    fun attachWeekend() {
        weekendRouter = weekendBuilder.build(view)
        attachChild(weekendRouter!!)
        if (recordContainer == null) recordContainer = view.findViewById(R.id.recordContainer)
        recordContainer!!.addView(weekendRouter!!.view)
    }

    fun detachWeekend() {
        if (weekendRouter != null) {
            detachChild(weekendRouter!!)
            if (recordContainer == null) recordContainer = view.findViewById(R.id.recordContainer)
            recordContainer!!.removeView(weekendRouter!!.view)
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
        if (recordContainer == null) recordContainer = view.findViewById(R.id.recordContainer)
        recordContainer!!.addView(weekdayRouter!!.view)
    }

    fun detachWeekday() {
        if (weekdayRouter != null) {
            detachChild(weekdayRouter!!)
            if (recordContainer == null) recordContainer = view.findViewById(R.id.recordContainer)
            recordContainer!!.removeView(weekdayRouter!!.view)
            weekdayRouter = null
        }
    }

    fun attachCountInput() {
        countInputRouter = countInputBuilder.build(view)
        attachChild(countInputRouter!!)
        if (dailyTabContainer == null) dailyTabContainer = view.findViewById(R.id.dailyTabContainer)
        dailyTabContainer!!.addView(countInputRouter!!.view)
    }
}
