package br.com.chicorialabs.coriapaykt.service

import br.com.chicorialabs.coriapaykt.data.UsuarioLogado
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val URL = "http://192.168.0.187:8080/"

object RetrofitService {

    val instance = Retrofit.Builder()
        .client(criarHttpClient())
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun criarHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor {  chain ->
                val request = aplicarToken(chain)
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    private fun aplicarToken(chain: Interceptor.Chain): Request {
        return if (UsuarioLogado.isUsuarioLogado()) {
            val token = UsuarioLogado.token
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "${token.tipo} ${token.token}")
                .build()
        } else {
            chain.request()
        }

    }

    inline fun <reified T> criarService() = instance.create(T::class.java)
}