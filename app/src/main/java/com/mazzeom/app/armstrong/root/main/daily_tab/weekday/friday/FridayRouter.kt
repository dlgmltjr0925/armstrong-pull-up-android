package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link FridayBuilder.FridayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class FridayRouter(
    view: FridayView,
    interactor: FridayInteractor,
    component: FridayBuilder.Component) : ViewRouter<FridayView, FridayInteractor>(view, interactor, component)
