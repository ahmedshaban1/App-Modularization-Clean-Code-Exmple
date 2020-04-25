package com.example.common

sealed class MessageType(var code:Int,var message:String? = null){
    class SnackBar(code:Int, message:String? = null) : MessageType(code,message)
    class Dialog(code:Int,message:String? = null) : MessageType(code,message)
    class Toast(code:Int,message:String? = null): MessageType(code,message)
    class None(code:Int,message:String? = null): MessageType(code,message)
}