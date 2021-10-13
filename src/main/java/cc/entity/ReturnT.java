package cc.entity;

import lombok.Data;

/**
 * @author everforcc 2021-09-14
 */
@Data
public class ReturnT<T> {

    private boolean success;

    private Exception exception;

    private T data;

}
