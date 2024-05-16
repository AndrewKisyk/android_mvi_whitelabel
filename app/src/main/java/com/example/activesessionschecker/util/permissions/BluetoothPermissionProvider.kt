package xyz.echoes.echoeswhitelabel.util.permissions

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import xyz.echoes.echoeswhitelabel.util.permissions.base.PermissionProvider

interface BluetoothPermissionProvider : PermissionProvider {
    class Base : BluetoothPermissionProvider {
        override fun providePermission(): String {
            return Manifest.permission.BLUETOOTH
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    class AboveAndroidSVersion :
        BluetoothPermissionProvider {
        override fun providePermission(): String {
            return Manifest.permission.BLUETOOTH_SCAN
        }
    }

    object Factory {
        fun create(): BluetoothPermissionProvider {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                AboveAndroidSVersion()
            } else {
                Base()
            }
        }
    }
}