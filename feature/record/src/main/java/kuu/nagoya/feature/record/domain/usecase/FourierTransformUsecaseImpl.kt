package kuu.nagoya.feature.record.domain.usecase

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D
import kuu.nagoya.feature.record.domain.model.FourierTransformationModel
import kuu.nagoya.feature.record.domain.model.SoundRawDataModel

internal class FourierTransformUsecaseImpl : FourierTransformUsecase {
    override suspend fun execute(soundRawDataModel: SoundRawDataModel): FourierTransformationModel {
        val fft = DoubleFFT_1D(soundRawDataModel.rawDara.size)
        val data = soundRawDataModel
            .rawDara
            .map { it.toDouble() }
            .toDoubleArray()

        fft.realForward(data)

        val power = (0..data.size - 2)
            .filter { it % 2 != 2 }
            .map {
                Math.sqrt(Math.pow(data.elementAt(it), 2.0) + Math.pow(data.elementAt(it + 1), 2.0))
            }
            .map { it / data.size }
            .map { it.toFloat() }
        return FourierTransformationModel(power)
    }
}