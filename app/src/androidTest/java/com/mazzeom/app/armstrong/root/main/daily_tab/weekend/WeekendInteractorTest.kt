package com.mazzeom.app.armstrong.root.main.daily_tab.weekend

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WeekendInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: WeekendInteractor.WeekendPresenter
  @Mock internal lateinit var router: WeekendRouter

  private var interactor: WeekendInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestWeekendInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<WeekendInteractor.WeekendPresenter, WeekendRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}