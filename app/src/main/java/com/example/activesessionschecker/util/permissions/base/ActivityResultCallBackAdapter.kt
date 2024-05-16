package xyz.echoes.echoeswhitelabel.util.permissions.base

import androidx.activity.result.ActivityResultCallback
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class ActivityResultCallBackAdapter @Inject constructor() : ActivityResultCallback<Map<String, Boolean>>,
    PermissionResultObserver {

    private val triggerPermissionResult = Channel<Map<String, Boolean>>(Channel.CONFLATED)

    override fun onActivityResult(result: Map<String, Boolean>) {
        triggerPermissionResult.trySend(result)
    }

    override fun getPermissionResult(): Flow<Map<String, Boolean>> {
        return triggerPermissionResult.receiveAsFlow()
    }
}