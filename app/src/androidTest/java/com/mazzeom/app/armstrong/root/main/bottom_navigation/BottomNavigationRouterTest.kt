package com.mazzeom.app.armstrong.root.main.bottom_navigation

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BottomNavigationRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: BottomNavigationBuilder.Component
  @Mock internal lateinit var interactor: BottomNavigationInteractor
  @Mock internal lateinit var view: BottomNavigationView

  private var router: BottomNavigationRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = BottomNavigationRouter(view, interactor, component)
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

