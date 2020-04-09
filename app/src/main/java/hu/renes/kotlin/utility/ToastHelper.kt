package hu.renes.kotlin.utility

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import hu.renes.kotlin.domain.ResourceInteractor
import hu.renes.kotlin.injection.qualifier.ApplicationContext
import javax.inject.Inject

class ToastHelper @Inject constructor(@ApplicationContext private val context: Context, private val resourceInteractor: ResourceInteractor) {

    fun makeShortToastOnUiThread(message: String) {
        val handler = Handler(Looper.getMainLooper())
        handler.post { this.makeToast(message) }
    }

    fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

    fun makeToast(messageId: Int) {
        makeToast(resourceInteractor.getStringResource(messageId))
    }
}