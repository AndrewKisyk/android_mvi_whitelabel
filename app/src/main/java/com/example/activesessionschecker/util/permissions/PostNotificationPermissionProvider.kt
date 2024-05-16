package xyz.echoes.echoeswhitelabel.util.permissions

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import xyz.echoes.echoeswhitelabel.util.permissions.base.PermissionProvider
import xyz.echoes.echoeswhitelabel.util.permissions.base.PermissionProvider.Companion.NOT_REQUIRED


interface PostNotificationPermissionProvider : PermissionProvider {

    class Base : PostNotificationPermissionProvider {
        override fun providePermission(): String {
            return NOT_REQUIRED
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    class AboveAndroidTiramisuVersion : PostNotificationPermissionProvider {
        override fun providePermission(): String {
            return Manifest.permission.POST_NOTIFICATIONS
        }
    }

    object Factory {
        fun create(): PostNotificationPermissionProvider {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                AboveAndroidTiramisuVersion()
            } else {
                Base()
            }
        }
    }
}
