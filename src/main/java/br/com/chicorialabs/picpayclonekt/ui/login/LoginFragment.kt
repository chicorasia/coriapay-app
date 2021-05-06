package br.com.chicorialabs.picpayclonekt.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.picpayclonekt.R
import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.databinding.FragmentLoginBinding
import br.com.chicorialabs.picpayclonekt.ui.componente.Componente
import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

//    companion object {
//        fun newInstance() = LoginFragment()
//    }

    private val viewModel: LoginViewModel by viewModel()
    private val componenteViewModel: ComponenteViewModel by sharedViewModel()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componenteViewModel.atualizaComponentes(bottomNavigation = false)
        binding.button.setOnClickListener {
            UsuarioLogado.usuario = Usuario("joaovf")
            vaiParaHome()
        }
    }

    private fun vaiParaHome() {
        val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
        val controlador = findNavController()
        controlador.navigate(direcao)
    }

}