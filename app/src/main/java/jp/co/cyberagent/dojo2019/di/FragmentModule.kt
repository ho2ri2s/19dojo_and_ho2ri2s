package jp.co.cyberagent.dojo2019.di

import androidx.fragment.app.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.co.cyberagent.dojo2019.ui.qrcode.QRcodeFragment
import jp.co.cyberagent.dojo2019.ui.user.UserFragment


//Fragmentに対してどんなModuleをInjectするかを定義
//今回は全てをMainModuleにまとめている
@Module
abstract class FragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun listFragment(): ListFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun qrcodeFragment(): QRcodeFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun userFragment(): UserFragment
}