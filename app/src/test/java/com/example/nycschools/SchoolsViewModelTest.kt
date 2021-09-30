package com.example.nycschools

import com.example.nycschools.api.ApiClient
import com.example.nycschools.data.ApiInterface
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

class SchoolsViewModelTest  {
    private var mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader("school.json")
        assertNotNull(reader.content)
    }

    @Test
    fun `should fetch schoolsList correctly given 200 response`() {
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockResponseFileReader("school.json").content)
        mockWebServer.enqueue(response)
        // Act
        val request = ApiClient.buildService(ApiInterface::class.java)
        val actualResponse = request.getSchoolsList().execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }
    @Test
    fun `should fetch satScoresList correctly given 200 response`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("sat_score.json").content)
        mockWebServer.enqueue(response)
        // Act
        val request = ApiClient.buildService(ApiInterface::class.java)
        val actualResponse = request.getSATScors().execute()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.code().toString().contains("200"))
    }

}


