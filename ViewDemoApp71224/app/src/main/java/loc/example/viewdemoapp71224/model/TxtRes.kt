package loc.example.viewdemoapp71224.model

import android.content.res.Resources

sealed class TxtRes {
    companion object {
        fun fromStrRes(id: Int, vararg args: String) =
            IdTextRes(id, args)
    }
}

data class IdTextRes(val id: Int, val args: Array<out String>) : TxtRes()

fun TxtRes.asString(resources: Resources) =
    when (this) {
        is IdTextRes -> resources.getString(id, *args)
    }