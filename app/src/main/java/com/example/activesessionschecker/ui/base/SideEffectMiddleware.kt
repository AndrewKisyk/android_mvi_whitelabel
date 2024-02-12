package com.example.activesessionschecker.ui.base


import com.example.activesessionschecker.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transformWhile
import kotlinx.coroutines.launch
import org.reduxkotlin.middleware


fun sideEffectMiddleware(scope: CoroutineScope) = middleware<AppState> { store, next, action ->
    scope.launch {
        when (action) {
            is SideEffect -> action.run().flowOn(Dispatchers.IO).transformWhile { flowAction ->
                emit(flowAction)
                !flowAction.isComplete()
            }.collect { flowAction ->
                store.dispatch(flowAction)
            }
            else -> next(action)
        }
    }
}