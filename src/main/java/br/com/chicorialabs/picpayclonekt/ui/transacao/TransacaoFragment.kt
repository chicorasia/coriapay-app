package br.com.chicorialabs.picpayclonekt.ui.transacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.chicorialabs.picpayclonekt.R
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

        configurarUsuario()
        configuraRadioGroup()
        binding.transacaoTransferirBtn.setOnClickListener {

            val valor = getValor()

            if(binding.transacaoRadiobuttonCartaocredito.isChecked) {
                //criar transacao com Cartao de Credito
                criarTransferenciaCartao()
            } else {
                //criar transacao com Saldo
            }
        }

    }

    private fun criarTransferenciaCartao() {
//        TODO: implemnentar esse método
    }

    private fun getValor(): Double {
        val valor = binding.transacaoValorEdt.text as String
        if(valor.isEmpty()) return 0.0
        else return valor.toDouble()
    }

    private fun configurarUsuario() {
        binding.transacaoLogin.text = usuario.login
        binding.transacaoNomeCompleto.text = usuario.nomeCompleto
    }

    private fun configuraRadioGroup() {
        binding.transacaoRadiogroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.transacao_radiobutton_cartaocredito -> {
                    binding.transacaoCartaocreditoConstraintlayout.visibility = View.VISIBLE
                }
                R.id.transacao_radiobutton_saldo -> {
                    binding.transacaoCartaocreditoConstraintlayout.visibility = View.GONE
                }
            }
        }
    }
}