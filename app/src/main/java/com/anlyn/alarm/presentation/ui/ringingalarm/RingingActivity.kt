package com.anlyn.alarm.presentation.ui.ringingalarm

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.anlyn.alarm.databinding.ActivityRingingBinding
import javax.inject.Inject


class RingingActivity : AppCompatActivity() {
        val c:Context = this

        @Inject lateinit var binding : ActivityRingingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ////wifi on/off여부 후 dialog builder.build
//        dialogCreate().show()
    }

    fun dialogCreate() : AlertDialog{
        //execute
        val alertDialogBuilderUserInput: AlertDialog.Builder = AlertDialog.Builder(c)

        alertDialogBuilderUserInput
            .setCancelable(false)
            .setPositiveButton(
                "Send"
            ) { dialogBox, id ->
                // ToDo get user input here
            }
            .setNegativeButton(
                "Cancel"
            ) { dialogBox, id -> dialogBox.cancel() }

        return alertDialogBuilderUserInput.create()

    }
}