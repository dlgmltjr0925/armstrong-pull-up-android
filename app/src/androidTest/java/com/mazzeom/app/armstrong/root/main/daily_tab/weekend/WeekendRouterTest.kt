package com.mazzeom.app.armstrong.root.main.daily_tab.weekend

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WeekendRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: WeekendBuilder.Component
  @Mock internal lateinit var interactor: WeekendInteractor
  @Mock internal lateinit var view: WeekendView

  private var router: WeekendRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = WeekendRouter(view, interactor, component)
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

