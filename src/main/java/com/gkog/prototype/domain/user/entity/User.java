package com.gkog.prototype.domain.user.entity;

import com.gkog.prototype.domain.user.enums.ProviderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname", nullable = false)
    private String nickName;

    @Column(name = "img_url")
//    @ColumnDefault("")
    private String profileImgUrl;


    @Column(name = "provider_type")
    private String providerType;

    @PrePersist
    void prePersist() {
        this.providerType = this.providerType == null ? ProviderType.APPLE.getName() : this.providerType;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User user = (User) obj;
        return this.nickName.equals(user.getNickName());
    }
}
