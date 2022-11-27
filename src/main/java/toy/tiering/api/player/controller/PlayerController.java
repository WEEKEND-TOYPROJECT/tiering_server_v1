package toy.tiering.api.player.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.tiering.api.common.response.ApiResponse;
import toy.tiering.api.myteam.dto.MyTeamDto;
import toy.tiering.api.myteam.dto.PageRequestDto;
import toy.tiering.api.myteam.dto.PageResultDto;
import toy.tiering.api.player.domain.Player;
import toy.tiering.api.player.dto.PlayerDto;
import toy.tiering.api.player.service.PlayerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/list/pages")
    public ResponseEntity<Page<Player>> playerList(String keyword, Pageable pageable) {

        Page<Player> playerList = playerService.playerList(keyword, pageable);

        return ResponseEntity.ok(playerList);

    }
    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveMyTeam(@RequestBody PlayerDto.Req playerReqDto) {
        playerService.save(playerReqDto);

        return ResponseEntity.ok(ApiResponse.success("player create complete", playerReqDto));
    }

}
