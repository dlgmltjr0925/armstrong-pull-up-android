package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.monday

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
 * Builder for the {@link MondayScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class MondayBuilder(dependency: ParentComponent) :
	ViewBuilder<MondayView, MondayRouter, MondayBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [MondayRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [MondayRouter].
	 */
	fun build(parentViewGroup: ViewGroup, profile: ProfileDTO, date: String): MondayRouter {
		val view = createView(parentViewGroup)
		val interactor = MondayInteractor()
		val component = DaggerMondayBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.profile(profile)
			.date(date)
			.build()
		return component.mondayRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MondayView {
		return inflater.inflate(R.layout.monday_rib, parentViewGroup, false) as MondayView
	}

	interface ParentComponent {
		// TODO: Define dependencies required from your parent interactor here.
	}

	@dagger.Module
	abstract class Module {

		@MondayScope
		@Binds
		internal abstract fun presenter(view: MondayView): MondayInteractor.MondayPresenter

		@dagger.Module
		companion object {

			@MondayScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: MondayView,
				interactor: MondayInteractor
			): MondayRouter {
				return MondayRouter(view, interactor, component)
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@MondayScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<MondayInteractor>, BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: MondayInteractor): Builder

			@BindsInstance
			fun view(view: MondayView): Builder

			fun parentComponent(component: ParentComponent): Builder
			fun build(): Component

			@BindsInstance
			fun profile(profile: ProfileDTO): Builder

			@BindsInstance
			fun date(date: String): Builder
		}
	}

	interface BuilderComponent {
		fun mondayRouter(): MondayRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class MondayScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class MondayInternal
}
