class JsonPlaceholderService {
    @GET("/todos")
    suspend fun getTodos(): List<Todo>
}