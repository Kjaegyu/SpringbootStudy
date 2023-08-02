package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
