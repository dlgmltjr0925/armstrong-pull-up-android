package com.mazzeom.app.armstrong.sign_in

import com.mazzeom.app.armstrong.root.sign_in.SignInInteractor
import com.mazzeom.app.armstrong.root.sign_in.SignInRouter
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SignInInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: SignInInteractor.SignInPresenter
  @Mock internal lateinit var router: SignInRouter

  private var interactor: SignInInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestSignInInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<SignInInteractor.SignInPresenter, SignInRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}