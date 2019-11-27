package kuu.nagoya.featurewordlist.infra

import kuu.nagoya.featurewordlist.entity.Word
import kuu.nagoya.featurewordlist.entity.WordGroup
import kuu.nagoya.featurewordlist.usecase.WordListReadonlyRepository

internal class WordListReadonlyRepositoryImpl :
    WordListReadonlyRepository {
    override suspend fun loadWordGroup(): List<WordGroup> {
        return listOf(
            WordGroup(
                0,
                "あいうえお",
                listOf(
                    Word(
                        0,
                        "あ",
                        "a"
                    ),
                    Word(
                        1,
                        "い",
                        "i"
                    ),
                    Word(
                        2,
                        "う",
                        "u"
                    ), Word(
                        3,
                        "え",
                        "e"
                    ),
                    Word(
                        4,
                        "お",
                        "o"
                    )
                )
            ),
            WordGroup(
                0,
                "かきくけこ",
                listOf(
                    Word(
                        0,
                        "か",
                        "ka"
                    ),
                    Word(
                        1,
                        "き",
                        "ki"
                    ),
                    Word(
                        2,
                        "く",
                        "ku"
                    ), Word(
                        3,
                        "け",
                        "ke"
                    ),
                    Word(
                        4,
                        "こ",
                        "ko"
                    )
                )
            )

        )
    }
}