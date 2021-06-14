package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MondayRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: MondayBuilder.Component
  @Mock internal lateinit var interactor: MondayInteractor
  @Mock internal lateinit var view: MondayView

  private var router: MondayRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = MondayRouter(view, interactor, component)
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

