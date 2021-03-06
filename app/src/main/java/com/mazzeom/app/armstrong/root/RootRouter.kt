package com.mazzeom.app.armstrong.root

import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.MainBuilder
import com.mazzeom.app.armstrong.root.main.MainRouter
import com.mazzeom.app.armstrong.root.sign_in.SignInBuilder
import com.mazzeom.app.armstrong.root.sign_in.SignInRouter
import com.mazzeom.app.armstrong.root.sign_up.SignUpBuilder
import com.mazzeom.app.armstrong.root.sign_up.SignUpRouter

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
    signInBuilder: SignInBuilder,
    signUpBuilder: SignUpBuilder,
    mainBuilder: MainBuilder
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {
    val signInBuilder: SignInBuilder = signInBuilder
    val signUpBuilder: SignUpBuilder = signUpBuilder
    val mainBuilder: MainBuilder = mainBuilder

    var signInRouter: SignInRouter? = null
    var signUpRouter: SignUpRouter? = null
    var mainRouter: MainRouter? = null

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

    fun attachSignUp() {
        signUpRouter = signUpBuilder.build(view)
        attachChild(signUpRouter!!)
        view.addView(signUpRouter!!.view)
    }

    fun detachSignUp() {
        if (signUpRouter != null) {
            detachChild(signUpRouter!!)
            view.removeView(signUpRouter!!.view)
        }
    }

    fun attachMain(profile: ProfileDTO) {
        mainRouter = mainBuilder.build(view, profile)
        attachChild(mainRouter!!)
        view.addView(mainRouter!!.view)
    }

    fun detachMain() {
        if (mainRouter != null) {
            detachChild(mainRouter!!)
            view.removeView(mainRouter!!.view)
            mainRouter = null
        }
    }
}
