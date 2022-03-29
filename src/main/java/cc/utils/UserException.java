package cc.utils;

public class UserException extends RuntimeException{

    // RuntimeException 外层不用try Exception外城需要try？
    public UserException(String message) {
        //
        super(String.format("%s",message));
    }

}
