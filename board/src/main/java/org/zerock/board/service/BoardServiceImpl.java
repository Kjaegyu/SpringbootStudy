package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResultDTO;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.ReplyRepository;

import javax.transaction.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository; //자동 주입 final
    private final ReplyRepository replyRepository; //새롭게 추가

    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);

        Board board  = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0],(Member) en[1],(Long)en[2]));

       Page<Object[]> result = repository.getBoardWithReplyCount(
               pageRequestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResultDTO<>(result, fn);

    }

    @Override
    public BoardDTO get(Long bno) {

        Object result = repository.getBoardByBno(bno);
        Object[] arr =(Object[]) result;

        return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) { //삭제 기능 구현 ,트랜잭션 추가

        //댓글 부터 삭제
        replyRepository.deleteByBno(bno);

        repository.deleteById(bno);

    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = repository.getOne(boardDTO.getBno());

        if(board != null) {
            board.changeTitle(boardDTO.getTitle());
            board.changeContent(boardDTO.getContent());

            repository.save(board);
        }

    }
}
