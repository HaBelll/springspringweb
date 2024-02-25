package com.hajong.springspringweb.config.auth.dto;

import com.hajong.springspringweb.domain.user.User;
import lombok.Getter;
import org.springframework.asm.SpringAsmInfo;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
