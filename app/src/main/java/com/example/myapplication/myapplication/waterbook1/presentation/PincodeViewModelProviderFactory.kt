package com.example.myapplication.myapplication.waterbook1.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.myapplication.waterbook1.data.repo.Repository

class PincodeViewModelProviderFactory (
    val repository: Repository
        ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PincodesViewModel(repository) as T
    }
}