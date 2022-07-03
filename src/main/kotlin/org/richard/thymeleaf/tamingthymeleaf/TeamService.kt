package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.stereotype.Service

@Service
class TeamService {


    fun getAll(): List<Team> {
        return listOf(
            Team("1", "Initiates"),
            Team("2", "Knights"),
            Team("3", "Padawans"),
            Team("4", "Rookies"),
            Team("5", "Wizards"),
        )
    }

    fun getTeam(id: String): Team {
        return Team(id, "Team Detail")
    }

    fun getPlayerOnTeam(teamId: String, playerId: String): Player {
        return Player("", "")
    }

    fun changeTeamName(teamId: String, teamName: String?) {

    }
}
