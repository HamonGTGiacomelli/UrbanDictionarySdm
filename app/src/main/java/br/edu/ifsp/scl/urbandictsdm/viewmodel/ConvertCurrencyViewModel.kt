package br.edu.ifsp.scl.urbandictsdm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.scl.urbandictsdm.model.currencyApi.AvailableCurrencyResponse
import br.edu.ifsp.scl.urbandictsdm.model.currencyApi.ConvertCurrencyResponse
import br.edu.ifsp.scl.urbandictsdm.model.currencyApi.CurrencyService

class ConvertCurrencyViewModel(context: Context) {

    private val currencyModel = CurrencyService(context)

    fun fetchAvailableCurrencies() : MutableLiveData<AvailableCurrencyResponse> {
        return currencyModel.fetchAvailableCurrencies()
    }

    fun convertCurrency(valueToConvert: String, fromCurrency: String, toCurrency: String) : MutableLiveData<ConvertCurrencyResponse> {
        return currencyModel.convertCurrency(valueToConvert,fromCurrency,toCurrency)
    }

}