package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.tuesday

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
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
 * Builder for the {@link TuesdayScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class TuesdayBuilder(dependency: ParentComponent) :
	ViewBuilder<TuesdayView, TuesdayRouter, TuesdayBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [TuesdayRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [TuesdayRouter].
	 */
	fun build(parentViewGroup: ViewGroup, profile: ProfileDTO, date: String): TuesdayRouter {
		val view = createView(parentViewGroup)
		val interactor = TuesdayInteractor()
		val component = DaggerTuesdayBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.profile(profile)
			.date(date)
			.build()
		return component.tuesdayRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): TuesdayView {
		return inflater.inflate(R.layout.tuesday_rib, parentViewGroup, false) as TuesdayView
	}

	interface ParentComponent {
		// TODO: Define dependencies required from your parent interactor here.
	}

	@dagger.Module
	abstract class Module {

		@TuesdayScope
		@Binds
		internal abstract fun presenter(view: TuesdayView): TuesdayInteractor.TuesdayPresenter

		@dagger.Module
		companion object {

			@TuesdayScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: TuesdayView,
				interactor: TuesdayInteractor
			): TuesdayRouter {
				return TuesdayRouter(view, interactor, component)
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@TuesdayScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<TuesdayInteractor>, BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: TuesdayInteractor): Builder

			@BindsInstance
			fun view(view: TuesdayView): Builder

			fun parentComponent(component: ParentComponent): Builder
			fun build(): Component

			@BindsInstance
			fun profile(profile: ProfileDTO): Builder

			@BindsInstance
			fun date(date: String): Builder
		}
	}

	interface BuilderComponent {
		fun tuesdayRouter(): TuesdayRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class TuesdayScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class TuesdayInternal
}
