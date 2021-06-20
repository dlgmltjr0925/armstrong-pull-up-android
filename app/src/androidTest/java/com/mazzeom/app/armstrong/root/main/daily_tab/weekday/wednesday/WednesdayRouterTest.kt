package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WednesdayRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: WednesdayBuilder.Component
  @Mock internal lateinit var interactor: WednesdayInteractor
  @Mock internal lateinit var view: WednesdayView

  private var router: WednesdayRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = WednesdayRouter(view, interactor, component)
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

