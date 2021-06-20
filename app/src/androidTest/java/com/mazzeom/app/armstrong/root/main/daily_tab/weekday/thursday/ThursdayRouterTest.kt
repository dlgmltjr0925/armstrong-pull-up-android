package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ThursdayRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: ThursdayBuilder.Component
  @Mock internal lateinit var interactor: ThursdayInteractor
  @Mock internal lateinit var view: ThursdayView

  private var router: ThursdayRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ThursdayRouter(view, interactor, component)
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

