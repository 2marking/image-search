package com.nadarm.imagesearcher.presentation.model.mapper

import com.nadarm.imagesearcher.domain.model.QueryResponse
import com.nadarm.imagesearcher.domain.model.SearchMeta
import com.nadarm.imagesearcher.presentation.model.SealedViewHolderData
import com.nadarm.imagesearcher.presentation.view.adapter.ImageListAdapter
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SealedViewHolderDataMapper @Inject constructor() {

    fun mapToSealedViewHolderData(
        queryResponse: QueryResponse,
        delegate: ImageListAdapter.Delegate
    ): List<SealedViewHolderData> {
        val query = queryResponse.meta.query
        val page = queryResponse.meta.page
        val isEnd = queryResponse.meta.isEnd

        val itemList: MutableList<SealedViewHolderData> = ArrayList()
        itemList.add(makeHeader(query, queryResponse.meta))
        itemList.addAll(makeImages(queryResponse, delegate))
        val footer = makeFooter(query, page, isEnd, delegate)
        if (footer != null) {
            itemList.add(footer)
        }
        return itemList
    }

    private fun makeFooter(
        query: String,
        page: Int,
        isEnd: Boolean,
        delegate: ImageListAdapter.Delegate
    ): SealedViewHolderData? {
        return when {
            page == 1 && !isEnd -> SealedViewHolderData.FooterOneBtnItem(query, "다음", page + 1, delegate)
            page > 1 && isEnd -> SealedViewHolderData.FooterOneBtnItem(query, "이전", page - 1, delegate)
            page > 1 && !isEnd -> SealedViewHolderData.FooterTwoBtnItem(query, page, delegate)
            else -> null
        }
    }

    private fun makeImages(
        queryResponse: QueryResponse,
        delegate: ImageListAdapter.Delegate
    ): List<SealedViewHolderData.ImageItem> {
        return queryResponse.documents.map {
            SealedViewHolderData.ImageItem(it, delegate)
        }
    }

    private fun makeHeader(
        query: String,
        meta: SearchMeta
    ): SealedViewHolderData.HeaderItem {
        val headerText: String = "$query 검색결과 : ${meta.totalCount}개"
        return SealedViewHolderData.HeaderItem(headerText)
    }


}