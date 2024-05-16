package xyz.echoes.echoeswhitelabel.util.permissions

import android.Manifest
import xyz.echoes.echoeswhitelabel.util.permissions.base.PermissionProvider

interface FineLocationPermissionProvider : PermissionProvider {

    class Base() : FineLocationPermissionProvider {
        override fun providePermission(): String {
            return Manifest.permission.ACCESS_FINE_LOCATION
        }
    }

    object Factory {
        fun create(): FineLocationPermissionProvider {
            return Base()
        }
    }
}