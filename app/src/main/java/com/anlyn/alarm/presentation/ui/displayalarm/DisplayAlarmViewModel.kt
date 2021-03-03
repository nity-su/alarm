package com.anlyn.alarm.presentation.ui.displayalarm

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anlyn.domain.models.AlarmEntity
import com.anlyn.domain.usercase.GetAllAlarmUseCase
import com.anlyn.domain.usercase.RemoveAlarmUseCase
import io.reactivex.rxjava3.core.Observable

class DisplayAlarmViewModel (private val getAllAlarmUseCase: GetAllAlarmUseCase,
                             private val removeAlarmUseCase: RemoveAlarmUseCase) : ViewModel(){
    private val _alarmLiveData = MutableLiveData<List<AlarmEntity>>()
     var alarmLiveData : LiveData<List<AlarmEntity>> = _alarmLiveData
//https://android.jlelse.eu/how-to-bind-a-list-of-items-to-a-recyclerview-with-android-data-binding-1bd08b4796b4
        init{
    getAllAlarmEntity()
}
    fun getAllAlarmEntity(){
        getAllAlarmUseCase.observable().subscribe({values -> _alarmLiveData.value = values
            alarmLiveData = _alarmLiveData})

    }

    fun deleteAlarmEntity(): deleteEvent{
        return object : deleteEvent{
            override fun event(alarmEntity: AlarmEntity) : Observable<Boolean> {
                Log.d("삭제","상겅")
                return removeAlarmUseCase.remove(alarmEntity)
            }
        }
    }

    interface deleteEvent{
        fun event(alarmEntity: AlarmEntity):Observable<Boolean>
    }

}