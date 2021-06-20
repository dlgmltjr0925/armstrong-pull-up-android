package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ThursdayInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: ThursdayInteractor.ThursdayPresenter
  @Mock internal lateinit var router: ThursdayRouter

  private var interactor: ThursdayInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestThursdayInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<ThursdayInteractor.ThursdayPresenter, ThursdayRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}