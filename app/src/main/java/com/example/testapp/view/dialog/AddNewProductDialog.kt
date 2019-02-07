package com.example.testapp.view.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.R
import com.example.testapp.viewModel.shoppinglist.ShoppingListViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class AddNewProductDialog  : DialogFragment() {
    private val vm: ShoppingListViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val customView = inflater.inflate(R.layout.dialog_view, container,false)
        return customView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog

    }

    companion object {
        fun newInstance() = AddNewProductDialog()
    }
}