package xyz.echoes.echoeswhitelabel.util.permissions.base

import kotlinx.coroutines.flow.Flow


interface PermissionResultObserver {
    fun getPermissionResult(): Flow<Map<String, Boolean>>
}
