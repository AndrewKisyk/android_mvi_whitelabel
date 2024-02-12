package com.example.activesessionschecker.data.domain.base

import com.example.activesessionschecker.data.base.HandleError
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import java.net.UnknownHostException


class HandleDomainError: HandleError {

    override fun handle(error: Exception) =
        if (error is UnknownHostException)
            NoInternetConnectionException()
        else
            ServiceUnavailableException()
}