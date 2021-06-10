package com.mazzeom.app.armstrong.root.main.record_tab

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RecordTabBuilder.RecordTabScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RecordTabRouter(
    view: RecordTabView,
    interactor: RecordTabInteractor,
    component: RecordTabBuilder.Component) : ViewRouter<RecordTabView, RecordTabInteractor>(view, interactor, component)
