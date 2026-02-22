package memo.commands;

public enum CommandType {
    LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, BYE, UNKNOWN, DELETE, FIND, TAG;

    /**
     * Parses a string to find the corresponding CommandType enum.
     * If the string does not match any valid command, it catches the IllegalArgumentException
     * and safely returns the UNKNOWN command type.
     *
     * @param s The command string to be parsed (e.g., "todo", "list").
     * @return The corresponding CommandType, or CommandType. UNKNOWN if the input is invalid.
     */
    public static CommandType fromString(String s) {
        try {
            return CommandType.valueOf(s.trim().toUpperCase());
        }  catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
