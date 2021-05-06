package br.com.chicorialabs.picpayclonekt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.picpayclonekt.R
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.databinding.FragmentHomeBinding
import br.com.chicorialabs.picpayclonekt.ui.componente.Componente
import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private val componenteViewModel: ComponenteViewModel by sharedViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (UsuarioLogado.isUsuarioNaoLogado()) {
            vaiParaLogin()
        }
        componenteViewModel.atualizaComponentes(bottomNavigation = true)


    }

    private fun vaiParaLogin() {
        val direcao =
                HomeFragmentDirections.actionGlobalLoginFragment()
        val controlador = findNavController()
        controlador.navigate(direcao)
    }
}