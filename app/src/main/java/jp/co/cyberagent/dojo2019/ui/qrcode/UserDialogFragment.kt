package jp.co.cyberagent.dojo2019.ui.qrcode

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatDialogFragment
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import javax.inject.Inject

class UserDialogFragment : DaggerAppCompatDialogFragment() {


    private lateinit var viewModel: QRcodeViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var message: String


    companion object {
        fun newInstance(name: String?, githubAccount: String?, twitterAccount: String?): UserDialogFragment {
            val bundle = Bundle().also {
                it.putString(
                    "message",
                    "name : ${name}\n" +
                            "github account : ${githubAccount}\n" +
                            "twitter account : ${twitterAccount}"
                )
            }
            val userDialogFragment = UserDialogFragment().apply { arguments = bundle }
            return userDialogFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        message = arguments?.getString("message") as String
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(QRcodeViewModel::class.java)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("追加しますか？")
            .setMessage(message)
            .setPositiveButton("追加", { _, _ ->
                viewModel.dialogOK.call(Unit)
            })
            .setNegativeButton("キャンセル", { _, _ ->
                viewModel.dialogCancel.call(Unit)
            })
        return builder.create()
    }
}