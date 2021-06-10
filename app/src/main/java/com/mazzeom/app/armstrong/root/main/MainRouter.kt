package com.mazzeom.app.armstrong.root.main

import android.util.Log
import android.widget.FrameLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationBuilder
import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationRouter
import com.mazzeom.app.armstrong.root.main.daily_tab.DailyTabBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.DailyTabRouter
import com.mazzeom.app.armstrong.root.main.profile_tab.ProfileTabBuilder
import com.mazzeom.app.armstrong.root.main.profile_tab.ProfileTabRouter
import com.mazzeom.app.armstrong.root.main.record_tab.RecordTabBuilder
import com.mazzeom.app.armstrong.root.main.record_tab.RecordTabRouter
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
    bottomNavigationBuilder: BottomNavigationBuilder,
    dailyTabBuilder: DailyTabBuilder,
    recordTabBuilder: RecordTabBuilder,
    profileTabBuilder: ProfileTabBuilder
) : ViewRouter<MainView, MainInteractor>(view, interactor, component) {
    val bottomNavigationBuilder: BottomNavigationBuilder = bottomNavigationBuilder
    val dailyTabBuilder: DailyTabBuilder = dailyTabBuilder
    val recordTabBuilder: RecordTabBuilder = recordTabBuilder
    val profileTabBuilder: ProfileTabBuilder = profileTabBuilder

    var bottomNavigationRouter: BottomNavigationRouter? = null
    var dailyTabRouter: DailyTabRouter? = null
    var recordTabRouter: RecordTabRouter? = null
    var profileTabRouter: ProfileTabRouter? = null
    lateinit var frameLayout: FrameLayout

    override fun didLoad() {
        super.didLoad()
        frameLayout = view.findViewById<FrameLayout>(R.id.frameLayout)
    }

    fun attachBottomNavigation() {
        bottomNavigationRouter = bottomNavigationBuilder.build(view)
        attachChild(bottomNavigationRouter!!)
        view.addView(bottomNavigationRouter!!.view)
    }

    fun attachDailyTab() {
        if (dailyTabRouter == null) dailyTabRouter = dailyTabBuilder.build(view)
        attachChild(dailyTabRouter!!)
        frameLayout.addView(dailyTabRouter!!.view)
    }

    fun detachDailyTab() {
        if (dailyTabRouter != null) {
            detachChild(dailyTabRouter!!)
            frameLayout.removeView(dailyTabRouter!!.view)
        }
    }

    fun attachRecordTab() {
        if (recordTabRouter == null) recordTabRouter = recordTabBuilder.build(view)
        attachChild(recordTabRouter!!)
        frameLayout.addView(recordTabRouter!!.view)
    }

    fun detachRecordTab() {
        if (recordTabRouter != null) {
            detachChild(recordTabRouter!!)
            frameLayout.removeView(recordTabRouter!!.view)
        }
    }

    fun attachProfileTab() {
        if (profileTabRouter == null) profileTabRouter = profileTabBuilder.build(view)
        attachChild(profileTabRouter!!)
        frameLayout.addView(profileTabRouter!!.view)
    }

    fun detachProfileTab() {
        if (profileTabRouter != null) {
            detachChild(profileTabRouter!!)
            frameLayout.removeView(profileTabRouter!!.view)
        }
    }

    fun replaceNavigationTab(currentTabId: Int, nextTabId: Int) {
        when(currentTabId) {
            0 -> detachDailyTab()
            1 -> detachRecordTab()
            2 -> detachProfileTab()
        }
        when(nextTabId) {
            0 -> attachDailyTab()
            1 -> attachRecordTab()
            2 -> attachProfileTab()
        }
    }
}
