package kuu.nagoya.dashboard.infra

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import kuu.nagoya.dashboard.usecase.RecordListReadonlyRepository
import kuu.nagoya.dashboard.usecase.RecordListRepository
import kuu.nagoya.entity.recordentity.Record
import kuu.nagoya.util.fromJson

internal class RecordListRepositoryImpl(
    private val context: Context
) : RecordListReadonlyRepository, RecordListRepository {


    private var sharedPreferences =
        context.getSharedPreferences(
            "Record",
            Context.MODE_PRIVATE
        )
    private val gson: Gson = Gson()
    private val preferenceKey = "record"

    override suspend fun loadRecordList():
            List<Record> {
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

    override suspend fun storeRecordList(recordList: List<Record>) {
        val json = gson.toJson(recordList)
        sharedPreferences.edit {
            this.putString(preferenceKey, json)
            this.commit()
        }
    }
}