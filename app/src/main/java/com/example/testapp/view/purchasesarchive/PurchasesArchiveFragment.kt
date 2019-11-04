package com.example.testapp.view.purchasesarchive

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.testapp.R
import com.example.testapp.utils.extensions.nonNullObserve
import com.example.testapp.view.BaseFragment
import com.example.testapp.view.ToolbarDescription
import com.example.testapp.view.ToolbarIcon
import com.example.testapp.viewModel.purchasesarchive.PurchasesArchiveViewModel
import kotlinx.android.synthetic.main.purchases_archive_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class PurchasesArchiveFragment : BaseFragment(R.layout.purchases_archive_layout) {

    override val toolbarDescription: ToolbarDescription = ToolbarDescription(true, ToolbarIcon.ARROW, R.string.purchases_archive_title, null)
    override val isDrawerEnabled: Boolean
        get() = false

    private val vm: PurchasesArchiveViewModel by viewModel()
    lateinit var purchasesArchiveAdapter: PurchasesArchiveAdapter
    private var archiveList: List<String>? = null

    override fun listenToVm() {
        vm.apply {
            archiveList.nonNullObserve(this@PurchasesArchiveFragment){ setData(it)}
            getArchiveList()
        }
    }

    override fun listenToUi() {
        initViews()
        addNewList.setOnClickListener { vm.goToShopingList("") }
    }

    private fun initViews(){
        purchasesArchiveAdapter = PurchasesArchiveAdapter { position ->
            vm.goToShopingList(archiveList!![position])
        }
        archiveRv.layoutManager = LinearLayoutManager(context)
        archiveRv.adapter = purchasesArchiveAdapter
    }

    private fun setData(dates: List<String>){
        this.archiveList = dates
        if(dates.isEmpty()){
            emptyHolder.visibility = View.VISIBLE
        }else {
            purchasesArchiveAdapter.setData(dates)
        }
    }

    companion object {
        fun newInstance() = PurchasesArchiveFragment()
    }
}