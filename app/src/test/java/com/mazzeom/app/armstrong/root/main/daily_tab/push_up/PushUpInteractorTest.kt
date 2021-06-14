package com.mazzeom.app.armstrong.root.main.daily_tab.push_up

import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpInteractor
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpRouter
import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PushUpInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: PushUpInteractor.PushUpPresenter
  @Mock internal lateinit var router: PushUpRouter

  private var interactor: PushUpInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestPushUpInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<PushUpInteractor.PushUpPresenter, PushUpRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}