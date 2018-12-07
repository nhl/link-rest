/* Generated By:JJTree: Do not edit this line. ASTNotLike.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

/**
 * "Not Like" expression.
 *
 */
public class ASTNotLike extends PatternMatchNode {

    ASTNotLike(int id) {
        super(id, false);
    }

    public ASTNotLike() {
        super(ExpressionParserTreeConstants.JJTNOTLIKE, false);
    }

    public ASTNotLike(SimpleNode path, Object value) {
        super(ExpressionParserTreeConstants.JJTNOTLIKE, false);
        jjtAddChild(path, 0);
        jjtAddChild(new ASTScalar(value), 1);
        connectChildren();
    }

    public ASTNotLike(SimpleNode path, Object value, char escapeChar) {
        super(ExpressionParserTreeConstants.JJTNOTLIKE, false, escapeChar);
        jjtAddChild(path, 0);
        jjtAddChild(new ASTScalar(value), 1);
        connectChildren();
    }

    @Override
    public Expression shallowCopy() {
        return new ASTNotLike(id);
    }

    @Override
    public int getType() {
        return Expression.NOT_LIKE;
    }

}
/* JavaCC - OriginalChecksum=8a6933c77d10dca5246bd01ecd53abcf (do not edit this line) */
