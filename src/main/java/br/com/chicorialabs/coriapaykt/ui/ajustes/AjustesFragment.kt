package br.com.chicorialabs.coriapaykt.ui.ajustes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.coriapaykt.databinding.FragmentAjustesBinding
import br.com.chicorialabs.coriapaykt.ui.componente.ComponenteViewModel
import br.com.chicorialabs.coriapaykt.ui.login.LoginViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AjustesFragment : Fragment() {

    private val mAjustesViewModel: AjustesViewModel by viewModel()
    private val mLoginViewModel: LoginViewModel by sharedViewModel()
    private val mComponenteViewModel: ComponenteViewModel by sharedViewModel()
    private lateinit var binding: FragmentAjustesBinding
    private val controlador by lazy {
        findNavController()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAjustesBinding.inflate(layoutInflater, container, false)
        binding.loginViewModel = mLoginViewModel
        binding.ajustesViewModel = mAjustesViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mComponenteViewModel.atualizaComponentes(bottomNavigation = true)

        mLoginViewModel.efetuouLogin.observe(viewLifecycleOwner, Observer<Boolean> {
            if(!it){
                val direcao = AjustesFragmentDirections.actionGlobalLoginFragment()
                controlador.navigate(direcao)
            }
        })


    }

}