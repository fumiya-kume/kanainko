package com.prevent.voice_data.infra

import android.content.Context
import com.prevent.voice_data.domain.AssetsInitUsecase
import java.io.File

class AssetsInitUsecaseImpl(
    private val context: Context
) : AssetsInitUsecase {
    override fun execute() {
        val mediaDir = context.externalMediaDirs.first()
        val modelFile = File("${mediaDir.absolutePath}/model")
        if (modelFile.exists()) {
            modelFile.delete()
        }
        modelFile.mkdir()

        val files = context.resources.assets.list("model")
        files?.forEach {
            val file = context.resources.assets.open("model/$it")
            val mediaDir = context.externalMediaDirs.first()
            val outputFile = File("${mediaDir.absolutePath}/model/$it")
            file.copyTo(outputFile.outputStream())
        }
    }
}