package br.com.chicorialabs.picpayclonekt.ui.transacao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.picpayclonekt.data.transacao.Transacao
import br.com.chicorialabs.picpayclonekt.service.ApiService
import kotlinx.coroutines.launch

class TransacaoViewModel(private val apiService: ApiService) : ViewModel() {

    private val _transacao = MutableLiveData<Transacao>()
    val transacao: LiveData<Transacao>
        get() = _transacao

    fun transferir(transacao: Transacao) {
        viewModelScope.launch {
            try {
                _transacao.value = apiService.realizarTransacao(transacao)
            } catch (e: Exception) {
                Log.e("pic_pay", "transferir: ${e.message}")
            }
        }
    }

}
