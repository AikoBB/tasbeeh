package com.aigerimbb.android.tasbeeh.event

sealed class Event {
    class Notification(val message: String) : Event()
    class FinishActivity: Event()
    class InvalidTasbeehName(val warning: String): Event()
    class InvalidTasbeehMaxCount(val warning: String): Event()
    class InvalidTasbeehMeaning(val warning: String): Event()
}


