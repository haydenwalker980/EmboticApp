package com.embotic.nodes

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.embotic.nodes.UnderMaintenance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

abstract class BaseActivity : AppCompatActivity() {
    private var dialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectToFirebase()
    }

    private fun connectToFirebase() {
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference()
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val underMaintenance = dataSnapshot.getValue(
                    UnderMaintenance::class.java
                ) ?: return
                if (underMaintenance.is_under_maintenance) {
                    showUnderMaintenanceDialog(underMaintenance.under_maintenance_message)
                } else {
                    dismissUnderMaintenanceDialog()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    private fun showUnderMaintenanceDialog(underMaintenanceMessage: String?) {
        if (dialog == null) {
            dialog = AlertDialog.Builder(this).create()
            dialog!!.setCancelable(false)
            dialog!!.setButton(
                DialogInterface.BUTTON_POSITIVE,
                "OK"
            ) { dialog, which ->
                dialog.dismiss()
                super@BaseActivity.finish()
            }
        }
        dialog!!.setMessage(underMaintenanceMessage)
        if (!this.isFinishing) dialog!!.show()
    }

    private fun dismissUnderMaintenanceDialog() {
        if (dialog != null && dialog!!.isShowing) dialog!!.dismiss()
    }
}