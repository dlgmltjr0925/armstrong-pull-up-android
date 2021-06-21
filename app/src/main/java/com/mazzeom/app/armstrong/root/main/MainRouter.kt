package com.mazzeom.app.armstrong.root.main

import android.animation.AnimatorSet
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.util.Log
import android.view.View
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

    companion object {
        val DURATION: Long = 200
        val TRANSLATION = 30f
    }

    override fun didLoad() {
        super.didLoad()
        frameLayout = view.findViewById<FrameLayout>(R.id.frameLayout)
    }

    fun attachBottomNavigation() {
        bottomNavigationRouter = bottomNavigationBuilder.build(view)
        attachChild(bottomNavigationRouter!!)
        view.addView(bottomNavigationRouter!!.view)
    }

    fun addViewWithAnimation(view: View, fromTranslationX: Float = 0f) {
        view.translationX = fromTranslationX
        view.alpha = 0.2f
        frameLayout.addView(view)
        val set1 = AnimatorSet()
        set1.play(ObjectAnimator.ofFloat(view, "translationX", 0f).apply { duration = DURATION })
        val set2 = AnimatorSet()
        set2.play(ObjectAnimator.ofFloat(view, "alpha", 1f).apply { duration = DURATION })
        set1.playTogether(set2)
        set1.start()
    }

    fun attachDailyTab() {
        if (dailyTabRouter == null) dailyTabRouter = dailyTabBuilder.build(view)
        attachChild(dailyTabRouter!!)
        addViewWithAnimation(dailyTabRouter!!.view, -TRANSLATION)
    }

    fun detachDailyTab() {
        if (dailyTabRouter != null) {
            detachChild(dailyTabRouter!!)
            frameLayout.removeView(dailyTabRouter!!.view)
        }
    }

    fun attachRecordTab(detachedTab: Int = 1) {
        if (recordTabRouter == null) recordTabRouter = recordTabBuilder.build(view)
        attachChild(recordTabRouter!!)
        val fromTranslationX = when(detachedTab) {
            0 -> TRANSLATION
            2 -> -TRANSLATION
            else -> 0f
        }
        addViewWithAnimation(recordTabRouter!!.view, fromTranslationX)
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
        addViewWithAnimation(profileTabRouter!!.view, TRANSLATION)
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
            1 -> attachRecordTab(currentTabId)
            2 -> attachProfileTab()
        }
    }
}
