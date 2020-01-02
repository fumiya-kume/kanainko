package kuu.nagoya.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(
    json: String
): T {
    return this.fromJson(
        json,
        object : TypeToken<T>() {}.type
    )
}