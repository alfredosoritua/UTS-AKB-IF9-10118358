package com.example.akb10118358.base;

public interface Presenter <T extends View> {
    void onAttach(T view);
    void onDetach();
}
