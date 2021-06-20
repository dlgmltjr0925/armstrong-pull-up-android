package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ThursdayBuilder.ThursdayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ThursdayRouter(
    view: ThursdayView,
    interactor: ThursdayInteractor,
    component: ThursdayBuilder.Component) : ViewRouter<ThursdayView, ThursdayInteractor>(view, interactor, component)
