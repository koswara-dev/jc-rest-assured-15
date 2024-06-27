package com.juaracoding;

import org.testng.annotations.Test;

public class MovieTest {

    String baseUrl = "https://api.themoviedb.org/3";
    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYzgwZjdmNjE2MmVmYWVhOWI5NzRkNmQ1MDVhYmJjYiIsIm5iZiI6MTcxOTQwODgxMC40OTEwNDQsInN1YiI6IjYyNWU0MTY5MzNhMzc2MDA1MGI1ZWYzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.grkl501TXVk3FjtIidZiSOxy-bJ9bZ6xwvzXpzVWWjE";

    @Test
    public void testMovieNowPlaying(){
        String endpoint = baseUrl+"/movie/now_playing?language=en-US&page=1";

    }

    @Test
    public void testMoviePopular(){

    }

}
