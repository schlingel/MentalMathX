package net.schlingel.bplaced.mentalmathx.game.strategy;

import net.schlingel.bplaced.mentalmathx.math.*;
import net.schlingel.bplaced.mentalmathx.math.Number;

import java.util.Random;

/**
 * Created by zombie on 09.09.14.
 */
public class OneOOneExerciseStrategy implements ExerciseStrategy {
    private static final Random RANDOM = new Random();
    private static final Operator[] OPERATORS = new Operator[] { Operator.Addition, Operator.Multiplication, Operator.Division, Operator.Subtraction };

    @Override
    public Term nextProblem(int round) {
        int m = nextNumber();
        int n = nextNumber();
        Calculation calc = new Calculation();
        Operator op = nextOperator();

        if(op == Operator.Division) {
            int result = m * n;

            calc.setLeftHandTerm(new Number(result));
            calc.setRightHandTerm(new Number(n));
        } else if(op == Operator.Subtraction) {
            int leftHand = m > n ? m : n;
            int rightHand = m > n ? n : m;

            calc.setLeftHandTerm(new Number(leftHand));
            calc.setRightHandTerm(new Number(rightHand));
        } else {
            calc.setLeftHandTerm(new Number(m));
            calc.setRightHandTerm(new Number(n));
        }

        calc.setOperator(op);

        return calc;
    }

    private int nextNumber() {
        return RANDOM.nextInt(11);
    }

    private Operator nextOperator() {
        return OPERATORS[RANDOM.nextInt(OPERATORS.length)];
    }

}
