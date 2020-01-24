package nagoya.kuu.learning_data

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class LearningDataRepositoryImpl(
    private val context: Context
) : LearningDataRepository {

    private var sharedPreferences =
        context.getSharedPreferences(
            "Record",
            Context.MODE_PRIVATE
        )
    private val gson: Gson = Gson()
    private val preferenceKey = "record"

    private fun loadLearningData(): Map<Int, Boolean> {
        val json = sharedPreferences.getString(preferenceKey, "")
        return if (json.isNullOrEmpty()) {
            mapOf()
        } else {
            val type: Type = object : TypeToken<MutableMap<Int, Boolean>>() {}.type
            gson.fromJson(json, type)
        }
    }

    override fun isLearnined(recordDataModelId: recordDataModelId): Boolean {
        val learnedData: Map<Int, Boolean> = loadLearningData()

        return learnedData.getOrDefault(recordDataModelId, false)
    }

    override fun uddateLearnedStatus(recordDataModelId: recordDataModelId, newValue: Boolean) {
        val learningData = loadLearningData().toMutableMap()

        learningData[recordDataModelId] = newValue

        sharedPreferences.edit {
            this.putString(preferenceKey, gson.toJson(learningData))
            commit()
        }
    }
}