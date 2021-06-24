package com.mazzeom.app.armstrong.root.main.daily_tab.count_input

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
 * Builder for the {@link CountInputScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class CountInputBuilder(dependency: ParentComponent) : ViewBuilder<CountInputView, CountInputRouter, CountInputBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [CountInputRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [CountInputRouter].
   */
  fun build(parentViewGroup: ViewGroup): CountInputRouter {
    val view = createView(parentViewGroup)
    val interactor = CountInputInteractor()
    val component = DaggerCountInputBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.countinputRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): CountInputView {
    return inflater.inflate(R.layout.count_input_rib, parentViewGroup, false) as CountInputView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @CountInputScope
    @Binds
    internal abstract fun presenter(view: CountInputView): CountInputInteractor.CountInputPresenter

    @dagger.Module
    companion object {

      @CountInputScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: CountInputView,
          interactor: CountInputInteractor): CountInputRouter {
        return CountInputRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @CountInputScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<CountInputInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: CountInputInteractor): Builder

      @BindsInstance
      fun view(view: CountInputView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun countinputRouter(): CountInputRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class CountInputScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class CountInputInternal
}
