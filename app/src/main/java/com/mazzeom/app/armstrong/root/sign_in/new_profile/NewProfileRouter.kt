package com.mazzeom.app.armstrong.root.sign_in.new_profile

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link NewProfileBuilder.NewProfileScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class NewProfileRouter(
    view: NewProfileView,
    interactor: NewProfileInteractor,
    component: NewProfileBuilder.Component) : ViewRouter<NewProfileView, NewProfileInteractor>(view, interactor, component)
