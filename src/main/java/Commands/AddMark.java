package Commands;

import WindeBot.Reminder;
import WindeBot.Ui;

import Exceptions.EmptyDescriptionException;
import Exceptions.TooManyParametersException;

/**
 * The AddMark class represents a command to mark a task as done in the reminder list.
 * This command marks a specified task by its index in the list.
 * If the input is invalid or the task cannot be found, an exception is thrown.
 */

public class AddMark extends Command {

    /**
     * Executes the AddMark command, marking a task as completed.
     *
     * @param input The user input string containing the index of the task to be marked.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If no index is provided in the input.
     * @throws TooManyParametersException If too many parameters are provided in the input.
     */

    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException, TooManyParametersException {
        String[] command = input.split(" ");
        if (command.length == 2) {
            ui.print("Nice! I've marked this task as done:");
            reminder.mark(Integer.parseInt(command[1]) - 1);
            ui.print("    " + reminder.getTask(Integer.parseInt(command[1]) - 1).toString());
        } else if (command.length < 2) {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M MARKING!");
        } else {
            throw new TooManyParametersException("ONE AT A TIME!");
        }
        return true;
    }
}
