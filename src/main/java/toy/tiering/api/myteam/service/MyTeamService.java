package toy.tiering.api.myteam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.dto.MyTeamDto;
import toy.tiering.api.myteam.dto.PageRequestDto;
import toy.tiering.api.myteam.dto.PageResultDto;
import toy.tiering.api.myteam.repository.MyTeamRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyTeamService {

    private final MyTeamRepository myTeamRepository;


    public PageResultDto myTeamList(PageRequestDto pageRequestDto) {

        Function<Object[], MyTeamDto.Res> fn = (arr -> new MyTeamDto.Res((MyTeam) arr[0]));
        Page<Object[]> result = myTeamRepository.searchPage(
                pageRequestDto.getType(),
                pageRequestDto.getKeyword(),
                pageRequestDto.getPageable(Sort.by("myTeamId").descending())
        );

        return new PageResultDto(result, fn);
    }

    @Transactional
    public Long myTeamSave(MyTeamDto.Req myTeamReqDto) {

        MyTeam myTeam = myTeamReqDto.toEntity();

        MyTeam myTeamSave = myTeamRepository.save(myTeam);

        return myTeamSave.getMyTeamId();
    }

    public MyTeamDto.Res readMyTeam(Long myTeamId) {

        MyTeam myTeam = myTeamRepository.findById(myTeamId)
                .orElseThrow(() -> new IllegalArgumentException("올바른 내 팀이 아닙니다."));

        return new MyTeamDto.Res(myTeam);
    }

    @Transactional
    public void modiMyTeam(Long myTeamId, MyTeamDto.Req myTeamReqDto) {
        MyTeam myTeam = myTeamRepository.getById(myTeamId);

        myTeam.changeMyTeam(myTeamReqDto.getMyTeamName(),
                            myTeamReqDto.getMyTeamPlayer(),
                            myTeamReqDto.getMyTeamPlayerPhoto(),
                            myTeamReqDto.getMyTeamPlayerPosition(),
                            myTeamReqDto.getMyTeamPlayerStat(),
                            myTeamReqDto.getMyTeamPlayerSeason(),
                            myTeamReqDto.getMyTeamPlayerNation());
    }

    @Transactional
    public void removeMyTeam(Long myTeamId) {
        myTeamRepository.deleteById(myTeamId);
    }


}
