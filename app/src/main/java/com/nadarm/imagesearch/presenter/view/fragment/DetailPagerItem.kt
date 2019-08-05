package com.nadarm.imagesearch.presenter.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nadarm.imagesearch.R
import com.nadarm.imagesearch.databinding.ItemDetailPagerBinding
import com.nadarm.imagesearch.domain.model.ImageDocument

class DetailPagerItem(private val imageDocument: ImageDocument) : Fragment() {

    private lateinit var binding: ItemDetailPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.item_detail_pager, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.binding.imageDocument = this.imageDocument
    }
}