package apidispatcher;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Test {
	
	
	public static void main(String[] args) {
		ExpressionParser expressionParser = new SpelExpressionParser();
		expressionParser.parseExpression("{'name','Nikola'}");
	}
}
