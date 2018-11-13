package food.wilder.common;

import android.content.Context;

public interface IStorage<T> {

    void add(T t);

    void upload(Context context, String username, Callback callback);

    void upload(Context context, String id);

    void get(Context context, String query, Callback callback);

}
