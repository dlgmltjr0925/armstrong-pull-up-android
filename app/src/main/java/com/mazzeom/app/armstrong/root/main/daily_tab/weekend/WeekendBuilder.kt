package com.mazzeom.app.armstrong.root.main.daily_tab.weekend

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
 * Builder for the {@link WeekendScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class WeekendBuilder(dependency: ParentComponent) : ViewBuilder<WeekendView, WeekendRouter, WeekendBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [WeekendRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [WeekendRouter].
   */
  fun build(parentViewGroup: ViewGroup): WeekendRouter {
    val view = createView(parentViewGroup)
    val interactor = WeekendInteractor()
    val component = DaggerWeekendBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.weekendRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): WeekendView {
    return inflater.inflate(R.layout.weekend_rib, parentViewGroup, false) as WeekendView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @WeekendScope
    @Binds
    internal abstract fun presenter(view: WeekendView): WeekendInteractor.WeekendPresenter

    @dagger.Module
    companion object {

      @WeekendScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: WeekendView,
          interactor: WeekendInteractor): WeekendRouter {
        return WeekendRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @WeekendScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<WeekendInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: WeekendInteractor): Builder

      @BindsInstance
      fun view(view: WeekendView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun weekendRouter(): WeekendRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class WeekendScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class WeekendInternal
}
