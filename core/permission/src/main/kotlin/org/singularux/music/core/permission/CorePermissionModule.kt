package org.singularux.music.core.permission

import android.content.Context
import android.os.Build
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CorePermissionModule {

    @Binds
    @Singleton
    fun bindsMusicPermissionManager(@ApplicationContext context: Context): MusicPermissionManager {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            MusicPermissionManagerAndroid33(context = context)
        } else {
            MusicPermissionManagerAndroid26(context = context)
        }
    }

}