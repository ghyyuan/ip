package memo.commands;

public enum CommandType {
    // define enum type by using enum keyword
    // constant names should be in uppercase letters
    LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, BYE, UNKNOWN, DELETE, FIND;

    public static CommandType fromString(String s) {
        try {
            return CommandType.valueOf(s.trim().toUpperCase());
        }  catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
