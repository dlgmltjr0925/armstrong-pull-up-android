package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FridayRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: FridayBuilder.Component
  @Mock internal lateinit var interactor: FridayInteractor
  @Mock internal lateinit var view: FridayView

  private var router: FridayRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = FridayRouter(view, interactor, component)
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

