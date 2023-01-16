package com.muca.web.service;

import org.springframework.stereotype.Service;

import com.muca.web.entity.MemberEntity;
import com.muca.web.repository.MemberCustomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberCustomRepository memberRepository;

    public MemberEntity findByName(String name) {
        return memberRepository.findByName(name);
    }

}
