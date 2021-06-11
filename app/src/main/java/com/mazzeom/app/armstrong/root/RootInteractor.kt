package com.mazzeom.app.armstrong.root

import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.sign_in.SignInInteractor
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {
    var profile: ProfileDTO? = null;

    @Inject
    lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        router.attachSignIn()
    }

    override fun willResignActive() {
        super.willResignActive()

        // TODO: Perform any required clean up here, or delete this method entirely if not needed.
    }

    inner class SignInListener: SignInInteractor.Listener {
        override fun login(profile: ProfileDTO) {
            router.detachSignIn()
            router.attachMain(profile)
        }
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter
}
