package com.mazzeom.app.armstrong.root.sign_in.new_profile

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewProfileInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: NewProfileInteractor.NewProfilePresenter
  @Mock internal lateinit var router: NewProfileRouter

  private var interactor: NewProfileInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestNewProfileInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<NewProfileInteractor.NewProfilePresenter, NewProfileRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}