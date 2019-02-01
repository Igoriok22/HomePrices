package com.example.testapp.view.dashboard

import android.view.View
import android.widget.AdapterView
import com.example.testapp.R
import com.example.testapp.utils.extensions.initLoader
import com.example.testapp.utils.extensions.nonNullObserve
import com.example.testapp.view.BaseFragment
import com.example.testapp.view.ToolbarDescription
import com.example.testapp.view.ToolbarIcon
import com.example.testapp.viewModel.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.dashboard_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment: BaseFragment(R.layout.dashboard_layout) {

    override val toolbarDescription: ToolbarDescription = ToolbarDescription(true, ToolbarIcon.HAMBURGER, R.string.dashboard_title, null)

    lateinit var foreignCurrencyAdapter: CurrencysAdapter
    lateinit var nativeCurrencyAdapter: CurrencysAdapter

    private val localCurrencyModel = LocalCurrencyModel()
    private var foreignCurrencyRequest: String = "EUR"
    private var nativeCurrencyRequest: String = "EUR"
    private var divider: String = "_"

    private val vm: DashboardViewModel by viewModel()

    override fun listenToVm() {
        vm.currencyRate.nonNullObserve(this@DashboardFragment) { updateUI(it) }

        vm.isLoading.nonNullObserve(this@DashboardFragment) {
            courseTv.visibility = if (it) View.GONE else View.VISIBLE
            dashboard_loader.initLoader(it)
        }

        vm.getCurrencyRateFromCashe()
    }

    override fun listenToUi() {
        initViews()

        foreignСurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                foreignCurrencyRequest = localCurrencyModel.currencys[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        nativeСurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                nativeCurrencyRequest = localCurrencyModel.currencys[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        accept_bt.setOnClickListener {
            vm.getCurrencyRate("$foreignCurrencyRequest$divider$nativeCurrencyRequest")
        }
    }

    private fun initViews(){
        foreignCurrencyAdapter = CurrencysAdapter(localCurrencyModel.currencys, context!!)
        nativeCurrencyAdapter = CurrencysAdapter(localCurrencyModel.currencys, context!!)

        foreignСurrencySpinner.adapter = foreignCurrencyAdapter
        nativeСurrencySpinner.adapter = nativeCurrencyAdapter
    }

    private fun updateUI(rate: Double){
        courseTv.text = getString(R.string.dashboard_current_rate, rate)
    }

    companion object {
        fun newInstance() = DashboardFragment()
    }
}