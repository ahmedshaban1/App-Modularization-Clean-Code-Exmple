package com.example.common

interface UiCommunicator {
    fun showLoading()
    fun hideLoading()
    fun handleMessages(messageType: MessageType)
}