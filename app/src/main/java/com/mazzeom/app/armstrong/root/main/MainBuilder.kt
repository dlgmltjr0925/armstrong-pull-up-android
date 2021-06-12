package com.mazzeom.app.armstrong.root.main

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
import com.mazzeom.app.armstrong.R
import com.mazzeom.app.armstrong.libs.api.dto.ProfileDTO
import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationBuilder
import com.mazzeom.app.armstrong.root.main.bottom_navigation.BottomNavigationInteractor
import com.mazzeom.app.armstrong.root.main.daily_tab.DailyTabBuilder
import com.mazzeom.app.armstrong.root.main.profile_tab.ProfileTabBuilder
import com.mazzeom.app.armstrong.root.main.profile_tab.ProfileTabInteractor
import com.mazzeom.app.armstrong.root.main.record_tab.RecordTabBuilder
import io.reactivex.Observable

/**
 * Builder for the {@link MainScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class MainBuilder(dependency: ParentComponent) : ViewBuilder<MainView, MainRouter, MainBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [MainRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MainRouter].
   */
  fun build(parentViewGroup: ViewGroup, profile: ProfileDTO): MainRouter {
    val view = createView(parentViewGroup)
    val interactor = MainInteractor(profile)
    val component = DaggerMainBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .profile(profile)
        .interactor(interactor)
        .build()
    return component.mainRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MainView {
    return inflater.inflate(R.layout.main_rib, parentViewGroup, false) as MainView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @MainScope
    @Binds
    internal abstract fun presenter(view: MainView): MainInteractor.MainPresenter

    @dagger.Module
    companion object {

      @MainScope
      @Provides
      @JvmStatic
      internal fun bottomNavigationListener(interactor: MainInteractor): BottomNavigationInteractor.Listener {
        return interactor.BottomNavigationListener()
      }

      @MainScope
      @Provides
      @JvmStatic
      internal fun profileTabListener(interactor: MainInteractor): ProfileTabInteractor.Listener {
        return interactor.ProfileTabListener()
      }

      @MainScope
      @Provides
      @JvmStatic
      internal fun onChangeProfile(interactor: MainInteractor): Observable<ProfileDTO> {
        return interactor.onChangeProfile()
      }

      @MainScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: MainView,
        interactor: MainInteractor
      ): MainRouter {
        return MainRouter(view, interactor, component, BottomNavigationBuilder(component), DailyTabBuilder(component), RecordTabBuilder(component), ProfileTabBuilder(component))
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @MainScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<MainInteractor>,
    BottomNavigationBuilder.ParentComponent,
    DailyTabBuilder.ParentComponent,
    RecordTabBuilder.ParentComponent,
    ProfileTabBuilder.ParentComponent,
    BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: MainInteractor): Builder

      @BindsInstance
      fun view(view: MainView): Builder

      @BindsInstance
      fun profile(profile: ProfileDTO): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun mainRouter(): MainRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class MainScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class MainInternal
}
