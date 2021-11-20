package com.logo.logoquiz.repo

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.logo.logoquiz.R
import com.logo.logoquiz.base.BaseApplication
import com.logo.logoquiz.utils.StorageUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.logo.logoquiz.model.LogoQuizSet

/**
 * Repo class to get data from raw. I have kept json file in raw folder but in paralle
 * we can load data from server as well in repo and return it accordigly
 */
class QuizRepo {
    /**
     * created this method only to show that we can use coroutine as well to fetch data from cache and server
     */
    suspend fun fetchLogosWithCoroutine(): ArrayList<LogoQuizSet>? {
        return withContext(Dispatchers.IO) {
            val list = object : TypeToken<ArrayList<LogoQuizSet>>() {}.type
            val cachedData =
                StorageUtils.getRawData(BaseApplication.context.applicationContext, R.raw.logo)
            return@withContext Gson().fromJson<ArrayList<LogoQuizSet>>(
                cachedData,
                list
            )
        }
    }

    /**
     * fetch all list data from raw
     */
    fun fetchLogos(): ArrayList<LogoQuizSet>? {
            val list = object : TypeToken<ArrayList<LogoQuizSet>>() {}.type
            val cachedData = StorageUtils.getRawData(BaseApplication.context.applicationContext, R.raw.logo)
            return Gson().fromJson<ArrayList<LogoQuizSet>>(
                cachedData,
                list
            )
    }
}