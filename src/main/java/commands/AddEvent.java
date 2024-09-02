package commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.EmptyDescriptionException;
import tasks.Event;
import windebot.Reminder;
import windebot.Ui;

/**
 * The AddEvent class represents a command to add an event task to the reminder list.
 * This command parses the input to extract the event description, start time, and end time.
 * If the input is invalid or incomplete, an exception is thrown.
 */

public class AddEvent extends Command {

    /**
     * Executes the AddEvent command, adding a new event task to the reminder.
     *
     * @param input The user input string containing the event description, start time, and end time.
     * @param reminder The Reminder object that manages the task list.
     * @param ui The Ui object used to interact with the user.
     * @return true if the command was executed successfully.
     * @throws EmptyDescriptionException If the input is incomplete or incorrectly formatted.
     */

    public boolean execute(String input, Reminder reminder, Ui ui) throws EmptyDescriptionException {
        String[] command = input.split(" ", 2);
        if (command.length == 2) {
            String[] order = command[1].split(" /from ");
            if (order.length == 2) {
                String[] fillerName = order[1].split(" /to ");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime start = LocalDateTime.parse(fillerName[0], dateFormatter);
                LocalDateTime end = LocalDateTime.parse(fillerName[1], dateFormatter);
                Event eventTask = new Event(order[0], start, end);
                reminder.addEvent(eventTask);
                ui.print("Got it. I've added this task:");
                ui.print("    " + eventTask.toString());
                ui.print("Now you have " + reminder.size() + " tasks in the list.");
            } else {
                throw new EmptyDescriptionException("WHEN EVENT DATE!");
            }
        } else {
            throw new EmptyDescriptionException("I NEED TO KNOW WHAT I'M EVENT-ING!");
        }
        return true;
    }
}
