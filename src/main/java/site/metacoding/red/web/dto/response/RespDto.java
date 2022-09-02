package site.metacoding.red.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class RespDto<T> {
	private Integer code; // 1 정상, -1 실패
	private String msg; // 통신에 대한 결과 메시지 담기
	private T body;
}
