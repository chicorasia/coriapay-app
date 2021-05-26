package br.com.chicorialabs.coriapaykt.ui.transacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.chicorialabs.coriapaykt.R
import br.com.chicorialabs.coriapaykt.databinding.FragmentTransacaoBinding
import br.com.chicorialabs.coriapaykt.ui.componente.ComponenteViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TransacaoFragment : Fragment() {

    private lateinit var binding: FragmentTransacaoBinding
    private val argumentos by navArgs<TransacaoFragmentArgs>()
    private val mTransacaoViewModel: TransacaoViewModel by viewModel()
    private val componenteViewModel: ComponenteViewModel by sharedViewModel()
    private val usuario by lazy {
        argumentos.usuario
    }
    private val controlador by lazy { findNavController() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransacaoBinding.inflate(layoutInflater, container, false)
        binding.transacaoViewModel = mTransacaoViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarUsuario()
        configuraRadioGroup()
        configurarBotaoTransferir()

        componenteViewModel.atualizaComponentes(bottomNavigation = false)
        observarTransacao()

    }

    private fun observarTransacao() {
        mTransacaoViewModel.transacao.observe(viewLifecycleOwner) {
            //retorna para a tela anterior quando a transacao é gravada e retornada
            val direcao = TransacaoFragmentDirections.actionTransacaoFragmentToNavigationPagar()
            controlador.navigate(direcao)
        }
    }

    private fun configurarBotaoTransferir() {
        binding.transacaoTransferirBtn.setOnClickListener {

            val isCartaoCredito = binding.transacaoRadiobuttonCartaocredito.isChecked
            val valor = getValor()

            val transacao = if (isCartaoCredito) {
                mTransacaoViewModel.criarTransferenciaCartao(isCartaoCredito,
                    valor = valor, destino = usuario)
            } else {
                mTransacaoViewModel.criarTransferenciaSaldo(valor = valor, destino = usuario)
            }

            mTransacaoViewModel.transferir(transacao)
        }
    }



    private fun getValor(): Double {
        val valor = binding.transacaoValorEdt.text.toString()
        return if(valor.isEmpty()) 0.0
        else valor.toDouble()
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