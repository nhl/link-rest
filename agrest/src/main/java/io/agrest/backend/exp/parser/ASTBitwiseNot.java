/* Generated By:JJTree: Do not edit this line. ASTBitwiseNot.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.agrest.backend.exp.parser;

import io.agrest.backend.exp.Expression;

public class ASTBitwiseNot extends SimpleNode {
    public ASTBitwiseNot(int id) {
        super(id);
    }

    public ASTBitwiseNot() {
        super(ExpressionParserTreeConstants.JJTBITWISENOT);
    }

    @Override
    public Expression shallowCopy() {
        return new ASTBitwiseNot(id);
    }
}
/* JavaCC - OriginalChecksum=b5dc5fa3a045576ad0704bd27af05a91 (do not edit this line) */
