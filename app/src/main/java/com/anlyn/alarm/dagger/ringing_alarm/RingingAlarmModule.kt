package com.anlyn.alarm.dagger.ringing_alarm

import androidx.activity.viewModels
import com.anlyn.alarm.databinding.ActivityRingingBinding
import com.anlyn.alarm.presentation.common.ASyncTransformer
import com.anlyn.alarm.presentation.ui.ringingalarm.RingingActivity
import com.anlyn.alarm.presentation.ui.ringingalarm.RingingVMFactory
import com.anlyn.alarm.presentation.ui.ringingalarm.RingingViewModel
import com.anlyn.data.db.RemoteRepositoryImpl
import com.anlyn.data.db.remote.PhraseDataSource
import com.anlyn.data.entities.AlarmQuestion
import com.anlyn.data.mapper.AlarmQuestionEntityMapper
import com.anlyn.domain.Mapper
import com.anlyn.domain.Repository.RemoteRepository
import com.anlyn.domain.models.QuestionEntity
import com.anlyn.domain.usercase.PhraseLoadUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object RingingAlarmModule{
    @JvmStatic
    @Provides
    fun providesActivityRingingBinding(activity:RingingActivity):ActivityRingingBinding{
        return ActivityRingingBinding.inflate(activity.layoutInflater)
    }

    @JvmStatic
    @Provides
    fun providesRingingVMFactory(useCase: PhraseLoadUseCase):RingingVMFactory{
        return RingingVMFactory(useCase)
    }

    @JvmStatic
    @Provides
    fun providesPhraseLoadUseCase(@Named("RemoteRepoImpl") repo: RemoteRepository):PhraseLoadUseCase
            = PhraseLoadUseCase(repo,ASyncTransformer())

}