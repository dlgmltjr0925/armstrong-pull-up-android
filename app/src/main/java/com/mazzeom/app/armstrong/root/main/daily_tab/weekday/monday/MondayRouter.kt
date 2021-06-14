package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MondayBuilder.MondayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MondayRouter(
    view: MondayView,
    interactor: MondayInteractor,
    component: MondayBuilder.Component) : ViewRouter<MondayView, MondayInteractor>(view, interactor, component)
