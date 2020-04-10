package hu.renes.kotlin.view.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import hu.renes.kotlin.injection.qualifier.ApplicationContext
import hu.renes.kotlin.view.base.adapter.BaseAdapter
import hu.renes.kotlin.view.base.adapter.BaseViewHolder
import javax.inject.Inject

class JobAdapter @Inject constructor(@ApplicationContext context: Context) :
    BaseAdapter<JobViewModel>(context) {

    private var onItemClickListener: JobViewHolder.OnItemClickedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(viewType, parent, false)
        return JobViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<JobViewModel>, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as JobViewHolder).setOnItemClickListener(onItemClickListener)
    }

    fun setOnItemClickListener(onItemClickListener: JobViewHolder.OnItemClickedListener) {
        this.onItemClickListener = onItemClickListener
    }
}
