package com.mazzeom.app.armstrong.root.main.daily_tab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DailyTabRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: DailyTabBuilder.Component
  @Mock internal lateinit var interactor: DailyTabInteractor
  @Mock internal lateinit var view: DailyTabView

  private var router: DailyTabRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = DailyTabRouter(view, interactor, component)
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

