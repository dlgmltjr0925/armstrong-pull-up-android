package com.mazzeom.app.armstrong.root.main.daily_tab.push_up

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link PushUpBuilder.PushUpScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class PushUpRouter(
    view: PushUpView,
    interactor: PushUpInteractor,
    component: PushUpBuilder.Component) : ViewRouter<PushUpView, PushUpInteractor>(view, interactor, component)
