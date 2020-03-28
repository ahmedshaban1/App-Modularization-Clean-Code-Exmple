package com.example.splash.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.CoroutinesMainDispatcherRule
import com.example.dao.BlogPostDao
import com.example.model.BlogPost
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
    private lateinit var collector: FlowCollector<Resource<List<BlogPostApi>>>

    @Before
    fun setUp() {
        collector = mockk(relaxed = true)
        SUT = SplashRepository(remoteDataSource, blogLocalDataSource)
    }

    @Test
    fun getBlogsAndSuccess() {
        //arrange
        val fakePosts = FakeData.createFakePostsApi(10)
        coEvery { remoteDataSource.getBlogPosts() } returns fakePosts
        coEvery { blogLocalDataSource.getBlogPosts() } returns fakePosts
        runBlocking {
            SUT.getPosts().collect(collector)
        }

        coVerifyOrder {
            collector.emit(Resource.loading(null))
            collector.emit(Resource.loading(data = fakePosts))
            collector.emit(Resource.success(data = fakePosts))
        }
    }


    object FakeData {
        fun createFakePostsApi(count: Int): List<BlogPostApi> {
            return (0 until count).map {
                createFakePostApi(it.toString())
            }
        }

        fun createFakePostApi(id: String): BlogPostApi {
            return BlogPostApi(pk = id, title = "title$id")

        }


        fun createFakePosts(count: Int): List<BlogPost> {
            return (0 until count).map {
                createFakePost(it.toString())
            }
        }

        fun createFakePost(id: String): BlogPost {
            return BlogPost(pk = id, title = "title$id")

        }

    }
}