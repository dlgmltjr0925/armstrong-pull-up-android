package com.mazzeom.app.armstrong.root.main.record_tab

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
 * Builder for the {@link RecordTabScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RecordTabBuilder(dependency: ParentComponent) : ViewBuilder<RecordTabView, RecordTabRouter, RecordTabBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RecordTabRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RecordTabRouter].
   */
  fun build(parentViewGroup: ViewGroup): RecordTabRouter {
    val view = createView(parentViewGroup)
    val interactor = RecordTabInteractor()
    val component = DaggerRecordTabBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.recordtabRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RecordTabView {
    return inflater.inflate(R.layout.record_tab_rib, parentViewGroup, false) as RecordTabView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @RecordTabScope
    @Binds
    internal abstract fun presenter(view: RecordTabView): RecordTabInteractor.RecordTabPresenter

    @dagger.Module
    companion object {

      @RecordTabScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: RecordTabView,
          interactor: RecordTabInteractor): RecordTabRouter {
        return RecordTabRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @RecordTabScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<RecordTabInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: RecordTabInteractor): Builder

      @BindsInstance
      fun view(view: RecordTabView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun recordtabRouter(): RecordTabRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class RecordTabScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class RecordTabInternal
}
