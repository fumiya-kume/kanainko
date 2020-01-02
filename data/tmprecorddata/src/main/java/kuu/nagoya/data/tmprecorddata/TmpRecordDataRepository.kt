package kuu.nagoya.data.tmprecorddata

import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity

interface TmpRecordDataRepository {
    fun storeTmpData(tmpRecordDataEntity: TmpRecordDataEntity)
    fun clear()
}
