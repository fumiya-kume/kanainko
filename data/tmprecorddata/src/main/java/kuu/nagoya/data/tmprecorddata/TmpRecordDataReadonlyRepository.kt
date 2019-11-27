package kuu.nagoya.data.tmprecorddata

import kuu.nagoya.data.tmprecorddata.entity.TmpRecordDataEntity

interface TmpRecordDataReadonlyRepository{
    fun loadTmpRecordData(): TmpRecordDataEntity
}