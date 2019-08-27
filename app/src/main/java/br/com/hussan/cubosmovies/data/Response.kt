package br.com.hussan.cubosmovies.data

sealed class Status {
    object Loading : Status()
    object Error : Status()
    object Success : Status()
}

class Response<T>(
    val status: Status,
    val data: T?,
    val error: Throwable?
) {
    companion object {

        fun <T> loading(): Response<T> {
            return Response(Status.Loading, null, null)
        }

        fun <T> success(data: T): Response<T> {
            return Response(Status.Success, data, null)
        }

        fun <T> error(error: Throwable): Response<T> {
            return Response(Status.Error, null, error)
        }
    }
}
