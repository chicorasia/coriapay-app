package br.com.chicorialabs.picpayclonekt.ui.componente

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComponenteViewModel : ViewModel() {

    private val _componentes = MutableLiveData<Componente>().also {
        it.value = temComponente
    }

    init {
        _componentes.value = Componente(bottomNavigation = false)
    }

    val componentes: LiveData<Componente>
        get() = _componentes

    var temComponente = Componente()
        set(value) {
            field = value
            _componentes.value
        }

    fun atualizaComponentes(bottomNavigation: Boolean){
        _componentes.value = Componente(bottomNavigation = bottomNavigation)
    }
}

data class Componente(
        var bottomNavigation: Boolean = false
)