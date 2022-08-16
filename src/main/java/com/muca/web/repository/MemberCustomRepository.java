package com.muca.web.repository;

import com.muca.web.entity.MemberEntity;

public interface MemberCustomRepository {
    MemberEntity findByName(String name);
}
