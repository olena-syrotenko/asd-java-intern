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
public class PartyDto {
	@JsonProperty("id")
	@Positive(message = "Id should be positive")
	@ApiModelProperty(notes = "Party id", example = "1")
	private Integer id;

	@JsonProperty("name")
	@Size(max = 45, message = "Name size cannot be more than 45 characters")
	@ApiModelProperty(notes = "Name of party", example = "test party")
	private String name;

	@JsonProperty("state")
	@Size(max = 15, message = "State size cannot be more than 15 characters")
	@ApiModelProperty(notes = "Party state", example = "Created")
	private String state;

	@JsonProperty("postal_address")
	@Size(max = 500, message = "Postal address size cannot be more than 500 characters")
	@ApiModelProperty(notes = "Postal address of party", example = "Mr John Smith. 132, My Street, Bigtown BG23 4YZ")
	private String postalAddress;

	@JsonProperty("email_address")
	@Email(message = "Invalid email")
	@Size(max = 100, message = "Email size cannot be more than 100 characters")
	@ApiModelProperty(notes = "Email address of party", example = "john.smith@example.com")
	private String emailAddress;

	@JsonProperty("mobile_phone")
	@Size(max = 30, message = "Mobile phone size cannot be more than 30 characters")
	@ApiModelProperty(notes = "Mobile phone number of party", example = "(555) 555-1234")
	private String mobilePhone;

	@JsonProperty("password")
	@Size(max = 225, message = "Password size cannot be more than 225 characters")
	@ApiModelProperty(notes = "Hashed password", example = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824")
	private String password;

	@JsonProperty("currency")
	@Size(max = 3, message = "Currency size cannot be more than 3 characters")
	@ApiModelProperty(notes = "Default currency for usage", example = "USD")
	private String currency;

	@JsonProperty("user_type")
	@Size(max = 15, message = "User type size cannot be more than 15 characters")
	@ApiModelProperty(notes = "User type", example = "Customer")
	private String userType;
}
