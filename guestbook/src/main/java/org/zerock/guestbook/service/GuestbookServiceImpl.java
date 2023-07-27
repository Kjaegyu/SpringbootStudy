package org.zerock.guestbook.service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.entity.Guestbook;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService{

    @Override
    public Long register(GuestbookDTO dto) {

        log.info("DTO-----------------------------");
        log.info(dto);

        Guestbook entity = dtoToentity(dto);

        log.info(entity);

        return null;
    }
}
