package com.mazzeom.app.armstrong.main

import com.mazzeom.app.armstrong.root.main.MainBuilder
import com.mazzeom.app.armstrong.root.main.MainInteractor
import com.mazzeom.app.armstrong.root.main.MainRouter
import com.mazzeom.app.armstrong.root.main.MainView
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: MainBuilder.Component
  @Mock internal lateinit var interactor: MainInteractor
  @Mock internal lateinit var view: MainView

  private var router: MainRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = MainRouter(view, interactor, component, BottomNavigationBuilder(component))
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

