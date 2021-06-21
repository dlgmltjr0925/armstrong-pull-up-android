package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.view.View
import android.widget.FrameLayout
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday.FridayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday.FridayRouter
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday.MondayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday.MondayRouter
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpRouter
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday.ThursdayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday.ThursdayRouter
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday.TuesdayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday.TuesdayRouter
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday.WednesdayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday.WednesdayRouter

import com.uber.rib.core.ViewRouter
import javax.inject.Inject

/**
 * Adds and removes children of {@link WeekdayBuilder.WeekdayScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class WeekdayRouter(
	view: WeekdayView,
	interactor: WeekdayInteractor,
	component: WeekdayBuilder.Component,
	pushUpBuilder: PushUpBuilder,
	mondayBuilder: MondayBuilder,
	tuesdayBuilder: TuesdayBuilder,
	wednesdayBuilder: WednesdayBuilder,
	thursdayBuilder: ThursdayBuilder,
	fridayBuilder: FridayBuilder
) : ViewRouter<WeekdayView, WeekdayInteractor>(view, interactor, component) {
	val pushUpBuilder = pushUpBuilder
	var mondayBuilder = mondayBuilder
	val tuesdayBuilder = tuesdayBuilder
	val wednesdayBuilder = wednesdayBuilder
	val thursdayBuilder = thursdayBuilder
	val fridayBuilder = fridayBuilder

	var pushUpRouter: PushUpRouter? = null
	var mondayRouter: MondayRouter? = null
	var tuesdayRouter: TuesdayRouter? = null
	var wednesdayRouter: WednesdayRouter? = null
	var thursdayRouter: ThursdayRouter? = null
	var fridayRouter: FridayRouter? = null

	var pushUpFrameLayout: FrameLayout? = null
	var pullUpFrameLayout: FrameLayout? = null

	fun attachPushUpRouter(profile: ProfileDTO, date: String) {
		pushUpRouter = pushUpBuilder.build(view, profile, date)
		attachChild(pushUpRouter!!)
		if (pushUpFrameLayout == null) pushUpFrameLayout = view.findViewById(R.id.pushUpFrameLayout)
		pushUpFrameLayout!!.addView(pushUpRouter!!.view)
	}

	fun attachMondayRouter(profile: ProfileDTO, date: String) {
		mondayRouter = mondayBuilder.build(view, profile, date)
		attachChild(mondayRouter!!)
		if (pullUpFrameLayout == null) pullUpFrameLayout = view.findViewById(R.id.pullUpFrameLayout)
		pullUpFrameLayout!!.addView(mondayRouter!!.view)
	}

	fun attachTuesdayRouter(profile: ProfileDTO, date: String) {
		tuesdayRouter = tuesdayBuilder.build(view, profile, date)
		attachChild(tuesdayRouter!!)
		if (pullUpFrameLayout == null) pullUpFrameLayout = view.findViewById(R.id.pullUpFrameLayout)
		pullUpFrameLayout!!.addView(tuesdayRouter!!.view)
	}

	fun attachWednesdayRouter(profile: ProfileDTO, date: String) {
		wednesdayRouter = wednesdayBuilder.build(view, profile, date)
		attachChild(wednesdayRouter!!)
		if (pullUpFrameLayout == null) pullUpFrameLayout = view.findViewById(R.id.pullUpFrameLayout)
		pullUpFrameLayout!!.addView(wednesdayRouter!!.view)
	}

	fun attachThursdayRouter(profile: ProfileDTO, date: String) {
		thursdayRouter = thursdayBuilder.build(view, profile, date)
		attachChild(thursdayRouter!!)
		if (pullUpFrameLayout == null) pullUpFrameLayout = view.findViewById(R.id.pullUpFrameLayout)
		pullUpFrameLayout!!.addView(thursdayRouter!!.view)
	}

	fun attachFridayRouter() {
		fridayRouter = fridayBuilder.build(view)
		attachChild(fridayRouter!!)
		if (pullUpFrameLayout == null) pullUpFrameLayout = view.findViewById(R.id.pullUpFrameLayout)
		pullUpFrameLayout!!.addView(fridayRouter!!.view)
	}

	fun detachFridayRouter() {
		if (fridayRouter != null) {
			detachChild(fridayRouter!!)
			if (pullUpFrameLayout != null) pullUpFrameLayout!!.removeView(fridayRouter!!.view)
			fridayRouter = null
		}
	}
}
