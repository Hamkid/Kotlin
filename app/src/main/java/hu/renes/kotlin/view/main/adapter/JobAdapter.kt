package hu.renes.kotlin.view.main.adapter

import android.content.Context
import android.view.ViewGroup
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.renes.kotlin.utility.extensions.inflate
import hu.renes.kotlin.view.base.adapter.BaseAdapter
import hu.renes.kotlin.view.base.adapter.BaseViewHolder
import javax.inject.Inject

class JobAdapter @Inject constructor(@ApplicationContext context: Context) :
    BaseAdapter<JobViewModel>(context) {

    private var onItemClickListener: JobViewHolder.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        return JobViewHolder(parent.inflate(viewType), context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<JobViewModel>, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as JobViewHolder).setOnItemClickListener(onItemClickListener)
    }

    fun setOnItemClickListener(onItemClickListener: JobViewHolder.OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}
