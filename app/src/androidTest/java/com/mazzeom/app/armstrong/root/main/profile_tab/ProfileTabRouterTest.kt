package com.mazzeom.app.armstrong.root.main.profile_tab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProfileTabRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: ProfileTabBuilder.Component
  @Mock internal lateinit var interactor: ProfileTabInteractor
  @Mock internal lateinit var view: ProfileTabView

  private var router: ProfileTabRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = ProfileTabRouter(view, interactor, component)
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

