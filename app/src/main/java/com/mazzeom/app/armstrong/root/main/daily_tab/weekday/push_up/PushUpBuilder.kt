package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.push_up

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
 * Builder for the {@link PushUpScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class PushUpBuilder(dependency: ParentComponent) :
	ViewBuilder<PushUpView, PushUpRouter, PushUpBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [PushUpRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [PushUpRouter].
	 */
	fun build(parentViewGroup: ViewGroup, profile: ProfileDTO, date: String): PushUpRouter {
		val view = createView(parentViewGroup)
		val interactor = PushUpInteractor()
		val component = DaggerPushUpBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.profile(profile)
			.date(date)
			.build()
		return component.pushupRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): PushUpView {
		return inflater.inflate(R.layout.push_up_rib, parentViewGroup, false) as PushUpView
	}

	interface ParentComponent {
		// TODO: Define dependencies required from your parent interactor here.
	}

	@dagger.Module
	abstract class Module {

		@PushUpScope
		@Binds
		internal abstract fun presenter(view: PushUpView): PushUpInteractor.PushUpPresenter

		@dagger.Module
		companion object {

			@PushUpScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: PushUpView,
				interactor: PushUpInteractor
			): PushUpRouter {
				return PushUpRouter(view, interactor, component)
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@PushUpScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<PushUpInteractor>, BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: PushUpInteractor): Builder

			@BindsInstance
			fun view(view: PushUpView): Builder

			fun parentComponent(component: ParentComponent): Builder

			fun build(): Component

			@BindsInstance
			fun profile(profile: ProfileDTO): Builder

			@BindsInstance
			fun date(date: String): Builder
		}
	}

	interface BuilderComponent {
		fun pushupRouter(): PushUpRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class PushUpScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class PushUpInternal
}
