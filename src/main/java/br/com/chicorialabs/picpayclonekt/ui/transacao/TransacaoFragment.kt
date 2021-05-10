package br.com.chicorialabs.picpayclonekt.ui.transacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.chicorialabs.picpayclonekt.R
import br.com.chicorialabs.picpayclonekt.data.CartaoCredito
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao.Companion.gerarHash
import br.com.chicorialabs.picpayclonekt.databinding.FragmentTransacaoBinding
import br.com.chicorialabs.picpayclonekt.extension.formatar
import br.com.chicorialabs.picpayclonekt.ui.componente.ComponenteViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class TransacaoFragment : Fragment() {

    private lateinit var binding: FragmentTransacaoBinding
    private val argumentos by navArgs<TransacaoFragmentArgs>()
    private val transacaoViewModel: TransacaoViewModel by viewModel()
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
        transacaoViewModel.transacao.observe(viewLifecycleOwner) {
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
                criarTransferenciaCartao(isCartaoCredito, valor)
            } else {
                criarTransferenciaSaldo(valor)
            }

            transacaoViewModel.transferir(transacao)
        }
    }

    private fun criarTransferenciaSaldo(valor: Double) = Transacao(
            codigo = gerarHash(),
            origem = UsuarioLogado.usuario,
            destino = usuario,
            dataHora = Calendar.getInstance().formatar(),
            isCartaoCredito = false,
            valor = valor,
        )

    private fun criarTransferenciaCartao(isCartaoCredito: Boolean, valor: Double) =
        Transacao(
            codigo = gerarHash(),
            origem = UsuarioLogado.usuario,
            destino = usuario,
            dataHora = Calendar.getInstance().formatar(),
            isCartaoCredito = isCartaoCredito,
            valor = valor,
            cartaoCredito = criarCartaoCredito()
        )

    private fun criarCartaoCredito(): CartaoCredito {
        val numeroCartao = binding.transacaoNumeroCartao.text.toString()
        val nomeTitular = binding.transacaoTitularCartao.text.toString()
        val vencimento = binding.transacaoValidadeCartao.text.toString()
        val cvc = binding.transcaoCvcCartao.text.toString()

        return CartaoCredito(
            numeroCartao = numeroCartao,
            nomeTitular = nomeTitular,
            dataExpiracao = vencimento,
            codigoSeguranca = cvc,
            usuario = UsuarioLogado.usuario,
        )

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