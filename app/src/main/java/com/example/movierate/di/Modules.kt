package com.example.movierate.di

import com.example.movierate.utils.HttpInterceptor
import com.example.movierate.interactors.MovieInteractorImpl
import com.example.movierate.mappers.FromNetworkMovieToMovie
import com.example.movierate.rest.RequestsRepositoryImpl
import com.example.movierate.rest.api.MovieApi
import com.example.movierate.ui.viewmodel.MovieViewModel
import com.example.movierate.usecases.BoxOfficeUseCaseImpl
import com.example.movierate.usecases.PopularMoviesUseCaseImpl
import com.example.movierate.usecases.SearchMovieUseCaseImpl
import com.example.movierate.usecases.SeeNowMovieUseCaseImpl
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val restModule = module {
    single {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        HttpInterceptor()
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    single(named("rapidApi")) {
        Retrofit.Builder()
            .baseUrl("https://movie-database-imdb-alternative.p.rapidapi.com/")
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("imdbApi")) {
        Retrofit.Builder()
            .baseUrl("https://imdb8.p.rapidapi.com/title/")
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("alternativeApi")) {
        Retrofit.Builder()
            .baseUrl("https://movies-tvshows-data-imdb.p.rapidapi.com/")
            .client(get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("serviceAlternativeApi")) {
        get<Retrofit>(named("alternativeApi")).create(MovieApi::class.java)
    }

    single(named("serviceRapidApi")) {
        get<Retrofit>(named("rapidApi")).create(MovieApi::class.java)
    }

    single(named("serviceImdbApi")) {
        get<Retrofit>(named("imdbApi")).create(MovieApi::class.java)
    }

    single { FromNetworkMovieToMovie() }

    single {
        RequestsRepositoryImpl(
            get(named("serviceRapidApi")),
            get(named("serviceImdbApi")),
            get(named("serviceAlternativeApi")),
            get()
        )
    }
}

val useCasesModule = module {

    single { PopularMoviesUseCaseImpl(get<RequestsRepositoryImpl>()) }

    single { BoxOfficeUseCaseImpl(get<RequestsRepositoryImpl>()) }

    single { SearchMovieUseCaseImpl(get<RequestsRepositoryImpl>()) }

    single { SeeNowMovieUseCaseImpl(get<RequestsRepositoryImpl>()) }
}

val viewModelModule = module {

    single {
        MovieInteractorImpl(
            get<PopularMoviesUseCaseImpl>(),
            get<BoxOfficeUseCaseImpl>(),
            get<SearchMovieUseCaseImpl>(),
            get<SeeNowMovieUseCaseImpl>()
        )
    }

    single { MovieViewModel(get<MovieInteractorImpl>()) }
}
