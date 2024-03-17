package com.ecommerce.userservice.models;
import com.ecommerce.userservice.models.SessionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;


import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Session extends BaseModel{
    private Date expiresAt;
    @ManyToOne
    private User user;
    private String token;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;

}
