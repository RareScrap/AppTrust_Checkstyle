package com.apptrust.javadoc;

import com.puppycrawl.tools.checkstyle.api.*;

public class LinesAfterJavadocCheck extends AbstractCheck {
    /**
     * Returns the default token a check is interested in. Only used if the
     * configuration for a check does not define the tokens.
     *
     * @return the default tokens
     * @see TokenTypes
     */
    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }

    /**
     * The configurable token set.
     * Used to protect Checks against malicious users who specify an
     * unacceptable token set in the configuration file.
     * The default implementation returns the check's default tokens.
     *
     * @return the token set this check is designed for.
     * @see TokenTypes
     */
    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }

    /**
     * The tokens that this check must be registered for.
     *
     * @return the token set this must be registered for.
     * @see TokenTypes
     */
    @Override
    public int[] getRequiredTokens() {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        DetailAST mainToken;
        switch (ast.getType()) {
            case TokenTypes.CLASS_DEF:
                mainToken = ast.findFirstToken(TokenTypes.LITERAL_CLASS);
                break;
            case TokenTypes.INTERFACE_DEF:
                mainToken = ast.findFirstToken(TokenTypes.LITERAL_INTERFACE);
                break;
            default:
                return;
        }

        String stringAbove = getLine(mainToken.getLineNo()-2);
        if (!stringAbove.contains("*/")) {
            log(mainToken.getLineNo(),"Javadoc missing or between class/interface definition and javadoc there is empty line(s)");
        }

    }
}