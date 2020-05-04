package br.edu.ifsp.scl.urbandictsdm.view.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import br.edu.ifsp.scl.urbandictsdm.R
import kotlinx.android.synthetic.main.currency_item.view.*

class CurrencySpinnerAdapter(val context: Context, val list: HashMap<String, String>) :
    BaseAdapter() {

    var valueList: List<String>
    var keysList: List<String>

    init {
        valueList = list.values.toList()
        keysList = list.keys.toList()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.currency_item, null)
        view.currencyTv.text = valueList.get(position)
        return view
    }

    override fun getItem(position: Int): Any {
        return keysList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}