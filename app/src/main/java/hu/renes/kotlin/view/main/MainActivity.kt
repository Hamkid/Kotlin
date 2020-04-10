package hu.renes.kotlin.view.main

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import hu.renes.kotlin.R
import hu.renes.kotlin.injection.component.ActivityComponent
import hu.renes.kotlin.view.base.BaseActivity
import hu.renes.kotlin.view.main.adapter.CandidateViewModel
import hu.renes.kotlin.view.main.adapter.JobAdapter
import hu.renes.kotlin.view.main.adapter.JobViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainActivityView, MainActivityPresenter>(), MainActivityView,
    JobViewHolder.OnItemClickListener {

    @Inject
    lateinit var mainPresenter: MainActivityPresenter

    @Inject
    lateinit var jobAdapter: JobAdapter

    override fun injectActivity(activityComponent: ActivityComponent) {
        getActivityComponent().inject(this)
    }

    override fun createPresenter(): MainActivityPresenter {
        return mainPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        presenter.initView()
        swipeLayout.setOnRefreshListener {
            presenter.initView()
        }
    }

    override fun onListShouldShown(candidateViewModel: CandidateViewModel) {
        dismissSwipeLoading()
        nameText.text = candidateViewModel.nameWithAge
        jobAdapter.setItems(candidateViewModel.jobs)
    }

    override fun onError(message: String) {
        dismissSwipeLoading()
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.button_retry)) {
                    presenter.initView()
                }
        snackBar.setActionTextColor(Color.GREEN)
        snackBar.show()
    }

    private fun dismissSwipeLoading() {
        if (swipeLayout != null && swipeLayout.isRefreshing()) {
            swipeLayout.setRefreshing(false)
        }
    }

    private fun initAdapter() {
        val layoutManager = LinearLayoutManager(this)
        recylerView.setAdapter(jobAdapter)
        recylerView.setLayoutManager(layoutManager)
        recylerView.setNestedScrollingEnabled(false)
        recylerView.addItemDecoration(DividerItemDecoration(this, 0))
        jobAdapter.setOnItemClickListener(this)

    }

    override fun onItemClicked(companyUri: Uri) {
        val browserIntent = Intent(Intent.ACTION_VIEW, companyUri)
        startActivity(browserIntent)
    }
}
