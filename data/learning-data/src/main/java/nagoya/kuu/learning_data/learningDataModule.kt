package nagoya.kuu.learning_data

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val learningDataModule = module {
    factory { LearningDataRepositoryImpl(androidContext()) as LearningDataRepository }
}