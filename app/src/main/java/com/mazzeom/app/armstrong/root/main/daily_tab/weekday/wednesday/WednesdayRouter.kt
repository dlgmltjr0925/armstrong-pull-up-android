package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link WednesdayBuilder.WednesdayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class WednesdayRouter(
    view: WednesdayView,
    interactor: WednesdayInteractor,
    component: WednesdayBuilder.Component) : ViewRouter<WednesdayView, WednesdayInteractor>(view, interactor, component)
