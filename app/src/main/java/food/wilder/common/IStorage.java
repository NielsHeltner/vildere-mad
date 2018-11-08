package food.wilder.common;

import android.content.Context;

public interface IStorage<T> {

    void add(T t);

    void upload();

    void get(Context context, String url, Callback Callback);

}
