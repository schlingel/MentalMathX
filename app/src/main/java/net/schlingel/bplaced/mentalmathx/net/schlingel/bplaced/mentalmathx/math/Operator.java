package net.schlingel.bplaced.mentalmathx.net.schlingel.bplaced.mentalmathx.math;

/**
 * Created by zombie on 27.06.14.
 */
public enum Operator {
    Addition,
    Subtraction,
    Division,
    Multiplication;

    @Override
    public String toString() {
        switch (this) {
            case Addition:
                return "+";
            case Subtraction:
                return "-";
            case Division:
                return "÷";
            case Multiplication:
                return "×";
        }

        throw new IllegalStateException("Operator instance is neither Addition, Subtraction, Division nor Multiplication!");
    }

    public int apply(int leftHandValue, int rightHandValue) {
        switch (this) {
            case Addition:
                return leftHandValue + rightHandValue;
            case Subtraction:
                return leftHandValue - rightHandValue;
            case Division:
                return leftHandValue / rightHandValue;
            case Multiplication:
                return leftHandValue * rightHandValue;
        }

        throw new IllegalStateException("Operator instance is neither Addition, Subtraction, Division nor Multiplication!");
    }

    public static Operator oppositeOf(Operator op) {
        if(op == null) {
            throw new IllegalArgumentException("Operator must not be null!");
        }

        switch (op) {
            case Addition:
                return Subtraction;
            case Subtraction:
                return Addition;
            case Multiplication:
                return Division;
            case Division:
                return Multiplication;
            default:
                throw new IllegalStateException("Got operator value which was not null but not one of the four basic arithmetics!");
        }
    }
}
