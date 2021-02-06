package com.anlyn.alarm.Dagger.androidInjection
//lateinit property dispatchingAndroidInjector has not been initialized
//https://stackoverflow.com/questions/53195678/lateinit-property-dispatchingandroidinjector-has-not-been-initialized
import android.app.Application
import com.anlyn.alarm.AlarmAppApplication
import com.anlyn.alarm.Dagger.application.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,ActivitiesModule::class,AppModule::class])
 interface MainComponent{
    fun inject(application: AlarmAppApplication);

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application:Application): Builder

        fun build(): MainComponent
    }
//        fun displaySubComponent(displayAlarmModule: DisplayAlarmModule):DisplayAlarmSubComponent

}