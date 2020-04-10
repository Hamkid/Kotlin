package hu.renes.kotlin.view.base.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    view: View,
    context: Context
) :
    RecyclerView.ViewHolder(view) {
    protected val context: Context
    private var clickableView: View

    interface OnItemClickListener {
        fun listItemClicked(v: View?, position: Int)
    }

    abstract fun bind(data: T)

    init {
        clickableView = view
        this.context = context
    }
}