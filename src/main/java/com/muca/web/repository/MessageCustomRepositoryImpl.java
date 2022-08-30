package com.muca.web.repository;

import static com.muca.web.entity.QMessageEntity.messageEntity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.muca.web.entity.MessageEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MessageCustomRepositoryImpl implements MessageCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MessageEntity> findByCreatedDate(LocalDateTime lastDateTime) {
        return queryFactory.selectFrom(messageEntity).where(afterDateTime(lastDateTime)).fetch();
    }

    private BooleanExpression afterDateTime(LocalDateTime lastDateTime) {
        if (lastDateTime == null) {
            return null;
        }
        return messageEntity.created.after(lastDateTime);
    }

}
