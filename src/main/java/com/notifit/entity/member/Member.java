package com.notifit.entity.member;

import com.notifit.exception.CustomException;
import com.notifit.exception.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String name;
    private String phoneNumber;

    private Member(String username, String password, String name, String phoneNumber) {

        if (username == null || username.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_USERNAME);
        }

        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static Member of(String username, String password, String name, String phoneNumber) {
        return new Member(
                username,
                password,
                name,
                phoneNumber);
    }
}
