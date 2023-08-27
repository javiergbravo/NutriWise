package com.jgbravo.nutriwise.domain.base.models.utils

import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Error
import com.jgbravo.nutriwise.domain.base.models.wrappers.Resource.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

inline fun <T : Any, R : Any> Flow<Resource<T>>.mapCatching(
    nullIsAllowed: Boolean = false,
    crossinline mapper: suspend (T) -> R
): Flow<Resource<R>> = this
    .catch { throwable -> transform { emit(Error(Exception(throwable))) } }
    .transform { resource ->
        return@transform when (resource) {
            is Success -> when {
                resource.data == null && nullIsAllowed -> emit(Success(null as R?))
                resource.data == null && !nullIsAllowed -> emit(Error(NullPointerException()))
                else -> emit(Success(mapper(resource.data as T)))
            }
            is Error -> emit(Error(resource.exception))
        }
    }

inline fun <T : Any, R : Any> Flow<T?>.mapNullableCatching(
    nullIsAllowed: Boolean = false,
    crossinline mapper: suspend (T) -> R
): Flow<Resource<R>> = this
    .catch { throwable -> transform { emit(Resource.Error(Exception(throwable))) } }
    .transform { data ->
        return@transform when {
            data == null && nullIsAllowed -> emit(Success(null as R?))
            data == null && !nullIsAllowed -> emit(Error(NullPointerException()))
            else -> emit(Success(mapper(data as T)))
        }
    }

inline fun <T : Any, R : Any> Flow<T>.mapAsResourceCatching(
    crossinline mapper: suspend (T) -> R
): Flow<Resource<R>> = this
    .catch { throwable -> transform { emit(Resource.Error(Exception(throwable))) } }
    .transform { data -> return@transform emit(Success(mapper(data))) }

/**
 * IF [discardErrorElements] is true, then the error elements will be discarded and the flow will continue else emit
 * Resource.Error with the exception.
 */
inline fun <T : Any, R : Any> Flow<List<T>>.mapAsResourceListCatching(
    discardErrorElements: Boolean,
    crossinline mapper: suspend (T) -> R
): Flow<Resource<List<R>>> = this
    .catch { throwable -> transform { emit(Resource.Error(Exception(throwable))) } }
    .transform { dataList ->
        return@transform if (discardErrorElements) {
            val listMapped = dataList.mapNotNull { item ->
                try {
                    mapper(item)
                } catch (e: Exception) {
                    null
                }
            }
            emit(Success(listMapped))
        } else {
            try {
                val listMapped = dataList.map { item -> mapper(item) }
                emit(Success(listMapped))
            } catch (e: Exception) {
                emit(Error(e))
            }
        }
    }
