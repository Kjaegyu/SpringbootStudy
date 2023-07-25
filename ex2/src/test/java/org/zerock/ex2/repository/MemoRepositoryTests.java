package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.ex2.entity.Memo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testInsertDummies(){

        IntStream.rangeClosed(1,100).forEach(i -> {
            Memo memo = Memo.builder().memoText("sample..."+i).build();
           memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect2(){

        //데이터베이스에 존재하는 mno
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("===================================");

        if(result.isPresent()){
            Memo memo = result.get();
            System.out.println(memo);
        }
    }
    @Test
    public void testPageDefault() {
        //1페이지 10개
        Pageable pageable = PageRequest.of(0,10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("--------------------------------------");
        System.out.println("Total Pages: "+result.getTotalPages()); //총 몇 페이지
        System.out.println("Total Count: "+result.getTotalElements()); //현재 페이지 번호 0부터 시작
        System.out.println("Page Size: " + result.getSize()); //페이지당 데이터 개수
        System.out.println("has next page?: "+result.hasNext()); //시작 페이지(0) 여부

    }

    @Test
    public void testQueryMethods() {

        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L,80L);

        for(Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageble() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(10L,50L,pageable);
        result.get().forEach(memo -> System.out.println(memo));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {

        memoRepository.deleteMemoByMnoLessThan(10L);
    }
}


