package com.muca.web.repository;

import org.springframework.stereotype.Repository;

import com.muca.web.entity.MemberEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.muca.web.entity.QMemberEntity.memberEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberEntity findByName(String name) {
        return jpaQueryFactory.selectFrom(memberEntity).where(eqName(name)).fetchOne();
    }

    private BooleanExpression eqName(String name) {
        return memberEntity.name.eq(name);
    }

}
