package jp.co.cyberagent.dojo2019

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import jp.co.cyberagent.dojo2019.di.DaggerAppComponent

class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}