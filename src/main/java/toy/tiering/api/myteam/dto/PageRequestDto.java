package toy.tiering.api.myteam.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
public class PageRequestDto {

    private int page;
    private int size;
    private String type;
    private String keyword;


    public PageRequestDto() {
        this.page = 1;
        this.size = 9;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
}
