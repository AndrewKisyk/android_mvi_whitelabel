package xyz.echoes.echoeswhitelabel.util.permissions.base

interface PermissionProvider {
    fun providePermission(): String
    companion object {
        const val NOT_REQUIRED = "xyz.echoes.echoeswhitelabel.util.permissions.base.NOT_REQUIRED"
    }
}