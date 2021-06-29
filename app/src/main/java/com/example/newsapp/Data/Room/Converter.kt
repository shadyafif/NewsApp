package com.example.newsapp.Data.Room

import androidx.room.TypeConverter
import com.example.newsapp.Data.Model.Source

class Converter {
    // Two converter methods for Source class
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}