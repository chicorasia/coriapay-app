package br.com.chicorialabs.picpayclonekt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import br.com.chicorialabs.picpayclonekt.repository.TransacaoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TransacaoRepository) : ViewModel() {

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double>
        get() = _saldo

    private val _erro = MutableLiveData<String>().also {
        it.value = null
    }
    val erro: LiveData<String>
        get() = _erro


    private val _transacoes = MutableLiveData<List<Transacao>>()
    val transacoes: LiveData<List<Transacao>>
        get() = _transacoes


    init {
        if (UsuarioLogado.isUsuarioLogado()){
            launch {
                val login = UsuarioLogado.usuario.login
                val saldo = repository.getSaldo(login)
                saldo.let {
                    _saldo.postValue(it)
                }
                val transacoesRecebidas = repository.getTransacoes(login)
                transacoesRecebidas.let {
                    _transacoes.postValue(it)
                }
            }
        }

    }

    private fun launch(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (ex: Exception) {
                _erro.postValue(ex.message)
            }
        }
    }
}