import com.example.movies.entities.res.Movie
import com.example.movies.entities.res.MoviesPopular
import com.example.movies.entities.res.Videos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDB {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int, @Query("api_key") api_key: String): MoviesPopular

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int, @Query("api_key") api_key: String): Movie

    @GET("movie/{id}/videos")
    suspend fun getVideos(@Path("id") id: Int, @Query("api_key") api_key: String): Videos

}