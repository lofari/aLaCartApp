package com.example.lorenzo.alacarta;

public interface CallListener<T>{
        void onSucess( T data );
        void onError( Throwable error );
}
