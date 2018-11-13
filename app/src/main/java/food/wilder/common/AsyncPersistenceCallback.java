package food.wilder.common;

public interface AsyncPersistenceCallback<T> {

    void callback(T t);

}
