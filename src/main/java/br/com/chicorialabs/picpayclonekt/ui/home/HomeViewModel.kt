package br.com.chicorialabs.picpayclonekt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.service.ApiService
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double>
        get() = _saldo

    private val _erro = MutableLiveData<String>().also {
        it.value = null
    }

    val erro: LiveData<String>
        get() = _erro



    init {
        launch {
            val login = UsuarioLogado.usuario.login
            val saldo = apiService.getSaldo(login).saldo
            saldo?.let {
                _saldo.value = it
            }
        }
    }

    private fun launch(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (ex: Exception) {
                _erro.value = ex.message
            }
        }
    }
}