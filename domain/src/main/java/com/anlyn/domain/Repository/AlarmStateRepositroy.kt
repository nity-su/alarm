package com.anlyn.domain.Repository

import com.anlyn.domain.models.AlarmEntity
import io.reactivex.rxjava3.core.Observable
import java.util.*

interface AlarmStateRepository {
    fun getAllAlarmSetCondition():Observable<List<AlarmEntity>>


}