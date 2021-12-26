package io.github.cbinarycastle.macao.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.cbinarycastle.macao.data.BackendService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BACKEND_URL = "url"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideBackendService(): BackendService {
        return Retrofit.Builder()
            .baseUrl(BACKEND_URL)
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