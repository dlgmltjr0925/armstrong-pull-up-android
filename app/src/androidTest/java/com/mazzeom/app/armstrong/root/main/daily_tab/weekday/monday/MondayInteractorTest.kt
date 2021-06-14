package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MondayInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: MondayInteractor.MondayPresenter
  @Mock internal lateinit var router: MondayRouter

  private var interactor: MondayInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestMondayInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<MondayInteractor.MondayPresenter, MondayRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}