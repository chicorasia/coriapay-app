package br.com.chicorialabs.picpayclonekt.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _efetuouLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val efetuouLogin: LiveData<Boolean>
        get() = _efetuouLogin

    fun onLoginEfetuado() {
        _efetuouLogin.value = true
    }

    fun onLogoff() {
        _efetuouLogin.value = false
    }
}