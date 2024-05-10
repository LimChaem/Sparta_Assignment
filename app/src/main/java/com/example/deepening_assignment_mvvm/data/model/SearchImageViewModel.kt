package com.example.deepening_assignment_mvvm.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.deepening_assignment_mvvm.data.network.RetrofitClient
import com.example.deepening_assignment_mvvm.data.repository.SearchImageRepository
import com.example.deepening_assignment_mvvm.presentaition.entity.DocumentsEntity
import com.example.deepening_assignment_mvvm.presentaition.entity.SearchImageImpl
import kotlinx.coroutines.launch

class SearchImageViewModel(private val searchImageRepository: SearchImageRepository) : ViewModel() {
    private val _getSearchImageResponse: MutableLiveData<List<DocumentsEntity>> = MutableLiveData()
    val getSearchImageResponse: LiveData<List<DocumentsEntity>> get() = _getSearchImageResponse

    fun getSearchImageList(searchKey: String) {
        viewModelScope.launch {
            _getSearchImageResponse.value = searchImageRepository.getImage(searchKey).items
        }
    }

}

class SearchImageViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return SearchImageViewModel(
            SearchImageImpl(
                RetrofitClient.netWorkInterface
            )
        ) as T
    }
}
