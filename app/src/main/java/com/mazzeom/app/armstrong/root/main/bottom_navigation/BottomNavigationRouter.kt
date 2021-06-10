package com.mazzeom.app.armstrong.root.main.bottom_navigation

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link BottomNavigationBuilder.BottomNavigationScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class BottomNavigationRouter(view: BottomNavigationView, interactor: BottomNavigationInteractor,component: BottomNavigationBuilder.Component) : ViewRouter<BottomNavigationView, BottomNavigationInteractor>(view, interactor, component) {

}
