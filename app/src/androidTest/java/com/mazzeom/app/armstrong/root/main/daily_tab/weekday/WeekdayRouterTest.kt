package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpBuilder
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WeekdayRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: WeekdayBuilder.Component
  @Mock internal lateinit var interactor: WeekdayInteractor
  @Mock internal lateinit var view: WeekdayView

  private var router: WeekdayRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = WeekdayRouter(view, interactor, component, PushUpBuilder(component))
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

