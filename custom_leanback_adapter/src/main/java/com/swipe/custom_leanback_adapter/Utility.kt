package com.swipe.custom_leanback_adapter

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.ConnectivityManager



object Utility {

    @JvmStatic
    fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int { // For example columnWidthdp=180
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / columnWidthDp + 0.5).toInt()
    }

    @JvmStatic
    fun dpToPx(dp: Int): Int {
        return  (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    @JvmStatic
    fun pxToDp(px: Int): Int {
        return  (px / Resources.getSystem().displayMetrics.density).toInt()
    }


    /**
     * method checks to see if app is currently set as default launcher
     * @return boolean true means currently set as default, otherwise false
     */

    fun isMyAppLauncherDefault(context: Context): Boolean {
        val filter = IntentFilter(Intent.ACTION_MAIN)
        filter.addCategory(Intent.CATEGORY_HOME)
        val filters: MutableList<IntentFilter> = ArrayList()
        filters.add(filter)
        val myPackageName: String = context.packageName
        val activities: List<ComponentName> = ArrayList()
        val packageManager = context.packageManager as PackageManager
        packageManager.getPreferredActivities(filters, activities, null)
        for (activity in activities) {
            if (myPackageName == activity.packageName) {
                return true
            }
        }
        return false
    }

    /**
     * method starts an intent that will bring up a prompt for the user
     * to select their default launcher. It comes up each time it is
     * detected that our app is not the default launcher
     */
    private fun launchAppChooser(context: Context) {
//        Log.d(TAG, "launchAppChooser()")
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun resetPreferredLauncherAndOpenChooser(context: Context) {
//        val packageManager = context.packageManager
//        val componentName = ComponentName(context, FakeLauncherActivity::class.java)
//        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)
//        val selector = Intent(Intent.ACTION_MAIN)
//        selector.addCategory(Intent.CATEGORY_HOME)
//        selector.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        context.startActivity(selector)
//        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP)
    }

}