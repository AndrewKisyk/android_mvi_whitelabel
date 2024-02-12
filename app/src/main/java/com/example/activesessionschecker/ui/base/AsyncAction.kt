package com.example.activesessionschecker.ui.base




open class AsyncAction<T : Any> : Action {

    lateinit var status: AsyncStatus<T>

    override fun isComplete() = runCatching { status !is AsyncStatus.Loading }.getOrElse { false }
    val data
        get() = (status as? AsyncStatus.Success)?.data ?: Any() as T
    val error
        get() = (status as? AsyncStatus.Failure)?.error ?: ""

    fun loading() = apply {
        status = AsyncStatus.Loading()
    }

    fun success(content: T)  = apply {
        status = AsyncStatus.Success(content)
    }

    fun failure(exception: Throwable)  = apply {
        status = AsyncStatus.Failure(exception.message ?: "Error Occurred !")
    }

    fun empty() = apply {
        status = AsyncStatus.Empty()
    }

    fun copyStatusFrom(other: AsyncAction<T>): AsyncAction<T> {
        status = other.status
        return this
    }
}
