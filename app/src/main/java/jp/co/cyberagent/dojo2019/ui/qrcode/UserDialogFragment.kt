package jp.co.cyberagent.dojo2019.ui.qrcode

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatDialogFragment
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import javax.inject.Inject
import kotlin.random.Random

//TODO DaggerDialogFragmentにしたい
class UserDialogFragment : DaggerAppCompatDialogFragment() {


    private lateinit var viewModel: QRcodeViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var message: String


    companion object {
        fun newInstance(name: String, githubAccount: String, twitterAccount: String): UserDialogFragment {
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
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(QRcodeViewModel::class.java)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("追加しますか？")
            .setMessage(message)
            .setPositiveButton("追加", { _, _ ->
                viewModel.dialogOK.postValue(Unit)
            })
            .setNegativeButton("キャンセル", { _, _ ->
                viewModel.dialogCancel.postValue(Unit)
            })
        return builder.create()
    }
}