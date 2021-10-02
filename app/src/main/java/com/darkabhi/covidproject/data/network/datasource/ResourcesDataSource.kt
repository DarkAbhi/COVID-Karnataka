package com.darkabhi.covidproject.data.network.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.darkabhi.covidproject.app.AppConfig
import com.darkabhi.covidproject.data.network.repository.ResourcesRepository
import com.darkabhi.covidproject.models.ResourceData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Created by Abhishek AN <abhishek@iku.earth> on 5/18/2021.
 */
class ResourcesDataSource(
    private val resourcesRepository: ResourcesRepository,
    private val resourceType: String
) : PagingSource<Int, ResourceData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResourceData> {
        val pageIndex = params.key ?: AppConfig.STARTING_PAGE_INDEX
        return try {
            val response = when (resourceType) {
                AppConfig.AMBULANCE_SERVICE_COLLECTION -> resourcesRepository.getAmbulanceData(
                    pageNo = pageIndex.toString()
                )
                AppConfig.BLOOD_DONORS_COLLECTION -> resourcesRepository.getBloodData(
                    pageNo = pageIndex.toString()
                )
                AppConfig.FOOD_COLLECTION -> resourcesRepository.getFoodData(
                    pageNo = pageIndex.toString()
                )
                AppConfig.HOME_TESTING_COLLECTION -> resourcesRepository.getHTData(
                    pageNo = pageIndex.toString()
                )
                AppConfig.ONLINE_DOC_COLLECTION -> resourcesRepository.getOnlineData(
                    pageNo = pageIndex.toString()
                )
                AppConfig.OXYGEN_COLLECTION -> resourcesRepository.getOxygenData(
                    pageNo = pageIndex.toString()
                )
                AppConfig.TELE_COLLECTION -> resourcesRepository.getTeleData(
                    pageNo = pageIndex.toString()
                )
                else -> Response.error(400, "".toResponseBody("".toMediaTypeOrNull()))
            }
            val result = response.body()
            val nextKey =
                if (result!!.data.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 16 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / response.body()!!.size)
                }
            LoadResult.Page(
                data = result.data,
                prevKey = if (pageIndex == AppConfig.STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, ResourceData>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
