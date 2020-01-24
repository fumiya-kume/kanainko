package nagoya.kuu.learning_data
interface LearningDataRepository {
    fun isLearnined(recordDataModelId: recordDataModelId): Boolean
    fun uddateLearnedStatus(recordDataModelId: recordDataModelId, newValue: Boolean)
}