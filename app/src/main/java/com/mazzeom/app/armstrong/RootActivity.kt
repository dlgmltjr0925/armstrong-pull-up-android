package com.mazzeom.app.armstrong

import android.app.Activity
import android.view.ViewGroup
import com.mazzeom.app.armstrong.root.RootBuilder
import com.mazzeom.app.armstrong.root.RootBuilder.ParentComponent
import com.mazzeom.app.armstrong.root.RootRouter
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class RootActivity: RibActivity() {
    private val FINISH_INTERVAL_TIME = 2000
    var backPressedTime: Long = 0

    override fun createRouter(parentViewGroup: ViewGroup): RootRouter {
        return RootBuilder(object : ParentComponent {}).build(parentViewGroup)
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        val intervalTime = currentTime - backPressedTime
        if (intervalTime <= FINISH_INTERVAL_TIME) {
            finish()
        } else {
            backPressedTime = currentTime
        }
    }
}