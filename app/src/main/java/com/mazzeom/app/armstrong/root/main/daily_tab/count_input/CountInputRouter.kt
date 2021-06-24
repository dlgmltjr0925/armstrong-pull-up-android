package com.mazzeom.app.armstrong.root.main.daily_tab.count_input

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link CountInputBuilder.CountInputScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class CountInputRouter(
    view: CountInputView,
    interactor: CountInputInteractor,
    component: CountInputBuilder.Component) : ViewRouter<CountInputView, CountInputInteractor>(view, interactor, component)
