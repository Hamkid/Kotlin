package hu.renes.kotlin.view.main.adapter

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import hu.renes.kotlin.view.base.adapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_job.view.*

class JobViewHolder constructor(
    view: View,
    context: Context
) : BaseViewHolder<JobViewModel>(view, context) {
    val companyTextView: TextView = itemView.companyName
    val titleTextView: TextView = itemView.title
    val periodTextView: TextView = itemView.period
    val companyImageView: ImageView = itemView.logo
    val layout: ConstraintLayout = itemView.layout

    private var onItemClickListener: OnItemClickListener? = null

    override fun bind(data: JobViewModel) {
        Picasso.get().load(data.logo).into(companyImageView)
        companyTextView.text = data.companyName
        titleTextView.text = data.title
        periodTextView.text = data.period
        layout.setOnClickListener {
            onItemClickListener?.onItemClicked(data.website)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        print("asas0")
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClicked(companyUri: Uri)
    }
}