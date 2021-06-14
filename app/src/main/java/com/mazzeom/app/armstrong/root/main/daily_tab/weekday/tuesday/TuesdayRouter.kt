package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link TuesdayBuilder.TuesdayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class TuesdayRouter(
    view: TuesdayView,
    interactor: TuesdayInteractor,
    component: TuesdayBuilder.Component) : ViewRouter<TuesdayView, TuesdayInteractor>(view, interactor, component)
