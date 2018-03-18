package com.example.iran.projetomaterialdesigne.Models.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Iran on 16/03/2018.
 */

public class TableOfGames implements Serializable {
    private String leagueCaption;
    private Integer matchday;
    public List<TableTeams> standing;

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public Integer getMatchday() {
        return matchday;
    }

    public void setMatchday(Integer matchday) {
        this.matchday = matchday;
    }

    public List<TableTeams> getStanding() {
        return standing;
    }

    public void setStanding(List<TableTeams> standing) {
        this.standing = standing;
    }
}
