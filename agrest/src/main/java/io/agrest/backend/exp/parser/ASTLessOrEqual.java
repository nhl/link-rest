/* Generated By:JJTree: Do not edit this line. ASTLessOrEqual.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

/**
 * "Less than or equal to" expression.
 *
 */
public class ASTLessOrEqual extends ConditionNode {

    /**
     * Constructor used by expression parser. Do not invoke directly.
     */
    ASTLessOrEqual(int id) {
        super(id);
    }

    public ASTLessOrEqual() {
        super(ExpressionParserTreeConstants.JJTLESSOREQUAL);
    }

    public ASTLessOrEqual(SimpleNode path, Object value) {
        super(ExpressionParserTreeConstants.JJTLESSOREQUAL);
        jjtAddChild(path, 0);
        jjtAddChild(new ASTScalar(value), 1);
        connectChildren();
    }

    @Override
    public Expression shallowCopy() {
        return new ASTLessOrEqual(id);
    }

    @Override
    public int getType() {
        return Expression.LESS_THAN_EQUAL_TO;
    }

}
/* JavaCC - OriginalChecksum=5e61943feef38b3eabab385951d8b5f9 (do not edit this line) */
