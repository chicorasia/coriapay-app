package br.com.chicorialabs.coriapaykt.data

sealed class State<T> {
    class Success<T>(val data: T) : State<T>()
    class Error<T>(val error: Exception) : State<T>()
    class NotLogged<T> : State<T>()
}
