package br.com.chicorialabs.coriapaykt.ui.login

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.coriapaykt.data.*
import br.com.chicorialabs.coriapaykt.service.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginViewModel(val apiService: ApiService) : ViewModel() {

    private val _efetuouLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val efetuouLogin: LiveData<Boolean>
        get() = _efetuouLogin

    val usuarioState = MutableLiveData<State<Usuario>>()
    private val _token = MutableLiveData<Token?>()
    val onLoading = ObservableBoolean(false)


    fun onLoginEfetuado() {
        launchAndCatchException {
            _efetuouLogin.value = true
        }
    }

    fun onLogoff() {
        UsuarioLogado.isUsuarioNaoLogado()
        _efetuouLogin.value = false
        _token.value = null
        usuarioState.value = State.NotLogged()
    }

    fun launchAndCatchException(block: suspend () -> Unit) {
        viewModelScope.launch {
            onLoading.set(true)
            try {
                delay(500) //estético
                block()
            } catch (ex: Exception) {
                Log.e("picpay_kt", "launchAndCatchException: ${ex.message}")
                usuarioState.value = State.Error(ex)
            } finally {
                onLoading.set(false)
            }
        }
    }

    fun login(login: Login) {
        launchAndCatchException {
            val token = apiService.autentica(login)
            UsuarioLogado.token = token
            _token.value = token
            val usuario = apiService.getUsuario(login.usuario)
            UsuarioLogado.usuario = usuario
            usuarioState.value = State.Success(usuario)
        }
    }




}