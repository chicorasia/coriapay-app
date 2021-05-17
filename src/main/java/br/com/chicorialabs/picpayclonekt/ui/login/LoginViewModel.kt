package br.com.chicorialabs.picpayclonekt.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.service.ApiService
import kotlinx.coroutines.launch

class LoginViewModel(val apiService: ApiService) : ViewModel() {

    private val _efetuouLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val efetuouLogin: LiveData<Boolean>
        get() = _efetuouLogin

    private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario>
        get() = _usuario


    fun onLoginEfetuado() {
        launchAndCatchException{
            _efetuouLogin.value = true
            _usuario.value = apiService.getUsuario(UsuarioLogado.usuario.login)
            _usuario.value?.let {
                UsuarioLogado.usuario = it
            }
        }
    }

    fun onLogoff() {
        _efetuouLogin.value = false
        UsuarioLogado.isUsuarioNaoLogado()
    }

    fun launchAndCatchException(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
            } catch (ex: Exception){
                Log.e("picpay_kt", "launchAndCatchException: ${ex.message}", )
            }
        }
    }
}