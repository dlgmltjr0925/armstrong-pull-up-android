package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link WeekdayBuilder.WeekdayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class WeekdayRouter(
    view: WeekdayView,
    interactor: WeekdayInteractor,
    component: WeekdayBuilder.Component) : ViewRouter<WeekdayView, WeekdayInteractor>(view, interactor, component)
