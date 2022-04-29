package com.nenad.newsapp.database

import androidx.room.TypeConverter
import com.nenad.newsapp.database.model.apiresponse.Source

class TypeConverters {

    @TypeConverter// for dao
    fun fromSrc(source:Source): String {

        return source.name


    }
    @TypeConverter
    fun toSrc(name: String) : Source {

        return Source(name, name)

    }


}