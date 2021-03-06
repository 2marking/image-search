package com.nadarm.imagesearcher.domain.useCase

import com.nadarm.imagesearcher.domain.repository.SearchQueryRepository
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddSearchedQuery @Inject constructor(
    private val repository: SearchQueryRepository
) : CompletableUseCase1<String> {

    override fun execute(query: String): Completable {
        return this.repository.addSearchedQuery(query)
    }

}