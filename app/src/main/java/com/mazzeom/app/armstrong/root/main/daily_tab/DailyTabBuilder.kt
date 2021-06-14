package com.mazzeom.app.armstrong.root.main.daily_tab

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.daily_tab.weekend.WeekendBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import io.reactivex.Observable
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link DailyTabScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class DailyTabBuilder(dependency: ParentComponent) :
	ViewBuilder<DailyTabView, DailyTabRouter, DailyTabBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [DailyTabRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [DailyTabRouter].
	 */
	fun build(parentViewGroup: ViewGroup): DailyTabRouter {
		val view = createView(parentViewGroup)
		val interactor = DailyTabInteractor()
		val component = DaggerDailyTabBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.build()
		return component.dailytabRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): DailyTabView {
		return inflater.inflate(R.layout.daily_tab_rib, parentViewGroup, false) as DailyTabView
	}

	interface ParentComponent {
		fun onChangeProfile(): Observable<ProfileDTO>
	}

	@dagger.Module
	abstract class Module {

		@DailyTabScope
		@Binds
		internal abstract fun presenter(view: DailyTabView): DailyTabInteractor.DailyTabPresenter

		@dagger.Module
		companion object {

			@DailyTabScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: DailyTabView,
				interactor: DailyTabInteractor
			): DailyTabRouter {
				return DailyTabRouter(view, interactor, component, WeekendBuilder(component))
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@DailyTabScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<DailyTabInteractor>, WeekendBuilder.ParentComponent,
		BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: DailyTabInteractor): Builder

			@BindsInstance
			fun view(view: DailyTabView): Builder

			fun parentComponent(component: ParentComponent): Builder
			fun build(): Component
		}
	}

	interface BuilderComponent {
		fun dailytabRouter(): DailyTabRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class DailyTabScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class DailyTabInternal
}
