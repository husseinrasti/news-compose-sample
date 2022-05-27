package com.husseinrasti.rss.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by Hussein Rasti on 5/17/21.
 *
 * <h1> Array Type Converter </h1>
 * This class convert a list type value to <code>String</code>
 * and save it to database and convert a <code>String</code> and return it
 */
class ArrayTypeConverter {

    /**
     * @param value gets a <code>String</code>
     * @return json from list of String
     */
    @TypeConverter
    fun fromJson(value: String?): List<String>? {
        val typeList = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, typeList)
    }

    /**
     * @param list gets a list of String type value
     * @return json from list of String
     */
    @TypeConverter
    fun toJson(list: List<String>?): String? {
        return Gson().toJson(list)
    }

}