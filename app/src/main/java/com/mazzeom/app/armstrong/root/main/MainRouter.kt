package com.mazzeom.app.armstrong.root.main

import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationBuilder
import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationRouter
import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MainBuilder.MainScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MainRouter(
    view: MainView,
    interactor: MainInteractor,
    component: MainBuilder.Component,
    bottomNavigationBuilder: BottomNavigationBuilder
) : ViewRouter<MainView, MainInteractor>(view, interactor, component) {
    val bottomNavigationBuilder: BottomNavigationBuilder = bottomNavigationBuilder
    var bottomNavigationRouter: BottomNavigationRouter? = null

    fun attachBottomNavigation() {
        bottomNavigationRouter = bottomNavigationBuilder.build(view)
        attachChild(bottomNavigationRouter!!)
        view.addView(bottomNavigationRouter!!.view)
    }
}
