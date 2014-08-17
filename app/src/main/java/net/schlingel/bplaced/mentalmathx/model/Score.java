package net.schlingel.bplaced.mentalmathx.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import net.schlingel.bplaced.mentalmathx.game.Mode;

/**
 * Created by zombie on 07.07.14.
 */
@DatabaseTable(tableName = "scores")
public class Score {
    public static final Score HEADER_ELEMENT = new Score();

    @DatabaseField(id = true)
    private Long id;

    @DatabaseField
    private Long timeInSec;

    @DatabaseField
    private Mode gameType;

    @DatabaseField
    private Integer correctGuesses;

    @DatabaseField
    private Integer wrongGuesses;


    public Integer getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(Integer wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public Integer getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(Integer correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public Long getTimeInSec() {
        return timeInSec;
    }

    public void setTimeInSec(Long timeInSec) {
        this.timeInSec = timeInSec;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mode getGameType() {
        return gameType;
    }

    public void setGameType(Mode gameType) {
        this.gameType = gameType;
    }

    public static Score from(Result result, Mode mode) {
        Score s = new Score();
        s.setTimeInSec(result.getTime());
        s.setWrongGuesses(result.getWrongGuesses());
        s.setCorrectGuesses(result.getCorrectGuesses());
        s.setGameType(mode);

        return s;
    }
}
