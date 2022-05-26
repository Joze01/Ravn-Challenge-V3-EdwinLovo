package com.elovo.data.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GsonHelper {
    val gson by lazy { Gson() }

    fun convertToString(data: Any): String = gson.toJson(data)

    fun <R : Any> convertToData(json: String, dataClass: Class<R>): R =
        gson.fromJson(json, dataClass)

    inline fun <reified R> convertToListData(json: String): List<R> =
        gson.fromJson(json, object : TypeToken<List<R>>() {}.type)
}
