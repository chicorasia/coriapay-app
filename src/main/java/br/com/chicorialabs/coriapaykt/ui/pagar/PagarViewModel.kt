package br.com.chicorialabs.coriapaykt.ui.pagar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.coriapaykt.data.Usuario
import br.com.chicorialabs.coriapaykt.data.UsuarioLogado
import br.com.chicorialabs.coriapaykt.service.ApiService
import kotlinx.coroutines.launch

class PagarViewModel(private val apiService: ApiService) : ViewModel() {

    private val _listaContatos = MutableLiveData<List<Usuario>>().also {
        it.value = mutableListOf()
    }

    val listaContatos: LiveData<List<Usuario>>
        get() = _listaContatos

    init {
        viewModelScope.launch {

            try {
                val login = UsuarioLogado.usuario.login
                _listaContatos.value = apiService.getContatos(login)
            } catch (e: Exception) {
                Log.e("pic_pay", "e.message")
            }
        }
    }

    //refatorar para usar uma suspend lambda e controlar a visualização de progressbar!

}