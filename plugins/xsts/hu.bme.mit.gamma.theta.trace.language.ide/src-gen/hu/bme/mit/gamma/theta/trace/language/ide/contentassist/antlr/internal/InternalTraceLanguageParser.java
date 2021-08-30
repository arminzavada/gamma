package hu.bme.mit.gamma.theta.trace.language.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import hu.bme.mit.gamma.theta.trace.language.services.TraceLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTraceLanguageParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_SIMPLESTRING", "RULE_INT", "RULE_SIMPLECHAR", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'(XstsStateSequence'", "')'", "'(XstsState'", "'(ExplState'", "'('"
    };
    public static final int RULE_ID=7;
    public static final int RULE_SIMPLECHAR=6;
    public static final int RULE_WS=11;
    public static final int RULE_STRING=8;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int RULE_INT=5;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_SIMPLESTRING=4;

    // delegates
    // delegators


        public InternalTraceLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTraceLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTraceLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "InternalTraceLanguage.g"; }


    	private TraceLanguageGrammarAccess grammarAccess;

    	public void setGrammarAccess(TraceLanguageGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleXstsStateSequence"
    // InternalTraceLanguage.g:53:1: entryRuleXstsStateSequence : ruleXstsStateSequence EOF ;
    public final void entryRuleXstsStateSequence() throws RecognitionException {
        try {
            // InternalTraceLanguage.g:54:1: ( ruleXstsStateSequence EOF )
            // InternalTraceLanguage.g:55:1: ruleXstsStateSequence EOF
            {
             before(grammarAccess.getXstsStateSequenceRule()); 
            pushFollow(FOLLOW_1);
            ruleXstsStateSequence();

            state._fsp--;

             after(grammarAccess.getXstsStateSequenceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleXstsStateSequence"


    // $ANTLR start "ruleXstsStateSequence"
    // InternalTraceLanguage.g:62:1: ruleXstsStateSequence : ( ( rule__XstsStateSequence__Group__0 ) ) ;
    public final void ruleXstsStateSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:66:2: ( ( ( rule__XstsStateSequence__Group__0 ) ) )
            // InternalTraceLanguage.g:67:2: ( ( rule__XstsStateSequence__Group__0 ) )
            {
            // InternalTraceLanguage.g:67:2: ( ( rule__XstsStateSequence__Group__0 ) )
            // InternalTraceLanguage.g:68:3: ( rule__XstsStateSequence__Group__0 )
            {
             before(grammarAccess.getXstsStateSequenceAccess().getGroup()); 
            // InternalTraceLanguage.g:69:3: ( rule__XstsStateSequence__Group__0 )
            // InternalTraceLanguage.g:69:4: rule__XstsStateSequence__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__XstsStateSequence__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getXstsStateSequenceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXstsStateSequence"


    // $ANTLR start "entryRuleXstsState"
    // InternalTraceLanguage.g:78:1: entryRuleXstsState : ruleXstsState EOF ;
    public final void entryRuleXstsState() throws RecognitionException {
        try {
            // InternalTraceLanguage.g:79:1: ( ruleXstsState EOF )
            // InternalTraceLanguage.g:80:1: ruleXstsState EOF
            {
             before(grammarAccess.getXstsStateRule()); 
            pushFollow(FOLLOW_1);
            ruleXstsState();

            state._fsp--;

             after(grammarAccess.getXstsStateRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleXstsState"


    // $ANTLR start "ruleXstsState"
    // InternalTraceLanguage.g:87:1: ruleXstsState : ( ( rule__XstsState__Group__0 ) ) ;
    public final void ruleXstsState() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:91:2: ( ( ( rule__XstsState__Group__0 ) ) )
            // InternalTraceLanguage.g:92:2: ( ( rule__XstsState__Group__0 ) )
            {
            // InternalTraceLanguage.g:92:2: ( ( rule__XstsState__Group__0 ) )
            // InternalTraceLanguage.g:93:3: ( rule__XstsState__Group__0 )
            {
             before(grammarAccess.getXstsStateAccess().getGroup()); 
            // InternalTraceLanguage.g:94:3: ( rule__XstsState__Group__0 )
            // InternalTraceLanguage.g:94:4: rule__XstsState__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__XstsState__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getXstsStateAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXstsState"


    // $ANTLR start "entryRuleExplState"
    // InternalTraceLanguage.g:103:1: entryRuleExplState : ruleExplState EOF ;
    public final void entryRuleExplState() throws RecognitionException {
        try {
            // InternalTraceLanguage.g:104:1: ( ruleExplState EOF )
            // InternalTraceLanguage.g:105:1: ruleExplState EOF
            {
             before(grammarAccess.getExplStateRule()); 
            pushFollow(FOLLOW_1);
            ruleExplState();

            state._fsp--;

             after(grammarAccess.getExplStateRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExplState"


    // $ANTLR start "ruleExplState"
    // InternalTraceLanguage.g:112:1: ruleExplState : ( ( rule__ExplState__Group__0 ) ) ;
    public final void ruleExplState() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:116:2: ( ( ( rule__ExplState__Group__0 ) ) )
            // InternalTraceLanguage.g:117:2: ( ( rule__ExplState__Group__0 ) )
            {
            // InternalTraceLanguage.g:117:2: ( ( rule__ExplState__Group__0 ) )
            // InternalTraceLanguage.g:118:3: ( rule__ExplState__Group__0 )
            {
             before(grammarAccess.getExplStateAccess().getGroup()); 
            // InternalTraceLanguage.g:119:3: ( rule__ExplState__Group__0 )
            // InternalTraceLanguage.g:119:4: rule__ExplState__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ExplState__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExplStateAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExplState"


    // $ANTLR start "entryRuleVariableValuation"
    // InternalTraceLanguage.g:128:1: entryRuleVariableValuation : ruleVariableValuation EOF ;
    public final void entryRuleVariableValuation() throws RecognitionException {
        try {
            // InternalTraceLanguage.g:129:1: ( ruleVariableValuation EOF )
            // InternalTraceLanguage.g:130:1: ruleVariableValuation EOF
            {
             before(grammarAccess.getVariableValuationRule()); 
            pushFollow(FOLLOW_1);
            ruleVariableValuation();

            state._fsp--;

             after(grammarAccess.getVariableValuationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVariableValuation"


    // $ANTLR start "ruleVariableValuation"
    // InternalTraceLanguage.g:137:1: ruleVariableValuation : ( ( rule__VariableValuation__Group__0 ) ) ;
    public final void ruleVariableValuation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:141:2: ( ( ( rule__VariableValuation__Group__0 ) ) )
            // InternalTraceLanguage.g:142:2: ( ( rule__VariableValuation__Group__0 ) )
            {
            // InternalTraceLanguage.g:142:2: ( ( rule__VariableValuation__Group__0 ) )
            // InternalTraceLanguage.g:143:3: ( rule__VariableValuation__Group__0 )
            {
             before(grammarAccess.getVariableValuationAccess().getGroup()); 
            // InternalTraceLanguage.g:144:3: ( rule__VariableValuation__Group__0 )
            // InternalTraceLanguage.g:144:4: rule__VariableValuation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VariableValuation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVariableValuationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVariableValuation"


    // $ANTLR start "entryRuleValue"
    // InternalTraceLanguage.g:153:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalTraceLanguage.g:154:1: ( ruleValue EOF )
            // InternalTraceLanguage.g:155:1: ruleValue EOF
            {
             before(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getValueRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalTraceLanguage.g:162:1: ruleValue : ( ( rule__Value__Alternatives ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:166:2: ( ( ( rule__Value__Alternatives ) ) )
            // InternalTraceLanguage.g:167:2: ( ( rule__Value__Alternatives ) )
            {
            // InternalTraceLanguage.g:167:2: ( ( rule__Value__Alternatives ) )
            // InternalTraceLanguage.g:168:3: ( rule__Value__Alternatives )
            {
             before(grammarAccess.getValueAccess().getAlternatives()); 
            // InternalTraceLanguage.g:169:3: ( rule__Value__Alternatives )
            // InternalTraceLanguage.g:169:4: rule__Value__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Value__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "rule__Value__Alternatives"
    // InternalTraceLanguage.g:177:1: rule__Value__Alternatives : ( ( RULE_SIMPLESTRING ) | ( RULE_INT ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:181:1: ( ( RULE_SIMPLESTRING ) | ( RULE_INT ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_SIMPLESTRING) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_INT) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalTraceLanguage.g:182:2: ( RULE_SIMPLESTRING )
                    {
                    // InternalTraceLanguage.g:182:2: ( RULE_SIMPLESTRING )
                    // InternalTraceLanguage.g:183:3: RULE_SIMPLESTRING
                    {
                     before(grammarAccess.getValueAccess().getSIMPLESTRINGTerminalRuleCall_0()); 
                    match(input,RULE_SIMPLESTRING,FOLLOW_2); 
                     after(grammarAccess.getValueAccess().getSIMPLESTRINGTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalTraceLanguage.g:188:2: ( RULE_INT )
                    {
                    // InternalTraceLanguage.g:188:2: ( RULE_INT )
                    // InternalTraceLanguage.g:189:3: RULE_INT
                    {
                     before(grammarAccess.getValueAccess().getINTTerminalRuleCall_1()); 
                    match(input,RULE_INT,FOLLOW_2); 
                     after(grammarAccess.getValueAccess().getINTTerminalRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Alternatives"


    // $ANTLR start "rule__XstsStateSequence__Group__0"
    // InternalTraceLanguage.g:198:1: rule__XstsStateSequence__Group__0 : rule__XstsStateSequence__Group__0__Impl rule__XstsStateSequence__Group__1 ;
    public final void rule__XstsStateSequence__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:202:1: ( rule__XstsStateSequence__Group__0__Impl rule__XstsStateSequence__Group__1 )
            // InternalTraceLanguage.g:203:2: rule__XstsStateSequence__Group__0__Impl rule__XstsStateSequence__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__XstsStateSequence__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__XstsStateSequence__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__0"


    // $ANTLR start "rule__XstsStateSequence__Group__0__Impl"
    // InternalTraceLanguage.g:210:1: rule__XstsStateSequence__Group__0__Impl : ( () ) ;
    public final void rule__XstsStateSequence__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:214:1: ( ( () ) )
            // InternalTraceLanguage.g:215:1: ( () )
            {
            // InternalTraceLanguage.g:215:1: ( () )
            // InternalTraceLanguage.g:216:2: ()
            {
             before(grammarAccess.getXstsStateSequenceAccess().getXstsStateSequenceAction_0()); 
            // InternalTraceLanguage.g:217:2: ()
            // InternalTraceLanguage.g:217:3: 
            {
            }

             after(grammarAccess.getXstsStateSequenceAccess().getXstsStateSequenceAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__0__Impl"


    // $ANTLR start "rule__XstsStateSequence__Group__1"
    // InternalTraceLanguage.g:225:1: rule__XstsStateSequence__Group__1 : rule__XstsStateSequence__Group__1__Impl rule__XstsStateSequence__Group__2 ;
    public final void rule__XstsStateSequence__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:229:1: ( rule__XstsStateSequence__Group__1__Impl rule__XstsStateSequence__Group__2 )
            // InternalTraceLanguage.g:230:2: rule__XstsStateSequence__Group__1__Impl rule__XstsStateSequence__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__XstsStateSequence__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__XstsStateSequence__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__1"


    // $ANTLR start "rule__XstsStateSequence__Group__1__Impl"
    // InternalTraceLanguage.g:237:1: rule__XstsStateSequence__Group__1__Impl : ( '(XstsStateSequence' ) ;
    public final void rule__XstsStateSequence__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:241:1: ( ( '(XstsStateSequence' ) )
            // InternalTraceLanguage.g:242:1: ( '(XstsStateSequence' )
            {
            // InternalTraceLanguage.g:242:1: ( '(XstsStateSequence' )
            // InternalTraceLanguage.g:243:2: '(XstsStateSequence'
            {
             before(grammarAccess.getXstsStateSequenceAccess().getXstsStateSequenceKeyword_1()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getXstsStateSequenceAccess().getXstsStateSequenceKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__1__Impl"


    // $ANTLR start "rule__XstsStateSequence__Group__2"
    // InternalTraceLanguage.g:252:1: rule__XstsStateSequence__Group__2 : rule__XstsStateSequence__Group__2__Impl rule__XstsStateSequence__Group__3 ;
    public final void rule__XstsStateSequence__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:256:1: ( rule__XstsStateSequence__Group__2__Impl rule__XstsStateSequence__Group__3 )
            // InternalTraceLanguage.g:257:2: rule__XstsStateSequence__Group__2__Impl rule__XstsStateSequence__Group__3
            {
            pushFollow(FOLLOW_4);
            rule__XstsStateSequence__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__XstsStateSequence__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__2"


    // $ANTLR start "rule__XstsStateSequence__Group__2__Impl"
    // InternalTraceLanguage.g:264:1: rule__XstsStateSequence__Group__2__Impl : ( ( rule__XstsStateSequence__StatesAssignment_2 )* ) ;
    public final void rule__XstsStateSequence__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:268:1: ( ( ( rule__XstsStateSequence__StatesAssignment_2 )* ) )
            // InternalTraceLanguage.g:269:1: ( ( rule__XstsStateSequence__StatesAssignment_2 )* )
            {
            // InternalTraceLanguage.g:269:1: ( ( rule__XstsStateSequence__StatesAssignment_2 )* )
            // InternalTraceLanguage.g:270:2: ( rule__XstsStateSequence__StatesAssignment_2 )*
            {
             before(grammarAccess.getXstsStateSequenceAccess().getStatesAssignment_2()); 
            // InternalTraceLanguage.g:271:2: ( rule__XstsStateSequence__StatesAssignment_2 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==15) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTraceLanguage.g:271:3: rule__XstsStateSequence__StatesAssignment_2
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__XstsStateSequence__StatesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getXstsStateSequenceAccess().getStatesAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__2__Impl"


    // $ANTLR start "rule__XstsStateSequence__Group__3"
    // InternalTraceLanguage.g:279:1: rule__XstsStateSequence__Group__3 : rule__XstsStateSequence__Group__3__Impl ;
    public final void rule__XstsStateSequence__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:283:1: ( rule__XstsStateSequence__Group__3__Impl )
            // InternalTraceLanguage.g:284:2: rule__XstsStateSequence__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XstsStateSequence__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__3"


    // $ANTLR start "rule__XstsStateSequence__Group__3__Impl"
    // InternalTraceLanguage.g:290:1: rule__XstsStateSequence__Group__3__Impl : ( ')' ) ;
    public final void rule__XstsStateSequence__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:294:1: ( ( ')' ) )
            // InternalTraceLanguage.g:295:1: ( ')' )
            {
            // InternalTraceLanguage.g:295:1: ( ')' )
            // InternalTraceLanguage.g:296:2: ')'
            {
             before(grammarAccess.getXstsStateSequenceAccess().getRightParenthesisKeyword_3()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getXstsStateSequenceAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__Group__3__Impl"


    // $ANTLR start "rule__XstsState__Group__0"
    // InternalTraceLanguage.g:306:1: rule__XstsState__Group__0 : rule__XstsState__Group__0__Impl rule__XstsState__Group__1 ;
    public final void rule__XstsState__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:310:1: ( rule__XstsState__Group__0__Impl rule__XstsState__Group__1 )
            // InternalTraceLanguage.g:311:2: rule__XstsState__Group__0__Impl rule__XstsState__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__XstsState__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__XstsState__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__0"


    // $ANTLR start "rule__XstsState__Group__0__Impl"
    // InternalTraceLanguage.g:318:1: rule__XstsState__Group__0__Impl : ( '(XstsState' ) ;
    public final void rule__XstsState__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:322:1: ( ( '(XstsState' ) )
            // InternalTraceLanguage.g:323:1: ( '(XstsState' )
            {
            // InternalTraceLanguage.g:323:1: ( '(XstsState' )
            // InternalTraceLanguage.g:324:2: '(XstsState'
            {
             before(grammarAccess.getXstsStateAccess().getXstsStateKeyword_0()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getXstsStateAccess().getXstsStateKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__0__Impl"


    // $ANTLR start "rule__XstsState__Group__1"
    // InternalTraceLanguage.g:333:1: rule__XstsState__Group__1 : rule__XstsState__Group__1__Impl rule__XstsState__Group__2 ;
    public final void rule__XstsState__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:337:1: ( rule__XstsState__Group__1__Impl rule__XstsState__Group__2 )
            // InternalTraceLanguage.g:338:2: rule__XstsState__Group__1__Impl rule__XstsState__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__XstsState__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__XstsState__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__1"


    // $ANTLR start "rule__XstsState__Group__1__Impl"
    // InternalTraceLanguage.g:345:1: rule__XstsState__Group__1__Impl : ( ( rule__XstsState__AnnotationsAssignment_1 )* ) ;
    public final void rule__XstsState__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:349:1: ( ( ( rule__XstsState__AnnotationsAssignment_1 )* ) )
            // InternalTraceLanguage.g:350:1: ( ( rule__XstsState__AnnotationsAssignment_1 )* )
            {
            // InternalTraceLanguage.g:350:1: ( ( rule__XstsState__AnnotationsAssignment_1 )* )
            // InternalTraceLanguage.g:351:2: ( rule__XstsState__AnnotationsAssignment_1 )*
            {
             before(grammarAccess.getXstsStateAccess().getAnnotationsAssignment_1()); 
            // InternalTraceLanguage.g:352:2: ( rule__XstsState__AnnotationsAssignment_1 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_SIMPLESTRING) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalTraceLanguage.g:352:3: rule__XstsState__AnnotationsAssignment_1
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__XstsState__AnnotationsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getXstsStateAccess().getAnnotationsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__1__Impl"


    // $ANTLR start "rule__XstsState__Group__2"
    // InternalTraceLanguage.g:360:1: rule__XstsState__Group__2 : rule__XstsState__Group__2__Impl rule__XstsState__Group__3 ;
    public final void rule__XstsState__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:364:1: ( rule__XstsState__Group__2__Impl rule__XstsState__Group__3 )
            // InternalTraceLanguage.g:365:2: rule__XstsState__Group__2__Impl rule__XstsState__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__XstsState__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__XstsState__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__2"


    // $ANTLR start "rule__XstsState__Group__2__Impl"
    // InternalTraceLanguage.g:372:1: rule__XstsState__Group__2__Impl : ( ( rule__XstsState__StateAssignment_2 ) ) ;
    public final void rule__XstsState__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:376:1: ( ( ( rule__XstsState__StateAssignment_2 ) ) )
            // InternalTraceLanguage.g:377:1: ( ( rule__XstsState__StateAssignment_2 ) )
            {
            // InternalTraceLanguage.g:377:1: ( ( rule__XstsState__StateAssignment_2 ) )
            // InternalTraceLanguage.g:378:2: ( rule__XstsState__StateAssignment_2 )
            {
             before(grammarAccess.getXstsStateAccess().getStateAssignment_2()); 
            // InternalTraceLanguage.g:379:2: ( rule__XstsState__StateAssignment_2 )
            // InternalTraceLanguage.g:379:3: rule__XstsState__StateAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__XstsState__StateAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getXstsStateAccess().getStateAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__2__Impl"


    // $ANTLR start "rule__XstsState__Group__3"
    // InternalTraceLanguage.g:387:1: rule__XstsState__Group__3 : rule__XstsState__Group__3__Impl ;
    public final void rule__XstsState__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:391:1: ( rule__XstsState__Group__3__Impl )
            // InternalTraceLanguage.g:392:2: rule__XstsState__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__XstsState__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__3"


    // $ANTLR start "rule__XstsState__Group__3__Impl"
    // InternalTraceLanguage.g:398:1: rule__XstsState__Group__3__Impl : ( ')' ) ;
    public final void rule__XstsState__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:402:1: ( ( ')' ) )
            // InternalTraceLanguage.g:403:1: ( ')' )
            {
            // InternalTraceLanguage.g:403:1: ( ')' )
            // InternalTraceLanguage.g:404:2: ')'
            {
             before(grammarAccess.getXstsStateAccess().getRightParenthesisKeyword_3()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getXstsStateAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__Group__3__Impl"


    // $ANTLR start "rule__ExplState__Group__0"
    // InternalTraceLanguage.g:414:1: rule__ExplState__Group__0 : rule__ExplState__Group__0__Impl rule__ExplState__Group__1 ;
    public final void rule__ExplState__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:418:1: ( rule__ExplState__Group__0__Impl rule__ExplState__Group__1 )
            // InternalTraceLanguage.g:419:2: rule__ExplState__Group__0__Impl rule__ExplState__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__ExplState__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExplState__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__0"


    // $ANTLR start "rule__ExplState__Group__0__Impl"
    // InternalTraceLanguage.g:426:1: rule__ExplState__Group__0__Impl : ( () ) ;
    public final void rule__ExplState__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:430:1: ( ( () ) )
            // InternalTraceLanguage.g:431:1: ( () )
            {
            // InternalTraceLanguage.g:431:1: ( () )
            // InternalTraceLanguage.g:432:2: ()
            {
             before(grammarAccess.getExplStateAccess().getExplStateAction_0()); 
            // InternalTraceLanguage.g:433:2: ()
            // InternalTraceLanguage.g:433:3: 
            {
            }

             after(grammarAccess.getExplStateAccess().getExplStateAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__0__Impl"


    // $ANTLR start "rule__ExplState__Group__1"
    // InternalTraceLanguage.g:441:1: rule__ExplState__Group__1 : rule__ExplState__Group__1__Impl rule__ExplState__Group__2 ;
    public final void rule__ExplState__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:445:1: ( rule__ExplState__Group__1__Impl rule__ExplState__Group__2 )
            // InternalTraceLanguage.g:446:2: rule__ExplState__Group__1__Impl rule__ExplState__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__ExplState__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExplState__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__1"


    // $ANTLR start "rule__ExplState__Group__1__Impl"
    // InternalTraceLanguage.g:453:1: rule__ExplState__Group__1__Impl : ( '(ExplState' ) ;
    public final void rule__ExplState__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:457:1: ( ( '(ExplState' ) )
            // InternalTraceLanguage.g:458:1: ( '(ExplState' )
            {
            // InternalTraceLanguage.g:458:1: ( '(ExplState' )
            // InternalTraceLanguage.g:459:2: '(ExplState'
            {
             before(grammarAccess.getExplStateAccess().getExplStateKeyword_1()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getExplStateAccess().getExplStateKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__1__Impl"


    // $ANTLR start "rule__ExplState__Group__2"
    // InternalTraceLanguage.g:468:1: rule__ExplState__Group__2 : rule__ExplState__Group__2__Impl rule__ExplState__Group__3 ;
    public final void rule__ExplState__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:472:1: ( rule__ExplState__Group__2__Impl rule__ExplState__Group__3 )
            // InternalTraceLanguage.g:473:2: rule__ExplState__Group__2__Impl rule__ExplState__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__ExplState__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ExplState__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__2"


    // $ANTLR start "rule__ExplState__Group__2__Impl"
    // InternalTraceLanguage.g:480:1: rule__ExplState__Group__2__Impl : ( ( rule__ExplState__ValuationsAssignment_2 )* ) ;
    public final void rule__ExplState__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:484:1: ( ( ( rule__ExplState__ValuationsAssignment_2 )* ) )
            // InternalTraceLanguage.g:485:1: ( ( rule__ExplState__ValuationsAssignment_2 )* )
            {
            // InternalTraceLanguage.g:485:1: ( ( rule__ExplState__ValuationsAssignment_2 )* )
            // InternalTraceLanguage.g:486:2: ( rule__ExplState__ValuationsAssignment_2 )*
            {
             before(grammarAccess.getExplStateAccess().getValuationsAssignment_2()); 
            // InternalTraceLanguage.g:487:2: ( rule__ExplState__ValuationsAssignment_2 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalTraceLanguage.g:487:3: rule__ExplState__ValuationsAssignment_2
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ExplState__ValuationsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getExplStateAccess().getValuationsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__2__Impl"


    // $ANTLR start "rule__ExplState__Group__3"
    // InternalTraceLanguage.g:495:1: rule__ExplState__Group__3 : rule__ExplState__Group__3__Impl ;
    public final void rule__ExplState__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:499:1: ( rule__ExplState__Group__3__Impl )
            // InternalTraceLanguage.g:500:2: rule__ExplState__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ExplState__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__3"


    // $ANTLR start "rule__ExplState__Group__3__Impl"
    // InternalTraceLanguage.g:506:1: rule__ExplState__Group__3__Impl : ( ')' ) ;
    public final void rule__ExplState__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:510:1: ( ( ')' ) )
            // InternalTraceLanguage.g:511:1: ( ')' )
            {
            // InternalTraceLanguage.g:511:1: ( ')' )
            // InternalTraceLanguage.g:512:2: ')'
            {
             before(grammarAccess.getExplStateAccess().getRightParenthesisKeyword_3()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getExplStateAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__Group__3__Impl"


    // $ANTLR start "rule__VariableValuation__Group__0"
    // InternalTraceLanguage.g:522:1: rule__VariableValuation__Group__0 : rule__VariableValuation__Group__0__Impl rule__VariableValuation__Group__1 ;
    public final void rule__VariableValuation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:526:1: ( rule__VariableValuation__Group__0__Impl rule__VariableValuation__Group__1 )
            // InternalTraceLanguage.g:527:2: rule__VariableValuation__Group__0__Impl rule__VariableValuation__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__VariableValuation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VariableValuation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__0"


    // $ANTLR start "rule__VariableValuation__Group__0__Impl"
    // InternalTraceLanguage.g:534:1: rule__VariableValuation__Group__0__Impl : ( '(' ) ;
    public final void rule__VariableValuation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:538:1: ( ( '(' ) )
            // InternalTraceLanguage.g:539:1: ( '(' )
            {
            // InternalTraceLanguage.g:539:1: ( '(' )
            // InternalTraceLanguage.g:540:2: '('
            {
             before(grammarAccess.getVariableValuationAccess().getLeftParenthesisKeyword_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getVariableValuationAccess().getLeftParenthesisKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__0__Impl"


    // $ANTLR start "rule__VariableValuation__Group__1"
    // InternalTraceLanguage.g:549:1: rule__VariableValuation__Group__1 : rule__VariableValuation__Group__1__Impl rule__VariableValuation__Group__2 ;
    public final void rule__VariableValuation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:553:1: ( rule__VariableValuation__Group__1__Impl rule__VariableValuation__Group__2 )
            // InternalTraceLanguage.g:554:2: rule__VariableValuation__Group__1__Impl rule__VariableValuation__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__VariableValuation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VariableValuation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__1"


    // $ANTLR start "rule__VariableValuation__Group__1__Impl"
    // InternalTraceLanguage.g:561:1: rule__VariableValuation__Group__1__Impl : ( ( rule__VariableValuation__NameAssignment_1 ) ) ;
    public final void rule__VariableValuation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:565:1: ( ( ( rule__VariableValuation__NameAssignment_1 ) ) )
            // InternalTraceLanguage.g:566:1: ( ( rule__VariableValuation__NameAssignment_1 ) )
            {
            // InternalTraceLanguage.g:566:1: ( ( rule__VariableValuation__NameAssignment_1 ) )
            // InternalTraceLanguage.g:567:2: ( rule__VariableValuation__NameAssignment_1 )
            {
             before(grammarAccess.getVariableValuationAccess().getNameAssignment_1()); 
            // InternalTraceLanguage.g:568:2: ( rule__VariableValuation__NameAssignment_1 )
            // InternalTraceLanguage.g:568:3: rule__VariableValuation__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__VariableValuation__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVariableValuationAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__1__Impl"


    // $ANTLR start "rule__VariableValuation__Group__2"
    // InternalTraceLanguage.g:576:1: rule__VariableValuation__Group__2 : rule__VariableValuation__Group__2__Impl rule__VariableValuation__Group__3 ;
    public final void rule__VariableValuation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:580:1: ( rule__VariableValuation__Group__2__Impl rule__VariableValuation__Group__3 )
            // InternalTraceLanguage.g:581:2: rule__VariableValuation__Group__2__Impl rule__VariableValuation__Group__3
            {
            pushFollow(FOLLOW_8);
            rule__VariableValuation__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VariableValuation__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__2"


    // $ANTLR start "rule__VariableValuation__Group__2__Impl"
    // InternalTraceLanguage.g:588:1: rule__VariableValuation__Group__2__Impl : ( ( rule__VariableValuation__ValueAssignment_2 ) ) ;
    public final void rule__VariableValuation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:592:1: ( ( ( rule__VariableValuation__ValueAssignment_2 ) ) )
            // InternalTraceLanguage.g:593:1: ( ( rule__VariableValuation__ValueAssignment_2 ) )
            {
            // InternalTraceLanguage.g:593:1: ( ( rule__VariableValuation__ValueAssignment_2 ) )
            // InternalTraceLanguage.g:594:2: ( rule__VariableValuation__ValueAssignment_2 )
            {
             before(grammarAccess.getVariableValuationAccess().getValueAssignment_2()); 
            // InternalTraceLanguage.g:595:2: ( rule__VariableValuation__ValueAssignment_2 )
            // InternalTraceLanguage.g:595:3: rule__VariableValuation__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__VariableValuation__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVariableValuationAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__2__Impl"


    // $ANTLR start "rule__VariableValuation__Group__3"
    // InternalTraceLanguage.g:603:1: rule__VariableValuation__Group__3 : rule__VariableValuation__Group__3__Impl ;
    public final void rule__VariableValuation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:607:1: ( rule__VariableValuation__Group__3__Impl )
            // InternalTraceLanguage.g:608:2: rule__VariableValuation__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VariableValuation__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__3"


    // $ANTLR start "rule__VariableValuation__Group__3__Impl"
    // InternalTraceLanguage.g:614:1: rule__VariableValuation__Group__3__Impl : ( ')' ) ;
    public final void rule__VariableValuation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:618:1: ( ( ')' ) )
            // InternalTraceLanguage.g:619:1: ( ')' )
            {
            // InternalTraceLanguage.g:619:1: ( ')' )
            // InternalTraceLanguage.g:620:2: ')'
            {
             before(grammarAccess.getVariableValuationAccess().getRightParenthesisKeyword_3()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getVariableValuationAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__Group__3__Impl"


    // $ANTLR start "rule__XstsStateSequence__StatesAssignment_2"
    // InternalTraceLanguage.g:630:1: rule__XstsStateSequence__StatesAssignment_2 : ( ruleXstsState ) ;
    public final void rule__XstsStateSequence__StatesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:634:1: ( ( ruleXstsState ) )
            // InternalTraceLanguage.g:635:2: ( ruleXstsState )
            {
            // InternalTraceLanguage.g:635:2: ( ruleXstsState )
            // InternalTraceLanguage.g:636:3: ruleXstsState
            {
             before(grammarAccess.getXstsStateSequenceAccess().getStatesXstsStateParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleXstsState();

            state._fsp--;

             after(grammarAccess.getXstsStateSequenceAccess().getStatesXstsStateParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsStateSequence__StatesAssignment_2"


    // $ANTLR start "rule__XstsState__AnnotationsAssignment_1"
    // InternalTraceLanguage.g:645:1: rule__XstsState__AnnotationsAssignment_1 : ( RULE_SIMPLESTRING ) ;
    public final void rule__XstsState__AnnotationsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:649:1: ( ( RULE_SIMPLESTRING ) )
            // InternalTraceLanguage.g:650:2: ( RULE_SIMPLESTRING )
            {
            // InternalTraceLanguage.g:650:2: ( RULE_SIMPLESTRING )
            // InternalTraceLanguage.g:651:3: RULE_SIMPLESTRING
            {
             before(grammarAccess.getXstsStateAccess().getAnnotationsSIMPLESTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_SIMPLESTRING,FOLLOW_2); 
             after(grammarAccess.getXstsStateAccess().getAnnotationsSIMPLESTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__AnnotationsAssignment_1"


    // $ANTLR start "rule__XstsState__StateAssignment_2"
    // InternalTraceLanguage.g:660:1: rule__XstsState__StateAssignment_2 : ( ruleExplState ) ;
    public final void rule__XstsState__StateAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:664:1: ( ( ruleExplState ) )
            // InternalTraceLanguage.g:665:2: ( ruleExplState )
            {
            // InternalTraceLanguage.g:665:2: ( ruleExplState )
            // InternalTraceLanguage.g:666:3: ruleExplState
            {
             before(grammarAccess.getXstsStateAccess().getStateExplStateParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExplState();

            state._fsp--;

             after(grammarAccess.getXstsStateAccess().getStateExplStateParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__XstsState__StateAssignment_2"


    // $ANTLR start "rule__ExplState__ValuationsAssignment_2"
    // InternalTraceLanguage.g:675:1: rule__ExplState__ValuationsAssignment_2 : ( ruleVariableValuation ) ;
    public final void rule__ExplState__ValuationsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:679:1: ( ( ruleVariableValuation ) )
            // InternalTraceLanguage.g:680:2: ( ruleVariableValuation )
            {
            // InternalTraceLanguage.g:680:2: ( ruleVariableValuation )
            // InternalTraceLanguage.g:681:3: ruleVariableValuation
            {
             before(grammarAccess.getExplStateAccess().getValuationsVariableValuationParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleVariableValuation();

            state._fsp--;

             after(grammarAccess.getExplStateAccess().getValuationsVariableValuationParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ExplState__ValuationsAssignment_2"


    // $ANTLR start "rule__VariableValuation__NameAssignment_1"
    // InternalTraceLanguage.g:690:1: rule__VariableValuation__NameAssignment_1 : ( RULE_SIMPLESTRING ) ;
    public final void rule__VariableValuation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:694:1: ( ( RULE_SIMPLESTRING ) )
            // InternalTraceLanguage.g:695:2: ( RULE_SIMPLESTRING )
            {
            // InternalTraceLanguage.g:695:2: ( RULE_SIMPLESTRING )
            // InternalTraceLanguage.g:696:3: RULE_SIMPLESTRING
            {
             before(grammarAccess.getVariableValuationAccess().getNameSIMPLESTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_SIMPLESTRING,FOLLOW_2); 
             after(grammarAccess.getVariableValuationAccess().getNameSIMPLESTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__NameAssignment_1"


    // $ANTLR start "rule__VariableValuation__ValueAssignment_2"
    // InternalTraceLanguage.g:705:1: rule__VariableValuation__ValueAssignment_2 : ( ruleValue ) ;
    public final void rule__VariableValuation__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalTraceLanguage.g:709:1: ( ( ruleValue ) )
            // InternalTraceLanguage.g:710:2: ( ruleValue )
            {
            // InternalTraceLanguage.g:710:2: ( ruleValue )
            // InternalTraceLanguage.g:711:3: ruleValue
            {
             before(grammarAccess.getVariableValuationAccess().getValueValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getVariableValuationAccess().getValueValueParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VariableValuation__ValueAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000024000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000030L});

}