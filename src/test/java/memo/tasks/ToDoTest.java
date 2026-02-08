package memo.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toString_newToDo_success() {

        ToDo todo = new ToDo("read book");

        assertEquals("[T][ ] read book", todo.toString());
    }

    @Test
    public void toStoreForm_newToDo_success() {

        ToDo todo = new ToDo("read book");


        assertEquals("T | 0 | read book", todo.toStoreForm());
    }

    @Test
    public void markAsDone_success() {
        ToDo todo = new ToDo("read book");
        todo.changeStatus(true);

        assertEquals("[T][X] read book", todo.toString());

        assertEquals("T | 1 | read book", todo.toStoreForm());
    }
}