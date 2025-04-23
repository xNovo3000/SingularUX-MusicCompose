package org.singularux.music.core.permission

import android.Manifest
import android.content.Context

class MusicPermissionManagerAndroid26(
    context: Context
) : MusicPermissionManagerAndroid(context = context) {

    override fun getPermissionString(permission: MusicPermission): String {
        return when (permission) {
            MusicPermission.READ_MUSIC -> Manifest.permission.READ_EXTERNAL_STORAGE
        }
    }

}