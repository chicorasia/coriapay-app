package br.com.chicorialabs.picpayclonekt.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.picpayclonekt.data.Login
import br.com.chicorialabs.picpayclonekt.data.Token
import br.com.chicorialabs.picpayclonekt.data.Usuario
import br.com.chicorialabs.picpayclonekt.data.UsuarioLogado
import br.com.chicorialabs.picpayclonekt.service.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(val apiService: ApiService) : ViewModel() {

    private val _efetuouLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val efetuouLogin: LiveData<Boolean>
        get() = _efetuouLogin

    private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario>
        get() = _usuario

    private val _token = MutableLiveData<Token?>()
    val token: LiveData<Token?>
        get() = _token

    private val _onLoading = MutableLiveData<Boolean>()
    val onLoading: LiveData<Boolean>
        get() = _onLoading

    private val _onError = MutableLiveData<Exception>()
    val onError: LiveData<Exception>
        get() = _onError


    //receber e processar um token

    fun onLoginEfetuado() {
        launchAndCatchException {
            _efetuouLogin.value = true
        }
    }

    fun onLogoff() {
        UsuarioLogado.isUsuarioNaoLogado()
        _efetuouLogin.value = false
        _token.value = null
    }

    fun launchAndCatchException(block: suspend () -> Unit) {
        viewModelScope.launch {
            _onLoading.value = true
            try {
                delay(500)
                block()
            } catch (ex: Exception) {
                Log.e("picpay_kt", "launchAndCatchException: ${ex.message}")
                _onError.value = ex
            } finally {
                _onLoading.value = false
            }
        }
    }

    fun login(login: Login) {
        launchAndCatchException {
            val token = apiService.autentica(login)
            _token.value = token
            UsuarioLogado.usuario = Usuario(login.usuario)
            UsuarioLogado.token = token
            _usuario.value = apiService.getUsuario(UsuarioLogado.usuario.login)
                _usuario.value?.let {
                    UsuarioLogado.usuario = it
                }
        }
    }


}