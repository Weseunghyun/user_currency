package com.sparta.currency_user.user.dto;

import com.sparta.currency_user.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Size(min = 2, max = 5, message = "이름은 2자 이상 5자 이하여야 합니다.")
    @NotBlank(message = "이름은 필수 값입니다.")
    private String name;

    @Pattern(
        regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
        message = "이메일의 형식에 맞지 않습니다 xxx@xxx.com 형식으로 입력해주세요"
    )
    @NotBlank(message = "이메일은 필수값 입니다.")
    private String email;

    public User toEntity() {
        return new User(
            this.name,
            this.email
        );
    }
}
