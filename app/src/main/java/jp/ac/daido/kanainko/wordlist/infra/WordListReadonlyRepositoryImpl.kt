package jp.ac.daido.kanainko.wordlist.infra

import jp.ac.daido.kanainko.wordlist.entity.Word
import jp.ac.daido.kanainko.wordlist.entity.WordGroup
import jp.ac.daido.kanainko.wordlist.usecase.WordListReadonlyRepository

internal class WordListReadonlyRepositoryImpl : WordListReadonlyRepository {
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