package com.jzwbxx.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {

	/**
	 * 类型
	 */
	public enum Type {

		/**
		 * 成功
		 */
		success,

		/**
		 * 错误
		 */
		error
	}

	public Message() {
		super();
	}

	public Message(Type type, String code, String content, Object data) {
		super();
		this.type = type;
		this.code = code;
		this.content = content;
		this.data = data;
	}

	/**
	 * 返回成功消息
	 *
	 * @return
	 */
	public static Message success() {
		return new Message(Type.success, "2", "成功", null);
	}

	/**
	 * 返回成功消息
	 *
	 * @param content
	 * @return
	 */
	public static Message success(String content) {
		return new Message(Type.success, "2", content, null);
	}

	/**
	 * 返回成功消息
	 *
	 * @param content
	 * @param data
	 * @return
	 */
	public static Message success(String content, Object data) {
		return new Message(Type.success, "2", content, data);
	}

	/**
	 * 返回成功消息
	 *
	 * @param content
	 * @param key
	 * @param value
	 * @return
	 */
	public static Message success(String content, String key, Object value) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(key, value);
		return new Message(Type.success, "2", content, data);
	}

	/**
	 * 返回错误消息
	 *
	 * @return
	 */
	public static Message error() {
		return new Message(Type.error, "3", "失败", null);
	}

	/**
	 * 返回其他错误消息
	 *
	 * @param content
	 * @param data
	 * @return
	 */
	public static Message error(String content, Object data) {
		return new Message(Type.error, "3", content, data);
	}

	/**
	 * 返回其他错误消息
	 *
	 * @param content
	 * @return
	 */
	public static Message error(String content) {
		return new Message(Type.error, "3", content, null);
	}

	private Type type;

	private String code;  //0：未登录，1：未注册，2：请求成功，3：其他错误

	private String content;

	private Object data;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ApiMessage [type=" + type + ", code=" + code + ", content=" + content + ", data=" + data + "]";
	}
}
