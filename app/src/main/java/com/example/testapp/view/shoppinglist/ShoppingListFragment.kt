package com.example.testapp.view.shoppinglist

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.testapp.R
import com.example.testapp.domain.model.Product
import com.example.testapp.utils.extensions.nonNullObserve
import com.example.testapp.view.BaseFragment
import com.example.testapp.view.ToolbarDescription
import com.example.testapp.view.ToolbarIcon
import com.example.testapp.view.dialog.AddNewProductDialog
import com.example.testapp.viewModel.shoppinglist.ShoppingListViewModel
import kotlinx.android.synthetic.main.shopping_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ShoppingListFragment : BaseFragment(R.layout.shopping_list_fragment) {

    override val toolbarDescription: ToolbarDescription = ToolbarDescription(true, ToolbarIcon.ARROW, R.string.shopping_list, null)
    override val isDrawerEnabled: Boolean
        get() = false

    private val vm: ShoppingListViewModel by viewModel()
    private var date: String = ""
    private lateinit var shoppingListAdapter: ShoppingListAdapter

    private val addNewProductDialog: AddNewProductDialog by lazy { AddNewProductDialog.newInstance()}

    override fun listenToVm() {
        vm.apply {
            shoppingList.nonNullObserve(this@ShoppingListFragment){ setData(it.products)}
            getShoppingList(date)
        }
    }

    override fun listenToUi() {
        initViews()
        addNewProductDialog.setOnClickListener(object: AddNewProductDialog.DialogClickListener{
            override fun onAcceptClick(product: Product) {
                vm.saveInShopingList(date, product)
            }
        })
        addNewProduct.setOnClickListener { addNewProductDialog.show(fragmentManager, tag) }
    }

    private fun initViews(){
        shoppingListAdapter = ShoppingListAdapter{ position ->
        }
        productsRv.layoutManager = LinearLayoutManager(context)
        productsRv.adapter = shoppingListAdapter
    }

    private fun setData(products: List<Product>){
        if(products.isEmpty()){
            emptyProductHolder.visibility = View.VISIBLE
        }else {
            shoppingListAdapter.setData(products)
        }
    }

    companion object {
        fun newInstance(date: String) = ShoppingListFragment().apply {
            this.date = date
        }
    }
}