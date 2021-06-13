package com.mazzeom.app.armstrong.root.main.daily_tab

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link DailyTabBuilder.DailyTabScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class DailyTabRouter(
    view: DailyTabView,
    interactor: DailyTabInteractor,
    component: DailyTabBuilder.Component
) : ViewRouter<DailyTabView, DailyTabInteractor>(view, interactor, component) {

}
