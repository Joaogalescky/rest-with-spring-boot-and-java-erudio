package com.joaogalescky;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // ResponseBody + Controller
public class MathController {

	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {

		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new Exception();
		}

		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	private boolean isNumeric(String strNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	private Double convertToDouble(String strNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}
