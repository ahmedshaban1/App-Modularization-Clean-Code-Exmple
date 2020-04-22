package com.example.auth.data.datasource.remote

import com.example.auth.data.datasource.AuthRepository
import com.example.auth.data.datasource.local.AuthLocal
import com.example.model.User
import com.example.remote.NetworkBoundResource
import com.example.remote.data.Resource
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val remote: AuthRemote, private val local: AuthLocal) :
    AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<User>> {
        return object : NetworkBoundResource<User>() {
            override suspend fun remoteFetch(): User {
                return remote.login(JsonObject().apply {
                    addProperty("email", email)
                    addProperty("password", password)
                })
            }

            override suspend fun saveFetchResult(data: User) {
                local.insertUser(data)
            }

            override suspend fun localFetch(): User {
                return User()
            }

            override fun shouldFetchWithLocalData(): Boolean {
                return false
            }
        }.asFlow()
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<User>> {
        return object : NetworkBoundResource<User>() {
            override suspend fun remoteFetch(): User {
                return remote.register(JsonObject().apply {
                    addProperty("email", email)
                    addProperty("username", username)
                    addProperty("password", password)
                })
            }

            override suspend fun saveFetchResult(data: User) {
            }

            override suspend fun localFetch(): User {
                return User()
            }

            override fun shouldFetchWithLocalData(): Boolean {
                return false
            }
        }.asFlow()
    }

}