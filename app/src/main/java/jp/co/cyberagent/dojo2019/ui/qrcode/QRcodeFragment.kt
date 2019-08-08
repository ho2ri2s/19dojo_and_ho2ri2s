package jp.co.cyberagent.dojo2019.ui.qrcode

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.android.support.DaggerFragment

import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_qrcode.*
import java.lang.Exception
import javax.inject.Inject


class QRcodeFragment : DaggerFragment() {


    private lateinit var viewModel: QRcodeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(QRcodeViewModel::class.java)

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
            txtGitAccount.text = it
        })
        viewModel.twitterAccount.observe(this, Observer {
            txtTwiAccount.text = it
        })
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.encodeBitmap(viewModel.builder.build().toString(), BarcodeFormat.QR_CODE, 500, 500)
            imgQRCode.setImageBitmap(bitmap)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}