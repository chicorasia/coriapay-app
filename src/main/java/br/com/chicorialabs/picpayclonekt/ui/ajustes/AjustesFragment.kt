package br.com.chicorialabs.picpayclonekt.ui.ajustes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.picpayclonekt.databinding.FragmentAjustesBinding
import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import br.com.chicorialabs.picpayclonekt.ui.login.LoginViewModel
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

    private val ajustesLogin by lazy {
        binding.ajustesLoginTv
    }

    private val meuPicPayLogin by lazy {
        binding.ajustesMeupicpayTv
    }

    private val meuNumero by lazy {
        binding.ajustesMeunumeroTv
    }

    private val meuEmail by lazy {
        binding.ajustesMeuemailTv
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAjustesBinding.inflate(layoutInflater, container, false)
        binding.loginViewModel = mLoginViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inicializaCamposTexto()

        mComponenteViewModel.atualizaComponentes(bottomNavigation = true)

        mLoginViewModel.efetuouLogin.observe(viewLifecycleOwner, Observer<Boolean> {
            if(!it){
                val direcao = AjustesFragmentDirections.actionGlobalLoginFragment()
                controlador.navigate(direcao)
            }
        })


    }

    private fun inicializaCamposTexto() {
        mAjustesViewModel.usuario.let {
            ajustesLogin.text = it.login
            meuPicPayLogin.text = it.login
            meuNumero.text = it.numeroTelefone ?: "Nenhum número cadastrado"
            meuEmail.text = it.email ?: "Nenhum email cadastrado"
        }
    }
}