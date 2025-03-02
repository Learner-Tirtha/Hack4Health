package com.example.transactionapp.data

import androidx.room.TypeConverter
import com.example.transactionapp.data.Gender

class Converters {
    @TypeConverter
    fun fromGender(gender: Gender): String = gender.name

    @TypeConverter
    fun toGender(gender: String): Gender = Gender.valueOf(gender)
}