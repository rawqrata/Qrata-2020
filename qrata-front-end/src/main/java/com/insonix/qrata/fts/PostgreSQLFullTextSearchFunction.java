package com.insonix.qrata.fts;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;

/**
 * @author Gurminder Singh
 *
 * @date 28-Jun-2013
 */
public class PostgreSQLFullTextSearchFunction implements SQLFunction {

	@Override
	public Type getReturnType(Type columnType, Mapping mapping)
			throws QueryException {
		return new BooleanType();
	}

	@Override
	public boolean hasArguments() {
		return true;
	}

	@Override
	public boolean hasParenthesesIfNoArguments() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String render(List args, SessionFactoryImplementor factory)
			throws QueryException {
		  if (args!= null && args.size() < 2) {
	         throw new IllegalArgumentException(
	               "The function must be passed 2 arguments");
	      }

		  String fragment = null;
		  String ftsConfig = null;
	      String field = null;
	      String value = null;
		  if(args.size() == 3) {
		      ftsConfig = (String) args.get(0);
		      field = (String) args.get(1);
		      value = (String) args.get(2);
		      fragment = field+" @@ to_tsquery("+ftsConfig+", "+value+")";
		  } else {
			  field = (String) args.get(0);
		      value = (String) args.get(1);
		      fragment = field+" @@ to_tsquery("+value+")";
		  }

	      return fragment;
	}

}
