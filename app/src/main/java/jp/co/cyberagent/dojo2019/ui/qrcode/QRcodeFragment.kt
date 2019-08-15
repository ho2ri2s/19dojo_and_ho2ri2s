package jp.co.cyberagent.dojo2019.ui.qrcode

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.android.support.DaggerFragment
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.data.db.entity.User
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_qrcode.*
import java.lang.Exception
import javax.inject.Inject


class QRcodeFragment : DaggerFragment() {


    private lateinit var viewModel: QRcodeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val user = User("", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(QRcodeViewModel::class.java)
        Log.d("TAG", "qrfrag ${viewModel}")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMyProfile()
        viewModel.name.observe(this, Observer {
            txtUser.text = it
        })
        viewModel.githubAccount.observe(this, Observer {
            txtGithubAccount.text = it
        })
        viewModel.twitterAccount.observe(this, Observer {
            txtTwitterAccount.text = it
        })
        viewModel.dialogOK.observe(this, "ok", Observer {
            viewModel.upsertUser(user)
            Log.d("TAG", "ListViewへ")
            val action = QRcodeFragmentDirections.actionListFragment()
            Navigation.findNavController(parentFragment?.view!!).navigate(action)
        })
        viewModel.dialogCancel.observe(this, "cancel", Observer {
            Toast.makeText(this.context, "cancel", Toast.LENGTH_SHORT).show()
        })
        val builder = Uri.Builder()
            .scheme("ca-tech")
            .authority("dojo")
            .path("/share")
            .appendQueryParameter("iam", viewModel.name.value)
            .appendQueryParameter("gw", viewModel.githubAccount.value)
            .appendQueryParameter("tw", viewModel.twitterAccount.value)

        //QRコード表示
        try {
            Log.d("TAG", builder.toString())
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap =
                barcodeEncoder.encodeBitmap(builder.build().toString(), BarcodeFormat.QR_CODE, 500, 500)
            imgQRCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //QRコードリーダーへ遷移
        fab.setOnClickListener {
            try {
                val intent: Intent = Intent("com.google.zxing.client.android.SCAN")
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE")
                startActivityForResult(intent, 0)
            } catch (e: Exception) {
                val marketUri: Uri = Uri.parse("market://details?id=com.google.zxing.client.android")
                val marketIntent: Intent = Intent(Intent.ACTION_VIEW, marketUri)
                startActivity(marketIntent)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                val contents = data?.getStringExtra("SCAN_RESULT")
                val uri = Uri.parse(contents)
                val iam = uri.getQueryParameter("iam")
                val tw = uri.getQueryParameter("tw")
                val gh = uri.getQueryParameter("gh")

                user.name = iam
                user.twitterAccount = tw
                user.githubAccount = gh

                UserDialogFragment.newInstance(iam, gh, tw)
                    .show(fragmentManager, "add_user")
            }
        }
    }

}