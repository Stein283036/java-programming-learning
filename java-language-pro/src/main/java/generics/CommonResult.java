package generics;

/**
 * @author njl
 * @date 2023/1/29
 */
public class CommonResult<T> /* 泛型类 */ {
	private T data;
	private boolean success;
	private Integer code;

	public static <T> CommonResult<T> error(CommonResult<?> result) {
		return error(result.getCode());
	}

	public static <T> CommonResult<T> error(Integer code) {
		CommonResult<T> error = new CommonResult<>();
		error.setSuccess(false);
		error.setCode(code);
		return error;
	}

	public static <T> CommonResult<T> success(T data) {
		CommonResult<T> result = new CommonResult<>();
		result.setData(data);
		result.setSuccess(true);
		result.setCode(200);
		return result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "CommonResult{" +
				"data=" + data +
				", success=" + success +
				", code=" + code +
				'}';
	}
}
