package net.schlingel.bplaced.mentalmathx.math;

/**
 * Created by zombie on 27.06.14.
 */
public class Number implements Term {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public Number() {
        this(0);
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
