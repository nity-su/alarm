package com.anlyn.alarmwheater.Dagger

import android.content.Context
import com.anlyn.alarmwheater.Dagger.data.DataModule
import com.anlyn.alarmwheater.MainActivity
import com.anlyn.data.db.local.AppDataBase
import com.anlyn.domain.AlarmCache
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
 interface MainComponent{
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun getContext(context: Context):Builder

        fun build():MainComponent
    }
}