package kuu.nagoya.data.tmprecorddata

import android.content.Context
import com.google.gson.Gson
import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity
import kuu.nagoya.util.fromJson

internal class TmpRecordDataRepositoryImpl(
    private val context: Context
) : TmpRecordDataRepository, TmpRecordDataReadonlyRepository {

    val preference = context.getSharedPreferences("tmprecord", Context.MODE_PRIVATE)
    private val preferenceKey = "tmprecord"
    override fun storeTmpData(tmpRecordDataEntity: TmpRecordDataEntity) {
        preference.edit()
            .apply {
                this.putString(
                    preferenceKey,
                    Gson().toJson(tmpRecordDataEntity)
                )
            }.apply()
    }

    override fun clear() {
        preference.edit()
            .apply {
                this.putString(
                    preferenceKey,
                    ""
                )
            }.apply()
    }

    override fun loadTmpRecordData(): TmpRecordDataEntity {
        val json = preference.getString(preferenceKey, "")
        if (json.isNullOrEmpty()) {
            return TmpRecordDataEntity(
                0,
                null,
                null
            )
        }

        val gson = Gson()
        return gson.fromJson(json)
    }
}
