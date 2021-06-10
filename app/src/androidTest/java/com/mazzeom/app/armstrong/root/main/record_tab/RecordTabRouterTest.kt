package com.mazzeom.app.armstrong.root.main.record_tab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RecordTabRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: RecordTabBuilder.Component
  @Mock internal lateinit var interactor: RecordTabInteractor
  @Mock internal lateinit var view: RecordTabView

  private var router: RecordTabRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = RecordTabRouter(view, interactor, component)
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

