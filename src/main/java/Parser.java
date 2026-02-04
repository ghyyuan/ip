public class Parser {

    // This method transforms the stored line into a Task Object
    public static Task fromStoreForm(String line) throws MemoException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new MemoException("Skipping corrupted file line TT");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = switch (type) {
            case "T" -> new ToDo(description);
            case "D" -> new Deadline(description, parts[3]);
            case "E" -> new Event(description, parts[3], parts[4]);
            default -> throw new MemoException("Unknown task type in file TT");
        };

        if (isDone) {
            task.changeStatus(true);
        }
        return task;
    }

//    // This method parse a user command to specific action
//    public static Command parse(String fullCmd) throws MemoException {
//        String[] inputs = fullCmd.split(" ", 2);
//
//        CommandType type = CommandType.fromString(inputs[0]);
//
//        return switch (type) {
//            case LIST -> new ListCommand();
//            case TODO -> new AddCommand(inputs[1]);
//            case BYE -> new ExitCommand();
//            case MARK -> new ChangeStatusCommand(true);
//            case UNMARK -> new ChangeStatusCommand(false);
//            case DELETE ->  new DeleteCommand(inputs[1]);
//            case UNKNOWN -> throw new MemoException("I don't know that one ><");
//        };

    public static Command parse(String fullCmd) throws MemoException {
        String[] inputs = fullCmd.split(" ", 2);
        CommandType type = CommandType.fromString(inputs[0]);

        if (type != CommandType.LIST && type != CommandType.BYE && inputs.length < 2) {
            throw new MemoException("The description of a " + type + " cannot be empty! =(");
        }

        return switch (type) {
            case LIST -> new ListCommand();
            case TODO -> new AddTodoCmd(inputs[1]);

            case DEADLINE -> {
                String[] parts = inputs[1].split(" /by ");
                if (parts.length < 2) {
                    throw new MemoException("Please specify the deadline with /by :(");
                }
                yield new AddDeadlineCmd(parts[0], parts[1]);
            }

            case EVENT -> {
                String[] fromParts = inputs[1].split(" /from ");
                if (fromParts.length < 2) {
                    throw new MemoException("Please specify /from for your event :(");
                }
                String[] toParts = fromParts[1].split(" /to ");
                if (toParts.length < 2) {
                    throw new MemoException("Please specify /to for your event :(");
                }
                yield new AddEventCmd(fromParts[0], toParts[0], toParts[1]);
            }

            case DELETE -> new DeleteCommand(Integer.parseInt(inputs[1]) - 1);
            case MARK -> new ChangeStatusCommand(Integer.parseInt(inputs[1]) - 1, true);
            case UNMARK -> new ChangeStatusCommand(Integer.parseInt(inputs[1]) - 1, false);
            case BYE -> new ExitCommand();
            case UNKNOWN -> throw new MemoException("I don't know what that means ><");
        };
    }

}
//
//        //String[] inputs = input.split(" ", 2);
////            Command cmd = Command.fromString(inputs[0]);
////
////            try {
////                if (cmd == Command.BYE) {
////                    ui.showBye();
////                    break;
////                }
////
////                switch (cmd) {
////                    case LIST:
//                        ui.showList(list);
//                        break;
//
//                    case MARK:
//                    case UNMARK:
//                        if (inputs.length != 2) {
//                            throw new MemoException("Command is not complete ><");
//                        }
//                        // check length BEFORE parsing
//                        int index = Integer.parseInt(inputs[1]) - 1;
//                        if (!(index >= 0 && index < list.size())) {
//                            throw new MemoException("Please enter a valid index within the list :O");
//                        }
//
//                        boolean isDone = cmd == Command.MARK;
//                        list.get(index).changeStatus(isDone);
//                        ui.showMarkStatus(list.get(index), isDone);
//                        save(f, list, ui);
//                        break;
//
//                    case DELETE:
//                        if (inputs.length != 2) {
//                            throw new MemoException("Command is not complete ><");
//                        }
//                        // check length BEFORE parsing
//                        int i = Integer.parseInt(inputs[1]) - 1;
//                        if (!(i >= 0 && i < list.size())) {
//                            throw new MemoException("Please enter a valid index within the list :O");
//                        }
//
//                        Task curr = list.get(i);
//                        list.remove(i);
//                        ui.showRemove(curr, list.size());
//                        save(f, list, ui);
//                        break;
//
//                    case TODO:
//                        if (inputs.length != 2) {
//                            throw new MemoException("Please enter what you gonna do =(");
//                        }
//                        Task t = new ToDo(inputs[1]);
//                        list.add(t);
//                        ui.showAddedTask(t, list.size());
//                        save(f, list, ui);
//                        break;
//
//                    case DEADLINE:
//                        if (inputs.length != 2) {
//                            throw new MemoException("Please enter what you gonna do with time =(");
//                        }
//                        String[] ddl = inputs[1].split(" /by ");
//                        if (ddl.length != 2) {
//                            throw new MemoException("Please specify the ddl with /by :(");
//                        }
//                        Deadline d = new Deadline(ddl[0], ddl[1]);
//                        list.add(d);
//                        ui.showAddedTask(d, list.size());
//                        save(f, list, ui);
//                        break;
//
//                    case EVENT:
//                        if (inputs.length != 2) {
//                            throw new MemoException("Please enter what you gonna do with time =(");
//                        }
//                        String[] str1 = inputs[1].split(" /from ");
//                        if (str1.length != 2) {
//                            throw new MemoException("Please specify the event with /from :(");
//                        }
//                        String[] str2 = str1[1].split(" /to ");
//                        if (str2.length != 2) {
//                            throw new MemoException("Please specify the event with /to:(");
//                        }
//                        Event e = new Event(str1[0], str2[0], str2[1]);
//                        list.add(e);
//                        ui.showAddedTask(e, list.size());
//                        save(f, list, ui);
//                        break;
//
//                    case UNKNOWN:
//                        ui.showError("What does that mean ><");
//                        break;
//                }
//
//            } catch (MemoException e) {
//                ui.showError(e.getMessage());
//            } catch (NumberFormatException e) {
//                ui.showError("Please enter a valid number ><");
//            }
//        }
//    }

