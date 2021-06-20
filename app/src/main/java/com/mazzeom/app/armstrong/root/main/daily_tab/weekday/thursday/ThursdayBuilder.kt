package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.thursday

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
 * Builder for the {@link ThursdayScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ThursdayBuilder(dependency: ParentComponent) :
	ViewBuilder<ThursdayView, ThursdayRouter, ThursdayBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [ThursdayRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [ThursdayRouter].
	 */
	fun build(parentViewGroup: ViewGroup, profile: ProfileDTO, date: String): ThursdayRouter {
		val view = createView(parentViewGroup)
		val interactor = ThursdayInteractor()
		val component = DaggerThursdayBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.profile(profile)
			.date(date)
			.build()
		return component.thursdayRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ThursdayView {
		return inflater.inflate(R.layout.thursday_rib, parentViewGroup, false) as ThursdayView
	}

	interface ParentComponent {
		// TODO: Define dependencies required from your parent interactor here.
	}

	@dagger.Module
	abstract class Module {

		@ThursdayScope
		@Binds
		internal abstract fun presenter(view: ThursdayView): ThursdayInteractor.ThursdayPresenter

		@dagger.Module
		companion object {

			@ThursdayScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: ThursdayView,
				interactor: ThursdayInteractor
			): ThursdayRouter {
				return ThursdayRouter(view, interactor, component)
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@ThursdayScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<ThursdayInteractor>, BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: ThursdayInteractor): Builder

			@BindsInstance
			fun view(view: ThursdayView): Builder

			fun parentComponent(component: ParentComponent): Builder
			fun build(): Component

			@BindsInstance
			fun profile(profile: ProfileDTO): Builder

			@BindsInstance
			fun date(date: String): Builder
		}
	}

	interface BuilderComponent {
		fun thursdayRouter(): ThursdayRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class ThursdayScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class ThursdayInternal
}
