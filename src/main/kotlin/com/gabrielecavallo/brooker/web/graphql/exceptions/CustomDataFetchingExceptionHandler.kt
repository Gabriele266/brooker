package com.gabrielecavallo.brooker.web.graphql.exceptions

import com.gabrielecavallo.brooker.exceptions.InvalidIdException
import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler
import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import graphql.execution.ResultPath
import org.springframework.stereotype.Component
import java.lang.reflect.UndeclaredThrowableException

@Component
class CustomDataFetchingExceptionHandler(
    val defaultDataFetcherExceptionHandler: DefaultDataFetcherExceptionHandler = DefaultDataFetcherExceptionHandler()
) : DataFetcherExceptionHandler {

    override fun onException(handlerParameters: DataFetcherExceptionHandlerParameters?): DataFetcherExceptionHandlerResult {
        return when (handlerParameters?.exception) {
            is InvalidIdException -> onInvalidIdException(
                handlerParameters.path,
                handlerParameters?.exception as InvalidIdException
            )
            is UndeclaredThrowableException -> {
                val child = (handlerParameters.exception as UndeclaredThrowableException).cause
                if (child is InvalidIdException) onInvalidIdException(handlerParameters.path, child)
                else
                    defaultDataFetcherExceptionHandler.onException(handlerParameters)
            }
            else -> defaultDataFetcherExceptionHandler.onException(handlerParameters)
        }
    }

    fun onInvalidIdException(path: ResultPath, exception: InvalidIdException): DataFetcherExceptionHandlerResult {
        val debugInfo = mapOf(
            "id" to exception.id
        )

        val error = TypedGraphQLError.newNotFoundBuilder().message("Invalid id")
            .debugInfo(debugInfo)
            .path(path)
            .build()

        return DataFetcherExceptionHandlerResult.newResult().error(error).build()
    }
}