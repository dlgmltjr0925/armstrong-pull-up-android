package com.mazzeom.app.armstrong.root.sign_up

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SignUpBuilder.SignUpScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SignUpRouter(
    view: SignUpView,
    interactor: SignUpInteractor,
    component: SignUpBuilder.Component) : ViewRouter<SignUpView, SignUpInteractor>(view, interactor, component)
