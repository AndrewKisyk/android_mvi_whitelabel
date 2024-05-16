package xyz.echoes.echoeswhitelabel.util.permissions.base

import androidx.activity.result.ActivityResultLauncher
import kotlinx.coroutines.flow.Flow
import xyz.echoes.echoeswhitelabel.util.permissions.base.PermissionProvider.Companion.NOT_REQUIRED

interface PermissionController {

    fun requestPermissions(providers: List<PermissionProvider>): Flow<Map<String, Boolean>>

    class PermissionControllerImpl(
        private val activityResultLauncher: ActivityResultLauncher<Array<String>>,
        private val permissionResultObserver: PermissionResultObserver
    ) : PermissionController {
        override fun requestPermissions(providers: List<PermissionProvider>): Flow<Map<String, Boolean>> {
            providers.map { it.providePermission() }.filter { it != NOT_REQUIRED }.toTypedArray()
                .also {
                    activityResultLauncher.launch(it)
                }
            return permissionResultObserver.getPermissionResult()
        }
    }
}

