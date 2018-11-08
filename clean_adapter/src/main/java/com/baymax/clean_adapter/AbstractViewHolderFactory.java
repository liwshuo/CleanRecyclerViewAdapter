package com.baymax.clean_adapter;

import android.util.Log;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by lishuo on 08,November,2018
 */
public abstract class AbstractViewHolderFactory<Item> implements IViewHolderFactory<Item> {

    @Override
    public BaseCleanExtraDataViewHolder create(ViewGroup parent, Class viewHolderClass) {
        BaseCleanExtraDataViewHolder viewHolder = null;
        Exception exception = null;
        try {
            Constructor constructor = viewHolderClass.getConstructor(ViewGroup.class);
            viewHolder = (BaseCleanExtraDataViewHolder) constructor.newInstance(parent);
        } catch (NoSuchMethodException e) {
            exception = e;
        } catch (IllegalAccessException e) {
            exception = e;
        } catch (InstantiationException e) {
            exception = e;
        } catch (InvocationTargetException e) {
            exception = e;
        }
        if (exception != null && BuildConfig.DEBUG) {
            IllegalArgumentException argumentException;
            if (exception instanceof InvocationTargetException) {
                argumentException = new IllegalArgumentException(((InvocationTargetException) exception).getTargetException());
                argumentException.setStackTrace(((InvocationTargetException) exception).getTargetException().getStackTrace());
            } else {
                argumentException = new IllegalArgumentException(exception);
                argumentException.setStackTrace(exception.getStackTrace());
            }
            throw argumentException;
        }
        if (viewHolder == null) {
            viewHolder = new BaseCleanViewHolder(parent, R.layout.dummy_view_holder) {
                @Override
                public void onBindViewHolder(Object o) {

                }
            };
        }
        return viewHolder;
    }
}
