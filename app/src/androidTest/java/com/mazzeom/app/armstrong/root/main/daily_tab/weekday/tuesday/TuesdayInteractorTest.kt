package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TuesdayInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: TuesdayInteractor.TuesdayPresenter
  @Mock internal lateinit var router: TuesdayRouter

  private var interactor: TuesdayInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestTuesdayInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<TuesdayInteractor.TuesdayPresenter, TuesdayRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}