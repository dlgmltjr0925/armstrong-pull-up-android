package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FridayInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: FridayInteractor.FridayPresenter
  @Mock internal lateinit var router: FridayRouter

  private var interactor: FridayInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestFridayInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<FridayInteractor.FridayPresenter, FridayRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}