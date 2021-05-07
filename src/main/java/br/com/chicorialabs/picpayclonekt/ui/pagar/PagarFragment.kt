package br.com.chicorialabs.picpayclonekt.ui.pagar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.databinding.FragmentPagarBinding
import br.com.chicorialabs.picpayclonekt.ui.adapter.PagarAdapter
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class PagarFragment : Fragment() {

    private val pagarViewModel: PagarViewModel by viewModel()
    private lateinit var binding: FragmentPagarBinding

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

        val listaMock = listOf<Usuario>(
                Usuario(login = "chicoria", nomeCompleto = "Chico Rasia"),
                Usuario(login = "joaovf", nomeCompleto = "Joao Vitor Ferreira"),
                Usuario(login = "tdurden", nomeCompleto = "Tyler Durden")
        )

        contatosRv.adapter = PagarAdapter(listaMock, onClick =  {
            Log.i("pic_pay", "onViewCreated: Clicou no item da RV!!!")
        } )
    }
}