package toy.tiering.api.myteam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.tiering.api.common.util.SecurityUtil;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.domain.SportType;
import toy.tiering.api.user.domain.User;

public class MyTeamDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Req {
        private User user;
        private SportType sportType;

        private String myTeamPlayer;
        private String myTeamPlayerPhoto;
        private String myTeamPlayerPosition;
        private String myTeamPlayerStat;
        private String myTeamPlayerSeason;
        private String myTeamPlayerNation;
        private String myTeamLogo;

        private String nickname;

        public String setNickname(String nickName) {
            this.nickname = nickName;
            return nickName;
        }

        public MyTeam toEntity() {
            Long userId = SecurityUtil.getCurrentUserId();

            return MyTeam.builder()
                    .user(User.builder().userId(userId).build())
                    .mySportType(sportType)
                    .myTeamPlayer(myTeamPlayer)
                    .myTeamPlayerPhoto(myTeamPlayerPhoto)
                    .myTeamPlayerPosition(myTeamPlayerPosition)
                    .myTeamPlayerStat(myTeamPlayerStat)
                    .myTeamPlayerSeason(myTeamPlayerSeason)
                    .myTeamPlayerNation(myTeamPlayerNation)
                    .myTeamLogo(myTeamLogo)
                    .nickName(nickname)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Res {
        private Long MyTeamId;

        private SportType sportType;

        private String myTeamPlayer;
        private String myTeamPlayerPhoto;
        private String myTeamPlayerPosition;
        private String myTeamPlayerStat;
        private String myTeamPlayerSeason;
        private String myTeamPlayerNation;
        private String myTeamLogo;

        private String nickname;

        public Res(MyTeam myTeam) {
            this.MyTeamId = myTeam.getMyTeamId();
            this.nickname = myTeam.getUser().getNickName();
            this.myTeamPlayer = myTeam.getMyTeamPlayer();
            this.myTeamPlayerPhoto = myTeam.getMyTeamPlayerPhoto();
            this.myTeamPlayerPosition = myTeam.getMyTeamPlayerPosition();
            this.myTeamPlayerStat = myTeam.getMyTeamPlayerStat();
            this.myTeamPlayerSeason = myTeam.getMyTeamPlayerSeason();
            this.myTeamPlayerNation = myTeam.getMyTeamPlayerNation();
            this.myTeamLogo = myTeam.getMyTeamLogo();
        }
    }

}
