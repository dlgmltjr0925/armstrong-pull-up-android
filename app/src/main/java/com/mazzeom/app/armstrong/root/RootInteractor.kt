package com.mazzeom.app.armstrong.root

import android.annotation.SuppressLint
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.MainInteractor
import com.mazzeom.app.armstrong.root.sign_in.SignInInteractor
import com.mazzeom.app.armstrong.root.sign_up.SignUpInteractor
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

    @SuppressLint("MissingSuperCall")
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

        override fun signUp() {
            router.detachSignIn()
            router.attachSignUp()
        }
    }

    inner class SignUpListener: SignUpInteractor.Listener {
        override fun signUp(profile: ProfileDTO) {
            router.detachSignUp()
            router.attachSignIn()
        }

        override fun goBack() {
            router.detachSignUp()
            router.attachSignIn()
        }
    }

    inner class MainListener: MainInteractor.Listener {
        override fun logout() {
            router.detachMain()
            router.attachSignIn()
        }
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter
}
