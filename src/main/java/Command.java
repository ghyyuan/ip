public enum Command {
    // define enum type by using enum keyword
    // constant names should be in uppercase letters
    LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, BYE, UNKNOWN;

    public static Command fromString(String s) {
        try {
            return Command.valueOf(s.trim().toUpperCase());
        }  catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
