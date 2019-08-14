package jp.co.cyberagent.dojo2019.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import jp.co.cyberagent.dojo2019.di.ViewModelKey
import jp.co.cyberagent.dojo2019.ui.list.ListViewModel
import jp.co.cyberagent.dojo2019.ui.qrcode.QRcodeViewModel
import jp.co.cyberagent.dojo2019.ui.user.UserViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QRcodeViewModel::class)
    abstract fun bindQRcodeViewModel(viewModel: QRcodeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(viewModel: UserViewModel): ViewModel
}