package com.debaters.debateOnServer

import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import graphql.execution.SimpleDataFetcherExceptionHandler
import io.sentry.Sentry

class GlobalExceptionHandler : SimpleDataFetcherExceptionHandler() {
    override fun onException(handlerParameters: DataFetcherExceptionHandlerParameters?): DataFetcherExceptionHandlerResult {
        Sentry.captureException(handlerParameters?.exception ?: IllegalStateException("unhandled error"))
        return super.onException(handlerParameters)
    }
}
