package com.mazzeom.app.armstrong.root

import com.mazzeom.app.armstrong.sign_in.SignInBuilder
import com.mazzeom.app.armstrong.sign_in.SignInRouter

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    signInBuilder: SignInBuilder
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {
    val signInBuilder: SignInBuilder = signInBuilder
    var signInRouter: SignInRouter? = null

    fun attachSignIn() {
        signInRouter = signInBuilder.build(view)
        attachChild(signInRouter!!)
        view.addView(signInRouter!!.view)
    }

    fun detachSignIn() {
        if (signInRouter != null) {
            detachChild(signInRouter!!)
            view.removeView(signInRouter!!.view)
        }
    }
}
