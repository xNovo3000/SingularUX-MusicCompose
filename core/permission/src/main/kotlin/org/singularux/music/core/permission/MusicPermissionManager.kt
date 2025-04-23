package org.singularux.music.core.permission

interface MusicPermissionManager {
    fun hasPermission(permission: MusicPermission): Boolean
    fun getPermissionString(permission: MusicPermission): String
}