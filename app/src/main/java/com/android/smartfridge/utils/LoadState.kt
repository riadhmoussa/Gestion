package com.android.smartfridge.utils

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}