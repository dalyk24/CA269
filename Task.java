/**
 * Task.java 
 * @author Killian Daly
 */

import java.time.LocalDate;
import java.time.Period;

enum State {
    TODO, BEGN, HALT, WAIT, DONE
}

class Task {
    private final String title;
    private String description;
    private LocalDate scheduled;
    private LocalDate deadline;
    private State state;

    /**
     * Default constructor takes title and state
     */
    public Task(String title, State state) {
        this.title = title;
        setState(state);
    }

    /**
     * Additional constructor all optional fields
     */
    public Task(String title, State state, String description, LocalDate scheduled, LocalDate deadline) {
        this.title = title;
        setState(state);
        setDesc(description);
        setScheduled(scheduled);
        this.deadline = deadline;
    }

    // getters
    public String getTitle() { // <-- getter
        return title;
    }

    public State getState() { // <-- getter
        return state;
    }

    public String getDesc() { // <-- getter
        return description;
    }

    public LocalDate getScheduled() { // <-- getter
        return scheduled;
    }

    public LocalDate getDeadline() { // <-- getter
        return deadline;
    }

    // setters
    public void setState(State state) {
        this.state = state;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public void setScheduled(LocalDate scheduled) {
        this.scheduled = scheduled;
    }

    /**
     * toString implementation
     */
    public String toString() {
        String message = this.title + " (" + this.state + ")";
        if (scheduled != null) {
            message += " scheduled: " + scheduled;
        }
        if (deadline != null) {
            message += " deadline: " + deadline;
        }
        return message;
    }

    public static void main(String[] args) {
        Task t1 = new Task("Test", State.TODO);
        LocalDate now = LocalDate.now();
        t1.setScheduled(now);
        System.out.println(t1);
    }
}

class Chore extends Task {
    private LocalDate repeat;

    Chore(String title, State state, LocalDate scheduled, LocalDate repeat) {
        // parameters are the mandatory fields
        // super() is to ensure parent constructor is called - otherwise we get Errors!
        super(title, state);
        // even in constructor, defer to setters
        // because there may be input validation, default values, etc. implemented in them
        setScheduled(scheduled);
        setRepeat(repeat);
    }

    public void setState(State state) {
        // if state is DONE
        super.setState(state);
        if (state == State.DONE) {
            // scheduled = repeat
            // but first set repeat to +7 days
            LocalDate repeat_new = repeat.plus(Period.ofDays(7));
            setScheduled(repeat);
            setRepeat(repeat_new);
            // remember to set state back to TODO
            super.setState(State.TODO);
        }
    }

    public void setRepeat(LocalDate repeat) {
        this.repeat = repeat;
    }
}

class RepeatedTask extends Task {

    RepeatedTask(String title, State state) {
        super(title, state);
    }

    public void setState(State state) {
        super.setState(state);
        if (state == State.DONE) {
            // remember to set state back to TODO
            super.setState(State.TODO);
        }
    }
}

class SharedTask extends Task {
    String sharePerson;

    SharedTask(String title, String sharePerson) {
        super(title, State.WAIT);

        setPerson(sharePerson);
    }

    public void setPerson(String sharePerson) {
        this.sharePerson = sharePerson;
    }

    public String getPerson() {
        return sharePerson;
    }

    public String toString() {
        String message = getTitle() + " (" + getState() + ")";
        if (getScheduled() != null) {
            message += " scheduled: " + getScheduled();
        }
        if (getDeadline() != null) {
            message += " deadline: " + getDeadline();
        }
        if (getPerson() != null) {
            message += " shared with: " + getPerson();
        }
        return message;
    }

}

class Dependency extends Task {
    Task depends;

    Dependency(String title, State state, Task depends) {
        super(title, state);

        setDepend(depends);
    }

    public void setDepend(Task depends) {
        this.depends = depends;
    }

    public Task getDepend() {
        return depends;
    }

    public void setState(State state) {
        if (state == State.DONE) {
            if (depends.getState() == State.DONE) {
                // remember to set state back to TODO
                super.setState(State.DONE);
            }
        }
        else {
            super.setState(state);
        }
    }

    public String toString() {
        String message = getTitle() + " (" + getState() + ")";
        if (getScheduled() != null) {
            message += " scheduled: " + getScheduled();
        }
        if (getDeadline() != null) {
            message += " deadline: " + getDeadline();
        }
        if (getDepend() != null) {
            message += " dependent on: " + getDepend();
        }
        return message;
    }

}
