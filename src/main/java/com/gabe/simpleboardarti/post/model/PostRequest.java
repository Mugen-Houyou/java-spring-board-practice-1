package com.gabe.simpleboardarti.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value= PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequest {
    @NotBlank
    @Size(min=4, max=32)
    private String userName;
    @NotBlank
    @Size(min=4, max=32)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=2, max=99)
    private String title;

    @NotBlank
    private String content;
}
