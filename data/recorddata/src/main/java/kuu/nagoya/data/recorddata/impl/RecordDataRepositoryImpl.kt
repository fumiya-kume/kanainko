package kuu.nagoya.data.recorddata.impl

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import kuu.nagoya.data.recorddata.RecordDataRepository
import kuu.nagoya.data.recorddata.model.RecordDataModel
import kuu.nagoya.util.fromJson

internal class RecordDataRepositoryImpl(
    private val context: Context
) : RecordDataRepository {

    private var sharedPreferences =
        context.getSharedPreferences(
            "Record",
            Context.MODE_PRIVATE
        )
    private val gson: Gson = Gson()
    private val preferenceKey = "record"

    override suspend fun storeRecordDataList(dataList: List<RecordDataModel>) {
        val json = gson.toJson(dataList)
        sharedPreferences.edit {
            this.putString(preferenceKey, json)
            this.commit()
        }
    }

    override suspend fun loadRecordDataList(): List<RecordDataModel> {
        val json = sharedPreferences
            .getString(
                preferenceKey,
                ""
            )

        if (json.isNullOrEmpty()) {
            return emptyList()
        }

        return gson.fromJson(
            json
        )
    }
}
