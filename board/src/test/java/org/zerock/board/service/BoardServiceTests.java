package org.zerock.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("test.")
                .content("Test...")
                .writerEmail("user55@aaa.com") //현재 데이터베이스에 존재하는 회원의메일
                .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boardDTO : result.getDtoList()) {
            System.out.println(boardDTO);
        }
    }
        @Test
        public void testGet() {
            Long bno = 100L;
            BoardDTO boardDTO = boardService.get(bno);

            System.out.println(boardDTO);
        }

        @Test
        public void testRemove() {
            Long bno = 5L;

            boardService.removeWithReplies(bno);
        }
        @Test
        public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목을 변경합니다.")
                .content("내용을 변경합니다.")
                .build();

        boardService.modify(boardDTO);

        }

    }

