package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDto {
	@JsonProperty("email")
	@Email(message = "Invalid email")
	@Size(max = 100, message = "Email size cannot be more than 100 characters")
	@ApiModelProperty(notes = "Email address", example = "john.smith@example.com")
	private String email;

	@JsonProperty("password")
	@ApiModelProperty(notes = "Password", example = "pass")
	private String password;

	@JsonProperty("channel_partner_id")
	@Positive(message = "Id should be positive")
	@ApiModelProperty(notes = "Id of channel partner", example = "1")
	private Integer channelPartnerId;
}
