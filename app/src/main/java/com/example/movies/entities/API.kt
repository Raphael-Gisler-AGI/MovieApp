import com.example.movies.entities.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("/comments")
    fun getComments(): Call<List<Comment>>
}