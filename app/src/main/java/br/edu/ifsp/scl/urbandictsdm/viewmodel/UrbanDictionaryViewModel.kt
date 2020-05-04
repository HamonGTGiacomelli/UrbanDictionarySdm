package br.edu.ifsp.scl.urbandictsdm.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.scl.urbandictsdm.model.urbanDictionaryApi.Response
import br.edu.ifsp.scl.urbandictsdm.model.urbanDictionaryApi.UrbanDictionaryService

class UrbanDictionaryViewModel(context: Context): ViewModel() {
    private val model =
        UrbanDictionaryService(
            context
        )

    fun buscaSignificado(palavra: String): MutableLiveData<Response> {
        return model.getMeanings(palavra)
    }
}