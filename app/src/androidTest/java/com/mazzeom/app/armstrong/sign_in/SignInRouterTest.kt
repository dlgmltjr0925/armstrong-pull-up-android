package com.mazzeom.app.armstrong.sign_in

import com.mazzeom.app.armstrong.root.sign_in.SignInBuilder
import com.mazzeom.app.armstrong.root.sign_in.SignInInteractor
import com.mazzeom.app.armstrong.root.sign_in.SignInRouter
import com.mazzeom.app.armstrong.root.sign_in.SignInView
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SignInRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: SignInBuilder.Component
  @Mock internal lateinit var interactor: SignInInteractor
  @Mock internal lateinit var view: SignInView

  private var router: SignInRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = SignInRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}
