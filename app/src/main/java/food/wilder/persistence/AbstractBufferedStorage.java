package food.wilder.persistence;

import java.util.List;

import food.wilder.common.IStorage;

public abstract class AbstractBufferedStorage<T> implements IStorage<T> {

    protected List<T> data;

    @Override
    public void add(T t) {
        data.add(t);
    }

}
