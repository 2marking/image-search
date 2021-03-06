package com.nadarm.imagesearcher.presentation.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nadarm.imagesearcher.R
import com.nadarm.imagesearcher.databinding.FragmentDetailBinding
import com.nadarm.imagesearcher.di.AndroidApplication
import com.nadarm.imagesearcher.domain.model.ImageDocument
import com.nadarm.imagesearcher.presentation.view.adapter.DetailPagerAdapter
import com.nadarm.imagesearcher.presentation.viewModel.DetailViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DetailFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: FragmentDetailBinding
    @Inject
    lateinit var detailVm: DetailViewModel.ViewModelImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        this.binding.lifecycleOwner = viewLifecycleOwner
        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity!!.application as AndroidApplication).getAppComponent().inject(this)

        val pagerAdapter: DetailPagerAdapter = DetailPagerAdapter(childFragmentManager, this.detailVm.inputs)
        this.binding.detailViewPager.adapter = pagerAdapter
        this.binding.detailVm = this.detailVm

        this.detailVm.outputs.imageDocuments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::refreshPagerItem, this::printLog)
            .addTo(compositeDisposable)

        this.detailVm.outputs.currentPageAndIndex()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setDetailViewPagerCurrentIndex, this::printLog)
            .addTo(compositeDisposable)

        this.detailVm.outputs.openUrlLink()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::openUrlLink, this::printLog)
            .addTo(compositeDisposable)
    }

    private fun refreshPagerItem(itemList: List<ImageDocument>) {
        val adapter = this.binding.detailViewPager.adapter as DetailPagerAdapter
        adapter.refresh(itemList)
        adapter.notifyDataSetChanged()
    }

    private fun setDetailViewPagerCurrentIndex(pageAndIndex: Pair<Int, Int>) {
        this.binding.detailViewPager.currentItem = pageAndIndex.second
    }

    private fun printLog(throwable: Throwable) {
        Log.e(this.tag, "Do something Error Log : $throwable.message")
    }

    private fun openUrlLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.compositeDisposable.clear()
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragment()
    }
}
