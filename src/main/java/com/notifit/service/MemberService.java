package com.notifit.service;

import com.notifit.controller.dtos.member.JoinRequest;
import com.notifit.entity.member.Member;
import com.notifit.exception.CustomException;
import com.notifit.exception.ErrorCode;
import com.notifit.repository.MemberRepository;
import com.notifit.service.utils.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(JoinRequest request) {

        boolean existsByUsername = memberRepository.existsByUsername(request.getUsername());
        if (existsByUsername) {
            throw new CustomException(ErrorCode.DUPLICATED_USERNAME);
        }

        Member member = convertRequestToEntity(request);
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    private Member convertRequestToEntity(JoinRequest request) {
        return Member.of(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getPhoneNumber(),
                request.isTermOfUseCheck(),
                request.isPrivacyCheck()
        );
    }
}
