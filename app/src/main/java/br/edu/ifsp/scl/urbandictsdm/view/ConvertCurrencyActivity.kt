package br.edu.ifsp.scl.urbandictsdm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import br.edu.ifsp.scl.urbandictsdm.R
import br.edu.ifsp.scl.urbandictsdm.view.spinner.CurrencySpinnerAdapter
import br.edu.ifsp.scl.urbandictsdm.viewmodel.ConvertCurrencyViewModel
import kotlinx.android.synthetic.main.activity_convert_currency.*
import java.util.*

class ConvertCurrencyActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    lateinit var selectedFromCurrency: String
    lateinit var selectedToCurrency: String
    lateinit var viewModel: ConvertCurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_currency)

        viewModel = ConvertCurrencyViewModel(this)

        viewModel.fetchAvailableCurrencies().observe(
            this@ConvertCurrencyActivity,
            androidx.lifecycle.Observer {
                currencyFromSpn.adapter = CurrencySpinnerAdapter(this, it.currencies)
                currencyToSpn.adapter = CurrencySpinnerAdapter(this, it.currencies)
                this.setSelectedFromCurrency(0)
                this.setSelectedToCurrency(0)
            }
        )

        currencyFromSpn.onItemSelectedListener = this
        currencyToSpn.onItemSelectedListener = this
        convertBtn.setOnClickListener(this)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "NothingSelected", Toast.LENGTH_LONG).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent?.id) {
            R.id.currencyFromSpn -> {
                this.setSelectedFromCurrency(position)
            }
            R.id.currencyToSpn -> {
                this.setSelectedToCurrency(position)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.convertBtn -> {
                val valueToConvert = valueToBeConvertedEt.text.toString()
                viewModel.convertCurrency(valueToConvert, selectedFromCurrency, selectedToCurrency).observe(
                    this,
                    androidx.lifecycle.Observer {
                        convertedValueTv.text = it.rates.get(selectedToCurrency)?.rate_for_amount
                    }
                )
            }
        }
    }

    fun setSelectedFromCurrency(position: Int) {
        selectedFromCurrency = currencyFromSpn.adapter.getItem(position) as String
    }

    fun setSelectedToCurrency(position: Int) {
        selectedToCurrency = currencyToSpn.adapter.getItem(position) as String
    }
}
