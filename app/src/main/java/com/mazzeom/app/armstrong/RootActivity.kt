package com.mazzeom.app.armstrong

import android.app.Activity
import android.view.ViewGroup
import com.mazzeom.app.armstrong.root.RootBuilder
import com.mazzeom.app.armstrong.root.RootBuilder.ParentComponent
import com.mazzeom.app.armstrong.root.RootRouter
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class RootActivity: RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): RootRouter {
        return RootBuilder(object : ParentComponent {}).build(parentViewGroup)
    }
}