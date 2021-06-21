package com.mazzeom.app.armstrong.root.main.daily_tab.weekday

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday.FridayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday.FridayInteractor
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday.MondayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up.PushUpBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday.ThursdayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday.TuesdayBuilder
import com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday.WednesdayBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link WeekdayScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class WeekdayBuilder(dependency: ParentComponent) :
	ViewBuilder<WeekdayView, WeekdayRouter, WeekdayBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [WeekdayRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [WeekdayRouter].
	 */
	fun build(parentViewGroup: ViewGroup, profile: ProfileDTO, date: String, dayOfWeek: Int): WeekdayRouter {
		val view = createView(parentViewGroup)
		val interactor = WeekdayInteractor()
		val component = DaggerWeekdayBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.profile(profile)
			.date(date)
			.dayOfWeek(dayOfWeek)
			.build()
		return component.weekdayRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): WeekdayView {
		return inflater.inflate(R.layout.weekday_rib, parentViewGroup, false) as WeekdayView
	}

	interface ParentComponent {
		// TODO: Define dependencies required from your parent interactor here.
	}

	@dagger.Module
	abstract class Module {

		@WeekdayScope
		@Binds
		internal abstract fun presenter(view: WeekdayView): WeekdayInteractor.WeekdayPresenter

		@dagger.Module
		companion object {

			@WeekdayScope
			@Provides
			@JvmStatic
			internal fun fridayListener(interactor: WeekdayInteractor): FridayInteractor.Listener {
				return interactor.FridayListener()
			}

			@WeekdayScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: WeekdayView,
				interactor: WeekdayInteractor
			): WeekdayRouter {
				return WeekdayRouter(
					view,
					interactor,
					component,
					PushUpBuilder(component),
					MondayBuilder(component),
					TuesdayBuilder(component),
					WednesdayBuilder(component),
					ThursdayBuilder(component),
					FridayBuilder(component)
				)
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@WeekdayScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<WeekdayInteractor>, PushUpBuilder.ParentComponent,
		MondayBuilder.ParentComponent, TuesdayBuilder.ParentComponent, WednesdayBuilder.ParentComponent,
		ThursdayBuilder.ParentComponent, FridayBuilder.ParentComponent,
		BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: WeekdayInteractor): Builder

			@BindsInstance
			fun view(view: WeekdayView): Builder

			fun parentComponent(component: ParentComponent): Builder

			fun build(): Component

			@BindsInstance
			fun profile(profile: ProfileDTO): Builder

			@BindsInstance
			fun date(date: String): Builder

			@BindsInstance
			fun dayOfWeek(dayOfWeek: Number): Builder
		}
	}

	interface BuilderComponent {
		fun weekdayRouter(): WeekdayRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class WeekdayScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class WeekdayInternal
}
