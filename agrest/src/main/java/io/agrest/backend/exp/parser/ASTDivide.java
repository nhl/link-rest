/* Generated By:JJTree: Do not edit this line. ASTDivide.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTDivide extends SimpleNode {
    public ASTDivide(int id) {
        super(id);
    }

    public ASTDivide() {
        super(ExpressionParserTreeConstants.JJTDIVIDE);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTDivide(id);
    }
}
/* JavaCC - OriginalChecksum=bae2c1100fc01cc38d21f2281435038c (do not edit this line) */
