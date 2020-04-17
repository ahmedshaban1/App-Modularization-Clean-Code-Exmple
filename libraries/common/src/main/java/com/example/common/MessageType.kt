package com.example.common

sealed class MessageType(var code:Int){
    class SnackBar(code:Int) : MessageType(code)
    class Dialog(code:Int) : MessageType(code)
    class Toast(code:Int): MessageType(code)
    class None(code:Int): MessageType(code)
}