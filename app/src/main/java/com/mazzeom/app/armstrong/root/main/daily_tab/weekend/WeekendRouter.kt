package com.mazzeom.app.armstrong.root.main.daily_tab.weekend

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link WeekendBuilder.WeekendScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class WeekendRouter(
    view: WeekendView,
    interactor: WeekendInteractor,
    component: WeekendBuilder.Component) : ViewRouter<WeekendView, WeekendInteractor>(view, interactor, component)
