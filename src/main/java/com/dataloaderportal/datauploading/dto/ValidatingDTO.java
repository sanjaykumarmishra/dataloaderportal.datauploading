package com.dataloaderportal.datauploading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidatingDTO {
	
	
	private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}
	
	
   

}
