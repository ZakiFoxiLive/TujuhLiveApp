package com.tujuhlive.feature.video.ui.navigation.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tujuhlive.core.common.UiEvent
import com.tujuhlive.feature.video.domain.usecase.GetVideoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouVideoViewModel @Inject constructor(private val getVideoListUseCase: GetVideoListUseCase) : ViewModel(){
    private val _videoList = mutableStateOf(ForYouVideoStateHolder())
    private val _language: MutableStateFlow<String> = MutableStateFlow("en")
    private val _uid: MutableStateFlow<String> = MutableStateFlow("-9999")
    private val _p: MutableStateFlow<Int> = MutableStateFlow(1)
    private val _refreshOrder: MutableStateFlow<Int> = MutableStateFlow(1)
    val videoList: State<ForYouVideoStateHolder> get() = _videoList
    val language: StateFlow<String> get() = _language
    val uid: StateFlow<String> get() = _uid
    val p: StateFlow<Int> get() = _p
    val refreshOrder: StateFlow<Int> get() = _refreshOrder

    fun setQuery(language: String, uid: String, p: Int, refreshOrder: Int){
        _language.value = language
        _uid.value = uid
        _p.value = p
        _refreshOrder.value = refreshOrder
    }

    init {
        viewModelScope.launch {
            _language.debounce(1000).collectLatest {
                getVideoList(it, uid.value, p.value, refreshOrder.value)
            }

            _uid.debounce(1000).collectLatest {
                getVideoList(language.value, it, p.value, refreshOrder.value)
            }

            _p.debounce(1000).collectLatest {
                getVideoList(language.value, uid.value, it, refreshOrder.value)
            }

            _refreshOrder.debounce(1000).collectLatest {
                getVideoList(language.value, uid.value, p.value, it)
            }
        }
    }

    fun getVideoList(language: String, uid: String, p: Int, refreshOrder: Int) = viewModelScope.launch {
        getVideoListUseCase(language, uid, p, refreshOrder).onEach {
            when(it){
                is UiEvent.Loading->{
                    _videoList.value = ForYouVideoStateHolder(isLoading = true)
                }
                is UiEvent.Error->{
                    _videoList.value = ForYouVideoStateHolder(error = it.message.toString())
                }
                is UiEvent.Success->{
                    _videoList.value = ForYouVideoStateHolder(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}