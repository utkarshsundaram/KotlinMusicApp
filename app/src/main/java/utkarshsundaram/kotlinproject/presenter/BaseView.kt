package utkarshsundaram.kotlinproject.presenter

import androidx.annotation.NonNull

interface BaseView<T>
{
    fun setPresenter(@NonNull presenter:T)

    fun onResponseFailure(ex:Throwable)
}