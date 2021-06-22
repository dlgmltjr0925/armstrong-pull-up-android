package com.mazzeom.app.armstrong.root.sign_in.new_profile

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewProfileRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: NewProfileBuilder.Component
  @Mock internal lateinit var interactor: NewProfileInteractor
  @Mock internal lateinit var view: NewProfileView

  private var router: NewProfileRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = NewProfileRouter(view, interactor, component)
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

