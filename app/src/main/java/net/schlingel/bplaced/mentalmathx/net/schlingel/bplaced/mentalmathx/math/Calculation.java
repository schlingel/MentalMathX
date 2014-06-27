package net.schlingel.bplaced.mentalmathx.net.schlingel.bplaced.mentalmathx.math;

/**
 * Created by zombie on 27.06.14.
 */
public class Calculation implements Term {
    private Term leftHandTerm;

    private Term rightHandTerm;

    private Operator operator;

    @Override
    public int value() {
        return operator.apply(leftHandTerm.value(), rightHandTerm.value());
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", leftHandTerm.toString(), operator.toString(), rightHandTerm.toString());
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setRightHandTerm(Term rightHandTerm) {
        this.rightHandTerm = rightHandTerm;
    }

    public Term getRightHandTerm() {
        return rightHandTerm;
    }

    public void setLeftHandTerm(Term leftHandTerm) {
        this.leftHandTerm = leftHandTerm;
    }

    public Term getLeftHandTerm() {
        return leftHandTerm;
    }
}
