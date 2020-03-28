package com.example.splash.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.CoroutinesMainDispatcherRule
import com.example.dao.BlogPostDao
import com.example.model.BlogPost
import com.example.model.BlogPostApi
import com.example.remote.data.Resource
import com.example.splash.data.SplashRepository
import com.example.splash.data.SplashRepositoryTest
import com.example.splash.data.SplashServices
import com.example.splash.data.datasource.local.BlogPostLocalImpl
import com.example.splash.data.datasource.remote.BlogPostRemoteImpl
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetBlogPostsUseCaseTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesMainDispatcherRule = CoroutinesMainDispatcherRule()

    lateinit var SUT: GetBlogPostsUseCase
    var api = spyk<SplashServices>()
    var dao = spyk<BlogPostDao>()
    var blogLocalDataSource = spyk(BlogPostLocalImpl(dao))
    var remoteDataSource = spyk(BlogPostRemoteImpl(api))
    var repo = spyk(SplashRepository(remoteDataSource,blogLocalDataSource))

    private lateinit var collector: FlowCollector<Resource<List<BlogPost>>>



    @Before
    fun setUp() {
        collector = mockk(relaxed = true)
        SUT = GetBlogPostsUseCase(repo)
    }


    @Test
    fun getBlogPostsAndSuccess() {
        val fakePostsApi = SplashRepositoryTest.FakeData.createFakePostsApi(10)
        val fakePosts = SplashRepositoryTest.FakeData.createFakePosts(10)

        coEvery { repo.getPosts() } returns flow { emit(Resource.success(data = fakePostsApi)) }

        runBlocking {
            SUT.getPosts().collect(collector = collector)
        }
        coVerifyOrder{
            collector.emit(Resource.success(data = fakePosts))
        }
    }
}