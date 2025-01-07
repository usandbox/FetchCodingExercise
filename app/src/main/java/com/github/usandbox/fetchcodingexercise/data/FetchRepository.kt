package com.github.usandbox.fetchcodingexercise.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getItems(): Result<List<Item>> {
        return runCatching {
            withContext(Dispatchers.IO) {
                apiService.getItems()
                    .filter { !it.name.isNullOrEmpty() }
                    .groupBy { it.listId }
                    .toSortedMap()
                    .map {
                        it.value.sortedBy { it.name }
                    }
                    .flatten()
            }
        }
    }
}
