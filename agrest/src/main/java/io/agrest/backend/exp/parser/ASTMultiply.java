/* Generated By:JJTree: Do not edit this line. ASTMultiply.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTMultiply extends SimpleNode {
    public ASTMultiply(int id) {
        super(id);
    }

    public ASTMultiply() {
        super(ExpressionParserTreeConstants.JJTMULTIPLY);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTMultiply(id);
    }
}
/* JavaCC - OriginalChecksum=208a9154f49abd2a3aec3a5c27b7ad5a (do not edit this line) */
