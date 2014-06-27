package net.schlingel.bplaced.mentalmathx.net.schlingel.bplaced.mentalmathx.math;

import net.schlingel.bplaced.mentalmathx.game.Difficulty;

import java.util.Random;

/**
 * Created by zombie on 27.06.14.
 *
 * Let x = m (+) n where (+) is a element of the set  {+, -, *, /} and x, m and n are elements of the natural numbers. (-) is the inverse function of (+).
 * Therefore x = m (+) n <-> m = x (-) n
 *
 * We take x as an input and generate random numbers for n till we get a natural number which applied to the operator (-) generates another positive natural number m. The method
 * returns the calculation as Calculation instance.
 */
public class Calculations {
    private static final Random rand = new Random();
    private static final Operator[] OPERATORS = new Operator[] {
        Operator.Addition,
        Operator.Subtraction,
        Operator.Multiplication,
        Operator.Division
    };

    private Difficulty difficulty;

    public Calculations(Difficulty difficulty) {
        if(difficulty == null) {
            throw new IllegalArgumentException("difficulty must not be null!");
        }

        this.difficulty = difficulty;
    }

    public Term createForResult(int result) {
        int n = -1;
        int m = -1;
        Operator op;

        do {
            op = randomOperator();
            n = randomNumber();
            m = Operator.oppositeOf(op).apply(result, n);
        } while(m <= 0);

        Calculation calc = new Calculation();
        calc.setLeftHandTerm(new Number(m));
        calc.setRightHandTerm(new Number(n));
        calc.setOperator(op);

        return calc;
    }

    public Term hardenProblem(Term term) {
        if(term instanceof Number) {
            return createForResult(term.value());
        }

        if(term instanceof Calculation) {
            Calculation calc = (Calculation)term;
            boolean isLeftHandTarget = rand.nextBoolean();

            if(isLeftHandTarget) {
                calc.setLeftHandTerm(hardenProblem(calc.getLeftHandTerm()));
            } else {
                calc.setRightHandTerm(hardenProblem(calc.getRightHandTerm()));
            }

            return calc;
        }

        throw new IllegalArgumentException("term must not be null!");
    }

    public Term getProblem() {
        Term t = createForResult(1 + rand.nextInt(50));
        hardenProblem(t);

        return t;
    }

    private int randomNumber() {
        switch (difficulty) {
            case Easy:
                return rand.nextInt(11);
            case Medium:
                return rand.nextInt(21);
            case Hard:
                return rand.nextInt(31);
        }

        throw new IllegalStateException("Difficulty must be set!");
    }

    private Operator randomOperator() {
        return OPERATORS[rand.nextInt(OPERATORS.length)];
    }
}
