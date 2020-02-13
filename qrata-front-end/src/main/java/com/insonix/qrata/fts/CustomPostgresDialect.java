package com.insonix.qrata.fts;

import org.hibernate.dialect.PostgreSQLDialect;

/**
 * @author Gurminder Singh
 *
 * @date 28-Jun-2013
 */
public class CustomPostgresDialect extends PostgreSQLDialect {

	public CustomPostgresDialect() {
		registerFunction("fts", new PostgreSQLFullTextSearchFunction());
	}
	
}
