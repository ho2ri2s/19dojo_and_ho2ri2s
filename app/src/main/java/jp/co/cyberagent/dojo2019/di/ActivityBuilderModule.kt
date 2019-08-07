package jp.co.cyberagent.dojo2019.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.co.cyberagent.dojo2019.ui.MainActivity

@Module
interface ActivityBuilderModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentModule::class
        ]
    )
    fun contributeMainInjector(): MainActivity
}