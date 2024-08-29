package Winde;

public class Winde {

    private static History history;
    private static Reminder reminder;
    private static Ui ui;

    Winde(String filePath) {
        ui = new Ui();
        history = new History(filePath);
        reminder = new Reminder(history.load());
    }

    Winde() {
        ui = new Ui();
        history = new History();
        reminder = new Reminder(history.load());
    }

    public static void main(String[] args) {
        new Winde().run();
    }

    private static void run() {
        ui.greet();
        Command c = new ListCommand();
        boolean shouldContinue = true;
        while (shouldContinue) {
            try {
                String input = ui.read();
                ui.showLine();
                c = Parser.parse(input);
                shouldContinue = c.execute(input, reminder, ui);
            } catch (Parser.UnsupportedCommandException e) {
                throw new RuntimeException(e);
            } catch (Command.EmptyDescriptionException e) {
                throw new RuntimeException(e);
            } catch (Command.TooManyParametersException e) {
                throw new RuntimeException(e);
            } finally {
                ui.showLine();
            }
        }
        c.exit(history, reminder, ui);
    }

    public void task(int i) {
        System.out.print(reminder.getTask(i - 1).toString());
    }
    /*
    public static void add(String action) {
        Task t = new Task(action);
        reminder.add(t);
        System.out.println("added: " + action);
    }

     */

    static class UnsupportedFilePathException extends Exception {
        public UnsupportedFilePathException(String message) {
            super(message);
        }
    }
}

