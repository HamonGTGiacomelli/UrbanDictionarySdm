package br.edu.ifsp.scl.urbandictsdm.model.currencyApi

class ConvertCurrencyResponse(
    val amount: String,
    val base_currency_code: String,
    val base_currency_name: String,
    val status: String,
    val updated_date: String,
    val rates: Map<String, Rates>
)

class Rates(
    val currency_name: String,
    val rate: String,
    val rate_for_amount: String
)