package com.nadarm.imagesearch.domain.repository

import com.nadarm.imagesearch.domain.model.ImageDocument
import io.reactivex.Single

interface ImageDocumentRepository {

    fun getImageDocuments(query: String, page: Int = 1): Single<List<ImageDocument>>

    fun getImageDocument(query: String, page: Int = 1, index: Int = 1): Single<ImageDocument>

}