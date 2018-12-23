package com.aigerimbb.android.tasbeeh.domain.usecase

abstract class UseCase<out Type, in Params> where Type: Any{

    abstract fun run(params: Params): Type
    class None

}