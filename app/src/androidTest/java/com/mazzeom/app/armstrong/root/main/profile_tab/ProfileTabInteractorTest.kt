package com.mazzeom.app.armstrong.root.main.profile_tab

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProfileTabInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: ProfileTabInteractor.ProfileTabPresenter
  @Mock internal lateinit var router: ProfileTabRouter

  private var interactor: ProfileTabInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestProfileTabInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<ProfileTabInteractor.ProfileTabPresenter, ProfileTabRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}