package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WeekdayInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: WeekdayInteractor.WeekdayPresenter
  @Mock internal lateinit var router: WeekdayRouter

  private var interactor: WeekdayInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestWeekdayInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<WeekdayInteractor.WeekdayPresenter, WeekdayRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}