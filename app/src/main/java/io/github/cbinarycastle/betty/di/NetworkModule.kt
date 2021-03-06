package io.github.cbinarycastle.betty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.betty.BuildConfig
import io.github.cbinarycastle.betty.data.BackendService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_ENTRYPOINT = "/api/v1/"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideBackendService(): BackendService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL + API_ENTRYPOINT)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BackendService::class.java)
    }

    private fun createOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()
}