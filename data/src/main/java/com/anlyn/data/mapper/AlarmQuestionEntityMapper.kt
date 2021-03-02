package com.anlyn.data.mapper

import com.anlyn.data.entities.AlarmQuestion
import com.anlyn.domain.Mapper
import com.anlyn.domain.models.QuestionEntity
import javax.inject.Inject

class AlarmQuestionEntityMapper @Inject constructor() : Mapper<AlarmQuestion,QuestionEntity>(){
    override fun mapFrom(from: AlarmQuestion): QuestionEntity {
        return QuestionEntity(sentence = from.sentence)
    }
}