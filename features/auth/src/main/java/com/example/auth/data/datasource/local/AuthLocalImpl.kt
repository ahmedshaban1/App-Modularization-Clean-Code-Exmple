package com.example.auth.data.datasource.local

import com.example.local.dao.UserDao
import com.example.model.User

class AuthLocalImpl(private val userDao: UserDao) : AuthLocal {
    override suspend fun insertUser(user: User): Long {
        return userDao.insert(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.delete(user)
    }

    override suspend fun getUserUser(): User {
        return userDao.getUser()
    }
}