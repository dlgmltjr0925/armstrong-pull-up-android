package com.mazzeom.app.armstrong.root.main.record_tab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RecordTabInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: RecordTabInteractor.RecordTabPresenter
  @Mock internal lateinit var router: RecordTabRouter

  private var interactor: RecordTabInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestRecordTabInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<RecordTabInteractor.RecordTabPresenter, RecordTabRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}