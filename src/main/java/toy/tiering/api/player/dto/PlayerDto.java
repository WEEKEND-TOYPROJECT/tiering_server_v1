package toy.tiering.api.player.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.tiering.api.common.util.SecurityUtil;
import toy.tiering.api.myteam.domain.MyTeam;
import toy.tiering.api.myteam.domain.SportType;
import toy.tiering.api.player.domain.Player;


public class PlayerDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Req {

        private MyTeam myTeam;
        private SportType sportType;
        private String playerName;
        private String playerPhoto;
        private String playerPosition;
        private String playerStat;
        private String playerSeason;
        private String playerNation;

        private String playerTeam;

        private String playerTeamLogo;

        public Player toEntity() {

            return Player.builder()
                    .playerName(playerName)
                    .playerPhoto(playerPhoto)
                    .playerPosition(playerPosition)
                    .playerStat(playerStat)
                    .playerSeason(playerSeason)
                    .playerNation(playerNation)
                    .playerTeam(playerTeam)
                    .playerTeamLogo(playerTeamLogo)
                    .sportType(sportType)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Res {
        private Long playerId;

        private SportType sportType;
        private String playerName;
        private String playerPhoto;
        private String playerPosition;
        private String playerStat;
        private String playerSeason;
        private String playerNation;

        public Res(Player player) {
            this.playerId = player.getPlayerId();
            this.sportType = player.getSportType();
            this.playerName = player.getPlayerName();
            this.playerPhoto = player.getPlayerPhoto();
            this.playerPosition = player.getPlayerPosition();
            this.playerStat = player.getPlayerStat();
            this.playerSeason = player.getPlayerSeason();
            this.playerNation = player.getPlayerNation();
        }
    }

}
