package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pin_details")
public class PinDetails {
	@Id
	@NotNull
	@Min(value=100000,message="Need Greater than 100000")
	@Max(value=999999,message="Need less than 999999")
	private int pincode;
	@NotBlank(message="Enter value town")
	private String town;
	@NotBlank(message="Enter value District")
	private String district;
	@NotBlank(message="Enter value Country")
	private String country;
	@NotBlank(message="Enter value State")
	private String state;
		

}
