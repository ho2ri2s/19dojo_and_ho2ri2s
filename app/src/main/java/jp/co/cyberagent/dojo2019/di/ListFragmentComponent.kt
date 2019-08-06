package jp.co.cyberagent.dojo2019.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import jp.co.cyberagent.dojo2019.ui.list.ListFragment

@FragmentScope
@Component(dependencies = [AppComponent::class])
interface ListFragmentComponent {

    @Component.Builder
    interface Builder {

        fun build(): ListFragmentComponent

        fun appComponent(appComponent: AppComponent): Builder
    }

    fun inject(listFragment: ListFragment)
}