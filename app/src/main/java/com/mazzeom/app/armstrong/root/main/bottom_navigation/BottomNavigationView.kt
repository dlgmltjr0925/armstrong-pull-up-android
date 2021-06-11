package com.mazzeom.app.armstrong.root.main.bottom_navigation

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import com.mazzeom.app.armstrong.R
import com.uber.rib.core.Initializer
import io.reactivex.Observable
import com.google.android.material.bottomnavigation.BottomNavigationView as NavigationView

/**
 * Top level view for {@link BottomNavigationBuilder.BottomNavigationScope}.
 */
class BottomNavigationView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : NavigationView(context, attrs, defStyle), BottomNavigationInteractor.BottomNavigationPresenter {
    override fun onClickNavigationItem(): Observable<Int> {
        return Observable.create { emitter ->
            this.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.dailyNavigationItem -> emitter.onNext(0)
                    R.id.recordNavigationItem -> emitter.onNext(1)
                    R.id.profileNavigationItem -> emitter.onNext(2)
                }
                true
            }
        }
    }
}
