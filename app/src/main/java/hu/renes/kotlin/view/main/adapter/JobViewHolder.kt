package hu.renes.kotlin.view.main.adapter

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import hu.renes.kotlin.view.base.adapter.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_job.*
import kotlinx.android.synthetic.main.item_job.view.*

class JobViewHolder constructor(
    view: View,
    context: Context
) : BaseViewHolder<JobViewModel>(view, context)
{
    val companyTextView: TextView = itemView.companyName
    val titleTextView: TextView = itemView.title
    val periodTextView: TextView = itemView.period
    val companyImageView: ImageView = itemView.logo

    private var onItemClickListener: OnItemClickedListener? = null

    override fun bind(data: JobViewModel) {
        Picasso.get().load(data.logo).into(companyImageView)
        companyTextView.text = data.companyName
        titleTextView.text = data.title
        periodTextView.text = data.period
        itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(data.website)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickedListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickedListener {
        fun onItemClicked(companyUri: Uri)
    }
}