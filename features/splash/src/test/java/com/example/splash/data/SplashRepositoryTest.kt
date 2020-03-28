package com.example.splash.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.CoroutinesMainDispatcherRule
import com.example.dao.BlogPostDao
import com.example.model.BlogPostApi
import com.example.remote.data.Resource
import com.example.splash.data.datasource.local.BlogPostLocalImpl
import com.example.splash.data.datasource.remote.BlogPostRemoteImpl
import io.mockk.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class SplashRepositoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesMainDispatcherRule = CoroutinesMainDispatcherRule()

    lateinit var SUT: SplashRepository
    var api = spyk<SplashServices>()
    var dao = spyk<BlogPostDao>()
    var blogLocalDataSource = spyk(BlogPostLocalImpl(dao))
    var remoteDataSource = spyk(BlogPostRemoteImpl(api))
    private lateinit var observer: Observer<Resource<List<BlogPostApi>>>

    @Before
    fun setUp() {
        observer = mockk(relaxed = true)
        SUT = SplashRepository(remoteDataSource, blogLocalDataSource)
    }

    @Test
    fun getBlogsAndSuccess() {
        //arrange
        val fakePosts = FakeData.createFakePots(10)
        coEvery { remoteDataSource.getBlogPosts() } returns fakePosts
        coEvery { blogLocalDataSource.getBlogPosts() } returns fakePosts
        runBlocking {
            SUT.getPosts().asLiveData().observeForever(observer)
        }

        verifyOrder {
            observer.onChanged(Resource.loading(null))
            observer.onChanged(Resource.loading(data = fakePosts))
            observer.onChanged(Resource.success(data = fakePosts))
        }
    }


    object FakeData {
        fun createFakePots(count: Int): List<BlogPostApi> {
            return (0 until count).map {
                createFakeUser(it.toString())
            }
        }

        fun createFakeUser(id: String): BlogPostApi {
            return BlogPostApi(pk = id, title = "title$id")

        }

    }
}