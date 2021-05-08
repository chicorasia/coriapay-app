package br.com.chicorialabs.picpayclonekt.ui.transacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.chicorialabs.picpayclonekt.databinding.FragmentTransacaoBinding

class TransacaoFragment : Fragment() {

    private lateinit var binding: FragmentTransacaoBinding
    private val argumentos by navArgs<TransacaoFragmentArgs>()
    private val usuario by lazy {
        argumentos.usuario
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransacaoBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.transacaoLogin.text = usuario.login
        binding.transacaoNomeCompleto.text = usuario.nomeCompleto

    }
}