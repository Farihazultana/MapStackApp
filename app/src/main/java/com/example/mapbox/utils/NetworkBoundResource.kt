package com.example.mapbox.utils

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.map
//import com.example.mapbox.data.model.CountryModel
//import kotlinx.coroutines.flow.*
//
//inline fun <ResultType, RequestType> networkBoundResource(
//    crossinline query: () -> LiveData<CountryModel>,
//    crossinline fetch: suspend () -> RequestType,
//    crossinline saveFetchResult: suspend (RequestType) -> Unit,
//    crossinline shouldFetch: (ResultType) -> Boolean = { true }
//
//) = flow {
//    val data = query()
//
//    val flow = if (shouldFetch()){
//        emit(Resource.loading(data))
//
//        try {
//            saveFetchResult(fetch())
//            query().map { Resource.success(it) }
//        }catch (throwable: Throwable){
//            query().map { Resource.error(throwable, it) }
//        }
//
//    }else{
//        query().map { Resource.success(it) }
//    }
//
//    emitAll(flow)
//}