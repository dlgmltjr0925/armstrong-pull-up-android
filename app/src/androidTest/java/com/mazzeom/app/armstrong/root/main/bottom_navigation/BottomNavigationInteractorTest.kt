package com.mazzeom.app.armstrong.root.main.bottom_navigation

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BottomNavigationInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: BottomNavigationInteractor.BottomNavigationPresenter
  @Mock internal lateinit var router: BottomNavigationRouter

  private var interactor: BottomNavigationInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestBottomNavigationInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<BottomNavigationInteractor.BottomNavigationPresenter, BottomNavigationRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}