package jp.co.cyberagent.dojo2019.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import jp.co.cyberagent.dojo2019.ui.list.ListViewModel
import jp.co.cyberagent.dojo2019.ui.qrcode.QRcodeViewModel
import jp.co.cyberagent.dojo2019.ui.user.UserViewModel

@Module
internal abstract class MainModule {
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