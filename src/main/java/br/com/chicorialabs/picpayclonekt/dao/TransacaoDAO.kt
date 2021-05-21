package br.com.chicorialabs.picpayclonekt.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.chicorialabs.picpayclonekt.data.transacao.TransacaoLocal

@Dao
interface TransacaoDAO {

    @Query("SELECT * FROM TRANSACAO")
    fun getAll(): LiveData<List<TransacaoLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(transacao: TransacaoLocal) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transacoes: List<TransacaoLocal>)

    @Delete
    fun delete(transacao: TransacaoLocal)

    @Update
    fun update(transacao: TransacaoLocal)
}