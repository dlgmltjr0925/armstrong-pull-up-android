package com.mazzeom.app.armstrong.root.sign_in.new_profile

import android.view.LayoutInflater
import android.view.ViewGroup
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
 * Builder for the {@link NewProfileScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class NewProfileBuilder(dependency: ParentComponent) : ViewBuilder<NewProfileView, NewProfileRouter, NewProfileBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [NewProfileRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [NewProfileRouter].
   */
  fun build(parentViewGroup: ViewGroup): NewProfileRouter {
    val view = createView(parentViewGroup)
    val interactor = NewProfileInteractor()
    val component = DaggerNewProfileBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.newprofileRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): NewProfileView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return null
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @NewProfileScope
    @Binds
    internal abstract fun presenter(view: NewProfileView): NewProfileInteractor.NewProfilePresenter

    @dagger.Module
    companion object {

      @NewProfileScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: NewProfileView,
          interactor: NewProfileInteractor): NewProfileRouter {
        return NewProfileRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @NewProfileScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<NewProfileInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: NewProfileInteractor): Builder

      @BindsInstance
      fun view(view: NewProfileView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun newprofileRouter(): NewProfileRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class NewProfileScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class NewProfileInternal
}
