package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.wednesday

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
 * Builder for the {@link WednesdayScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class WednesdayBuilder(dependency: ParentComponent) :
	ViewBuilder<WednesdayView, WednesdayRouter, WednesdayBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [WednesdayRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [WednesdayRouter].
	 */
	fun build(parentViewGroup: ViewGroup, profile: ProfileDTO, date: String): WednesdayRouter {
		val view = createView(parentViewGroup)
		val interactor = WednesdayInteractor()
		val component = DaggerWednesdayBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.profile(profile)
			.date(date)
			.build()
		return component.wednesdayRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): WednesdayView {
		return inflater.inflate(R.layout.wednesday_rib, parentViewGroup, false) as WednesdayView
	}

	interface ParentComponent {
		// TODO: Define dependencies required from your parent interactor here.
	}

	@dagger.Module
	abstract class Module {

		@WednesdayScope
		@Binds
		internal abstract fun presenter(view: WednesdayView): WednesdayInteractor.WednesdayPresenter

		@dagger.Module
		companion object {

			@WednesdayScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: WednesdayView,
				interactor: WednesdayInteractor
			): WednesdayRouter {
				return WednesdayRouter(view, interactor, component)
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@WednesdayScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<WednesdayInteractor>, BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: WednesdayInteractor): Builder

			@BindsInstance
			fun view(view: WednesdayView): Builder

			fun parentComponent(component: ParentComponent): Builder

			fun build(): Component

			@BindsInstance
			fun profile(profile: ProfileDTO): Builder

			@BindsInstance
			fun date(date: String): Builder
		}
	}

	interface BuilderComponent {
		fun wednesdayRouter(): WednesdayRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class WednesdayScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class WednesdayInternal
}
