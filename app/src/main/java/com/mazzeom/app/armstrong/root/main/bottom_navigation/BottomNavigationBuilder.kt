package com.mazzeom.app.armstrong.root.main.bottom_navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mazzeom.app.armstrong.R
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
 * Builder for the {@link BottomNavigationScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class BottomNavigationBuilder(dependency: ParentComponent) : ViewBuilder<BottomNavigationView, BottomNavigationRouter, BottomNavigationBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [BottomNavigationRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [BottomNavigationRouter].
   */
  fun build(parentViewGroup: ViewGroup): BottomNavigationRouter {
    val view = createView(parentViewGroup)
    val interactor = BottomNavigationInteractor()
    val component = DaggerBottomNavigationBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.bottomnavigationRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): BottomNavigationView {
    return inflater.inflate(R.layout.bottom_navigation_rib, parentViewGroup, false) as BottomNavigationView
  }

  interface ParentComponent {
    fun listener(): BottomNavigationInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @BottomNavigationScope
    @Binds
    internal abstract fun presenter(view: BottomNavigationView): BottomNavigationInteractor.BottomNavigationPresenter

    @dagger.Module
    companion object {

      @BottomNavigationScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: BottomNavigationView,
          interactor: BottomNavigationInteractor): BottomNavigationRouter {
        return BottomNavigationRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @BottomNavigationScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<BottomNavigationInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: BottomNavigationInteractor): Builder

      @BindsInstance
      fun view(view: BottomNavigationView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun bottomnavigationRouter(): BottomNavigationRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class BottomNavigationScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class BottomNavigationInternal
}
