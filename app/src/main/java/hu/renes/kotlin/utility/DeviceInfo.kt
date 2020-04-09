package hu.renes.kotlin.utility

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import androidx.core.content.pm.PackageInfoCompat
import hu.renes.kotlin.injection.qualifier.ApplicationContext
import javax.inject.Inject


class DeviceInfo @Inject constructor(@ApplicationContext private val context: Context) {

    fun getAndroidVersion(): Int {
        return Build.VERSION.SDK_INT
    }

    fun getAndroidVersionName(): String {
        return Build.VERSION.RELEASE
    }

    fun getDeviceType(): String {
        return Build.DEVICE
    }

    fun getVersionName(): String {
        try {
            return context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            return ""
        }

    }

    fun getVersionCode(): Int {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val longVersionCode = PackageInfoCompat.getLongVersionCode(pInfo)
        return longVersionCode.toInt()
    }

    fun getDeviceDimensions(): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    fun getAppVersion(): String {
        val version = getVersionName() + "(" + getVersionCode() + ")"
        return ("app_version: " + version
                + ", model: " + Build.MODEL
                + ", android_version_name: " + getAndroidVersionName()
                + ", android_version: " + getAndroidVersion())

    }

}