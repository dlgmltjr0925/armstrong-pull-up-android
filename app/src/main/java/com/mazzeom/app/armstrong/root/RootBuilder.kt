package com.mazzeom.app.armstrong.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.root.main.MainBuilder
import com.mazzeom.app.armstrong.root.main.MainInteractor
import com.mazzeom.app.armstrong.root.main.profile_tab.ProfileTabBuilder
import com.mazzeom.app.armstrong.root.sign_in.SignInBuilder
import com.mazzeom.app.armstrong.root.sign_in.SignInInteractor
import com.mazzeom.app.armstrong.root.sign_up.SignUpBuilder
import com.mazzeom.app.armstrong.root.sign_up.SignUpInteractor
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Scope
import javax.inject.Singleton

/**
 * Builder for the {@link RootScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RootBuilder(dependency: ParentComponent) :
	ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

	/**
	 * Builds a new [RootRouter].
	 *
	 * @param parentViewGroup parent view group that this router's view will be added to.
	 * @return a new [RootRouter].
	 */
	fun build(parentViewGroup: ViewGroup): RootRouter {
		val view = createView(parentViewGroup)
		val interactor = RootInteractor()
		val component = DaggerRootBuilder_Component.builder()
			.parentComponent(dependency)
			.view(view)
			.interactor(interactor)
			.build()
		return component.rootRouter()
	}

	override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView {
		return inflater.inflate(R.layout.root_rib, parentViewGroup, false) as RootView
	}

	interface ParentComponent {
		// TODO: Define dependencies required from your parent interactor here.
	}

	@dagger.Module
	abstract class Module {

		@RootScope
		@Binds
		internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

		@dagger.Module
		companion object {

			@RootScope
			@Provides
			@JvmStatic
			internal fun signInListener(interactor: RootInteractor): SignInInteractor.Listener {
				return interactor.SignInListener()
			}

			@RootScope
			@Provides
			@JvmStatic
			internal fun signUpListener(interactor: RootInteractor): SignUpInteractor.Listener {
				return interactor.SignUpListener()
			}

			@RootScope
			@Provides
			@JvmStatic
			fun mainListener(interactor: RootInteractor): MainInteractor.Listener {
				return interactor.MainListener()
			}

			@RootScope
			@Provides
			@JvmStatic
			internal fun router(
				component: Component,
				view: RootView,
				interactor: RootInteractor
			): RootRouter {
				return RootRouter(
					view,
					interactor,
					component,
					SignInBuilder(component),
					SignUpBuilder(component),
					MainBuilder(component)
				)
			}
		}

		// TODO: Create provider methods for dependencies created by this Rib. These should be static.
	}

	@RootScope
	@dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
	interface Component : InteractorBaseComponent<RootInteractor>,
		SignInBuilder.ParentComponent,
		SignUpBuilder.ParentComponent,
		MainBuilder.ParentComponent,
		BuilderComponent {

		@dagger.Component.Builder
		interface Builder {
			@BindsInstance
			fun interactor(interactor: RootInteractor): Builder

			@BindsInstance
			fun view(view: RootView): Builder

			fun parentComponent(component: ParentComponent): Builder

			fun build(): Component
		}
	}

	interface BuilderComponent {
		fun rootRouter(): RootRouter
	}

	@Scope
	@Retention(CLASS)
	internal annotation class RootScope

	@Qualifier
	@Retention(CLASS)
	internal annotation class RootInternal
}
