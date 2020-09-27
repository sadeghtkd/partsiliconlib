package com.partsilicon.partsiliconlib.utils
import android.content.Context
import android.content.Context.MODE_PRIVATE



class SharedPreferencesUtility(private val context: Context) {
    private val preferenceFileKey = "PsiLib"
    private val preferenceIsFirstLaunchKey = "FirstLaunch"
    private val preferenceIsRefCodeEnteredKey = "RefCodeEntered"

    fun setUnreadCount(count:Int) {
        val editor = context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE).edit()
        editor.putInt("unread_count", count)
        editor.apply()
    }

    fun getUnreadCount(): Int {
        return context.getSharedPreferences(preferenceFileKey, MODE_PRIVATE)
                .getInt("unread_count", 0)
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