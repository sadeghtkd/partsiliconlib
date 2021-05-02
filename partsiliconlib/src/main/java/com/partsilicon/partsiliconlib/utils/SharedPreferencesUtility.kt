package com.partsilicon.partsiliconlib.utils
import android.content.Context
import android.content.Context.MODE_PRIVATE



class SharedPreferencesUtility(private val context: Context) {
    private val preferenceFileKey = "PsiLib"
    private val preferenceIsFirstLaunchKey = "FirstLaunch"
    private val preferenceIsRefCodeEnteredKey = "RefCodeEntered"
    private val preferenceBeginDialog = "BeginDialog"

    fun setUnreadCount(count:Int) {
        val editor = context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE).edit()
        editor.putInt("unread_count", count)
        editor.apply()
    }

    fun getUnreadCount(): Int {
        return context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE)
                .getInt("unread_count", 0)
    }

    fun setDialogId(dialog_id:String) {
        val editor = context.getSharedPreferences(preferenceBeginDialog , MODE_PRIVATE).edit()
        editor.putString("dialog_id", dialog_id)
        editor.apply()
    }

    fun getDialogId(): String? {
        return context.getSharedPreferences(preferenceBeginDialog, MODE_PRIVATE)
                .getString("dialog_id", "")
    }

    fun setIsFirstLaunch() {
        val editor = context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE).edit()
        editor.putBoolean(preferenceIsFirstLaunchKey, false)
        editor.apply()
    }

    fun isFirstLaunch(): Boolean {
        return context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE)
                .getBoolean(preferenceIsFirstLaunchKey, true)
    }

    fun setIsRefCodeEntered() {
        val editor = context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE).edit()
        editor.putBoolean(preferenceIsRefCodeEnteredKey, true)
        editor.apply()
    }

    fun isRefCodeEntered(): Boolean {
        return context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE)
                .getBoolean(preferenceIsRefCodeEnteredKey, false)
    }

}