package br.com.chicorialabs.picpayclonekt.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.databinding.FragmentPagarBinding
import br.com.chicorialabs.picpayclonekt.ui.adapter.PagarAdapter
import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PagarFragment : Fragment() {

    private val pagarViewModel: PagarViewModel by viewModel()
    private val componenteViewModel: ComponenteViewModel by sharedViewModel()
    private lateinit var binding: FragmentPagarBinding
    private val controlador: NavController by lazy { findNavController() }

    private val contatosRv : RecyclerView by lazy {
        binding.pagarContatosRv
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observarContatos()
        componenteViewModel.atualizaComponentes(bottomNavigation = true)

    }

    private fun observarContatos() {
        pagarViewModel.listaContatos.observe(viewLifecycleOwner) { listaContatos ->
            initListaContatos(listaContatos)
        }
    }

    private fun initListaContatos(listaContatos: List<Usuario>) {

        contatosRv.adapter = PagarAdapter(listaContatos, onClick = { usuarioClicado ->

            val direcao = PagarFragmentDirections
                    .actionNavigationPagarToTransacaoFragment(usuarioClicado)
            controlador.navigate(direcao)

        })
    }
}