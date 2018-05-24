package de.claudioaltamura.jetty.jersey.superheroes.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Errors implements Serializable {

	private List<Error> errors = new ArrayList<>();
	
	public void add(Error error) {
		errors.add(error);
	}	
	
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

}
