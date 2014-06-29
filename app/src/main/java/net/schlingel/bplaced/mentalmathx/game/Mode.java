package net.schlingel.bplaced.mentalmathx.game;

import java.io.Serializable;

/**
 * Created by zombie on 29.06.14.
 */
public enum Mode implements Serializable {
    TenRounds,
    HoundredRounds,
    Marathon;

    public static final String NAME = "Mode";
}
