package com.mazzeom.app.armstrong.root.sign_in

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link SignInBuilder.SignInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class SignInRouter(view: SignInView, interactor: SignInInteractor, component: SignInBuilder.Component)
: ViewRouter<SignInView, SignInInteractor>(view, interactor, component) {

}
