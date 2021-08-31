package cc.structure.msgtype.json.jsonpath;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author c.c.
 * @date 2021/1/12
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String category;
    private String author;
    private String title;
    private String isbn;
    private double price;
}
