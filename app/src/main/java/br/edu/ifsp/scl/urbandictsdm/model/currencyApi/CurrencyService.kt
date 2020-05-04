package br.edu.ifsp.scl.urbandictsdm.model.currencyApi

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.scl.urbandictsdm.R
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import java.lang.StringBuilder

class CurrencyService(val context: Context) {
    private val queue = Volley.newRequestQueue(context)
    private val gson = Gson()

    fun fetchAvailableCurrencies() : MutableLiveData<AvailableCurrencyResponse> {

        val url = StringBuilder(CurrencyApi.BASE_URL)
            .append("${CurrencyApi.AVAILABLE_CURRENCY_SERVICE}")
            .toString()

        val availableCurrencyResponse = MutableLiveData<AvailableCurrencyResponse>()
        val request = object : JsonObjectRequest(
            Method.GET,
            url,
            null,
            { response: JSONObject ->
                response?.let {
                    availableCurrencyResponse.value = gson.fromJson(response.toString(), AvailableCurrencyResponse::class.java)
                }
            },
            {
                    error: VolleyError ->
                Log.e(context.getString(R.string.app_name), error.message!!)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return mutableMapOf(
                    Pair(
                        CurrencyApi.X_RAPIDAPI_KEY_FIELD,
                        CurrencyApi.X_RAPIDAPI_KEY_VALUE
                    ),
                    Pair(
                        CurrencyApi.X_RAPIDAPI_HOST_FIELD,
                        CurrencyApi.X_RAPIDAPI_HOST_VALUE
                    )
                )
            }
        }

        queue.add(request)

        return availableCurrencyResponse
    }

    fun convertCurrency(valueToConvert: String, fromCurrency: String, toCurrency: String) : MutableLiveData<ConvertCurrencyResponse> {

        val url = StringBuilder(CurrencyApi.BASE_URL)
            .append("${CurrencyApi.CONVERT_CURRENCY_SERVICE}")
            .append("?format=json")
            .append("&from=${fromCurrency}")
            .append("&to=${toCurrency}")
            .append("&amount=${valueToConvert}")
            .toString()

        val availableCurrencyResponse = MutableLiveData<ConvertCurrencyResponse>()
        val request = object : JsonObjectRequest(
            Method.GET,
            url,
            null,
            { response: JSONObject ->
                response?.let {
                    availableCurrencyResponse.value = gson.fromJson(response.toString(), ConvertCurrencyResponse::class.java)
                }
            },
            {
                    error: VolleyError ->
                Log.e(context.getString(R.string.app_name), error.message!!)
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                return mutableMapOf(
                    Pair(
                        CurrencyApi.X_RAPIDAPI_KEY_FIELD,
                        CurrencyApi.X_RAPIDAPI_KEY_VALUE
                    ),
                    Pair(
                        CurrencyApi.X_RAPIDAPI_HOST_FIELD,
                        CurrencyApi.X_RAPIDAPI_HOST_VALUE
                    )
                )
            }
        }

        queue.add(request)

        return availableCurrencyResponse
    }
}