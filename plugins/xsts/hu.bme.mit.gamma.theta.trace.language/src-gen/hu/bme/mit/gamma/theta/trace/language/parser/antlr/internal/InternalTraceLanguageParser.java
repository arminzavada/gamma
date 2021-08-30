package hu.bme.mit.gamma.theta.trace.language.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import hu.bme.mit.gamma.theta.trace.language.services.TraceLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTraceLanguageParser extends AbstractInternalAntlrParser {
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

        public InternalTraceLanguageParser(TokenStream input, TraceLanguageGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "XstsStateSequence";
       	}

       	@Override
       	protected TraceLanguageGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleXstsStateSequence"
    // InternalTraceLanguage.g:64:1: entryRuleXstsStateSequence returns [EObject current=null] : iv_ruleXstsStateSequence= ruleXstsStateSequence EOF ;
    public final EObject entryRuleXstsStateSequence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXstsStateSequence = null;


        try {
            // InternalTraceLanguage.g:64:58: (iv_ruleXstsStateSequence= ruleXstsStateSequence EOF )
            // InternalTraceLanguage.g:65:2: iv_ruleXstsStateSequence= ruleXstsStateSequence EOF
            {
             newCompositeNode(grammarAccess.getXstsStateSequenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXstsStateSequence=ruleXstsStateSequence();

            state._fsp--;

             current =iv_ruleXstsStateSequence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXstsStateSequence"


    // $ANTLR start "ruleXstsStateSequence"
    // InternalTraceLanguage.g:71:1: ruleXstsStateSequence returns [EObject current=null] : ( () otherlv_1= '(XstsStateSequence' ( (lv_states_2_0= ruleXstsState ) )* otherlv_3= ')' ) ;
    public final EObject ruleXstsStateSequence() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_states_2_0 = null;



        	enterRule();

        try {
            // InternalTraceLanguage.g:77:2: ( ( () otherlv_1= '(XstsStateSequence' ( (lv_states_2_0= ruleXstsState ) )* otherlv_3= ')' ) )
            // InternalTraceLanguage.g:78:2: ( () otherlv_1= '(XstsStateSequence' ( (lv_states_2_0= ruleXstsState ) )* otherlv_3= ')' )
            {
            // InternalTraceLanguage.g:78:2: ( () otherlv_1= '(XstsStateSequence' ( (lv_states_2_0= ruleXstsState ) )* otherlv_3= ')' )
            // InternalTraceLanguage.g:79:3: () otherlv_1= '(XstsStateSequence' ( (lv_states_2_0= ruleXstsState ) )* otherlv_3= ')'
            {
            // InternalTraceLanguage.g:79:3: ()
            // InternalTraceLanguage.g:80:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXstsStateSequenceAccess().getXstsStateSequenceAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,13,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getXstsStateSequenceAccess().getXstsStateSequenceKeyword_1());
            		
            // InternalTraceLanguage.g:90:3: ( (lv_states_2_0= ruleXstsState ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==15) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalTraceLanguage.g:91:4: (lv_states_2_0= ruleXstsState )
            	    {
            	    // InternalTraceLanguage.g:91:4: (lv_states_2_0= ruleXstsState )
            	    // InternalTraceLanguage.g:92:5: lv_states_2_0= ruleXstsState
            	    {

            	    					newCompositeNode(grammarAccess.getXstsStateSequenceAccess().getStatesXstsStateParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_states_2_0=ruleXstsState();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXstsStateSequenceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"states",
            	    						lv_states_2_0,
            	    						"hu.bme.mit.gamma.theta.trace.language.TraceLanguage.XstsState");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            otherlv_3=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getXstsStateSequenceAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXstsStateSequence"


    // $ANTLR start "entryRuleXstsState"
    // InternalTraceLanguage.g:117:1: entryRuleXstsState returns [EObject current=null] : iv_ruleXstsState= ruleXstsState EOF ;
    public final EObject entryRuleXstsState() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXstsState = null;


        try {
            // InternalTraceLanguage.g:117:50: (iv_ruleXstsState= ruleXstsState EOF )
            // InternalTraceLanguage.g:118:2: iv_ruleXstsState= ruleXstsState EOF
            {
             newCompositeNode(grammarAccess.getXstsStateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXstsState=ruleXstsState();

            state._fsp--;

             current =iv_ruleXstsState; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXstsState"


    // $ANTLR start "ruleXstsState"
    // InternalTraceLanguage.g:124:1: ruleXstsState returns [EObject current=null] : (otherlv_0= '(XstsState' ( (lv_annotations_1_0= RULE_SIMPLESTRING ) )* ( (lv_state_2_0= ruleExplState ) ) otherlv_3= ')' ) ;
    public final EObject ruleXstsState() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_annotations_1_0=null;
        Token otherlv_3=null;
        EObject lv_state_2_0 = null;



        	enterRule();

        try {
            // InternalTraceLanguage.g:130:2: ( (otherlv_0= '(XstsState' ( (lv_annotations_1_0= RULE_SIMPLESTRING ) )* ( (lv_state_2_0= ruleExplState ) ) otherlv_3= ')' ) )
            // InternalTraceLanguage.g:131:2: (otherlv_0= '(XstsState' ( (lv_annotations_1_0= RULE_SIMPLESTRING ) )* ( (lv_state_2_0= ruleExplState ) ) otherlv_3= ')' )
            {
            // InternalTraceLanguage.g:131:2: (otherlv_0= '(XstsState' ( (lv_annotations_1_0= RULE_SIMPLESTRING ) )* ( (lv_state_2_0= ruleExplState ) ) otherlv_3= ')' )
            // InternalTraceLanguage.g:132:3: otherlv_0= '(XstsState' ( (lv_annotations_1_0= RULE_SIMPLESTRING ) )* ( (lv_state_2_0= ruleExplState ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getXstsStateAccess().getXstsStateKeyword_0());
            		
            // InternalTraceLanguage.g:136:3: ( (lv_annotations_1_0= RULE_SIMPLESTRING ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_SIMPLESTRING) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalTraceLanguage.g:137:4: (lv_annotations_1_0= RULE_SIMPLESTRING )
            	    {
            	    // InternalTraceLanguage.g:137:4: (lv_annotations_1_0= RULE_SIMPLESTRING )
            	    // InternalTraceLanguage.g:138:5: lv_annotations_1_0= RULE_SIMPLESTRING
            	    {
            	    lv_annotations_1_0=(Token)match(input,RULE_SIMPLESTRING,FOLLOW_4); 

            	    					newLeafNode(lv_annotations_1_0, grammarAccess.getXstsStateAccess().getAnnotationsSIMPLESTRINGTerminalRuleCall_1_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getXstsStateRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"annotations",
            	    						lv_annotations_1_0,
            	    						"hu.bme.mit.gamma.theta.trace.language.TraceLanguage.SIMPLESTRING");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalTraceLanguage.g:154:3: ( (lv_state_2_0= ruleExplState ) )
            // InternalTraceLanguage.g:155:4: (lv_state_2_0= ruleExplState )
            {
            // InternalTraceLanguage.g:155:4: (lv_state_2_0= ruleExplState )
            // InternalTraceLanguage.g:156:5: lv_state_2_0= ruleExplState
            {

            					newCompositeNode(grammarAccess.getXstsStateAccess().getStateExplStateParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_5);
            lv_state_2_0=ruleExplState();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXstsStateRule());
            					}
            					set(
            						current,
            						"state",
            						lv_state_2_0,
            						"hu.bme.mit.gamma.theta.trace.language.TraceLanguage.ExplState");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getXstsStateAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXstsState"


    // $ANTLR start "entryRuleExplState"
    // InternalTraceLanguage.g:181:1: entryRuleExplState returns [EObject current=null] : iv_ruleExplState= ruleExplState EOF ;
    public final EObject entryRuleExplState() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExplState = null;


        try {
            // InternalTraceLanguage.g:181:50: (iv_ruleExplState= ruleExplState EOF )
            // InternalTraceLanguage.g:182:2: iv_ruleExplState= ruleExplState EOF
            {
             newCompositeNode(grammarAccess.getExplStateRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExplState=ruleExplState();

            state._fsp--;

             current =iv_ruleExplState; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExplState"


    // $ANTLR start "ruleExplState"
    // InternalTraceLanguage.g:188:1: ruleExplState returns [EObject current=null] : ( () otherlv_1= '(ExplState' ( (lv_valuations_2_0= ruleVariableValuation ) )* otherlv_3= ')' ) ;
    public final EObject ruleExplState() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_valuations_2_0 = null;



        	enterRule();

        try {
            // InternalTraceLanguage.g:194:2: ( ( () otherlv_1= '(ExplState' ( (lv_valuations_2_0= ruleVariableValuation ) )* otherlv_3= ')' ) )
            // InternalTraceLanguage.g:195:2: ( () otherlv_1= '(ExplState' ( (lv_valuations_2_0= ruleVariableValuation ) )* otherlv_3= ')' )
            {
            // InternalTraceLanguage.g:195:2: ( () otherlv_1= '(ExplState' ( (lv_valuations_2_0= ruleVariableValuation ) )* otherlv_3= ')' )
            // InternalTraceLanguage.g:196:3: () otherlv_1= '(ExplState' ( (lv_valuations_2_0= ruleVariableValuation ) )* otherlv_3= ')'
            {
            // InternalTraceLanguage.g:196:3: ()
            // InternalTraceLanguage.g:197:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExplStateAccess().getExplStateAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,16,FOLLOW_6); 

            			newLeafNode(otherlv_1, grammarAccess.getExplStateAccess().getExplStateKeyword_1());
            		
            // InternalTraceLanguage.g:207:3: ( (lv_valuations_2_0= ruleVariableValuation ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==17) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalTraceLanguage.g:208:4: (lv_valuations_2_0= ruleVariableValuation )
            	    {
            	    // InternalTraceLanguage.g:208:4: (lv_valuations_2_0= ruleVariableValuation )
            	    // InternalTraceLanguage.g:209:5: lv_valuations_2_0= ruleVariableValuation
            	    {

            	    					newCompositeNode(grammarAccess.getExplStateAccess().getValuationsVariableValuationParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_valuations_2_0=ruleVariableValuation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getExplStateRule());
            	    					}
            	    					add(
            	    						current,
            	    						"valuations",
            	    						lv_valuations_2_0,
            	    						"hu.bme.mit.gamma.theta.trace.language.TraceLanguage.VariableValuation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_3=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getExplStateAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExplState"


    // $ANTLR start "entryRuleVariableValuation"
    // InternalTraceLanguage.g:234:1: entryRuleVariableValuation returns [EObject current=null] : iv_ruleVariableValuation= ruleVariableValuation EOF ;
    public final EObject entryRuleVariableValuation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableValuation = null;


        try {
            // InternalTraceLanguage.g:234:58: (iv_ruleVariableValuation= ruleVariableValuation EOF )
            // InternalTraceLanguage.g:235:2: iv_ruleVariableValuation= ruleVariableValuation EOF
            {
             newCompositeNode(grammarAccess.getVariableValuationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableValuation=ruleVariableValuation();

            state._fsp--;

             current =iv_ruleVariableValuation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableValuation"


    // $ANTLR start "ruleVariableValuation"
    // InternalTraceLanguage.g:241:1: ruleVariableValuation returns [EObject current=null] : (otherlv_0= '(' ( (lv_name_1_0= RULE_SIMPLESTRING ) ) ( (lv_value_2_0= ruleValue ) ) otherlv_3= ')' ) ;
    public final EObject ruleVariableValuation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_3=null;
        AntlrDatatypeRuleToken lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalTraceLanguage.g:247:2: ( (otherlv_0= '(' ( (lv_name_1_0= RULE_SIMPLESTRING ) ) ( (lv_value_2_0= ruleValue ) ) otherlv_3= ')' ) )
            // InternalTraceLanguage.g:248:2: (otherlv_0= '(' ( (lv_name_1_0= RULE_SIMPLESTRING ) ) ( (lv_value_2_0= ruleValue ) ) otherlv_3= ')' )
            {
            // InternalTraceLanguage.g:248:2: (otherlv_0= '(' ( (lv_name_1_0= RULE_SIMPLESTRING ) ) ( (lv_value_2_0= ruleValue ) ) otherlv_3= ')' )
            // InternalTraceLanguage.g:249:3: otherlv_0= '(' ( (lv_name_1_0= RULE_SIMPLESTRING ) ) ( (lv_value_2_0= ruleValue ) ) otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getVariableValuationAccess().getLeftParenthesisKeyword_0());
            		
            // InternalTraceLanguage.g:253:3: ( (lv_name_1_0= RULE_SIMPLESTRING ) )
            // InternalTraceLanguage.g:254:4: (lv_name_1_0= RULE_SIMPLESTRING )
            {
            // InternalTraceLanguage.g:254:4: (lv_name_1_0= RULE_SIMPLESTRING )
            // InternalTraceLanguage.g:255:5: lv_name_1_0= RULE_SIMPLESTRING
            {
            lv_name_1_0=(Token)match(input,RULE_SIMPLESTRING,FOLLOW_8); 

            					newLeafNode(lv_name_1_0, grammarAccess.getVariableValuationAccess().getNameSIMPLESTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableValuationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"hu.bme.mit.gamma.theta.trace.language.TraceLanguage.SIMPLESTRING");
            				

            }


            }

            // InternalTraceLanguage.g:271:3: ( (lv_value_2_0= ruleValue ) )
            // InternalTraceLanguage.g:272:4: (lv_value_2_0= ruleValue )
            {
            // InternalTraceLanguage.g:272:4: (lv_value_2_0= ruleValue )
            // InternalTraceLanguage.g:273:5: lv_value_2_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getVariableValuationAccess().getValueValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_5);
            lv_value_2_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariableValuationRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"hu.bme.mit.gamma.theta.trace.language.TraceLanguage.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getVariableValuationAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableValuation"


    // $ANTLR start "entryRuleValue"
    // InternalTraceLanguage.g:298:1: entryRuleValue returns [String current=null] : iv_ruleValue= ruleValue EOF ;
    public final String entryRuleValue() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleValue = null;


        try {
            // InternalTraceLanguage.g:298:45: (iv_ruleValue= ruleValue EOF )
            // InternalTraceLanguage.g:299:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalTraceLanguage.g:305:1: ruleValue returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SIMPLESTRING_0= RULE_SIMPLESTRING | this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleValue() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_SIMPLESTRING_0=null;
        Token this_INT_1=null;


        	enterRule();

        try {
            // InternalTraceLanguage.g:311:2: ( (this_SIMPLESTRING_0= RULE_SIMPLESTRING | this_INT_1= RULE_INT ) )
            // InternalTraceLanguage.g:312:2: (this_SIMPLESTRING_0= RULE_SIMPLESTRING | this_INT_1= RULE_INT )
            {
            // InternalTraceLanguage.g:312:2: (this_SIMPLESTRING_0= RULE_SIMPLESTRING | this_INT_1= RULE_INT )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_SIMPLESTRING) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_INT) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalTraceLanguage.g:313:3: this_SIMPLESTRING_0= RULE_SIMPLESTRING
                    {
                    this_SIMPLESTRING_0=(Token)match(input,RULE_SIMPLESTRING,FOLLOW_2); 

                    			current.merge(this_SIMPLESTRING_0);
                    		

                    			newLeafNode(this_SIMPLESTRING_0, grammarAccess.getValueAccess().getSIMPLESTRINGTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalTraceLanguage.g:321:3: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_2); 

                    			current.merge(this_INT_1);
                    		

                    			newLeafNode(this_INT_1, grammarAccess.getValueAccess().getINTTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValue"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000024000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000030L});

}