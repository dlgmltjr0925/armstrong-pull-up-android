package com.mazzeom.app.armstrong.root.main.profile_tab

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.RootBuilder
import com.mazzeom.app.armstrong.root.main.MainBuilder
import com.mazzeom.app.armstrong.root.main.MainInteractor
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
 * Builder for the {@link ProfileTabScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ProfileTabBuilder(dependency: ParentComponent) : ViewBuilder<ProfileTabView, ProfileTabRouter, ProfileTabBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [ProfileTabRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [ProfileTabRouter].
   */
  fun build(parentViewGroup: ViewGroup): ProfileTabRouter {
    val view = createView(parentViewGroup)
    val interactor = ProfileTabInteractor()
    val component = DaggerProfileTabBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.profiletabRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ProfileTabView {
    return inflater.inflate(R.layout.profile_tab_rib, parentViewGroup, false) as ProfileTabView
  }

  interface ParentComponent {
    fun onChangeProfile(): Observable<ProfileDTO>
    fun profileTablistener(): ProfileTabInteractor.Listener
    fun mainListener(): MainInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @ProfileTabScope
    @Binds
    internal abstract fun presenter(view: ProfileTabView): ProfileTabInteractor.ProfileTabPresenter

    @dagger.Module
    companion object {

      @ProfileTabScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: ProfileTabView,
          interactor: ProfileTabInteractor): ProfileTabRouter {
        return ProfileTabRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @ProfileTabScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<ProfileTabInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: ProfileTabInteractor): Builder

      @BindsInstance
      fun view(view: ProfileTabView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun profiletabRouter(): ProfileTabRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class ProfileTabScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class ProfileTabInternal
}
