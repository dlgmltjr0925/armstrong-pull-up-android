package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WednesdayInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: WednesdayInteractor.WednesdayPresenter
  @Mock internal lateinit var router: WednesdayRouter

  private var interactor: WednesdayInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestWednesdayInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<WednesdayInteractor.WednesdayPresenter, WednesdayRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}