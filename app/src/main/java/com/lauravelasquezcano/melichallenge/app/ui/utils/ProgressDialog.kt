package com.lauravelasquezcano.melichallenge.app.ui.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.lauravelasquezcano.melichallenge.R

class ProgressDialog(context: Context) {

    private var progressDialog: Dialog = Dialog(context, R.style.ProgressDialogStyle)

    init {
        progressDialog.setCancelable(false)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.create()
    }

    fun showProgress() {
        if (progressDialog.isShowing.not()) progressDialog.show()
    }

    fun hideProgress() {
        progressDialog.dismiss()
    }
}