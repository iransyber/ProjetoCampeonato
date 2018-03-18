package com.example.iran.projetomaterialdesigne.Models.Campeonatos;

import java.io.Serializable;

/**
 * Created by iran_ on 08/10/2017.
 */

public class Links implements Serializable {
    private Self self;
    private Teams teams;
    private Fixtures fixtures;
    private LeagueTable leagueTable;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Teams getTeams() {
        return teams;
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
    }

    public Fixtures getFixtures() {
        return fixtures;
    }

    public void setFixtures(Fixtures fixtures) {
        this.fixtures = fixtures;
    }

    public LeagueTable getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(LeagueTable leagueTable) {
        this.leagueTable = leagueTable;
    }
}
