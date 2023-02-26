package com.markus.onjava.concurrent.future;

import com.markus.onjava.Nap;

/**
 * @author: markus
 * @date: 2023/2/26 9:03 PM
 * @Description: 有限状态机
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Machine {
    enum State {
        START, ONE, TWO, THREE, END;

        State step() {
            if (equals(END)) return END;
            return values()[ordinal() + 1];
        }
    }

    public State state = State.START;
    private final int id;

    public Machine(int id) {
        this.id = id;
    }

    public static Machine work(Machine m) {
        if (!m.state.equals(State.END)) {
            new Nap(0.1);
            m.state = m.state.step();
        }
        System.out.println(m);
        return m;
    }

    @Override
    public String toString() {
        return "Machine" + id + ": " + ((state.equals(State.END)) ? "complete" : state);
    }
}
