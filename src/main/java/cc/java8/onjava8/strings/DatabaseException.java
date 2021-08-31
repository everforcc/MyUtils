package cc.java8.onjava8.strings;

/**
 * @author c.c.
 * @date 2021/2/23
 */
// strings/DatabaseException.java
public class DatabaseException extends Exception {

    public DatabaseException(int transactionID,int queryID, String message) {
        super(String.format("(t%d, q%d) %s", transactionID,queryID, message));
    }

    public static void main(String[] args) {
        try {
            throw new DatabaseException(3, 7, "Write failed");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
/*
Output:
DatabaseException: (t3, q7) Write failed
*/

