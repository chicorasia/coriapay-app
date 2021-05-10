package br.com.chicorialabs.picpayclonekt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.databinding.FragmentHomeBinding
import br.com.chicorialabs.picpayclonekt.extension.formatarMoeda
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (UsuarioLogado.isUsuarioNaoLogado()) {
            vaiParaLogin()
        }
        componenteViewModel.atualizaComponentes(bottomNavigation = true)

        initObservadorSaldo()
        initObservadorErro()


    }

    private fun initObservadorErro() {
        homeViewModel.erro.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(
                    this@HomeFragment.context,
                    "Ocorreu um erro: ${it}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initObservadorSaldo() {
        homeViewModel.saldo.observe(viewLifecycleOwner) {
            it?.let {
                binding.homeSaldo.text = it.formatarMoeda()
            }
        }
    }

    private fun vaiParaLogin() {
        val direcao =
                HomeFragmentDirections.actionGlobalLoginFragment()
        val controlador = findNavController()
        controlador.navigate(direcao)
    }
}