package com.mazzeom.app.armstrong.root.main.daily_tab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DailyTabInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: DailyTabInteractor.DailyTabPresenter
  @Mock internal lateinit var router: DailyTabRouter

  private var interactor: DailyTabInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestDailyTabInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<DailyTabInteractor.DailyTabPresenter, DailyTabRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}