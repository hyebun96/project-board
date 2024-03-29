package com.fastcampus.projectboard.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("비지니스 로직 - 페이지네이션")
@SpringBootTest
class PaginationServiceTest {

    private final PaginationService sut;

    public PaginationServiceTest(@Autowired PaginationService sut) {
        this.sut = sut;
    }

    @DisplayName("현제 페이지 번호와 총 페이지 수를 주면, 페이징 바 리스트를 만들어 준다.")
    @MethodSource
    @ParameterizedTest(name = "[{index}] 현재페이지 : {0}, 총페이지 : {1} => {2}")
    void givenCurrentPageNumberAndTotalPages_whenCalculating_thanReturnPaginationBarNumbers(int currentPageNumber, int totalPages, List<Integer> expected){
        // Given

        // When
        List<Integer> actual = sut.getPaginationBarNumbers(currentPageNumber, totalPages);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> givenCurrentPageNumberAndTotalPages_whenCalculating_thanReturnPaginationBarNumbers(){
        return Stream.of(
                arguments(0, 10, List.of(0, 1, 2, 3, 4)),
                arguments(1, 10, List.of(0, 1, 2, 3, 4)),
                arguments(2, 10, List.of(0, 1, 2, 3, 4)),
                arguments(3, 10, List.of(1, 2, 3, 4, 5)),
                arguments(4, 10, List.of(2, 3, 4, 5, 6)),
                arguments(5, 10, List.of(3, 4, 5, 6, 7)),
                arguments(6, 10, List.of(4, 5, 6, 7, 8)),
                arguments(7, 10, List.of(5, 6, 7, 8, 9)),
                arguments(8, 10, List.of(6, 7, 8, 9)),
                arguments(9, 10, List.of(7, 8, 9))
                //arguments(12, 10, List.of(10, 11, 12))
        );
    }

    @DisplayName("현재 설정되어 있는 페이지네이션 바의 길이를 알려준다.")
    @Test
    void givenNothing_whenCalling_thenReturnCurrentBarLength(){
        // Given

        // When
        int barLength = sut.currentBarLength();

        // Then
    }

}