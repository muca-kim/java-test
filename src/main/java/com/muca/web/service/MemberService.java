package com.muca.web.service;

import org.springframework.stereotype.Service;

import com.muca.web.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

}
