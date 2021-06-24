package com.mazzeom.app.armstrong.root.main.daily_tab.count_input

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CountInputRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: CountInputBuilder.Component
  @Mock internal lateinit var interactor: CountInputInteractor
  @Mock internal lateinit var view: CountInputView

  private var router: CountInputRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = CountInputRouter(view, interactor, component)
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

