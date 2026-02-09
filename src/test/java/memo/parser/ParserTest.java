package memo.parser;

import memo.commands.Command;
import memo.commands.ExitCmd;
import memo.exceptions.MemoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_emptyTodoDescription_exceptionThrown() {

        MemoException thrown = assertThrows(MemoException.class, () -> {
            Parser.parse("todo");
        });

        assertEquals("The description of a TODO cannot be empty! =(", thrown.getMessage());
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {

        assertThrows(MemoException.class, () -> {
            Parser.parse("blahblahblah");
        });
    }

    @Test
    public void parse_byeCommand_success() throws MemoException {
        Command c = Parser.parse("bye");

        assertInstanceOf(ExitCmd.class, c);
    }

    @Test
    public void parse_deadlineMissingBy_exceptionThrown() {
        MemoException thrown = assertThrows(MemoException.class, () -> {
            Parser.parse("deadline homework /at tomorrow");
        });

        assertEquals("Please specify the deadline with /by :(", thrown.getMessage());
    }
}
