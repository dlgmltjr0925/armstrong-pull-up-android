package com.mazzeom.app.armstrong.root.main.daily_tab.count_input

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CountInputInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: CountInputInteractor.CountInputPresenter
  @Mock internal lateinit var router: CountInputRouter

  private var interactor: CountInputInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestCountInputInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<CountInputInteractor.CountInputPresenter, CountInputRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}