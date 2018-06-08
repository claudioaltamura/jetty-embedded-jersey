package de.claudioaltamura.jetty.jersey.superheroes.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class Error implements Serializable {
	
	private int status;
	
	private String source;
	
	private String title;
	
	private String detail;
	

	public static class ErrorBuilder {
		
		private int status;
		
		private String source;
		
		private String title;
		
		private String detail;
	
		public ErrorBuilder status(int status) {
			this.status = status;
			return this;
		}
		
		public ErrorBuilder source(String source) {
			this.source = source;
			return this;
		}
		
		public ErrorBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public ErrorBuilder detail(String detail) {
			this.detail = detail;
			return this;
		}
		
		public Error build() {
			Error error = new Error();
			error.setDetail(detail);
			error.setSource(source);
			error.setStatus(status);
			error.setTitle(title);
			return error;
		}
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Error [status=" + status + ", source=" + source + ", title=" + title + ", detail=" + detail + "]";
	}

}
