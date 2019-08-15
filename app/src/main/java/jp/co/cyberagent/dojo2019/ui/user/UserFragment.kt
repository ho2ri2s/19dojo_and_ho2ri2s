package jp.co.cyberagent.dojo2019.ui.user


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.DaggerFragment

import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.databinding.FragmentUserBinding
import jp.co.cyberagent.dojo2019.di.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject


class UserFragment : DaggerFragment() {

    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentUserBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModelインスタンス化
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        //TextInputLayout周り
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMyProfile()

        setTextChangedListener(edtGithubAccount, tilGithubAccount)

        btnSave.setOnClickListener {
            var correctInput = true
            if(edtGithubAccount.text.isNullOrEmpty()){
                tilGithubAccount.error = "github account is required"
                tilGithubAccount.isErrorEnabled = true
                correctInput = false
            }
            if(correctInput) {
                binding.viewModel?.saveMyInfo()
                val action = UserFragmentDirections.actionQRcodeFragment()
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    fun setTextChangedListener(edt: EditText, til: TextInputLayout){
        edt.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                til.isErrorEnabled = false
            }

        })
    }
}