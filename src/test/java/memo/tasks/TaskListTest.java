package memo.tasks;

import memo.exceptions.MemoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void deleteTask_validIndex_success() throws MemoException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));
        taskList.addTask(new ToDo("Task 2"));

        Task removed = taskList.deleteTask(0);

        assertEquals("[T][ ] Task 1", removed.toString());
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void deleteTask_negativeIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));

        MemoException thrown = assertThrows(MemoException.class, () -> {
            taskList.deleteTask(-1);
        });
        assertEquals("Please enter a valid index within the list :O", thrown.getMessage());
    }

    @Test
    public void deleteTask_outOfBoundsIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task 1"));

        MemoException thrown = assertThrows(MemoException.class, () -> {
            taskList.deleteTask(5);
        });
        assertEquals("Please enter a valid index within the list :O", thrown.getMessage());
    }
}
