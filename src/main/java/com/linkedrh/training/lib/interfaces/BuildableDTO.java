package com.linkedrh.training.lib.interfaces;

public interface BuildableDTO<T> {

    public void buildFrom(T entity);
}
