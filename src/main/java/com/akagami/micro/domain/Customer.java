package com.akagami.micro.domain;

import java.text.AttributedString;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private int  id;
	@NonNull  private String name;
}
