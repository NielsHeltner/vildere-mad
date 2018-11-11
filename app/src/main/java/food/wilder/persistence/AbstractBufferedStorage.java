package food.wilder.persistence;

import android.content.Context;

import java.util.List;

import food.wilder.common.IStorage;
import food.wilder.common.Callback;

public abstract class AbstractBufferedStorage<T> implements IStorage<T> {

    protected List<T> data;

    @Override
    public void add(T t) {
        data.add(t);
    }


    @Override
    public void get(Context context, String url, Callback Callback) {  }

}
