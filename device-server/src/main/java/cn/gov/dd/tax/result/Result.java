package cn.gov.dd.tax.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
	private String status = "OK";
	private int successCount = 0;
	private int failedCount = 0;
	private T obj = null;
	private HttpStatus httpStatus = HttpStatus.OK;
}
