package org.zerock.board.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.QBoard;
import org.zerock.board.entity.QMember;
import org.zerock.board.entity.QReply;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }
    @Override
    public Board search1() {

        log.info("search1...........................");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));


        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());


        log.info("-----------------------------");
        log.info(tuple);
        log.info("-----------------------------");

        return null;
    }
}
