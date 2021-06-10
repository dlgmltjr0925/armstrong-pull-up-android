package com.mazzeom.app.armstrong.sign_in

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
 * Builder for the {@link SignInScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class SignInBuilder(dependency: ParentComponent) : ViewBuilder<SignInView, SignInRouter, SignInBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [SignInRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [SignInRouter].
   */
  fun build(parentViewGroup: ViewGroup): SignInRouter {
    val view = createView(parentViewGroup)
    val interactor = SignInInteractor()
    val component = DaggerSignInBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.signinRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): SignInView {
    return inflater.inflate(R.layout.sign_in_rib, parentViewGroup, false) as SignInView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @SignInScope
    @Binds
    internal abstract fun presenter(view: SignInView): SignInInteractor.SignInPresenter

    @dagger.Module
    companion object {

      @SignInScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: SignInView,
          interactor: SignInInteractor): SignInRouter {
        return SignInRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @SignInScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<SignInInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: SignInInteractor): Builder

      @BindsInstance
      fun view(view: SignInView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun signinRouter(): SignInRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class SignInScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class SignInInternal
}
