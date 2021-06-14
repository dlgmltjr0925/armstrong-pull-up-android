package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TuesdayRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: TuesdayBuilder.Component
  @Mock internal lateinit var interactor: TuesdayInteractor
  @Mock internal lateinit var view: TuesdayView

  private var router: TuesdayRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = TuesdayRouter(view, interactor, component)
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

