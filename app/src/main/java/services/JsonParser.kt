package services

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import models.Cat

object JsonParser{

    fun SerilizeCats(cat : Cat) : String {
        return Gson().toJson(cat)
    }

    fun DeserilizeCats(jsonString : String) : List<Cat>    {
        val catType = object : TypeToken<List<Cat>>() {}.type
        return Gson().fromJson<List<Cat>>(jsonString, catType)
    }
}