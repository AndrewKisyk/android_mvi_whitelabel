package com.example.activesessionschecker.di
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.WorkManager
import com.example.activesessionschecker.AppState
import com.example.activesessionschecker.rootReducer
import com.example.activesessionschecker.ui.base.loggerMiddleware
import com.example.activesessionschecker.ui.base.sideEffectMiddleware
import com.example.activesessionschecker.util.AppCoroutineScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.reduxkotlin.TypedStore
import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.threadsafe.createTypedThreadSafeStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {
    @Provides
    fun provideRequestManager(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .build()
    }


    @Singleton
    @Provides
    fun provideTypedStore(appCoroutineScope: AppCoroutineScope): TypedStore<AppState, Any> {
        return createTypedThreadSafeStore(
            ::rootReducer,
            AppState(),
            applyMiddleware(
                sideEffectMiddleware(appCoroutineScope),
                loggerMiddleware()
            )
        )
    }

}