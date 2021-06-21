package com.mazzeom.app.armstrong.root.main.daily_tab.weekday.friday

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
 * Builder for the {@link FridayScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class FridayBuilder(dependency: ParentComponent) : ViewBuilder<FridayView, FridayRouter, FridayBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [FridayRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [FridayRouter].
   */
  fun build(parentViewGroup: ViewGroup): FridayRouter {
    val view = createView(parentViewGroup)
    val interactor = FridayInteractor()
    val component = DaggerFridayBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.fridayRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): FridayView {
    return inflater.inflate(R.layout.friday_rib, parentViewGroup, false) as FridayView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @FridayScope
    @Binds
    internal abstract fun presenter(view: FridayView): FridayInteractor.FridayPresenter

    @dagger.Module
    companion object {

      @FridayScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: FridayView,
          interactor: FridayInteractor): FridayRouter {
        return FridayRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @FridayScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<FridayInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: FridayInteractor): Builder

      @BindsInstance
      fun view(view: FridayView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun fridayRouter(): FridayRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class FridayScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class FridayInternal
}
