package com.mazzeom.app.armstrong.root.main.daily_tab.push_up

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PushUpRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: PushUpBuilder.Component
  @Mock internal lateinit var interactor: PushUpInteractor
  @Mock internal lateinit var view: PushUpView

  private var router: PushUpRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = PushUpRouter(view, interactor, component)
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

