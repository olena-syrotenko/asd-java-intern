package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyDto {
	@JsonProperty("id")
	@Positive(message = "Id should be positive")
	private Integer id;

	@JsonProperty("name")
	@NotBlank(message = "Name is mandatory")
	@Size(max = 45, message = "Name size cannot be more than 45 characters")
	private String name;

	@JsonProperty("state")
	@Size(max = 15, message = "State size cannot be more than 15 characters")
	private String state;

	@JsonProperty("postal_address")
	@Size(max = 500, message = "Postal address size cannot be more than 500 characters")
	private String postalAddress;

	@JsonProperty("email_address")
	@Email(message = "Invalid email")
	@Size(max = 100, message = "Email size cannot be more than 100 characters")
	private String emailAddress;

	@JsonProperty("mobile_phone")
	@Size(max = 30, message = "Mobile phone size cannot be more than 30 characters")
	private String mobilePhone;

	@JsonProperty("password")
	@Size(max = 225, message = "Password size cannot be more than 225 characters")
	private String password;

	@JsonProperty("currency")
	@Size(max = 3, message = "Currency size cannot be more than 3 characters")
	private String currency;

	@JsonProperty("user_type")
	@Size(max = 15, message = "User type size cannot be more than 15 characters")
	private String userType;
}
