package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.entity.Board;

public interface MemberRepository extends JpaRepository<Board, Long> {
}
