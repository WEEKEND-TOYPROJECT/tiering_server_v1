package toy.tiering.api.myteam.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import toy.tiering.api.common.response.ApiResponse;
import toy.tiering.api.myteam.dto.MyTeamDto;
import toy.tiering.api.myteam.dto.PageRequestDto;
import toy.tiering.api.myteam.dto.PageResultDto;
import toy.tiering.api.myteam.service.MyTeamService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myteams")
@Slf4j
public class MyTeamController {

    private final MyTeamService myTeamService;

    @GetMapping("/list/pages")
    public ResponseEntity<PageResultDto<MyTeamDto, Object[]>> myTeamList(PageRequestDto pageRequestDto) {

        PageResultDto pageResultDto = myTeamService.myTeamList(pageRequestDto);

        return ResponseEntity.ok(pageResultDto);
    }

    @GetMapping("/read/{myTeamId}")
    public ResponseEntity<ApiResponse> readMyTeam(@PathVariable Long myTeamId) {
        MyTeamDto.Res response = myTeamService.readMyTeam(myTeamId);

        return ResponseEntity.ok(ApiResponse.success("myTeam", response));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveMyTeam(@RequestBody MyTeamDto.Req myTeamReqDto) {
        myTeamService.myTeamSave(myTeamReqDto);

        return ResponseEntity.ok(ApiResponse.success("myTeam create complete", myTeamReqDto));
    }

    @PutMapping("/update/{myTeamId}")
    public ResponseEntity<ApiResponse> modiMyTeam(@RequestBody MyTeamDto.Req myTeamReqDto, @PathVariable Long myTeamId) {
        myTeamService.modiMyTeam(myTeamId, myTeamReqDto);
        return ResponseEntity.ok(ApiResponse.success("myTeam update complete", myTeamReqDto));
    }

    @DeleteMapping("/remove/{myTeamId}")
    public ResponseEntity<ApiResponse> removeMyTeam(@PathVariable Long myTeamId) {
        myTeamService.removeMyTeam(myTeamId);
        return ResponseEntity.ok(ApiResponse.success("myTeam remove complete", "내 팀이 삭제 완료 되었습니다."));
    }
}
