package com.mazzeom.app.armstrong.root.main.profile_tab

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ProfileTabBuilder.ProfileTabScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ProfileTabRouter(
    view: ProfileTabView,
    interactor: ProfileTabInteractor,
    component: ProfileTabBuilder.Component) : ViewRouter<ProfileTabView, ProfileTabInteractor>(view, interactor, component)
