package org.richard.thymeleaf.tamingthymeleaf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {

    @GetMapping("/{id}")
    fun getTeamInfo(@PathVariable("id") id: String, model: Model): String {

        val team = teamService.getTeam(id)
        model.addAttribute("team", team)
//        return Team()
        return "teams/info"
    }

    @GetMapping
    fun index(model: Model): String {
        val teams = teamService.getAll()
        model.addAttribute("teams", teams)
        return "teams/index"
    }

    @GetMapping("/{teamId}/players/{playerId}")
    fun playerOnTeamInfo(
        @PathVariable("teamId") teamId: String,
        @PathVariable("playerId") playerId: String, model: Model
    ): String {
        model.addAttribute("player", teamService.getPlayerOnTeam(teamId, playerId));
        return "teams/info";
    }

}