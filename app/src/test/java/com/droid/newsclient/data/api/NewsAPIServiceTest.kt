package com.droid.newsclient.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(server.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPIService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(
            fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }


    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1)
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=b7122b5c5f8948eda9715867b6240ce6")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctSize() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articleList = responseBody!!.articles
            assertThat(articleList.size).isEqualTo(20)

        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us", 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]
            assertThat(article.author).isEqualTo("Jacob Kastrenakes")
            assertThat(article.url).isEqualTo("https://www.theverge.com/2021/9/1/22650896/hue-spotify-music-sync-integration")
            assertThat(article.publishedAt).isEqualTo("2021-09-01T07:30:00Z")
        }
    }


    @Test
    fun getTechCategorySources_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("sourcesresponse.json")
            val responseBody = service.getTechCategorySources("technology", "abc-news", 1)
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines/sources?category=technology&source=abc-news&page=1&apiKey=b7122b5c5f8948eda9715867b6240ce6")
        }
    }


    @Test
    fun getTechCategorySources_receivedResponse_correctSize() {
        runBlocking {
            enqueueMockResponse("sourcesresponse.json")
            val responseBody = service.getTechCategorySources("technology", "abc-news", 1).body()
            val sourcesList = responseBody!!.sources
            assertThat(sourcesList.size).isEqualTo(14)

        }
    }

    @Test
    fun getTechCategorySources_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("sourcesresponse.json")
            val responseBody = service.getTechCategorySources("technology", "abc-news", 1).body()
            val articleList = responseBody!!.sources
            val article = articleList[0]
            assertThat(article.name).isEqualTo("Ars Technica")
            assertThat(article.url).isEqualTo("http://arstechnica.com")
            assertThat(article.description).isEqualTo("The PC enthusiast's resource. Power users and the tools they love, without computing religion.")
        }
    }


    @Test
    fun getTechArticlesFromSource_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTechArticlesFromSource("technology", "abc-news", 1)
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?category=technology&source=abc-news&page=1&apiKey=b7122b5c5f8948eda9715867b6240ce6")
        }
    }


    @Test
    fun getTechArticlesFromSource_receivedResponse_correctSize() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTechArticlesFromSource("technology", "abc-news", 1).body()
            val sourcesList = responseBody!!.articles
            assertThat(sourcesList.size).isEqualTo(20)

        }
    }



}