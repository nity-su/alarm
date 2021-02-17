package com.anlyn.alarm.presentation.ui.ringingalarm

import com.anlyn.alarm.R
import android.app.KeyguardManager
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.anlyn.alarm.BR
import com.anlyn.alarm.databinding.ActivityRingingBinding
import com.anlyn.data.MediaPlayer
import dagger.android.AndroidInjection
import org.w3c.dom.Text
import javax.inject.Inject


class RingingActivity : AppCompatActivity() {

        @Inject lateinit var binding : ActivityRingingBinding
        @Inject lateinit var factory: RingingVMFactory

        private val c:Context = this
        private val viewModel: RingingViewModel by lazy <RingingViewModel>{
            val model by viewModels<RingingViewModel> { factory }
            model
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.setVariable(BR.ringingModel,viewModel)
        val path=intent.getStringExtra("path") ?: ""

        val id = intent?.getIntExtra("id",0)
        val mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        mManager!!.cancel(id!!)
        ////wifi on/off여부 후 dialog builder.build
        viewModel.getPhrase()
            .subscribe({phrase -> val dialog = dialogCreate(phrase.sentence)
                dialog.show()
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener({
                val root = it.rootView
                    val editText = root.findViewById<TextView>(R.id.userInputDialog)
                    if(editText.text.toString().equals(phrase.sentence)){
                        dialog.dismiss()
                    }
                })}
            ,{})

    }

    override fun onDestroy() {
        MediaPlayer.stop()
        super.onDestroy()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
//        settingWindow()
    }

    fun settingWindow(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            setInheritShowWhenLocked(true)
            val keyguardManager =
                getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager?.requestDismissKeyguard(this, null)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }
    }

    fun dialogCreate(phrase:String?) : AlertDialog{
        //execute
        val alertDialogBuilderUserInput: AlertDialog.Builder = AlertDialog.Builder(c)
        val lf = layoutInflater
        val mView: View = lf.inflate(R.layout.user_input_dialog_box, null)

        alertDialogBuilderUserInput.setView(mView)
        mView.findViewById<TextView>(R.id.dialogTitle).setText(phrase)
        val editText = mView.findViewById<TextView>(R.id.userInputDialog)

        alertDialogBuilderUserInput
            .setCancelable(false)
            .setPositiveButton(
                "확인",object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        TODO("Not yet implemented")
                    }
                }
            )

        return alertDialogBuilderUserInput.create()

    }
}