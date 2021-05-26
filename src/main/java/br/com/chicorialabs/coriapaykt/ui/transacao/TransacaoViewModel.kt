package br.com.chicorialabs.coriapaykt.ui.transacao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.coriapaykt.data.CartaoCredito
import br.com.chicorialabs.coriapaykt.data.Usuario
import br.com.chicorialabs.coriapaykt.data.UsuarioLogado
import br.com.chicorialabs.coriapaykt.data.transacao.Transacao
import br.com.chicorialabs.coriapaykt.extension.formatar
import br.com.chicorialabs.coriapaykt.service.ApiService
import kotlinx.coroutines.launch
import java.util.*

class TransacaoViewModel(private val apiService: ApiService) : ViewModel() {

    private val _transacao = MutableLiveData<Transacao>()
    val transacao: LiveData<Transacao>
        get() = _transacao

    val numeroCartao = MutableLiveData<String>()
    val nomeTitular = MutableLiveData<String>()
    val vencimento = MutableLiveData<String>()
    val cvc = MutableLiveData<String>()

    fun transferir(transacao: Transacao) {
        viewModelScope.launch {
            try {
                _transacao.value = apiService.realizarTransacao(transacao)
            } catch (e: Exception) {
                Log.e("pic_pay", "transferir: ${e.message}")
            }
        }
    }

    fun criarTransferenciaSaldo(valor: Double, destino: Usuario) = Transacao(
        codigo = Transacao.gerarHash(),
        origem = UsuarioLogado.usuario,
        destino = destino,
        dataHora = Calendar.getInstance().formatar(),
        isCartaoCredito = false,
        valor = valor,
    )


    fun criarTransferenciaCartao(isCartaoCredito: Boolean, valor: Double, destino: Usuario) =
        Transacao(
            codigo = Transacao.gerarHash(),
            origem = UsuarioLogado.usuario,
            destino = destino,
            dataHora = Calendar.getInstance().formatar(),
            isCartaoCredito = isCartaoCredito,
            valor = valor,
            cartaoCredito = criarCartaoCredito()
        )


    fun criarCartaoCredito(): CartaoCredito {
        val numeroCartao = numeroCartao.value
        val nomeTitular = nomeTitular.value
        val vencimento = vencimento.value
        val cvc = cvc.value

        return CartaoCredito(
            numeroCartao = numeroCartao!!,
            nomeTitular = nomeTitular!!,
            dataExpiracao = vencimento!!,
            codigoSeguranca = cvc!!,
            usuario = UsuarioLogado.usuario,
        )

    }


}
