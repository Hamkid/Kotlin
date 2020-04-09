package hu.renes.kotlin.domain

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.core.content.ContextCompat
import hu.renes.kotlin.R
import hu.renes.kotlin.injection.qualifier.ApplicationContext
import javax.inject.Inject

class ResourceInteractor @Inject constructor(@ApplicationContext private val context: Context) {

    fun test(): String {
        return context.getString(R.string.app_name)
    }

    fun convertDpToPixel(dp: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), getResources().displayMetrics)
    }

    fun getResources(): Resources {
        return context.resources
    }

    fun getStringResource(resourceId: Int): String {
        return getResources().getString(resourceId)
    }

    fun getStringResource(resourceId: Int, vararg formatArgs: Any): String {
        return getResources().getString(resourceId, *formatArgs)
    }

    fun getFormattedString(resourceId: Int, vararg items: Any): String {
        return String.format(getStringResource(resourceId), *items)
    }

    fun getQuantityString(quantity: Int, resourceId: Int): String {
        return getResources().getQuantityString(resourceId, quantity)
    }

    fun getStringResource(resourceName: String): String? {
        val id = getResources().getIdentifier(
            resourceName,
            "string", context.packageName
        )
        return if (id == 0) {
            null
        } else {
            getStringResource(id)
        }
    }

    fun getArrayResourceIdentifier(resourceName: String): Int {
        return getResources().getIdentifier(resourceName, "array", context.packageName)
    }

    fun getStringResourceIdentifier(resourceName: String): Int {
        return getResources().getIdentifier(resourceName, "string", context.packageName)
    }

    fun getDrawableResourceIdentifier(resourceName: String): Int {
        return getResources().getIdentifier(resourceName, "drawable", context.packageName)
    }

    fun getLayoutResourceIdentifier(resourceName: String): Int {
        return getResources().getIdentifier(resourceName, "layout", context.packageName)
    }

    fun getViewIdResourceIdentifier(resourceName: String): Int {
        return getResources().getIdentifier(resourceName, "id", context.packageName)
    }

    fun getResId(resourceName: String, c: Class<*>): Int {
        try {
            val idField = c.getDeclaredField(resourceName)
            return idField.getInt(idField)
        } catch (e: Exception) {
            return 0 // No resource found for given name
        }

    }

    fun getStringArray(arrayResourceId: Int): Array<String> {
        return getResources().getStringArray(arrayResourceId)
    }

    fun getIntArray(arrayResourceId: Int): IntArray {
        return getResources().getIntArray(arrayResourceId)
    }

    fun getTypedArray(arrayResourceId: Int): TypedArray {
        return getResources().obtainTypedArray(arrayResourceId)
    }

    fun getDrawableResource(resourceId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resourceId)
    }

    fun getColorResource(resourceId: Int): Int {
        return ContextCompat.getColor(context, resourceId)
    }

    fun getDimension(resourceId: Int): Float {
        return getResources().getDimension(resourceId)
    }

    fun getInteger(resourceId: Int): Int {
        return getResources().getInteger(resourceId)
    }
}