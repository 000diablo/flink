// $ANTLR 3.4 /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g 2012-10-25 15:00:18
 
package eu.stratosphere.meteor; 

import eu.stratosphere.sopremo.operator.*;
import eu.stratosphere.sopremo.io.*;
import eu.stratosphere.sopremo.query.*;
import eu.stratosphere.sopremo.pact.*;
import eu.stratosphere.sopremo.expressions.*;
import eu.stratosphere.sopremo.function.*;
import java.math.*;
import java.util.IdentityHashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class MeteorParser extends MeteorParserBase {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "APOSTROPHE", "AS", "COMMENT", "DECIMAL", "DIGIT", "ELSE", "ESC_SEQ", "EXPONENT", "EXPRESSION", "FN", "HEX_DIGIT", "ID", "IF", "IN", "INTEGER", "JAVAUDF", "LOWER_LETTER", "NOT", "OCTAL_ESC", "OPERATOR", "OR", "QUOTATION", "SIGN", "STAR", "STRING", "UINT", "UNICODE_ESC", "UPPER_LETTER", "VAR", "WS", "'!'", "'!='", "'&&'", "'('", "')'", "'+'", "'++'", "','", "'-'", "'--'", "'.'", "'/'", "':'", "';'", "'<'", "'<='", "'='", "'=='", "'>'", "'>='", "'?'", "'?.'", "'['", "']'", "'false'", "'from'", "'null'", "'read'", "'to'", "'true'", "'using'", "'write'", "'{'", "'||'", "'}'", "'~'"
    };

    public static final int EOF=-1;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int AND=4;
    public static final int APOSTROPHE=5;
    public static final int AS=6;
    public static final int COMMENT=7;
    public static final int DECIMAL=8;
    public static final int DIGIT=9;
    public static final int ELSE=10;
    public static final int ESC_SEQ=11;
    public static final int EXPONENT=12;
    public static final int EXPRESSION=13;
    public static final int FN=14;
    public static final int HEX_DIGIT=15;
    public static final int ID=16;
    public static final int IF=17;
    public static final int IN=18;
    public static final int INTEGER=19;
    public static final int JAVAUDF=20;
    public static final int LOWER_LETTER=21;
    public static final int NOT=22;
    public static final int OCTAL_ESC=23;
    public static final int OPERATOR=24;
    public static final int OR=25;
    public static final int QUOTATION=26;
    public static final int SIGN=27;
    public static final int STAR=28;
    public static final int STRING=29;
    public static final int UINT=30;
    public static final int UNICODE_ESC=31;
    public static final int UPPER_LETTER=32;
    public static final int VAR=33;
    public static final int WS=34;

    // delegates
    public MeteorParserBase[] getDelegates() {
        return new MeteorParserBase[] {};
    }

    // delegators


    public MeteorParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public MeteorParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return MeteorParser.tokenNames; }
    public String getGrammarFileName() { return "/home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g"; }


      private Stack<String> paraphrase = new Stack<String>();

      private boolean setInnerOutput(Token VAR, Operator<?> op) {
    	  JsonStreamExpression output = new JsonStreamExpression(((operator_scope)operator_stack.peek()).result.getOutput(((objectCreation_scope)objectCreation_stack.peek()).mappings.size()));
    	  ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.TagMapping(output, new JsonStreamExpression(op)));
    	  getVariableRegistry().getRegistry(1).put(VAR.getText(), output);
    	  return true;
    	}

      public void parseSinks() throws RecognitionException {
        script();
      }
      
      private EvaluationExpression makePath(Token inputVar, String... path) {
          EvaluationExpression selection = getVariable(inputVar).toInputSelection(((operator_scope)operator_stack.peek()).result);
          
          List<EvaluationExpression> accesses = new ArrayList<EvaluationExpression>();
          accesses.add((EvaluationExpression) selection);
          for (String fragment : path)
            accesses.add(new ObjectAccess(fragment));
          return PathExpression.wrapIfNecessary(accesses);
        }


    public static class script_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "script"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:65:1: script : ( statement ';' )+ ->;
    public final MeteorParser.script_return script() throws RecognitionException {
        MeteorParser.script_return retval = new MeteorParser.script_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal2=null;
        MeteorParser.statement_return statement1 =null;


        EvaluationExpression char_literal2_tree=null;
        RewriteRuleTokenStream stream_48=new RewriteRuleTokenStream(adaptor,"token 48");
        RewriteRuleSubtreeStream stream_statement=new RewriteRuleSubtreeStream(adaptor,"rule statement");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:66:2: ( ( statement ';' )+ ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:66:5: ( statement ';' )+
            {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:66:5: ( statement ';' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==ID||LA1_0==VAR||LA1_0==62||(LA1_0 >= 65 && LA1_0 <= 66)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:66:6: statement ';'
            	    {
            	    pushFollow(FOLLOW_statement_in_script121);
            	    statement1=statement();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_statement.add(statement1.getTree());

            	    char_literal2=(Token)match(input,48,FOLLOW_48_in_script123); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_48.add(char_literal2);


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 66:22: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "script"


    public static class statement_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "statement"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:68:1: statement : ( assignment | operator | packageImport | functionDefinition | javaudf ) ->;
    public final MeteorParser.statement_return statement() throws RecognitionException {
        MeteorParser.statement_return retval = new MeteorParser.statement_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.assignment_return assignment3 =null;

        MeteorParser.operator_return operator4 =null;

        MeteorParser.packageImport_return packageImport5 =null;

        MeteorParser.functionDefinition_return functionDefinition6 =null;

        MeteorParser.javaudf_return javaudf7 =null;


        RewriteRuleSubtreeStream stream_assignment=new RewriteRuleSubtreeStream(adaptor,"rule assignment");
        RewriteRuleSubtreeStream stream_functionDefinition=new RewriteRuleSubtreeStream(adaptor,"rule functionDefinition");
        RewriteRuleSubtreeStream stream_javaudf=new RewriteRuleSubtreeStream(adaptor,"rule javaudf");
        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        RewriteRuleSubtreeStream stream_packageImport=new RewriteRuleSubtreeStream(adaptor,"rule packageImport");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:2: ( ( assignment | operator | packageImport | functionDefinition | javaudf ) ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:4: ( assignment | operator | packageImport | functionDefinition | javaudf )
            {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:4: ( assignment | operator | packageImport | functionDefinition | javaudf )
            int alt2=5;
            switch ( input.LA(1) ) {
            case VAR:
                {
                alt2=1;
                }
                break;
            case 62:
            case 66:
                {
                alt2=2;
                }
                break;
            case ID:
                {
                int LA2_3 = input.LA(2);

                if ( (LA2_3==ID||LA2_3==VAR||LA2_3==47||LA2_3==57) ) {
                    alt2=2;
                }
                else if ( (LA2_3==51) ) {
                    int LA2_5 = input.LA(3);

                    if ( (LA2_5==FN) ) {
                        alt2=4;
                    }
                    else if ( (LA2_5==JAVAUDF) ) {
                        alt2=5;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 2, 5, input);

                        throw nvae;

                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 3, input);

                    throw nvae;

                }
                }
                break;
            case 65:
                {
                alt2=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }

            switch (alt2) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:5: assignment
                    {
                    pushFollow(FOLLOW_assignment_in_statement137);
                    assignment3=assignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_assignment.add(assignment3.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:18: operator
                    {
                    pushFollow(FOLLOW_operator_in_statement141);
                    operator4=operator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_operator.add(operator4.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:29: packageImport
                    {
                    pushFollow(FOLLOW_packageImport_in_statement145);
                    packageImport5=packageImport();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_packageImport.add(packageImport5.getTree());

                    }
                    break;
                case 4 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:45: functionDefinition
                    {
                    pushFollow(FOLLOW_functionDefinition_in_statement149);
                    functionDefinition6=functionDefinition();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_functionDefinition.add(functionDefinition6.getTree());

                    }
                    break;
                case 5 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:69:66: javaudf
                    {
                    pushFollow(FOLLOW_javaudf_in_statement153);
                    javaudf7=javaudf();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_javaudf.add(javaudf7.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 69:75: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "statement"


    public static class packageImport_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "packageImport"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:71:1: packageImport : 'using' packageName= ID ( ',' additionalPackage= ID )* ->;
    public final MeteorParser.packageImport_return packageImport() throws RecognitionException {
        MeteorParser.packageImport_return retval = new MeteorParser.packageImport_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token additionalPackage=null;
        Token string_literal8=null;
        Token char_literal9=null;

        EvaluationExpression packageName_tree=null;
        EvaluationExpression additionalPackage_tree=null;
        EvaluationExpression string_literal8_tree=null;
        EvaluationExpression char_literal9_tree=null;
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_65=new RewriteRuleTokenStream(adaptor,"token 65");

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:72:3: ( 'using' packageName= ID ( ',' additionalPackage= ID )* ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:72:6: 'using' packageName= ID ( ',' additionalPackage= ID )*
            {
            string_literal8=(Token)match(input,65,FOLLOW_65_in_packageImport168); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_65.add(string_literal8);


            packageName=(Token)match(input,ID,FOLLOW_ID_in_packageImport172); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(packageName);


            if ( state.backtracking==0 ) { getPackageManager().importPackage((packageName!=null?packageName.getText():null)); }

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:73:6: ( ',' additionalPackage= ID )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==42) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:73:7: ',' additionalPackage= ID
            	    {
            	    char_literal9=(Token)match(input,42,FOLLOW_42_in_packageImport183); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_42.add(char_literal9);


            	    additionalPackage=(Token)match(input,ID,FOLLOW_ID_in_packageImport187); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(additionalPackage);


            	    if ( state.backtracking==0 ) { getPackageManager().importPackage((additionalPackage!=null?additionalPackage.getText():null)); }

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 73:98: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "packageImport"


    public static class assignment_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "assignment"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:75:1: assignment : target= VAR '=' source= operator ->;
    public final MeteorParser.assignment_return assignment() throws RecognitionException {
        MeteorParser.assignment_return retval = new MeteorParser.assignment_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token target=null;
        Token char_literal10=null;
        MeteorParser.operator_return source =null;


        EvaluationExpression target_tree=null;
        EvaluationExpression char_literal10_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:76:2: (target= VAR '=' source= operator ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:76:4: target= VAR '=' source= operator
            {
            target=(Token)match(input,VAR,FOLLOW_VAR_in_assignment204); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(target);


            char_literal10=(Token)match(input,51,FOLLOW_51_in_assignment206); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_51.add(char_literal10);


            pushFollow(FOLLOW_operator_in_assignment210);
            source=operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_operator.add(source.getTree());

            if ( state.backtracking==0 ) { putVariable(target, new JsonStreamExpression((source!=null?source.op:null))); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 76:99: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "assignment"


    public static class functionDefinition_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "functionDefinition"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:78:1: functionDefinition : name= ID '=' FN '(' (param= ID ( ',' param= ID )* )? ')' def= contextAwareExpression[null] ->;
    public final MeteorParser.functionDefinition_return functionDefinition() throws RecognitionException {
        MeteorParser.functionDefinition_return retval = new MeteorParser.functionDefinition_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        Token param=null;
        Token char_literal11=null;
        Token FN12=null;
        Token char_literal13=null;
        Token char_literal14=null;
        Token char_literal15=null;
        MeteorParser.contextAwareExpression_return def =null;


        EvaluationExpression name_tree=null;
        EvaluationExpression param_tree=null;
        EvaluationExpression char_literal11_tree=null;
        EvaluationExpression FN12_tree=null;
        EvaluationExpression char_literal13_tree=null;
        EvaluationExpression char_literal14_tree=null;
        EvaluationExpression char_literal15_tree=null;
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleTokenStream stream_FN=new RewriteRuleTokenStream(adaptor,"token FN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_contextAwareExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwareExpression");
         List<Token> params = new ArrayList(); 
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:80:3: (name= ID '=' FN '(' (param= ID ( ',' param= ID )* )? ')' def= contextAwareExpression[null] ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:80:5: name= ID '=' FN '(' (param= ID ( ',' param= ID )* )? ')' def= contextAwareExpression[null]
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_functionDefinition232); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            char_literal11=(Token)match(input,51,FOLLOW_51_in_functionDefinition234); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_51.add(char_literal11);


            FN12=(Token)match(input,FN,FOLLOW_FN_in_functionDefinition236); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_FN.add(FN12);


            char_literal13=(Token)match(input,38,FOLLOW_38_in_functionDefinition238); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_38.add(char_literal13);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:81:3: (param= ID ( ',' param= ID )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==ID) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:81:4: param= ID ( ',' param= ID )*
                    {
                    param=(Token)match(input,ID,FOLLOW_ID_in_functionDefinition247); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(param);


                    if ( state.backtracking==0 ) { params.add(param); }

                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:82:3: ( ',' param= ID )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==42) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:82:4: ',' param= ID
                    	    {
                    	    char_literal14=(Token)match(input,42,FOLLOW_42_in_functionDefinition254); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_42.add(char_literal14);


                    	    param=(Token)match(input,ID,FOLLOW_ID_in_functionDefinition258); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_ID.add(param);


                    	    if ( state.backtracking==0 ) { params.add(param); }

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }


            char_literal15=(Token)match(input,39,FOLLOW_39_in_functionDefinition269); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal15);


            if ( state.backtracking==0 ) { 
                addConstantScope();
                for(int index = 0; index < params.size(); index++) 
                  this.getConstantRegistry().put(params.get(index).getText(), new InputSelection(index)); 
              }

            pushFollow(FOLLOW_contextAwareExpression_in_functionDefinition281);
            def=contextAwareExpression(null);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_contextAwareExpression.add(def.getTree());

            if ( state.backtracking==0 ) { 
                addFunction(name.getText(), new ExpressionFunction(params.size(), def.tree));
                removeConstantScope(); 
              }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 93:5: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "functionDefinition"


    public static class javaudf_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "javaudf"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:95:1: javaudf : name= ID '=' JAVAUDF '(' path= STRING ')' ->;
    public final MeteorParser.javaudf_return javaudf() throws RecognitionException {
        MeteorParser.javaudf_return retval = new MeteorParser.javaudf_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        Token path=null;
        Token char_literal16=null;
        Token JAVAUDF17=null;
        Token char_literal18=null;
        Token char_literal19=null;

        EvaluationExpression name_tree=null;
        EvaluationExpression path_tree=null;
        EvaluationExpression char_literal16_tree=null;
        EvaluationExpression JAVAUDF17_tree=null;
        EvaluationExpression char_literal18_tree=null;
        EvaluationExpression char_literal19_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
        RewriteRuleTokenStream stream_JAVAUDF=new RewriteRuleTokenStream(adaptor,"token JAVAUDF");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:96:3: (name= ID '=' JAVAUDF '(' path= STRING ')' ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:96:5: name= ID '=' JAVAUDF '(' path= STRING ')'
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_javaudf302); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            char_literal16=(Token)match(input,51,FOLLOW_51_in_javaudf304); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_51.add(char_literal16);


            JAVAUDF17=(Token)match(input,JAVAUDF,FOLLOW_JAVAUDF_in_javaudf306); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_JAVAUDF.add(JAVAUDF17);


            char_literal18=(Token)match(input,38,FOLLOW_38_in_javaudf308); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_38.add(char_literal18);


            path=(Token)match(input,STRING,FOLLOW_STRING_in_javaudf312); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_STRING.add(path);


            char_literal19=(Token)match(input,39,FOLLOW_39_in_javaudf314); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal19);


            if ( state.backtracking==0 ) { addFunction(name.getText(), path.getText()); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 97:53: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "javaudf"


    protected static class contextAwareExpression_scope {
        EvaluationExpression context;
    }
    protected Stack contextAwareExpression_stack = new Stack();


    public static class contextAwareExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "contextAwareExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:99:1: contextAwareExpression[EvaluationExpression contextExpression] : ternaryExpression ;
    public final MeteorParser.contextAwareExpression_return contextAwareExpression(EvaluationExpression contextExpression) throws RecognitionException {
        contextAwareExpression_stack.push(new contextAwareExpression_scope());
        MeteorParser.contextAwareExpression_return retval = new MeteorParser.contextAwareExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.ternaryExpression_return ternaryExpression20 =null;



         ((contextAwareExpression_scope)contextAwareExpression_stack.peek()).context = contextExpression; 
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:102:3: ( ternaryExpression )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:102:5: ternaryExpression
            {
            root_0 = (EvaluationExpression)adaptor.nil();


            pushFollow(FOLLOW_ternaryExpression_in_contextAwareExpression342);
            ternaryExpression20=ternaryExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, ternaryExpression20.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            contextAwareExpression_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "contextAwareExpression"


    public static class expression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:104:1: expression : ( ( operatorExpression )=> operatorExpression | ternaryExpression );
    public final MeteorParser.expression_return expression() throws RecognitionException {
        MeteorParser.expression_return retval = new MeteorParser.expression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.operatorExpression_return operatorExpression21 =null;

        MeteorParser.ternaryExpression_return ternaryExpression22 =null;



        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:105:3: ( ( operatorExpression )=> operatorExpression | ternaryExpression )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==62) && (synpred1_Meteor())) {
                alt6=1;
            }
            else if ( (LA6_0==66) && (synpred1_Meteor())) {
                alt6=1;
            }
            else if ( (LA6_0==ID) ) {
                int LA6_3 = input.LA(2);

                if ( (LA6_3==47) ) {
                    int LA6_5 = input.LA(3);

                    if ( (LA6_5==ID) ) {
                        int LA6_9 = input.LA(4);

                        if ( (LA6_9==AND||LA6_9==AS||(LA6_9 >= IF && LA6_9 <= IN)||LA6_9==NOT||LA6_9==OR||LA6_9==STAR||(LA6_9 >= 36 && LA6_9 <= 40)||(LA6_9 >= 42 && LA6_9 <= 43)||(LA6_9 >= 45 && LA6_9 <= 46)||(LA6_9 >= 49 && LA6_9 <= 50)||(LA6_9 >= 52 && LA6_9 <= 56)||LA6_9==58||(LA6_9 >= 68 && LA6_9 <= 69)) ) {
                            alt6=2;
                        }
                        else if ( (LA6_9==ID) && (synpred1_Meteor())) {
                            alt6=1;
                        }
                        else if ( (LA6_9==57) ) {
                            int LA6_7 = input.LA(5);

                            if ( (LA6_7==VAR) && (synpred1_Meteor())) {
                                alt6=1;
                            }
                            else if ( (LA6_7==INTEGER||LA6_7==STAR||LA6_7==UINT) ) {
                                alt6=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 6, 7, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA6_9==VAR) && (synpred1_Meteor())) {
                            alt6=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 6, 9, input);

                            throw nvae;

                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 5, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_3==AND||LA6_3==AS||(LA6_3 >= IF && LA6_3 <= IN)||LA6_3==NOT||LA6_3==OR||LA6_3==STAR||(LA6_3 >= 36 && LA6_3 <= 40)||(LA6_3 >= 42 && LA6_3 <= 43)||(LA6_3 >= 45 && LA6_3 <= 46)||(LA6_3 >= 49 && LA6_3 <= 50)||(LA6_3 >= 52 && LA6_3 <= 56)||LA6_3==58||(LA6_3 >= 68 && LA6_3 <= 69)) ) {
                    alt6=2;
                }
                else if ( (LA6_3==ID) && (synpred1_Meteor())) {
                    alt6=1;
                }
                else if ( (LA6_3==57) ) {
                    int LA6_7 = input.LA(3);

                    if ( (LA6_7==VAR) && (synpred1_Meteor())) {
                        alt6=1;
                    }
                    else if ( (LA6_7==INTEGER||LA6_7==STAR||LA6_7==UINT) ) {
                        alt6=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 7, input);

                        throw nvae;

                    }
                }
                else if ( (LA6_3==VAR) && (synpred1_Meteor())) {
                    alt6=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 3, input);

                    throw nvae;

                }
            }
            else if ( (LA6_0==DECIMAL||LA6_0==INTEGER||(LA6_0 >= STRING && LA6_0 <= UINT)||LA6_0==VAR||LA6_0==35||LA6_0==38||LA6_0==41||LA6_0==44||LA6_0==57||LA6_0==59||LA6_0==61||LA6_0==64||LA6_0==67||LA6_0==70) ) {
                alt6=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:105:5: ( operatorExpression )=> operatorExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_operatorExpression_in_expression357);
                    operatorExpression21=operatorExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, operatorExpression21.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:106:5: ternaryExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_ternaryExpression_in_expression363);
                    ternaryExpression22=ternaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, ternaryExpression22.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expression"


    public static class ternaryExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "ternaryExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:108:1: ternaryExpression : ( ( orExpression '?' )=>ifClause= orExpression '?' ifExpr= orExpression ':' elseExpr= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause) | ( orExpression IF )=>ifExpr2= orExpression IF ifClause2= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2) | orExpression );
    public final MeteorParser.ternaryExpression_return ternaryExpression() throws RecognitionException {
        MeteorParser.ternaryExpression_return retval = new MeteorParser.ternaryExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal23=null;
        Token char_literal24=null;
        Token IF25=null;
        MeteorParser.orExpression_return ifClause =null;

        MeteorParser.orExpression_return ifExpr =null;

        MeteorParser.orExpression_return elseExpr =null;

        MeteorParser.orExpression_return ifExpr2 =null;

        MeteorParser.orExpression_return ifClause2 =null;

        MeteorParser.orExpression_return orExpression26 =null;


        EvaluationExpression char_literal23_tree=null;
        EvaluationExpression char_literal24_tree=null;
        EvaluationExpression IF25_tree=null;
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_55=new RewriteRuleTokenStream(adaptor,"token 55");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_orExpression=new RewriteRuleSubtreeStream(adaptor,"rule orExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:109:2: ( ( orExpression '?' )=>ifClause= orExpression '?' ifExpr= orExpression ':' elseExpr= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause) | ( orExpression IF )=>ifExpr2= orExpression IF ifClause2= orExpression -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2) | orExpression )
            int alt7=3;
            switch ( input.LA(1) ) {
            case 41:
                {
                int LA7_1 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;

                }
                }
                break;
            case 44:
                {
                int LA7_2 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 2, input);

                    throw nvae;

                }
                }
                break;
            case 35:
            case 70:
                {
                int LA7_3 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 3, input);

                    throw nvae;

                }
                }
                break;
            case 38:
                {
                int LA7_4 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 4, input);

                    throw nvae;

                }
                }
                break;
            case ID:
                {
                int LA7_5 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 5, input);

                    throw nvae;

                }
                }
                break;
            case 64:
                {
                int LA7_6 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 6, input);

                    throw nvae;

                }
                }
                break;
            case 59:
                {
                int LA7_7 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 7, input);

                    throw nvae;

                }
                }
                break;
            case DECIMAL:
                {
                int LA7_8 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 8, input);

                    throw nvae;

                }
                }
                break;
            case STRING:
                {
                int LA7_9 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 9, input);

                    throw nvae;

                }
                }
                break;
            case UINT:
                {
                int LA7_10 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 10, input);

                    throw nvae;

                }
                }
                break;
            case INTEGER:
                {
                int LA7_11 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 11, input);

                    throw nvae;

                }
                }
                break;
            case 61:
                {
                int LA7_12 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 12, input);

                    throw nvae;

                }
                }
                break;
            case VAR:
                {
                int LA7_13 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 13, input);

                    throw nvae;

                }
                }
                break;
            case 57:
                {
                int LA7_14 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 14, input);

                    throw nvae;

                }
                }
                break;
            case 67:
                {
                int LA7_15 = input.LA(2);

                if ( (synpred2_Meteor()) ) {
                    alt7=1;
                }
                else if ( (synpred3_Meteor()) ) {
                    alt7=2;
                }
                else if ( (true) ) {
                    alt7=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 15, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:109:4: ( orExpression '?' )=>ifClause= orExpression '?' ifExpr= orExpression ':' elseExpr= orExpression
                    {
                    pushFollow(FOLLOW_orExpression_in_ternaryExpression381);
                    ifClause=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifClause.getTree());

                    char_literal23=(Token)match(input,55,FOLLOW_55_in_ternaryExpression383); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_55.add(char_literal23);


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression387);
                    ifExpr=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifExpr.getTree());

                    char_literal24=(Token)match(input,47,FOLLOW_47_in_ternaryExpression389); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal24);


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression393);
                    elseExpr=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(elseExpr.getTree());

                    // AST REWRITE
                    // elements: ifClause
                    // token labels: 
                    // rule labels: retval, ifClause
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_ifClause=new RewriteRuleSubtreeStream(adaptor,"rule ifClause",ifClause!=null?ifClause.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 110:2: -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause)
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:110:5: ^( EXPRESSION[\"TernaryExpression\"] $ifClause)
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "TernaryExpression")
                        , root_1);

                        adaptor.addChild(root_1, stream_ifClause.nextTree());

                        adaptor.addChild(root_1,  ifExpr == null ? EvaluationExpression.VALUE : (ifExpr!=null?((EvaluationExpression)ifExpr.tree):null) );

                        adaptor.addChild(root_1,  (elseExpr!=null?((EvaluationExpression)elseExpr.tree):null) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:111:4: ( orExpression IF )=>ifExpr2= orExpression IF ifClause2= orExpression
                    {
                    pushFollow(FOLLOW_orExpression_in_ternaryExpression422);
                    ifExpr2=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifExpr2.getTree());

                    IF25=(Token)match(input,IF,FOLLOW_IF_in_ternaryExpression424); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IF.add(IF25);


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression428);
                    ifClause2=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_orExpression.add(ifClause2.getTree());

                    // AST REWRITE
                    // elements: ifClause2, ifExpr2
                    // token labels: 
                    // rule labels: retval, ifExpr2, ifClause2
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_ifExpr2=new RewriteRuleSubtreeStream(adaptor,"rule ifExpr2",ifExpr2!=null?ifExpr2.tree:null);
                    RewriteRuleSubtreeStream stream_ifClause2=new RewriteRuleSubtreeStream(adaptor,"rule ifClause2",ifClause2!=null?ifClause2.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 112:3: -> ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2)
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:112:6: ^( EXPRESSION[\"TernaryExpression\"] $ifClause2 $ifExpr2)
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "TernaryExpression")
                        , root_1);

                        adaptor.addChild(root_1, stream_ifClause2.nextTree());

                        adaptor.addChild(root_1, stream_ifExpr2.nextTree());

                        adaptor.addChild(root_1,  EvaluationExpression.VALUE );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:113:5: orExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_orExpression_in_ternaryExpression451);
                    orExpression26=orExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, orExpression26.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "ternaryExpression"


    public static class orExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "orExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:115:1: orExpression :exprs+= andExpression ( ( OR | '||' ) exprs+= andExpression )* -> { $exprs.size() == 1 }? ->;
    public final MeteorParser.orExpression_return orExpression() throws RecognitionException {
        MeteorParser.orExpression_return retval = new MeteorParser.orExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token OR27=null;
        Token string_literal28=null;
        List list_exprs=null;
        RuleReturnScope exprs = null;
        EvaluationExpression OR27_tree=null;
        EvaluationExpression string_literal28_tree=null;
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
        RewriteRuleSubtreeStream stream_andExpression=new RewriteRuleSubtreeStream(adaptor,"rule andExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:116:3: (exprs+= andExpression ( ( OR | '||' ) exprs+= andExpression )* -> { $exprs.size() == 1 }? ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:116:5: exprs+= andExpression ( ( OR | '||' ) exprs+= andExpression )*
            {
            pushFollow(FOLLOW_andExpression_in_orExpression464);
            exprs=andExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_andExpression.add(exprs.getTree());
            if (list_exprs==null) list_exprs=new ArrayList();
            list_exprs.add(exprs.getTree());


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:116:26: ( ( OR | '||' ) exprs+= andExpression )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==OR||LA9_0==68) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:116:27: ( OR | '||' ) exprs+= andExpression
            	    {
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:116:27: ( OR | '||' )
            	    int alt8=2;
            	    int LA8_0 = input.LA(1);

            	    if ( (LA8_0==OR) ) {
            	        alt8=1;
            	    }
            	    else if ( (LA8_0==68) ) {
            	        alt8=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 8, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt8) {
            	        case 1 :
            	            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:116:28: OR
            	            {
            	            OR27=(Token)match(input,OR,FOLLOW_OR_in_orExpression468); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_OR.add(OR27);


            	            }
            	            break;
            	        case 2 :
            	            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:116:33: '||'
            	            {
            	            string_literal28=(Token)match(input,68,FOLLOW_68_in_orExpression472); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_68.add(string_literal28);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_andExpression_in_orExpression477);
            	    exprs=andExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_andExpression.add(exprs.getTree());
            	    if (list_exprs==null) list_exprs=new ArrayList();
            	    list_exprs.add(exprs.getTree());


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 117:3: -> { $exprs.size() == 1 }?
            if ( list_exprs.size() == 1 ) {
                adaptor.addChild(root_0,  list_exprs.get(0) );

            }

            else // 118:3: ->
            {
                adaptor.addChild(root_0,  OrExpression.valueOf(list_exprs) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "orExpression"


    public static class andExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "andExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:120:1: andExpression :exprs+= elementExpression ( ( AND | '&&' ) exprs+= elementExpression )* -> { $exprs.size() == 1 }? ->;
    public final MeteorParser.andExpression_return andExpression() throws RecognitionException {
        MeteorParser.andExpression_return retval = new MeteorParser.andExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token AND29=null;
        Token string_literal30=null;
        List list_exprs=null;
        RuleReturnScope exprs = null;
        EvaluationExpression AND29_tree=null;
        EvaluationExpression string_literal30_tree=null;
        RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
        RewriteRuleTokenStream stream_37=new RewriteRuleTokenStream(adaptor,"token 37");
        RewriteRuleSubtreeStream stream_elementExpression=new RewriteRuleSubtreeStream(adaptor,"rule elementExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:121:3: (exprs+= elementExpression ( ( AND | '&&' ) exprs+= elementExpression )* -> { $exprs.size() == 1 }? ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:121:5: exprs+= elementExpression ( ( AND | '&&' ) exprs+= elementExpression )*
            {
            pushFollow(FOLLOW_elementExpression_in_andExpression506);
            exprs=elementExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_elementExpression.add(exprs.getTree());
            if (list_exprs==null) list_exprs=new ArrayList();
            list_exprs.add(exprs.getTree());


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:121:30: ( ( AND | '&&' ) exprs+= elementExpression )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==AND||LA11_0==37) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:121:31: ( AND | '&&' ) exprs+= elementExpression
            	    {
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:121:31: ( AND | '&&' )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==AND) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==37) ) {
            	        alt10=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return retval;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;

            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:121:32: AND
            	            {
            	            AND29=(Token)match(input,AND,FOLLOW_AND_in_andExpression510); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_AND.add(AND29);


            	            }
            	            break;
            	        case 2 :
            	            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:121:38: '&&'
            	            {
            	            string_literal30=(Token)match(input,37,FOLLOW_37_in_andExpression514); if (state.failed) return retval; 
            	            if ( state.backtracking==0 ) stream_37.add(string_literal30);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_elementExpression_in_andExpression519);
            	    exprs=elementExpression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_elementExpression.add(exprs.getTree());
            	    if (list_exprs==null) list_exprs=new ArrayList();
            	    list_exprs.add(exprs.getTree());


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 122:3: -> { $exprs.size() == 1 }?
            if ( list_exprs.size() == 1 ) {
                adaptor.addChild(root_0,  list_exprs.get(0) );

            }

            else // 123:3: ->
            {
                adaptor.addChild(root_0,  AndExpression.valueOf(list_exprs) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "andExpression"


    public static class elementExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "elementExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:125:1: elementExpression : elem= comparisonExpression ( (not= NOT )? IN set= comparisonExpression )? -> { set == null }? $elem -> ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set) ;
    public final MeteorParser.elementExpression_return elementExpression() throws RecognitionException {
        MeteorParser.elementExpression_return retval = new MeteorParser.elementExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token not=null;
        Token IN31=null;
        MeteorParser.comparisonExpression_return elem =null;

        MeteorParser.comparisonExpression_return set =null;


        EvaluationExpression not_tree=null;
        EvaluationExpression IN31_tree=null;
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleSubtreeStream stream_comparisonExpression=new RewriteRuleSubtreeStream(adaptor,"rule comparisonExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:126:2: (elem= comparisonExpression ( (not= NOT )? IN set= comparisonExpression )? -> { set == null }? $elem -> ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set) )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:126:4: elem= comparisonExpression ( (not= NOT )? IN set= comparisonExpression )?
            {
            pushFollow(FOLLOW_comparisonExpression_in_elementExpression548);
            elem=comparisonExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_comparisonExpression.add(elem.getTree());

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:126:30: ( (not= NOT )? IN set= comparisonExpression )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==IN||LA13_0==NOT) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:126:31: (not= NOT )? IN set= comparisonExpression
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:126:34: (not= NOT )?
                    int alt12=2;
                    int LA12_0 = input.LA(1);

                    if ( (LA12_0==NOT) ) {
                        alt12=1;
                    }
                    switch (alt12) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:126:34: not= NOT
                            {
                            not=(Token)match(input,NOT,FOLLOW_NOT_in_elementExpression553); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_NOT.add(not);


                            }
                            break;

                    }


                    IN31=(Token)match(input,IN,FOLLOW_IN_in_elementExpression556); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IN.add(IN31);


                    pushFollow(FOLLOW_comparisonExpression_in_elementExpression560);
                    set=comparisonExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_comparisonExpression.add(set.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: elem, elem, set
            // token labels: 
            // rule labels: elem, retval, set
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_elem=new RewriteRuleSubtreeStream(adaptor,"rule elem",elem!=null?elem.tree:null);
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_set=new RewriteRuleSubtreeStream(adaptor,"rule set",set!=null?set.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 127:2: -> { set == null }? $elem
            if ( set == null ) {
                adaptor.addChild(root_0, stream_elem.nextTree());

            }

            else // 128:2: -> ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set)
            {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:128:5: ^( EXPRESSION[\"ElementInSetExpression\"] $elem $set)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ElementInSetExpression")
                , root_1);

                adaptor.addChild(root_1, stream_elem.nextTree());

                adaptor.addChild(root_1,  not == null ? ElementInSetExpression.Quantor.EXISTS_IN : ElementInSetExpression.Quantor.EXISTS_NOT_IN);

                adaptor.addChild(root_1, stream_set.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "elementExpression"


    public static class comparisonExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "comparisonExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:131:1: comparisonExpression : e1= arithmeticExpression ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )? -> { $s == null }? $e1 -> { $s.getText().equals(\"!=\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> { $s.getText().equals(\"==\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) ;
    public final MeteorParser.comparisonExpression_return comparisonExpression() throws RecognitionException {
        MeteorParser.comparisonExpression_return retval = new MeteorParser.comparisonExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token s=null;
        MeteorParser.arithmeticExpression_return e1 =null;

        MeteorParser.arithmeticExpression_return e2 =null;


        EvaluationExpression s_tree=null;
        RewriteRuleTokenStream stream_49=new RewriteRuleTokenStream(adaptor,"token 49");
        RewriteRuleTokenStream stream_52=new RewriteRuleTokenStream(adaptor,"token 52");
        RewriteRuleTokenStream stream_36=new RewriteRuleTokenStream(adaptor,"token 36");
        RewriteRuleTokenStream stream_53=new RewriteRuleTokenStream(adaptor,"token 53");
        RewriteRuleTokenStream stream_54=new RewriteRuleTokenStream(adaptor,"token 54");
        RewriteRuleTokenStream stream_50=new RewriteRuleTokenStream(adaptor,"token 50");
        RewriteRuleSubtreeStream stream_arithmeticExpression=new RewriteRuleSubtreeStream(adaptor,"rule arithmeticExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:2: (e1= arithmeticExpression ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )? -> { $s == null }? $e1 -> { $s.getText().equals(\"!=\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> { $s.getText().equals(\"==\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) -> ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2) )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:4: e1= arithmeticExpression ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )?
            {
            pushFollow(FOLLOW_arithmeticExpression_in_comparisonExpression601);
            e1=arithmeticExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_arithmeticExpression.add(e1.getTree());

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:28: ( (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==36||(LA15_0 >= 49 && LA15_0 <= 50)||(LA15_0 >= 52 && LA15_0 <= 54)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:29: (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' ) e2= arithmeticExpression
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:29: (s= '<=' |s= '>=' |s= '<' |s= '>' |s= '==' |s= '!=' )
                    int alt14=6;
                    switch ( input.LA(1) ) {
                    case 50:
                        {
                        alt14=1;
                        }
                        break;
                    case 54:
                        {
                        alt14=2;
                        }
                        break;
                    case 49:
                        {
                        alt14=3;
                        }
                        break;
                    case 53:
                        {
                        alt14=4;
                        }
                        break;
                    case 52:
                        {
                        alt14=5;
                        }
                        break;
                    case 36:
                        {
                        alt14=6;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                        throw nvae;

                    }

                    switch (alt14) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:30: s= '<='
                            {
                            s=(Token)match(input,50,FOLLOW_50_in_comparisonExpression607); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_50.add(s);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:39: s= '>='
                            {
                            s=(Token)match(input,54,FOLLOW_54_in_comparisonExpression613); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_54.add(s);


                            }
                            break;
                        case 3 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:48: s= '<'
                            {
                            s=(Token)match(input,49,FOLLOW_49_in_comparisonExpression619); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_49.add(s);


                            }
                            break;
                        case 4 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:56: s= '>'
                            {
                            s=(Token)match(input,53,FOLLOW_53_in_comparisonExpression625); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_53.add(s);


                            }
                            break;
                        case 5 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:64: s= '=='
                            {
                            s=(Token)match(input,52,FOLLOW_52_in_comparisonExpression631); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_52.add(s);


                            }
                            break;
                        case 6 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:132:73: s= '!='
                            {
                            s=(Token)match(input,36,FOLLOW_36_in_comparisonExpression637); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_36.add(s);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_arithmeticExpression_in_comparisonExpression642);
                    e2=arithmeticExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arithmeticExpression.add(e2.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: e2, e1, e2, e1, e2, e1, e1
            // token labels: 
            // rule labels: retval, e1, e2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 133:2: -> { $s == null }? $e1
            if ( s == null ) {
                adaptor.addChild(root_0, stream_e1.nextTree());

            }

            else // 134:3: -> { $s.getText().equals(\"!=\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
            if ( s.getText().equals("!=") ) {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:134:38: ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ComparativeExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1, ComparativeExpression.BinaryOperator.NOT_EQUAL);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 135:3: -> { $s.getText().equals(\"==\") }? ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
            if ( s.getText().equals("==") ) {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:135:38: ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ComparativeExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1, ComparativeExpression.BinaryOperator.EQUAL);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 136:2: -> ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
            {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:136:6: ^( EXPRESSION[\"ComparativeExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ComparativeExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1, ComparativeExpression.BinaryOperator.valueOfSymbol((s!=null?s.getText():null)));

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "comparisonExpression"


    public static class arithmeticExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arithmeticExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:138:1: arithmeticExpression : e1= multiplicationExpression ( (s= '+' |s= '-' ) e2= multiplicationExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1;
    public final MeteorParser.arithmeticExpression_return arithmeticExpression() throws RecognitionException {
        MeteorParser.arithmeticExpression_return retval = new MeteorParser.arithmeticExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token s=null;
        MeteorParser.multiplicationExpression_return e1 =null;

        MeteorParser.multiplicationExpression_return e2 =null;


        EvaluationExpression s_tree=null;
        RewriteRuleTokenStream stream_43=new RewriteRuleTokenStream(adaptor,"token 43");
        RewriteRuleTokenStream stream_40=new RewriteRuleTokenStream(adaptor,"token 40");
        RewriteRuleSubtreeStream stream_multiplicationExpression=new RewriteRuleSubtreeStream(adaptor,"rule multiplicationExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:139:2: (e1= multiplicationExpression ( (s= '+' |s= '-' ) e2= multiplicationExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:139:4: e1= multiplicationExpression ( (s= '+' |s= '-' ) e2= multiplicationExpression )?
            {
            pushFollow(FOLLOW_multiplicationExpression_in_arithmeticExpression722);
            e1=multiplicationExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_multiplicationExpression.add(e1.getTree());

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:139:32: ( (s= '+' |s= '-' ) e2= multiplicationExpression )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==40||LA17_0==43) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:139:33: (s= '+' |s= '-' ) e2= multiplicationExpression
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:139:33: (s= '+' |s= '-' )
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==40) ) {
                        alt16=1;
                    }
                    else if ( (LA16_0==43) ) {
                        alt16=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 0, input);

                        throw nvae;

                    }
                    switch (alt16) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:139:34: s= '+'
                            {
                            s=(Token)match(input,40,FOLLOW_40_in_arithmeticExpression728); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_40.add(s);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:139:42: s= '-'
                            {
                            s=(Token)match(input,43,FOLLOW_43_in_arithmeticExpression734); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_43.add(s);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_multiplicationExpression_in_arithmeticExpression739);
                    e2=multiplicationExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_multiplicationExpression.add(e2.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: e1, e2, e1
            // token labels: 
            // rule labels: retval, e1, e2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 140:2: -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
            if ( s != null ) {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:140:21: ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ArithmeticExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1,  s.getText().equals("+") ? ArithmeticExpression.ArithmeticOperator.ADDITION : ArithmeticExpression.ArithmeticOperator.SUBTRACTION);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 142:2: -> $e1
            {
                adaptor.addChild(root_0, stream_e1.nextTree());

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arithmeticExpression"


    public static class multiplicationExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "multiplicationExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:144:1: multiplicationExpression : e1= preincrementExpression ( (s= '*' |s= '/' ) e2= preincrementExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1;
    public final MeteorParser.multiplicationExpression_return multiplicationExpression() throws RecognitionException {
        MeteorParser.multiplicationExpression_return retval = new MeteorParser.multiplicationExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token s=null;
        MeteorParser.preincrementExpression_return e1 =null;

        MeteorParser.preincrementExpression_return e2 =null;


        EvaluationExpression s_tree=null;
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_46=new RewriteRuleTokenStream(adaptor,"token 46");
        RewriteRuleSubtreeStream stream_preincrementExpression=new RewriteRuleSubtreeStream(adaptor,"rule preincrementExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:145:2: (e1= preincrementExpression ( (s= '*' |s= '/' ) e2= preincrementExpression )? -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2) -> $e1)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:145:4: e1= preincrementExpression ( (s= '*' |s= '/' ) e2= preincrementExpression )?
            {
            pushFollow(FOLLOW_preincrementExpression_in_multiplicationExpression782);
            e1=preincrementExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_preincrementExpression.add(e1.getTree());

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:145:30: ( (s= '*' |s= '/' ) e2= preincrementExpression )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==STAR||LA19_0==46) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:145:31: (s= '*' |s= '/' ) e2= preincrementExpression
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:145:31: (s= '*' |s= '/' )
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==STAR) ) {
                        alt18=1;
                    }
                    else if ( (LA18_0==46) ) {
                        alt18=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 0, input);

                        throw nvae;

                    }
                    switch (alt18) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:145:32: s= '*'
                            {
                            s=(Token)match(input,STAR,FOLLOW_STAR_in_multiplicationExpression788); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STAR.add(s);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:145:40: s= '/'
                            {
                            s=(Token)match(input,46,FOLLOW_46_in_multiplicationExpression794); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_46.add(s);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_preincrementExpression_in_multiplicationExpression799);
                    e2=preincrementExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_preincrementExpression.add(e2.getTree());

                    }
                    break;

            }


            // AST REWRITE
            // elements: e2, e1, e1
            // token labels: 
            // rule labels: retval, e1, e2
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
            RewriteRuleSubtreeStream stream_e1=new RewriteRuleSubtreeStream(adaptor,"rule e1",e1!=null?e1.tree:null);
            RewriteRuleSubtreeStream stream_e2=new RewriteRuleSubtreeStream(adaptor,"rule e2",e2!=null?e2.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 146:2: -> { s != null }? ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
            if ( s != null ) {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:146:21: ^( EXPRESSION[\"ArithmeticExpression\"] $e1 $e2)
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ArithmeticExpression")
                , root_1);

                adaptor.addChild(root_1, stream_e1.nextTree());

                adaptor.addChild(root_1,  s.getText().equals("*") ? ArithmeticExpression.ArithmeticOperator.MULTIPLICATION : ArithmeticExpression.ArithmeticOperator.DIVISION);

                adaptor.addChild(root_1, stream_e2.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }

            else // 148:2: -> $e1
            {
                adaptor.addChild(root_0, stream_e1.nextTree());

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "multiplicationExpression"


    public static class preincrementExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "preincrementExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:150:1: preincrementExpression : ( '++' preincrementExpression | '--' preincrementExpression | unaryExpression );
    public final MeteorParser.preincrementExpression_return preincrementExpression() throws RecognitionException {
        MeteorParser.preincrementExpression_return retval = new MeteorParser.preincrementExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token string_literal32=null;
        Token string_literal34=null;
        MeteorParser.preincrementExpression_return preincrementExpression33 =null;

        MeteorParser.preincrementExpression_return preincrementExpression35 =null;

        MeteorParser.unaryExpression_return unaryExpression36 =null;


        EvaluationExpression string_literal32_tree=null;
        EvaluationExpression string_literal34_tree=null;

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:151:2: ( '++' preincrementExpression | '--' preincrementExpression | unaryExpression )
            int alt20=3;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt20=1;
                }
                break;
            case 44:
                {
                alt20=2;
                }
                break;
            case DECIMAL:
            case ID:
            case INTEGER:
            case STRING:
            case UINT:
            case VAR:
            case 35:
            case 38:
            case 57:
            case 59:
            case 61:
            case 64:
            case 67:
            case 70:
                {
                alt20=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:151:4: '++' preincrementExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    string_literal32=(Token)match(input,41,FOLLOW_41_in_preincrementExpression840); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal32_tree = 
                    (EvaluationExpression)adaptor.create(string_literal32)
                    ;
                    adaptor.addChild(root_0, string_literal32_tree);
                    }

                    pushFollow(FOLLOW_preincrementExpression_in_preincrementExpression842);
                    preincrementExpression33=preincrementExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, preincrementExpression33.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:152:4: '--' preincrementExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    string_literal34=(Token)match(input,44,FOLLOW_44_in_preincrementExpression847); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    string_literal34_tree = 
                    (EvaluationExpression)adaptor.create(string_literal34)
                    ;
                    adaptor.addChild(root_0, string_literal34_tree);
                    }

                    pushFollow(FOLLOW_preincrementExpression_in_preincrementExpression849);
                    preincrementExpression35=preincrementExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, preincrementExpression35.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:153:4: unaryExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_unaryExpression_in_preincrementExpression854);
                    unaryExpression36=unaryExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, unaryExpression36.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "preincrementExpression"


    public static class unaryExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "unaryExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:155:1: unaryExpression : ( '!' | '~' )? castExpression ;
    public final MeteorParser.unaryExpression_return unaryExpression() throws RecognitionException {
        MeteorParser.unaryExpression_return retval = new MeteorParser.unaryExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token set37=null;
        MeteorParser.castExpression_return castExpression38 =null;


        EvaluationExpression set37_tree=null;

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:156:2: ( ( '!' | '~' )? castExpression )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:156:4: ( '!' | '~' )? castExpression
            {
            root_0 = (EvaluationExpression)adaptor.nil();


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:156:4: ( '!' | '~' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==35||LA21_0==70) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:
                    {
                    set37=(Token)input.LT(1);

                    if ( input.LA(1)==35||input.LA(1)==70 ) {
                        input.consume();
                        if ( state.backtracking==0 ) adaptor.addChild(root_0, 
                        (EvaluationExpression)adaptor.create(set37)
                        );
                        state.errorRecovery=false;
                        state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }


            pushFollow(FOLLOW_castExpression_in_unaryExpression873);
            castExpression38=castExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, castExpression38.getTree());

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "unaryExpression"


    public static class castExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "castExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:158:1: castExpression : ( ( '(' ID ')' )=> '(' type= ID ')' expr= generalPathExpression ->| ( generalPathExpression AS )=>expr= generalPathExpression AS type= ID ->| generalPathExpression );
    public final MeteorParser.castExpression_return castExpression() throws RecognitionException {
        MeteorParser.castExpression_return retval = new MeteorParser.castExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token type=null;
        Token char_literal39=null;
        Token char_literal40=null;
        Token AS41=null;
        MeteorParser.generalPathExpression_return expr =null;

        MeteorParser.generalPathExpression_return generalPathExpression42 =null;


        EvaluationExpression type_tree=null;
        EvaluationExpression char_literal39_tree=null;
        EvaluationExpression char_literal40_tree=null;
        EvaluationExpression AS41_tree=null;
        RewriteRuleTokenStream stream_AS=new RewriteRuleTokenStream(adaptor,"token AS");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_generalPathExpression=new RewriteRuleSubtreeStream(adaptor,"rule generalPathExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:159:2: ( ( '(' ID ')' )=> '(' type= ID ')' expr= generalPathExpression ->| ( generalPathExpression AS )=>expr= generalPathExpression AS type= ID ->| generalPathExpression )
            int alt22=3;
            switch ( input.LA(1) ) {
            case 38:
                {
                int LA22_1 = input.LA(2);

                if ( (synpred4_Meteor()) ) {
                    alt22=1;
                }
                else if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;

                }
                }
                break;
            case ID:
                {
                int LA22_2 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 2, input);

                    throw nvae;

                }
                }
                break;
            case 64:
                {
                int LA22_3 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 3, input);

                    throw nvae;

                }
                }
                break;
            case 59:
                {
                int LA22_4 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 4, input);

                    throw nvae;

                }
                }
                break;
            case DECIMAL:
                {
                int LA22_5 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 5, input);

                    throw nvae;

                }
                }
                break;
            case STRING:
                {
                int LA22_6 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 6, input);

                    throw nvae;

                }
                }
                break;
            case UINT:
                {
                int LA22_7 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 7, input);

                    throw nvae;

                }
                }
                break;
            case INTEGER:
                {
                int LA22_8 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 8, input);

                    throw nvae;

                }
                }
                break;
            case 61:
                {
                int LA22_9 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 9, input);

                    throw nvae;

                }
                }
                break;
            case VAR:
                {
                int LA22_10 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 10, input);

                    throw nvae;

                }
                }
                break;
            case 57:
                {
                int LA22_11 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 11, input);

                    throw nvae;

                }
                }
                break;
            case 67:
                {
                int LA22_12 = input.LA(2);

                if ( (synpred5_Meteor()) ) {
                    alt22=2;
                }
                else if ( (true) ) {
                    alt22=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 12, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }

            switch (alt22) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:159:4: ( '(' ID ')' )=> '(' type= ID ')' expr= generalPathExpression
                    {
                    char_literal39=(Token)match(input,38,FOLLOW_38_in_castExpression891); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_38.add(char_literal39);


                    type=(Token)match(input,ID,FOLLOW_ID_in_castExpression895); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(type);


                    char_literal40=(Token)match(input,39,FOLLOW_39_in_castExpression897); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_39.add(char_literal40);


                    pushFollow(FOLLOW_generalPathExpression_in_castExpression901);
                    expr=generalPathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_generalPathExpression.add(expr.getTree());

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 160:3: ->
                    {
                        adaptor.addChild(root_0,  coerce((type!=null?type.getText():null), (expr!=null?((EvaluationExpression)expr.tree):null)) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:161:4: ( generalPathExpression AS )=>expr= generalPathExpression AS type= ID
                    {
                    pushFollow(FOLLOW_generalPathExpression_in_castExpression921);
                    expr=generalPathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_generalPathExpression.add(expr.getTree());

                    AS41=(Token)match(input,AS,FOLLOW_AS_in_castExpression923); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_AS.add(AS41);


                    type=(Token)match(input,ID,FOLLOW_ID_in_castExpression927); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(type);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 162:3: ->
                    {
                        adaptor.addChild(root_0,  coerce((type!=null?type.getText():null), (expr!=null?((EvaluationExpression)expr.tree):null)) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:163:4: generalPathExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_generalPathExpression_in_castExpression938);
                    generalPathExpression42=generalPathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, generalPathExpression42.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "castExpression"


    public static class generalPathExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "generalPathExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:165:1: generalPathExpression : value= valueExpression ( ( pathExpression )=>path= pathExpression ->| -> $value) ;
    public final MeteorParser.generalPathExpression_return generalPathExpression() throws RecognitionException {
        MeteorParser.generalPathExpression_return retval = new MeteorParser.generalPathExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.valueExpression_return value =null;

        MeteorParser.pathExpression_return path =null;


        RewriteRuleSubtreeStream stream_valueExpression=new RewriteRuleSubtreeStream(adaptor,"rule valueExpression");
        RewriteRuleSubtreeStream stream_pathExpression=new RewriteRuleSubtreeStream(adaptor,"rule pathExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:166:2: (value= valueExpression ( ( pathExpression )=>path= pathExpression ->| -> $value) )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:166:4: value= valueExpression ( ( pathExpression )=>path= pathExpression ->| -> $value)
            {
            pushFollow(FOLLOW_valueExpression_in_generalPathExpression950);
            value=valueExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_valueExpression.add(value.getTree());

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:167:4: ( ( pathExpression )=>path= pathExpression ->| -> $value)
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==56) && (synpred6_Meteor())) {
                alt23=1;
            }
            else if ( (LA23_0==45) && (synpred6_Meteor())) {
                alt23=1;
            }
            else if ( (LA23_0==57) && (synpred6_Meteor())) {
                alt23=1;
            }
            else if ( (LA23_0==EOF||LA23_0==AND||LA23_0==AS||(LA23_0 >= ID && LA23_0 <= IN)||LA23_0==NOT||LA23_0==OR||LA23_0==STAR||(LA23_0 >= 36 && LA23_0 <= 37)||(LA23_0 >= 39 && LA23_0 <= 40)||(LA23_0 >= 42 && LA23_0 <= 43)||(LA23_0 >= 46 && LA23_0 <= 50)||(LA23_0 >= 52 && LA23_0 <= 55)||LA23_0==58||(LA23_0 >= 68 && LA23_0 <= 69)) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;

            }
            switch (alt23) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:167:5: ( pathExpression )=>path= pathExpression
                    {
                    pushFollow(FOLLOW_pathExpression_in_generalPathExpression964);
                    path=pathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pathExpression.add(path.getTree());

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 167:44: ->
                    {
                        adaptor.addChild(root_0,  PathExpression.wrapIfNecessary((value!=null?((EvaluationExpression)value.tree):null), (path!=null?((EvaluationExpression)path.tree):null)) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:168:6: 
                    {
                    // AST REWRITE
                    // elements: value
                    // token labels: 
                    // rule labels: retval, value
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value",value!=null?value.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 168:6: -> $value
                    {
                        adaptor.addChild(root_0, stream_value.nextTree());

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "generalPathExpression"


    public static class contextAwarePathExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "contextAwarePathExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:170:1: contextAwarePathExpression[EvaluationExpression context] : path= pathExpression ->;
    public final MeteorParser.contextAwarePathExpression_return contextAwarePathExpression(EvaluationExpression context) throws RecognitionException {
        MeteorParser.contextAwarePathExpression_return retval = new MeteorParser.contextAwarePathExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.pathExpression_return path =null;


        RewriteRuleSubtreeStream stream_pathExpression=new RewriteRuleSubtreeStream(adaptor,"rule pathExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:171:3: (path= pathExpression ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:171:5: path= pathExpression
            {
            pushFollow(FOLLOW_pathExpression_in_contextAwarePathExpression993);
            path=pathExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_pathExpression.add(path.getTree());

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 171:25: ->
            {
                adaptor.addChild(root_0,  PathExpression.wrapIfNecessary(context, (path!=null?((EvaluationExpression)path.tree):null)) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "contextAwarePathExpression"


    protected static class pathExpression_scope {
        List<EvaluationExpression> fragments;
    }
    protected Stack pathExpression_stack = new Stack();


    public static class pathExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pathExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:173:1: pathExpression : ( ( ( '?.' )=> '?.' (field= ID ) ) | ( ( '.' )=> '.' (field= ID ) ) | ( '[' )=> arrayAccess )+ ->;
    public final MeteorParser.pathExpression_return pathExpression() throws RecognitionException {
        pathExpression_stack.push(new pathExpression_scope());
        MeteorParser.pathExpression_return retval = new MeteorParser.pathExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token field=null;
        Token string_literal43=null;
        Token char_literal44=null;
        MeteorParser.arrayAccess_return arrayAccess45 =null;


        EvaluationExpression field_tree=null;
        EvaluationExpression string_literal43_tree=null;
        EvaluationExpression char_literal44_tree=null;
        RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
        RewriteRuleTokenStream stream_56=new RewriteRuleTokenStream(adaptor,"token 56");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_arrayAccess=new RewriteRuleSubtreeStream(adaptor,"rule arrayAccess");
         ((pathExpression_scope)pathExpression_stack.peek()).fragments = new ArrayList<EvaluationExpression>();
                paraphrase.push("a path expression"); 
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:178:3: ( ( ( ( '?.' )=> '?.' (field= ID ) ) | ( ( '.' )=> '.' (field= ID ) ) | ( '[' )=> arrayAccess )+ ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:5: ( ( ( '?.' )=> '?.' (field= ID ) ) | ( ( '.' )=> '.' (field= ID ) ) | ( '[' )=> arrayAccess )+
            {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:5: ( ( ( '?.' )=> '?.' (field= ID ) ) | ( ( '.' )=> '.' (field= ID ) ) | ( '[' )=> arrayAccess )+
            int cnt24=0;
            loop24:
            do {
                int alt24=4;
                switch ( input.LA(1) ) {
                case 56:
                    {
                    int LA24_2 = input.LA(2);

                    if ( (synpred7_Meteor()) ) {
                        alt24=1;
                    }


                    }
                    break;
                case 45:
                    {
                    int LA24_3 = input.LA(2);

                    if ( (synpred8_Meteor()) ) {
                        alt24=2;
                    }


                    }
                    break;
                case 57:
                    {
                    int LA24_4 = input.LA(2);

                    if ( (synpred9_Meteor()) ) {
                        alt24=3;
                    }


                    }
                    break;

                }

                switch (alt24) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:7: ( ( '?.' )=> '?.' (field= ID ) )
            	    {
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:7: ( ( '?.' )=> '?.' (field= ID ) )
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:8: ( '?.' )=> '?.' (field= ID )
            	    {
            	    string_literal43=(Token)match(input,56,FOLLOW_56_in_pathExpression1036); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_56.add(string_literal43);


            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:22: (field= ID )
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:23: field= ID
            	    {
            	    field=(Token)match(input,ID,FOLLOW_ID_in_pathExpression1041); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(field);


            	    if ( state.backtracking==0 ) { ((pathExpression_scope)pathExpression_stack.peek()).fragments.add(new ObjectAccess((field!=null?field.getText():null), true)); }

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:11: ( ( '.' )=> '.' (field= ID ) )
            	    {
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:11: ( ( '.' )=> '.' (field= ID ) )
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:12: ( '.' )=> '.' (field= ID )
            	    {
            	    char_literal44=(Token)match(input,45,FOLLOW_45_in_pathExpression1065); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_45.add(char_literal44);


            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:24: (field= ID )
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:25: field= ID
            	    {
            	    field=(Token)match(input,ID,FOLLOW_ID_in_pathExpression1070); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_ID.add(field);


            	    if ( state.backtracking==0 ) { ((pathExpression_scope)pathExpression_stack.peek()).fragments.add(new ObjectAccess((field!=null?field.getText():null))); }

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:181:11: ( '[' )=> arrayAccess
            	    {
            	    pushFollow(FOLLOW_arrayAccess_in_pathExpression1093);
            	    arrayAccess45=arrayAccess();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_arrayAccess.add(arrayAccess45.getTree());

            	    if ( state.backtracking==0 ) { ((pathExpression_scope)pathExpression_stack.peek()).fragments.add((arrayAccess45!=null?((EvaluationExpression)arrayAccess45.tree):null)); }

            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 182:3: ->
            {
                adaptor.addChild(root_0,  PathExpression.wrapIfNecessary(((pathExpression_scope)pathExpression_stack.peek()).fragments) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            pathExpression_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "pathExpression"


    public static class valueExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "valueExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:184:1: valueExpression : ( ( ID '(' )=> methodCall[null] | parenthesesExpression | literal | ( VAR '[' VAR )=> streamIndexAccess | VAR ->| ( ( ID ':' )=>packageName= ID ':' )? constant= ID {...}? => ->| arrayCreation | objectCreation );
    public final MeteorParser.valueExpression_return valueExpression() throws RecognitionException {
        MeteorParser.valueExpression_return retval = new MeteorParser.valueExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token constant=null;
        Token VAR50=null;
        Token char_literal51=null;
        MeteorParser.methodCall_return methodCall46 =null;

        MeteorParser.parenthesesExpression_return parenthesesExpression47 =null;

        MeteorParser.literal_return literal48 =null;

        MeteorParser.streamIndexAccess_return streamIndexAccess49 =null;

        MeteorParser.arrayCreation_return arrayCreation52 =null;

        MeteorParser.objectCreation_return objectCreation53 =null;


        EvaluationExpression packageName_tree=null;
        EvaluationExpression constant_tree=null;
        EvaluationExpression VAR50_tree=null;
        EvaluationExpression char_literal51_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:185:2: ( ( ID '(' )=> methodCall[null] | parenthesesExpression | literal | ( VAR '[' VAR )=> streamIndexAccess | VAR ->| ( ( ID ':' )=>packageName= ID ':' )? constant= ID {...}? => ->| arrayCreation | objectCreation )
            int alt26=8;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==47) ) {
                    int LA26_7 = input.LA(3);

                    if ( (synpred10_Meteor()) ) {
                        alt26=1;
                    }
                    else if ( (true) ) {
                        alt26=6;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 7, input);

                        throw nvae;

                    }
                }
                else if ( (LA26_1==38) && (synpred10_Meteor())) {
                    alt26=1;
                }
                else if ( (LA26_1==EOF||LA26_1==AND||LA26_1==AS||(LA26_1 >= ID && LA26_1 <= IN)||LA26_1==NOT||LA26_1==OR||LA26_1==STAR||(LA26_1 >= 36 && LA26_1 <= 37)||(LA26_1 >= 39 && LA26_1 <= 40)||(LA26_1 >= 42 && LA26_1 <= 43)||(LA26_1 >= 45 && LA26_1 <= 46)||(LA26_1 >= 48 && LA26_1 <= 50)||(LA26_1 >= 52 && LA26_1 <= 58)||(LA26_1 >= 68 && LA26_1 <= 69)) ) {
                    alt26=6;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;

                }
                }
                break;
            case 38:
                {
                alt26=2;
                }
                break;
            case DECIMAL:
            case INTEGER:
            case STRING:
            case UINT:
            case 59:
            case 61:
            case 64:
                {
                alt26=3;
                }
                break;
            case VAR:
                {
                int LA26_4 = input.LA(2);

                if ( (LA26_4==57) ) {
                    int LA26_10 = input.LA(3);

                    if ( (LA26_10==STAR) ) {
                        alt26=5;
                    }
                    else if ( (LA26_10==ID) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==38) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==64) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==59) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==DECIMAL) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==STRING) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==UINT) ) {
                        int LA26_18 = input.LA(4);

                        if ( (LA26_18==56) && (synpred11_Meteor())) {
                            alt26=4;
                        }
                        else if ( (LA26_18==45) && (synpred11_Meteor())) {
                            alt26=4;
                        }
                        else if ( (LA26_18==57) && (synpred11_Meteor())) {
                            alt26=4;
                        }
                        else if ( (LA26_18==58) ) {
                            int LA26_27 = input.LA(5);

                            if ( (synpred11_Meteor()) ) {
                                alt26=4;
                            }
                            else if ( (true) ) {
                                alt26=5;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 26, 27, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA26_18==47) ) {
                            alt26=5;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 26, 18, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA26_10==INTEGER) ) {
                        int LA26_19 = input.LA(4);

                        if ( (LA26_19==56) && (synpred11_Meteor())) {
                            alt26=4;
                        }
                        else if ( (LA26_19==45) && (synpred11_Meteor())) {
                            alt26=4;
                        }
                        else if ( (LA26_19==57) && (synpred11_Meteor())) {
                            alt26=4;
                        }
                        else if ( (LA26_19==58) ) {
                            int LA26_28 = input.LA(5);

                            if ( (synpred11_Meteor()) ) {
                                alt26=4;
                            }
                            else if ( (true) ) {
                                alt26=5;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 26, 28, input);

                                throw nvae;

                            }
                        }
                        else if ( (LA26_19==47) ) {
                            alt26=5;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 26, 19, input);

                            throw nvae;

                        }
                    }
                    else if ( (LA26_10==61) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==VAR) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==57) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else if ( (LA26_10==67) && (synpred11_Meteor())) {
                        alt26=4;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 10, input);

                        throw nvae;

                    }
                }
                else if ( (LA26_4==EOF||LA26_4==AND||LA26_4==AS||(LA26_4 >= ID && LA26_4 <= IN)||LA26_4==NOT||LA26_4==OR||LA26_4==STAR||(LA26_4 >= 36 && LA26_4 <= 37)||(LA26_4 >= 39 && LA26_4 <= 40)||(LA26_4 >= 42 && LA26_4 <= 43)||(LA26_4 >= 45 && LA26_4 <= 50)||(LA26_4 >= 52 && LA26_4 <= 56)||LA26_4==58||(LA26_4 >= 68 && LA26_4 <= 69)) ) {
                    alt26=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 4, input);

                    throw nvae;

                }
                }
                break;
            case 57:
                {
                alt26=7;
                }
                break;
            case 67:
                {
                alt26=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }

            switch (alt26) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:185:4: ( ID '(' )=> methodCall[null]
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_methodCall_in_valueExpression1121);
                    methodCall46=methodCall(null);

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, methodCall46.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:186:4: parenthesesExpression
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_parenthesesExpression_in_valueExpression1127);
                    parenthesesExpression47=parenthesesExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, parenthesesExpression47.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:187:4: literal
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_literal_in_valueExpression1133);
                    literal48=literal();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, literal48.getTree());

                    }
                    break;
                case 4 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:188:4: ( VAR '[' VAR )=> streamIndexAccess
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_streamIndexAccess_in_valueExpression1148);
                    streamIndexAccess49=streamIndexAccess();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, streamIndexAccess49.getTree());

                    }
                    break;
                case 5 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:189:4: VAR
                    {
                    VAR50=(Token)match(input,VAR,FOLLOW_VAR_in_valueExpression1153); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR50);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 189:8: ->
                    {
                        adaptor.addChild(root_0,  makePath(VAR50) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:190:5: ( ( ID ':' )=>packageName= ID ':' )? constant= ID {...}? =>
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:190:5: ( ( ID ':' )=>packageName= ID ':' )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==ID) ) {
                        int LA25_1 = input.LA(2);

                        if ( (LA25_1==47) ) {
                            int LA25_2 = input.LA(3);

                            if ( (synpred12_Meteor()) ) {
                                alt25=1;
                            }
                        }
                    }
                    switch (alt25) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:190:6: ( ID ':' )=>packageName= ID ':'
                            {
                            packageName=(Token)match(input,ID,FOLLOW_ID_in_valueExpression1173); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ID.add(packageName);


                            char_literal51=(Token)match(input,47,FOLLOW_47_in_valueExpression1175); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_47.add(char_literal51);


                            }
                            break;

                    }


                    constant=(Token)match(input,ID,FOLLOW_ID_in_valueExpression1181); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(constant);


                    if ( !(( getScope((packageName!=null?packageName.getText():null)).getConstantRegistry().get((constant!=null?constant.getText():null)) != null )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "valueExpression", " getScope($packageName.text).getConstantRegistry().get($constant.text) != null ");
                    }

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 191:5: ->
                    {
                        adaptor.addChild(root_0,  getScope((packageName!=null?packageName.getText():null)).getConstantRegistry().get((constant!=null?constant.getText():null)) );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 7 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:192:4: arrayCreation
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_arrayCreation_in_valueExpression1201);
                    arrayCreation52=arrayCreation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, arrayCreation52.getTree());

                    }
                    break;
                case 8 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:193:4: objectCreation
                    {
                    root_0 = (EvaluationExpression)adaptor.nil();


                    pushFollow(FOLLOW_objectCreation_in_valueExpression1207);
                    objectCreation53=objectCreation();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, objectCreation53.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "valueExpression"


    public static class operatorExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operatorExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:195:1: operatorExpression : op= operator -> ^( EXPRESSION[\"NestedOperatorExpression\"] ) ;
    public final MeteorParser.operatorExpression_return operatorExpression() throws RecognitionException {
        MeteorParser.operatorExpression_return retval = new MeteorParser.operatorExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        MeteorParser.operator_return op =null;


        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:196:2: (op= operator -> ^( EXPRESSION[\"NestedOperatorExpression\"] ) )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:196:4: op= operator
            {
            pushFollow(FOLLOW_operator_in_operatorExpression1220);
            op=operator();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_operator.add(op.getTree());

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 196:16: -> ^( EXPRESSION[\"NestedOperatorExpression\"] )
            {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:196:19: ^( EXPRESSION[\"NestedOperatorExpression\"] )
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "NestedOperatorExpression")
                , root_1);

                adaptor.addChild(root_1,  (op!=null?op.op:null) );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "operatorExpression"


    public static class parenthesesExpression_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "parenthesesExpression"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:198:1: parenthesesExpression : ( '(' expression ')' ) -> expression ;
    public final MeteorParser.parenthesesExpression_return parenthesesExpression() throws RecognitionException {
        MeteorParser.parenthesesExpression_return retval = new MeteorParser.parenthesesExpression_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal54=null;
        Token char_literal56=null;
        MeteorParser.expression_return expression55 =null;


        EvaluationExpression char_literal54_tree=null;
        EvaluationExpression char_literal56_tree=null;
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:199:2: ( ( '(' expression ')' ) -> expression )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:199:4: ( '(' expression ')' )
            {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:199:4: ( '(' expression ')' )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:199:5: '(' expression ')'
            {
            char_literal54=(Token)match(input,38,FOLLOW_38_in_parenthesesExpression1241); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_38.add(char_literal54);


            pushFollow(FOLLOW_expression_in_parenthesesExpression1243);
            expression55=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(expression55.getTree());

            char_literal56=(Token)match(input,39,FOLLOW_39_in_parenthesesExpression1245); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal56);


            }


            // AST REWRITE
            // elements: expression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 199:25: -> expression
            {
                adaptor.addChild(root_0, stream_expression.nextTree());

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "parenthesesExpression"


    public static class methodCall_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "methodCall"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:201:1: methodCall[EvaluationExpression targetExpr] : (packageName= ID ':' )? name= ID '(' (param= expression ( ',' param= expression )* )? ')' ->;
    public final MeteorParser.methodCall_return methodCall(EvaluationExpression targetExpr) throws RecognitionException {
        MeteorParser.methodCall_return retval = new MeteorParser.methodCall_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token name=null;
        Token char_literal57=null;
        Token char_literal58=null;
        Token char_literal59=null;
        Token char_literal60=null;
        MeteorParser.expression_return param =null;


        EvaluationExpression packageName_tree=null;
        EvaluationExpression name_tree=null;
        EvaluationExpression char_literal57_tree=null;
        EvaluationExpression char_literal58_tree=null;
        EvaluationExpression char_literal59_tree=null;
        EvaluationExpression char_literal60_tree=null;
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
         List<EvaluationExpression> params = new ArrayList();
                paraphrase.push("a method call"); 
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:205:2: ( (packageName= ID ':' )? name= ID '(' (param= expression ( ',' param= expression )* )? ')' ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:205:4: (packageName= ID ':' )? name= ID '(' (param= expression ( ',' param= expression )* )? ')'
            {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:205:4: (packageName= ID ':' )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==ID) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==47) ) {
                    alt27=1;
                }
            }
            switch (alt27) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:205:5: packageName= ID ':'
                    {
                    packageName=(Token)match(input,ID,FOLLOW_ID_in_methodCall1274); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(packageName);


                    char_literal57=(Token)match(input,47,FOLLOW_47_in_methodCall1276); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal57);


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_methodCall1282); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            char_literal58=(Token)match(input,38,FOLLOW_38_in_methodCall1284); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_38.add(char_literal58);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:206:2: (param= expression ( ',' param= expression )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==DECIMAL||LA29_0==ID||LA29_0==INTEGER||(LA29_0 >= STRING && LA29_0 <= UINT)||LA29_0==VAR||LA29_0==35||LA29_0==38||LA29_0==41||LA29_0==44||LA29_0==57||LA29_0==59||(LA29_0 >= 61 && LA29_0 <= 62)||LA29_0==64||(LA29_0 >= 66 && LA29_0 <= 67)||LA29_0==70) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:206:3: param= expression ( ',' param= expression )*
                    {
                    pushFollow(FOLLOW_expression_in_methodCall1291);
                    param=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(param.getTree());

                    if ( state.backtracking==0 ) { params.add((param!=null?((EvaluationExpression)param.tree):null)); }

                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:207:2: ( ',' param= expression )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==42) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:207:3: ',' param= expression
                    	    {
                    	    char_literal59=(Token)match(input,42,FOLLOW_42_in_methodCall1297); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_42.add(char_literal59);


                    	    pushFollow(FOLLOW_expression_in_methodCall1301);
                    	    param=expression();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_expression.add(param.getTree());

                    	    if ( state.backtracking==0 ) { params.add((param!=null?((EvaluationExpression)param.tree):null)); }

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);


                    }
                    break;

            }


            char_literal60=(Token)match(input,39,FOLLOW_39_in_methodCall1311); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_39.add(char_literal60);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 208:6: ->
            {
                adaptor.addChild(root_0,  createCheckedMethodCall((packageName!=null?packageName.getText():null), name, targetExpr, params.toArray(new EvaluationExpression[params.size()])) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "methodCall"


    public static class fieldAssignment_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "fieldAssignment"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:210:1: fieldAssignment : ( ( ( ID ':' )=> ID ':' expression ->) | VAR ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) ) );
    public final MeteorParser.fieldAssignment_return fieldAssignment() throws RecognitionException {
        MeteorParser.fieldAssignment_return retval = new MeteorParser.fieldAssignment_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token ID61=null;
        Token char_literal62=null;
        Token VAR64=null;
        Token char_literal65=null;
        Token STAR66=null;
        Token char_literal67=null;
        Token char_literal68=null;
        MeteorParser.operator_return op =null;

        MeteorParser.contextAwarePathExpression_return p =null;

        MeteorParser.expression_return e2 =null;

        MeteorParser.expression_return expression63 =null;


        EvaluationExpression ID61_tree=null;
        EvaluationExpression char_literal62_tree=null;
        EvaluationExpression VAR64_tree=null;
        EvaluationExpression char_literal65_tree=null;
        EvaluationExpression STAR66_tree=null;
        EvaluationExpression char_literal67_tree=null;
        EvaluationExpression char_literal68_tree=null;
        RewriteRuleTokenStream stream_45=new RewriteRuleTokenStream(adaptor,"token 45");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_51=new RewriteRuleTokenStream(adaptor,"token 51");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
        RewriteRuleSubtreeStream stream_contextAwarePathExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwarePathExpression");
        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:211:2: ( ( ( ID ':' )=> ID ':' expression ->) | VAR ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==ID) && (synpred13_Meteor())) {
                alt32=1;
            }
            else if ( (LA32_0==VAR) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }
            switch (alt32) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:211:4: ( ( ID ':' )=> ID ':' expression ->)
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:211:4: ( ( ID ':' )=> ID ':' expression ->)
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:211:5: ( ID ':' )=> ID ':' expression
                    {
                    ID61=(Token)match(input,ID,FOLLOW_ID_in_fieldAssignment1333); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(ID61);


                    char_literal62=(Token)match(input,47,FOLLOW_47_in_fieldAssignment1335); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal62);


                    pushFollow(FOLLOW_expression_in_fieldAssignment1337);
                    expression63=expression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_expression.add(expression63.getTree());

                    if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.FieldAssignment((ID61!=null?ID61.getText():null), (expression63!=null?((EvaluationExpression)expression63.tree):null))); }

                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 212:104: ->
                    {
                        root_0 = null;
                    }


                    retval.tree = root_0;
                    }

                    }


                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:213:5: VAR ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) )
                    {
                    VAR64=(Token)match(input,VAR,FOLLOW_VAR_in_fieldAssignment1354); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(VAR64);


                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:214:5: ( '.' STAR ->| '=' op= operator {...}? =>|p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->) )
                    int alt31=3;
                    switch ( input.LA(1) ) {
                    case 45:
                        {
                        int LA31_1 = input.LA(2);

                        if ( (LA31_1==STAR) ) {
                            alt31=1;
                        }
                        else if ( (LA31_1==ID) ) {
                            alt31=3;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 31, 1, input);

                            throw nvae;

                        }
                        }
                        break;
                    case 51:
                        {
                        alt31=2;
                        }
                        break;
                    case 56:
                    case 57:
                        {
                        alt31=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 31, 0, input);

                        throw nvae;

                    }

                    switch (alt31) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:214:7: '.' STAR
                            {
                            char_literal65=(Token)match(input,45,FOLLOW_45_in_fieldAssignment1363); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_45.add(char_literal65);


                            STAR66=(Token)match(input,STAR,FOLLOW_STAR_in_fieldAssignment1365); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_STAR.add(STAR66);


                            if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.CopyFields(makePath(VAR64))); }

                            // AST REWRITE
                            // elements: 
                            // token labels: 
                            // rule labels: retval
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            if ( state.backtracking==0 ) {

                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                            root_0 = (EvaluationExpression)adaptor.nil();
                            // 214:98: ->
                            {
                                root_0 = null;
                            }


                            retval.tree = root_0;
                            }

                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:215:9: '=' op= operator {...}? =>
                            {
                            char_literal67=(Token)match(input,51,FOLLOW_51_in_fieldAssignment1379); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_51.add(char_literal67);


                            pushFollow(FOLLOW_operator_in_fieldAssignment1383);
                            op=operator();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_operator.add(op.getTree());

                            if ( !(( setInnerOutput(VAR64, (op!=null?op.op:null)) )) ) {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                throw new FailedPredicateException(input, "fieldAssignment", " setInnerOutput($VAR, $op.op) ");
                            }

                            }
                            break;
                        case 3 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:216:9: p= contextAwarePathExpression[getVariable($VAR).toInputSelection($operator::result)] ( ':' e2= expression ->| ->)
                            {
                            pushFollow(FOLLOW_contextAwarePathExpression_in_fieldAssignment1398);
                            p=contextAwarePathExpression(getVariable(VAR64).toInputSelection(((operator_scope)operator_stack.peek()).result));

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) stream_contextAwarePathExpression.add(p.getTree());

                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:217:7: ( ':' e2= expression ->| ->)
                            int alt30=2;
                            int LA30_0 = input.LA(1);

                            if ( (LA30_0==47) ) {
                                alt30=1;
                            }
                            else if ( (LA30_0==42||LA30_0==69) ) {
                                alt30=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 30, 0, input);

                                throw nvae;

                            }
                            switch (alt30) {
                                case 1 :
                                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:217:9: ':' e2= expression
                                    {
                                    char_literal68=(Token)match(input,47,FOLLOW_47_in_fieldAssignment1409); if (state.failed) return retval; 
                                    if ( state.backtracking==0 ) stream_47.add(char_literal68);


                                    pushFollow(FOLLOW_expression_in_fieldAssignment1413);
                                    e2=expression();

                                    state._fsp--;
                                    if (state.failed) return retval;
                                    if ( state.backtracking==0 ) stream_expression.add(e2.getTree());

                                    if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.TagMapping((p!=null?((EvaluationExpression)p.tree):null), (e2!=null?((EvaluationExpression)e2.tree):null))); }

                                    // AST REWRITE
                                    // elements: 
                                    // token labels: 
                                    // rule labels: retval
                                    // token list labels: 
                                    // rule list labels: 
                                    // wildcard labels: 
                                    if ( state.backtracking==0 ) {

                                    retval.tree = root_0;
                                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                                    root_0 = (EvaluationExpression)adaptor.nil();
                                    // 217:112: ->
                                    {
                                        root_0 = null;
                                    }


                                    retval.tree = root_0;
                                    }

                                    }
                                    break;
                                case 2 :
                                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:218:23: 
                                    {
                                    if ( state.backtracking==0 ) { ((objectCreation_scope)objectCreation_stack.peek()).mappings.add(new ObjectCreation.FieldAssignment(getAssignmentName((p!=null?((EvaluationExpression)p.tree):null)), (p!=null?((EvaluationExpression)p.tree):null))); }

                                    // AST REWRITE
                                    // elements: 
                                    // token labels: 
                                    // rule labels: retval
                                    // token list labels: 
                                    // rule list labels: 
                                    // wildcard labels: 
                                    if ( state.backtracking==0 ) {

                                    retval.tree = root_0;
                                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                                    root_0 = (EvaluationExpression)adaptor.nil();
                                    // 218:131: ->
                                    {
                                        root_0 = null;
                                    }


                                    retval.tree = root_0;
                                    }

                                    }
                                    break;

                            }


                            }
                            break;

                    }


                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (NoViableAltException re) {
             explainUsage("inside of a json object {...} only <field: expression>, <$var.path>, <$var = operator> or <$var: expression> are allowed", re); 
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "fieldAssignment"


    protected static class objectCreation_scope {
        List<ObjectCreation.Mapping> mappings;
    }
    protected Stack objectCreation_stack = new Stack();


    public static class objectCreation_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "objectCreation"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:223:1: objectCreation : '{' ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )? '}' -> ^( EXPRESSION[\"ObjectCreation\"] ) ;
    public final MeteorParser.objectCreation_return objectCreation() throws RecognitionException {
        objectCreation_stack.push(new objectCreation_scope());
        MeteorParser.objectCreation_return retval = new MeteorParser.objectCreation_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal69=null;
        Token char_literal71=null;
        Token char_literal73=null;
        Token char_literal74=null;
        MeteorParser.fieldAssignment_return fieldAssignment70 =null;

        MeteorParser.fieldAssignment_return fieldAssignment72 =null;


        EvaluationExpression char_literal69_tree=null;
        EvaluationExpression char_literal71_tree=null;
        EvaluationExpression char_literal73_tree=null;
        EvaluationExpression char_literal74_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_69=new RewriteRuleTokenStream(adaptor,"token 69");
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleSubtreeStream stream_fieldAssignment=new RewriteRuleSubtreeStream(adaptor,"rule fieldAssignment");
         ((objectCreation_scope)objectCreation_stack.peek()).mappings = new ArrayList<ObjectCreation.Mapping>(); 
                paraphrase.push("a json object"); 
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:2: ( '{' ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )? '}' -> ^( EXPRESSION[\"ObjectCreation\"] ) )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:4: '{' ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )? '}'
            {
            char_literal69=(Token)match(input,67,FOLLOW_67_in_objectCreation1478); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_67.add(char_literal69);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:8: ( fieldAssignment ( ',' fieldAssignment )* ( ',' )? )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==ID||LA35_0==VAR) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:9: fieldAssignment ( ',' fieldAssignment )* ( ',' )?
                    {
                    pushFollow(FOLLOW_fieldAssignment_in_objectCreation1481);
                    fieldAssignment70=fieldAssignment();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_fieldAssignment.add(fieldAssignment70.getTree());

                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:25: ( ',' fieldAssignment )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0==42) ) {
                            int LA33_1 = input.LA(2);

                            if ( (LA33_1==ID||LA33_1==VAR) ) {
                                alt33=1;
                            }


                        }


                        switch (alt33) {
                    	case 1 :
                    	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:26: ',' fieldAssignment
                    	    {
                    	    char_literal71=(Token)match(input,42,FOLLOW_42_in_objectCreation1484); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_42.add(char_literal71);


                    	    pushFollow(FOLLOW_fieldAssignment_in_objectCreation1486);
                    	    fieldAssignment72=fieldAssignment();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_fieldAssignment.add(fieldAssignment72.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);


                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:48: ( ',' )?
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==42) ) {
                        alt34=1;
                    }
                    switch (alt34) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:48: ','
                            {
                            char_literal73=(Token)match(input,42,FOLLOW_42_in_objectCreation1490); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_42.add(char_literal73);


                            }
                            break;

                    }


                    }
                    break;

            }


            char_literal74=(Token)match(input,69,FOLLOW_69_in_objectCreation1495); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_69.add(char_literal74);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 228:59: -> ^( EXPRESSION[\"ObjectCreation\"] )
            {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:228:62: ^( EXPRESSION[\"ObjectCreation\"] )
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ObjectCreation")
                , root_1);

                adaptor.addChild(root_1,  ((objectCreation_scope)objectCreation_stack.peek()).mappings );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            objectCreation_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "objectCreation"


    public static class literal_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "literal"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:230:1: literal : (val= 'true' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= 'false' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= DECIMAL -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= STRING -> ^( EXPRESSION[\"ConstantExpression\"] ) | (val= UINT |val= INTEGER ) -> ^( EXPRESSION[\"ConstantExpression\"] ) | 'null' ->);
    public final MeteorParser.literal_return literal() throws RecognitionException {
        MeteorParser.literal_return retval = new MeteorParser.literal_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token val=null;
        Token string_literal75=null;

        EvaluationExpression val_tree=null;
        EvaluationExpression string_literal75_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");
        RewriteRuleTokenStream stream_59=new RewriteRuleTokenStream(adaptor,"token 59");
        RewriteRuleTokenStream stream_DECIMAL=new RewriteRuleTokenStream(adaptor,"token DECIMAL");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_UINT=new RewriteRuleTokenStream(adaptor,"token UINT");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");

         paraphrase.push("a literal"); 
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:233:2: (val= 'true' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= 'false' -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= DECIMAL -> ^( EXPRESSION[\"ConstantExpression\"] ) |val= STRING -> ^( EXPRESSION[\"ConstantExpression\"] ) | (val= UINT |val= INTEGER ) -> ^( EXPRESSION[\"ConstantExpression\"] ) | 'null' ->)
            int alt37=6;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt37=1;
                }
                break;
            case 59:
                {
                alt37=2;
                }
                break;
            case DECIMAL:
                {
                alt37=3;
                }
                break;
            case STRING:
                {
                alt37=4;
                }
                break;
            case INTEGER:
            case UINT:
                {
                alt37=5;
                }
                break;
            case 61:
                {
                alt37=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }

            switch (alt37) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:233:4: val= 'true'
                    {
                    val=(Token)match(input,64,FOLLOW_64_in_literal1525); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_64.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 233:15: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:233:18: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  Boolean.TRUE );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:234:4: val= 'false'
                    {
                    val=(Token)match(input,59,FOLLOW_59_in_literal1541); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_59.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 234:16: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:234:19: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  Boolean.FALSE );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:235:4: val= DECIMAL
                    {
                    val=(Token)match(input,DECIMAL,FOLLOW_DECIMAL_in_literal1557); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_DECIMAL.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 235:16: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:235:19: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  new BigDecimal((val!=null?val.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 4 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:236:4: val= STRING
                    {
                    val=(Token)match(input,STRING,FOLLOW_STRING_in_literal1573); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(val);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 236:15: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:236:18: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  val.getText() );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 5 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:237:5: (val= UINT |val= INTEGER )
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:237:5: (val= UINT |val= INTEGER )
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( (LA36_0==UINT) ) {
                        alt36=1;
                    }
                    else if ( (LA36_0==INTEGER) ) {
                        alt36=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 36, 0, input);

                        throw nvae;

                    }
                    switch (alt36) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:237:6: val= UINT
                            {
                            val=(Token)match(input,UINT,FOLLOW_UINT_in_literal1591); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(val);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:237:17: val= INTEGER
                            {
                            val=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_literal1597); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(val);


                            }
                            break;

                    }


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 237:30: -> ^( EXPRESSION[\"ConstantExpression\"] )
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:237:33: ^( EXPRESSION[\"ConstantExpression\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ConstantExpression")
                        , root_1);

                        adaptor.addChild(root_1,  parseInt((val!=null?val.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 6 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:238:5: 'null'
                    {
                    string_literal75=(Token)match(input,61,FOLLOW_61_in_literal1613); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_61.add(string_literal75);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 238:12: ->
                    {
                        adaptor.addChild(root_0,  ConstantExpression.NULL );

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "literal"


    public static class arrayAccess_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayAccess"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:240:1: arrayAccess : ( '[' STAR ']' path= pathExpression -> ^( EXPRESSION[\"ArrayProjection\"] $path) | '[' (pos= INTEGER |pos= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) | '[' (start= INTEGER |start= UINT ) ':' (end= INTEGER |end= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) );
    public final MeteorParser.arrayAccess_return arrayAccess() throws RecognitionException {
        MeteorParser.arrayAccess_return retval = new MeteorParser.arrayAccess_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token pos=null;
        Token start=null;
        Token end=null;
        Token char_literal76=null;
        Token STAR77=null;
        Token char_literal78=null;
        Token char_literal79=null;
        Token char_literal80=null;
        Token char_literal81=null;
        Token char_literal82=null;
        Token char_literal83=null;
        MeteorParser.pathExpression_return path =null;


        EvaluationExpression pos_tree=null;
        EvaluationExpression start_tree=null;
        EvaluationExpression end_tree=null;
        EvaluationExpression char_literal76_tree=null;
        EvaluationExpression STAR77_tree=null;
        EvaluationExpression char_literal78_tree=null;
        EvaluationExpression char_literal79_tree=null;
        EvaluationExpression char_literal80_tree=null;
        EvaluationExpression char_literal81_tree=null;
        EvaluationExpression char_literal82_tree=null;
        EvaluationExpression char_literal83_tree=null;
        RewriteRuleTokenStream stream_INTEGER=new RewriteRuleTokenStream(adaptor,"token INTEGER");
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_STAR=new RewriteRuleTokenStream(adaptor,"token STAR");
        RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_UINT=new RewriteRuleTokenStream(adaptor,"token UINT");
        RewriteRuleSubtreeStream stream_pathExpression=new RewriteRuleSubtreeStream(adaptor,"rule pathExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:3: ( '[' STAR ']' path= pathExpression -> ^( EXPRESSION[\"ArrayProjection\"] $path) | '[' (pos= INTEGER |pos= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) | '[' (start= INTEGER |start= UINT ) ':' (end= INTEGER |end= UINT ) ']' -> ^( EXPRESSION[\"ArrayAccess\"] ) )
            int alt41=3;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==57) ) {
                switch ( input.LA(2) ) {
                case STAR:
                    {
                    alt41=1;
                    }
                    break;
                case INTEGER:
                    {
                    int LA41_3 = input.LA(3);

                    if ( (LA41_3==58) ) {
                        alt41=2;
                    }
                    else if ( (LA41_3==47) ) {
                        alt41=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 3, input);

                        throw nvae;

                    }
                    }
                    break;
                case UINT:
                    {
                    int LA41_4 = input.LA(3);

                    if ( (LA41_4==58) ) {
                        alt41=2;
                    }
                    else if ( (LA41_4==47) ) {
                        alt41=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 4, input);

                        throw nvae;

                    }
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 1, input);

                    throw nvae;

                }

            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }
            switch (alt41) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:241:5: '[' STAR ']' path= pathExpression
                    {
                    char_literal76=(Token)match(input,57,FOLLOW_57_in_arrayAccess1627); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_57.add(char_literal76);


                    STAR77=(Token)match(input,STAR,FOLLOW_STAR_in_arrayAccess1629); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STAR.add(STAR77);


                    char_literal78=(Token)match(input,58,FOLLOW_58_in_arrayAccess1631); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_58.add(char_literal78);


                    pushFollow(FOLLOW_pathExpression_in_arrayAccess1635);
                    path=pathExpression();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_pathExpression.add(path.getTree());

                    // AST REWRITE
                    // elements: path
                    // token labels: 
                    // rule labels: retval, path
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                    RewriteRuleSubtreeStream stream_path=new RewriteRuleSubtreeStream(adaptor,"rule path",path!=null?path.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 242:3: -> ^( EXPRESSION[\"ArrayProjection\"] $path)
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:242:6: ^( EXPRESSION[\"ArrayProjection\"] $path)
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayProjection")
                        , root_1);

                        adaptor.addChild(root_1, stream_path.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:243:5: '[' (pos= INTEGER |pos= UINT ) ']'
                    {
                    char_literal79=(Token)match(input,57,FOLLOW_57_in_arrayAccess1655); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_57.add(char_literal79);


                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:243:9: (pos= INTEGER |pos= UINT )
                    int alt38=2;
                    int LA38_0 = input.LA(1);

                    if ( (LA38_0==INTEGER) ) {
                        alt38=1;
                    }
                    else if ( (LA38_0==UINT) ) {
                        alt38=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 38, 0, input);

                        throw nvae;

                    }
                    switch (alt38) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:243:10: pos= INTEGER
                            {
                            pos=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_arrayAccess1660); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(pos);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:243:24: pos= UINT
                            {
                            pos=(Token)match(input,UINT,FOLLOW_UINT_in_arrayAccess1666); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(pos);


                            }
                            break;

                    }


                    char_literal80=(Token)match(input,58,FOLLOW_58_in_arrayAccess1669); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_58.add(char_literal80);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 244:3: -> ^( EXPRESSION[\"ArrayAccess\"] )
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:244:6: ^( EXPRESSION[\"ArrayAccess\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayAccess")
                        , root_1);

                        adaptor.addChild(root_1,  Integer.valueOf((pos!=null?pos.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:245:5: '[' (start= INTEGER |start= UINT ) ':' (end= INTEGER |end= UINT ) ']'
                    {
                    char_literal81=(Token)match(input,57,FOLLOW_57_in_arrayAccess1687); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_57.add(char_literal81);


                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:245:9: (start= INTEGER |start= UINT )
                    int alt39=2;
                    int LA39_0 = input.LA(1);

                    if ( (LA39_0==INTEGER) ) {
                        alt39=1;
                    }
                    else if ( (LA39_0==UINT) ) {
                        alt39=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 39, 0, input);

                        throw nvae;

                    }
                    switch (alt39) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:245:10: start= INTEGER
                            {
                            start=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_arrayAccess1692); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(start);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:245:26: start= UINT
                            {
                            start=(Token)match(input,UINT,FOLLOW_UINT_in_arrayAccess1698); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(start);


                            }
                            break;

                    }


                    char_literal82=(Token)match(input,47,FOLLOW_47_in_arrayAccess1701); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal82);


                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:245:42: (end= INTEGER |end= UINT )
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( (LA40_0==INTEGER) ) {
                        alt40=1;
                    }
                    else if ( (LA40_0==UINT) ) {
                        alt40=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 40, 0, input);

                        throw nvae;

                    }
                    switch (alt40) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:245:43: end= INTEGER
                            {
                            end=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_arrayAccess1706); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_INTEGER.add(end);


                            }
                            break;
                        case 2 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:245:57: end= UINT
                            {
                            end=(Token)match(input,UINT,FOLLOW_UINT_in_arrayAccess1712); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_UINT.add(end);


                            }
                            break;

                    }


                    char_literal83=(Token)match(input,58,FOLLOW_58_in_arrayAccess1715); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_58.add(char_literal83);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    if ( state.backtracking==0 ) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (EvaluationExpression)adaptor.nil();
                    // 246:3: -> ^( EXPRESSION[\"ArrayAccess\"] )
                    {
                        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:246:6: ^( EXPRESSION[\"ArrayAccess\"] )
                        {
                        EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                        root_1 = (EvaluationExpression)adaptor.becomeRoot(
                        (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayAccess")
                        , root_1);

                        adaptor.addChild(root_1,  Integer.valueOf((start!=null?start.getText():null)) );

                        adaptor.addChild(root_1,  Integer.valueOf((end!=null?end.getText():null)) );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayAccess"


    public static class streamIndexAccess_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "streamIndexAccess"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:248:1: streamIndexAccess : op= VAR {...}? => '[' path= generalPathExpression ']' {...}? ->;
    public final MeteorParser.streamIndexAccess_return streamIndexAccess() throws RecognitionException {
        MeteorParser.streamIndexAccess_return retval = new MeteorParser.streamIndexAccess_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token op=null;
        Token char_literal84=null;
        Token char_literal85=null;
        MeteorParser.generalPathExpression_return path =null;


        EvaluationExpression op_tree=null;
        EvaluationExpression char_literal84_tree=null;
        EvaluationExpression char_literal85_tree=null;
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
        RewriteRuleSubtreeStream stream_generalPathExpression=new RewriteRuleSubtreeStream(adaptor,"rule generalPathExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:249:3: (op= VAR {...}? => '[' path= generalPathExpression ']' {...}? ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:249:5: op= VAR {...}? => '[' path= generalPathExpression ']' {...}?
            {
            op=(Token)match(input,VAR,FOLLOW_VAR_in_streamIndexAccess1743); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(op);


            if ( !(( getVariable(op) != null )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "streamIndexAccess", " getVariable($op) != null ");
            }

            char_literal84=(Token)match(input,57,FOLLOW_57_in_streamIndexAccess1752); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_57.add(char_literal84);


            pushFollow(FOLLOW_generalPathExpression_in_streamIndexAccess1756);
            path=generalPathExpression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_generalPathExpression.add(path.getTree());

            char_literal85=(Token)match(input,58,FOLLOW_58_in_streamIndexAccess1758); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(char_literal85);


            if ( !(( !((path!=null?((EvaluationExpression)path.tree):null) instanceof ConstantExpression) )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "streamIndexAccess", " !($path.tree instanceof ConstantExpression) ");
            }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 251:3: ->
            {
                adaptor.addChild(root_0,  new StreamIndexExpression(getVariable(op).getStream(), (path!=null?((EvaluationExpression)path.tree):null)) );

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "streamIndexAccess"


    public static class arrayCreation_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayCreation"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:253:1: arrayCreation : '[' elems+= expression ( ',' elems+= expression )* ( ',' )? ']' -> ^( EXPRESSION[\"ArrayCreation\"] ) ;
    public final MeteorParser.arrayCreation_return arrayCreation() throws RecognitionException {
        MeteorParser.arrayCreation_return retval = new MeteorParser.arrayCreation_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token char_literal86=null;
        Token char_literal87=null;
        Token char_literal88=null;
        Token char_literal89=null;
        List list_elems=null;
        RuleReturnScope elems = null;
        EvaluationExpression char_literal86_tree=null;
        EvaluationExpression char_literal87_tree=null;
        EvaluationExpression char_literal88_tree=null;
        EvaluationExpression char_literal89_tree=null;
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");
        RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
         paraphrase.push("a json array"); 
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:256:2: ( '[' elems+= expression ( ',' elems+= expression )* ( ',' )? ']' -> ^( EXPRESSION[\"ArrayCreation\"] ) )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:256:5: '[' elems+= expression ( ',' elems+= expression )* ( ',' )? ']'
            {
            char_literal86=(Token)match(input,57,FOLLOW_57_in_arrayCreation1787); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_57.add(char_literal86);


            pushFollow(FOLLOW_expression_in_arrayCreation1791);
            elems=expression();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_expression.add(elems.getTree());
            if (list_elems==null) list_elems=new ArrayList();
            list_elems.add(elems.getTree());


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:256:27: ( ',' elems+= expression )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==42) ) {
                    int LA42_1 = input.LA(2);

                    if ( (LA42_1==DECIMAL||LA42_1==ID||LA42_1==INTEGER||(LA42_1 >= STRING && LA42_1 <= UINT)||LA42_1==VAR||LA42_1==35||LA42_1==38||LA42_1==41||LA42_1==44||LA42_1==57||LA42_1==59||(LA42_1 >= 61 && LA42_1 <= 62)||LA42_1==64||(LA42_1 >= 66 && LA42_1 <= 67)||LA42_1==70) ) {
                        alt42=1;
                    }


                }


                switch (alt42) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:256:28: ',' elems+= expression
            	    {
            	    char_literal87=(Token)match(input,42,FOLLOW_42_in_arrayCreation1794); if (state.failed) return retval; 
            	    if ( state.backtracking==0 ) stream_42.add(char_literal87);


            	    pushFollow(FOLLOW_expression_in_arrayCreation1798);
            	    elems=expression();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_expression.add(elems.getTree());
            	    if (list_elems==null) list_elems=new ArrayList();
            	    list_elems.add(elems.getTree());


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:256:52: ( ',' )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==42) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:256:52: ','
                    {
                    char_literal88=(Token)match(input,42,FOLLOW_42_in_arrayCreation1802); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_42.add(char_literal88);


                    }
                    break;

            }


            char_literal89=(Token)match(input,58,FOLLOW_58_in_arrayCreation1805); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(char_literal89);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 256:61: -> ^( EXPRESSION[\"ArrayCreation\"] )
            {
                // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:256:64: ^( EXPRESSION[\"ArrayCreation\"] )
                {
                EvaluationExpression root_1 = (EvaluationExpression)adaptor.nil();
                root_1 = (EvaluationExpression)adaptor.becomeRoot(
                (EvaluationExpression)adaptor.create(EXPRESSION, "ArrayCreation")
                , root_1);

                adaptor.addChild(root_1,  list_elems.toArray(new EvaluationExpression[list_elems.size()]) );

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) { paraphrase.pop(); }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayCreation"


    protected static class operator_scope {
        Operator<?> result;
        int numInputs;
        Map<JsonStream, List<ExpressionTag>> inputTags;
    }
    protected Stack operator_stack = new Stack();


    public static class operator_return extends ParserRuleReturnScope {
        public Operator<?> op=null;
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operator"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:258:1: operator returns [Operator<?> op=null] : opRule= ( readOperator | writeOperator | genericOperator ) ;
    public final MeteorParser.operator_return operator() throws RecognitionException {
        operator_stack.push(new operator_scope());
        MeteorParser.operator_return retval = new MeteorParser.operator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token opRule=null;
        MeteorParser.readOperator_return readOperator90 =null;

        MeteorParser.writeOperator_return writeOperator91 =null;

        MeteorParser.genericOperator_return genericOperator92 =null;


        EvaluationExpression opRule_tree=null;


          if(state.backtracking == 0) 
        	  addScope();
        	((operator_scope)operator_stack.peek()).inputTags = new IdentityHashMap<JsonStream, List<ExpressionTag>>();

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:271:2: (opRule= ( readOperator | writeOperator | genericOperator ) )
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:271:4: opRule= ( readOperator | writeOperator | genericOperator )
            {
            root_0 = (EvaluationExpression)adaptor.nil();


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:271:11: ( readOperator | writeOperator | genericOperator )
            int alt44=3;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt44=1;
                }
                break;
            case 66:
                {
                alt44=2;
                }
                break;
            case ID:
                {
                alt44=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;

            }

            switch (alt44) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:271:12: readOperator
                    {
                    pushFollow(FOLLOW_readOperator_in_operator1842);
                    readOperator90=readOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, readOperator90.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:271:27: writeOperator
                    {
                    pushFollow(FOLLOW_writeOperator_in_operator1846);
                    writeOperator91=writeOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, writeOperator91.getTree());

                    }
                    break;
                case 3 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:271:43: genericOperator
                    {
                    pushFollow(FOLLOW_genericOperator_in_operator1850);
                    genericOperator92=genericOperator();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, genericOperator92.getTree());

                    }
                    break;

            }


            if ( state.backtracking==0 ) { 
              retval.op = ((operator_scope)operator_stack.peek()).result;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
            if ( state.backtracking==0 ) {
              removeScope();
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            operator_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "operator"


    public static class readOperator_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "readOperator"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:276:1: readOperator : 'read' 'from' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->;
    public final MeteorParser.readOperator_return readOperator() throws RecognitionException {
        MeteorParser.readOperator_return retval = new MeteorParser.readOperator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token loc=null;
        Token file=null;
        Token string_literal93=null;
        Token string_literal94=null;
        Token char_literal95=null;
        Token char_literal96=null;

        EvaluationExpression loc_tree=null;
        EvaluationExpression file_tree=null;
        EvaluationExpression string_literal93_tree=null;
        EvaluationExpression string_literal94_tree=null;
        EvaluationExpression char_literal95_tree=null;
        EvaluationExpression char_literal96_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_62=new RewriteRuleTokenStream(adaptor,"token 62");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:2: ( 'read' 'from' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:4: 'read' 'from' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            {
            string_literal93=(Token)match(input,62,FOLLOW_62_in_readOperator1864); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_62.add(string_literal93);


            string_literal94=(Token)match(input,60,FOLLOW_60_in_readOperator1866); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_60.add(string_literal94);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:18: ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==ID) ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==38) ) {
                    alt46=2;
                }
                else if ( (LA46_1==STRING) ) {
                    alt46=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA46_0==STRING) ) {
                alt46=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;

            }
            switch (alt46) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:19: (loc= ID )? file= STRING
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:22: (loc= ID )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( (LA45_0==ID) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:22: loc= ID
                            {
                            loc=(Token)match(input,ID,FOLLOW_ID_in_readOperator1871); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ID.add(loc);


                            }
                            break;

                    }


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_readOperator1876); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:277:41: loc= ID '(' file= STRING ')'
                    {
                    loc=(Token)match(input,ID,FOLLOW_ID_in_readOperator1882); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(loc);


                    char_literal95=(Token)match(input,38,FOLLOW_38_in_readOperator1884); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_38.add(char_literal95);


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_readOperator1888); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    char_literal96=(Token)match(input,39,FOLLOW_39_in_readOperator1890); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_39.add(char_literal96);


                    }
                    break;

            }


            if ( state.backtracking==0 ) { ((operator_scope)operator_stack.peek()).result = new Source(JsonInputFormat.class, (file!=null?file.getText():null)); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 277:140: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "readOperator"


    public static class writeOperator_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "writeOperator"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:279:1: writeOperator : 'write' from= VAR 'to' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->;
    public final MeteorParser.writeOperator_return writeOperator() throws RecognitionException {
        MeteorParser.writeOperator_return retval = new MeteorParser.writeOperator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token from=null;
        Token loc=null;
        Token file=null;
        Token string_literal97=null;
        Token string_literal98=null;
        Token char_literal99=null;
        Token char_literal100=null;

        EvaluationExpression from_tree=null;
        EvaluationExpression loc_tree=null;
        EvaluationExpression file_tree=null;
        EvaluationExpression string_literal97_tree=null;
        EvaluationExpression string_literal98_tree=null;
        EvaluationExpression char_literal99_tree=null;
        EvaluationExpression char_literal100_tree=null;
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_39=new RewriteRuleTokenStream(adaptor,"token 39");
        RewriteRuleTokenStream stream_STRING=new RewriteRuleTokenStream(adaptor,"token STRING");
        RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:280:2: ( 'write' from= VAR 'to' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' ) ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:280:4: 'write' from= VAR 'to' ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            {
            string_literal97=(Token)match(input,66,FOLLOW_66_in_writeOperator1904); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_66.add(string_literal97);


            from=(Token)match(input,VAR,FOLLOW_VAR_in_writeOperator1908); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(from);


            string_literal98=(Token)match(input,63,FOLLOW_63_in_writeOperator1910); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_63.add(string_literal98);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:280:26: ( (loc= ID )? file= STRING |loc= ID '(' file= STRING ')' )
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==ID) ) {
                int LA48_1 = input.LA(2);

                if ( (LA48_1==38) ) {
                    alt48=2;
                }
                else if ( (LA48_1==STRING) ) {
                    alt48=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 48, 1, input);

                    throw nvae;

                }
            }
            else if ( (LA48_0==STRING) ) {
                alt48=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;

            }
            switch (alt48) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:280:27: (loc= ID )? file= STRING
                    {
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:280:30: (loc= ID )?
                    int alt47=2;
                    int LA47_0 = input.LA(1);

                    if ( (LA47_0==ID) ) {
                        alt47=1;
                    }
                    switch (alt47) {
                        case 1 :
                            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:280:30: loc= ID
                            {
                            loc=(Token)match(input,ID,FOLLOW_ID_in_writeOperator1915); if (state.failed) return retval; 
                            if ( state.backtracking==0 ) stream_ID.add(loc);


                            }
                            break;

                    }


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_writeOperator1920); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:280:49: loc= ID '(' file= STRING ')'
                    {
                    loc=(Token)match(input,ID,FOLLOW_ID_in_writeOperator1926); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(loc);


                    char_literal99=(Token)match(input,38,FOLLOW_38_in_writeOperator1928); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_38.add(char_literal99);


                    file=(Token)match(input,STRING,FOLLOW_STRING_in_writeOperator1932); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_STRING.add(file);


                    char_literal100=(Token)match(input,39,FOLLOW_39_in_writeOperator1934); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_39.add(char_literal100);


                    }
                    break;

            }


            if ( state.backtracking==0 ) { 
            	Sink sink = new Sink(JsonOutputFormat.class, (file!=null?file.getText():null));
              ((operator_scope)operator_stack.peek()).result = sink;
              sink.setInputs(getVariable(from).getStream());
              this.sinks.add(sink);
            }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 286:3: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "writeOperator"


    protected static class genericOperator_scope {
        OperatorInfo<?> operatorInfo;
    }
    protected Stack genericOperator_stack = new Stack();


    public static class genericOperator_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "genericOperator"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:288:1: genericOperator : (packageName= ID ':' )? name= ID {...}? => ( operatorFlag )* ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* ) ( operatorOption )* ->;
    public final MeteorParser.genericOperator_return genericOperator() throws RecognitionException {
        genericOperator_stack.push(new genericOperator_scope());
        MeteorParser.genericOperator_return retval = new MeteorParser.genericOperator_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token packageName=null;
        Token name=null;
        Token char_literal101=null;
        Token char_literal105=null;
        MeteorParser.operatorFlag_return operatorFlag102 =null;

        MeteorParser.arrayInput_return arrayInput103 =null;

        MeteorParser.input_return input104 =null;

        MeteorParser.input_return input106 =null;

        MeteorParser.operatorOption_return operatorOption107 =null;


        EvaluationExpression packageName_tree=null;
        EvaluationExpression name_tree=null;
        EvaluationExpression char_literal101_tree=null;
        EvaluationExpression char_literal105_tree=null;
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleTokenStream stream_47=new RewriteRuleTokenStream(adaptor,"token 47");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_operatorOption=new RewriteRuleSubtreeStream(adaptor,"rule operatorOption");
        RewriteRuleSubtreeStream stream_input=new RewriteRuleSubtreeStream(adaptor,"rule input");
        RewriteRuleSubtreeStream stream_operatorFlag=new RewriteRuleSubtreeStream(adaptor,"rule operatorFlag");
        RewriteRuleSubtreeStream stream_arrayInput=new RewriteRuleSubtreeStream(adaptor,"rule arrayInput");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:291:3: ( (packageName= ID ':' )? name= ID {...}? => ( operatorFlag )* ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* ) ( operatorOption )* ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:291:5: (packageName= ID ':' )? name= ID {...}? => ( operatorFlag )* ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* ) ( operatorOption )*
            {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:291:5: (packageName= ID ':' )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==ID) ) {
                int LA49_1 = input.LA(2);

                if ( (LA49_1==47) ) {
                    alt49=1;
                }
            }
            switch (alt49) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:291:6: packageName= ID ':'
                    {
                    packageName=(Token)match(input,ID,FOLLOW_ID_in_genericOperator1955); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_ID.add(packageName);


                    char_literal101=(Token)match(input,47,FOLLOW_47_in_genericOperator1957); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_47.add(char_literal101);


                    }
                    break;

            }


            name=(Token)match(input,ID,FOLLOW_ID_in_genericOperator1963); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            if ( !(( (((genericOperator_scope)genericOperator_stack.peek()).operatorInfo = findOperatorGreedily((packageName!=null?packageName.getText():null), name)) != null )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "genericOperator", " ($genericOperator::operatorInfo = findOperatorGreedily($packageName.text, $name)) != null ");
            }

            if ( state.backtracking==0 ) { ((operator_scope)operator_stack.peek()).result = ((genericOperator_scope)genericOperator_stack.peek()).operatorInfo.newInstance(); }

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:293:1: ( operatorFlag )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==ID) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:293:1: operatorFlag
            	    {
            	    pushFollow(FOLLOW_operatorFlag_in_genericOperator1971);
            	    operatorFlag102=operatorFlag();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_operatorFlag.add(operatorFlag102.getTree());

            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:2: ( ( '[' )=> arrayInput | ( VAR )=> input ( ( ',' )=> ',' input )* )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==57) && (synpred14_Meteor())) {
                alt52=1;
            }
            else if ( (LA52_0==VAR) && (synpred15_Meteor())) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;

            }
            switch (alt52) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:2: ( '[' )=> arrayInput
                    {
                    pushFollow(FOLLOW_arrayInput_in_genericOperator1980);
                    arrayInput103=arrayInput();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_arrayInput.add(arrayInput103.getTree());

                    }
                    break;
                case 2 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:23: ( VAR )=> input ( ( ',' )=> ',' input )*
                    {
                    pushFollow(FOLLOW_input_in_genericOperator1989);
                    input104=input();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_input.add(input104.getTree());

                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:37: ( ( ',' )=> ',' input )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==42) ) {
                            int LA51_2 = input.LA(2);

                            if ( (LA51_2==VAR) ) {
                                int LA51_3 = input.LA(3);

                                if ( (synpred16_Meteor()) ) {
                                    alt51=1;
                                }


                            }


                        }


                        switch (alt51) {
                    	case 1 :
                    	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:38: ( ',' )=> ',' input
                    	    {
                    	    char_literal105=(Token)match(input,42,FOLLOW_42_in_genericOperator1997); if (state.failed) return retval; 
                    	    if ( state.backtracking==0 ) stream_42.add(char_literal105);


                    	    pushFollow(FOLLOW_input_in_genericOperator1999);
                    	    input106=input();

                    	    state._fsp--;
                    	    if (state.failed) return retval;
                    	    if ( state.backtracking==0 ) stream_input.add(input106.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);


                    }
                    break;

            }


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:295:1: ( operatorOption )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==ID) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:295:1: operatorOption
            	    {
            	    pushFollow(FOLLOW_operatorOption_in_genericOperator2004);
            	    operatorOption107=operatorOption();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) stream_operatorOption.add(operatorOption107.getTree());

            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 295:17: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            genericOperator_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "genericOperator"


    protected static class operatorOption_scope {
        OperatorInfo.OperatorPropertyInfo property;
    }
    protected Stack operatorOption_stack = new Stack();


    public static class operatorOption_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operatorOption"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:297:1: operatorOption : name= ID expr= contextAwareExpression[null] ->;
    public final MeteorParser.operatorOption_return operatorOption() throws RecognitionException {
        operatorOption_stack.push(new operatorOption_scope());
        MeteorParser.operatorOption_return retval = new MeteorParser.operatorOption_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        MeteorParser.contextAwareExpression_return expr =null;


        EvaluationExpression name_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_contextAwareExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwareExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:300:3: (name= ID expr= contextAwareExpression[null] ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:301:3: name= ID expr= contextAwareExpression[null]
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_operatorOption2026); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            if ( state.backtracking==0 ) { ((operatorOption_scope)operatorOption_stack.peek()).property = findOperatorPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, name); }

            pushFollow(FOLLOW_contextAwareExpression_in_operatorOption2035);
            expr=contextAwareExpression(null);

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) stream_contextAwareExpression.add(expr.getTree());

            if ( state.backtracking==0 ) { ((operatorOption_scope)operatorOption_stack.peek()).property.setValue(((operator_scope)operator_stack.peek()).result, (expr!=null?((EvaluationExpression)expr.tree):null)); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 303:108: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            operatorOption_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "operatorOption"


    protected static class operatorFlag_scope {
        OperatorInfo.OperatorPropertyInfo property;
    }
    protected Stack operatorFlag_stack = new Stack();


    public static class operatorFlag_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "operatorFlag"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:305:1: operatorFlag : name= ID {...}? ->;
    public final MeteorParser.operatorFlag_return operatorFlag() throws RecognitionException {
        operatorFlag_stack.push(new operatorFlag_scope());
        MeteorParser.operatorFlag_return retval = new MeteorParser.operatorFlag_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;

        EvaluationExpression name_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:309:3: (name= ID {...}? ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:309:5: name= ID {...}?
            {
            name=(Token)match(input,ID,FOLLOW_ID_in_operatorFlag2056); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_ID.add(name);


            if ( !(( (((operatorFlag_scope)operatorFlag_stack.peek()).property = findOperatorPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, name)) != null )) ) {
                if (state.backtracking>0) {state.failed=true; return retval;}
                throw new FailedPredicateException(input, "operatorFlag", " ($operatorFlag::property = findOperatorPropertyRelunctantly($genericOperator::operatorInfo, $name)) != null ");
            }

            if ( state.backtracking==0 ) { if(!((operatorFlag_scope)operatorFlag_stack.peek()).property.isFlag())
                throw new QueryParserException(String.format("Property %s is not a flag", (name!=null?name.getText():null)), name);
              ((operatorFlag_scope)operatorFlag_stack.peek()).property.setValue(((operator_scope)operator_stack.peek()).result, true); }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 312:64: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            operatorFlag_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "operatorFlag"


    protected static class input_scope {
        OperatorInfo.InputPropertyInfo inputProperty;
    }
    protected Stack input_stack = new Stack();


    public static class input_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "input"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:314:1: input : (name= VAR IN )? from= VAR ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )? ->;
    public final MeteorParser.input_return input() throws RecognitionException {
        input_stack.push(new input_scope());
        MeteorParser.input_return retval = new MeteorParser.input_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token name=null;
        Token from=null;
        Token IN108=null;
        MeteorParser.contextAwareExpression_return expr =null;


        EvaluationExpression name_tree=null;
        EvaluationExpression from_tree=null;
        EvaluationExpression IN108_tree=null;
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
        RewriteRuleSubtreeStream stream_contextAwareExpression=new RewriteRuleSubtreeStream(adaptor,"rule contextAwareExpression");
        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:317:3: ( (name= VAR IN )? from= VAR ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )? ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:317:5: (name= VAR IN )? from= VAR ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )?
            {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:317:5: (name= VAR IN )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==VAR) ) {
                int LA54_1 = input.LA(2);

                if ( (LA54_1==IN) ) {
                    alt54=1;
                }
            }
            switch (alt54) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:317:6: name= VAR IN
                    {
                    name=(Token)match(input,VAR,FOLLOW_VAR_in_input2079); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(name);


                    IN108=(Token)match(input,IN,FOLLOW_IN_in_input2081); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_IN.add(IN108);


                    }
                    break;

            }


            from=(Token)match(input,VAR,FOLLOW_VAR_in_input2087); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(from);


            if ( state.backtracking==0 ) { 
              int inputIndex = ((operator_scope)operator_stack.peek()).numInputs++;
              JsonStreamExpression input = getVariable(from);
              ((operator_scope)operator_stack.peek()).result.setInput(inputIndex, input.getStream());
              
              JsonStreamExpression inputExpression = new JsonStreamExpression(input.getStream(), inputIndex);
              putVariable(name != null ? name : from, inputExpression);
            }

            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:326:2: ({...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)] )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==DECIMAL||LA55_0==INTEGER||(LA55_0 >= STRING && LA55_0 <= UINT)||LA55_0==VAR||LA55_0==35||LA55_0==38||LA55_0==41||LA55_0==44||LA55_0==57||LA55_0==59||LA55_0==61||LA55_0==64||LA55_0==67||LA55_0==70) && (( (((input_scope)input_stack.peek()).inputProperty = findInputPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, input.LT(1))) != null ))) {
                alt55=1;
            }
            else if ( (LA55_0==ID) ) {
                int LA55_5 = input.LA(2);

                if ( (( (((input_scope)input_stack.peek()).inputProperty = findInputPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, input.LT(1))) != null )) ) {
                    alt55=1;
                }
            }
            switch (alt55) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:326:2: {...}? =>expr= contextAwareExpression[new InputSelection($operator::numInputs - 1)]
                    {
                    if ( !(( (((input_scope)input_stack.peek()).inputProperty = findInputPropertyRelunctantly(((genericOperator_scope)genericOperator_stack.peek()).operatorInfo, input.LT(1))) != null )) ) {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        throw new FailedPredicateException(input, "input", " ($input::inputProperty = findInputPropertyRelunctantly($genericOperator::operatorInfo, input.LT(1))) != null ");
                    }

                    if ( state.backtracking==0 ) { this.input.consume(); }

                    pushFollow(FOLLOW_contextAwareExpression_in_input2106);
                    expr=contextAwareExpression(new InputSelection(((operator_scope)operator_stack.peek()).numInputs - 1));

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) stream_contextAwareExpression.add(expr.getTree());

                    if ( state.backtracking==0 ) { ((input_scope)input_stack.peek()).inputProperty.setValue(((operator_scope)operator_stack.peek()).result, ((operator_scope)operator_stack.peek()).numInputs-1, (expr!=null?((EvaluationExpression)expr.tree):null)); }

                    }
                    break;

            }


            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 329:4: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
            input_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "input"


    public static class arrayInput_return extends ParserRuleReturnScope {
        EvaluationExpression tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "arrayInput"
    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:331:1: arrayInput : '[' names+= VAR ( ',' names+= VAR )? ']' 'in' from= VAR ->;
    public final MeteorParser.arrayInput_return arrayInput() throws RecognitionException {
        MeteorParser.arrayInput_return retval = new MeteorParser.arrayInput_return();
        retval.start = input.LT(1);


        EvaluationExpression root_0 = null;

        Token from=null;
        Token char_literal109=null;
        Token char_literal110=null;
        Token char_literal111=null;
        Token string_literal112=null;
        Token names=null;
        List list_names=null;

        EvaluationExpression from_tree=null;
        EvaluationExpression char_literal109_tree=null;
        EvaluationExpression char_literal110_tree=null;
        EvaluationExpression char_literal111_tree=null;
        EvaluationExpression string_literal112_tree=null;
        EvaluationExpression names_tree=null;
        RewriteRuleTokenStream stream_58=new RewriteRuleTokenStream(adaptor,"token 58");
        RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
        RewriteRuleTokenStream stream_IN=new RewriteRuleTokenStream(adaptor,"token IN");
        RewriteRuleTokenStream stream_42=new RewriteRuleTokenStream(adaptor,"token 42");
        RewriteRuleTokenStream stream_57=new RewriteRuleTokenStream(adaptor,"token 57");

        try {
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:332:3: ( '[' names+= VAR ( ',' names+= VAR )? ']' 'in' from= VAR ->)
            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:332:5: '[' names+= VAR ( ',' names+= VAR )? ']' 'in' from= VAR
            {
            char_literal109=(Token)match(input,57,FOLLOW_57_in_arrayInput2124); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_57.add(char_literal109);


            names=(Token)match(input,VAR,FOLLOW_VAR_in_arrayInput2128); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(names);

            if (list_names==null) list_names=new ArrayList();
            list_names.add(names);


            // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:332:20: ( ',' names+= VAR )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==42) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:332:21: ',' names+= VAR
                    {
                    char_literal110=(Token)match(input,42,FOLLOW_42_in_arrayInput2131); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_42.add(char_literal110);


                    names=(Token)match(input,VAR,FOLLOW_VAR_in_arrayInput2135); if (state.failed) return retval; 
                    if ( state.backtracking==0 ) stream_VAR.add(names);

                    if (list_names==null) list_names=new ArrayList();
                    list_names.add(names);


                    }
                    break;

            }


            char_literal111=(Token)match(input,58,FOLLOW_58_in_arrayInput2139); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_58.add(char_literal111);


            string_literal112=(Token)match(input,IN,FOLLOW_IN_in_arrayInput2141); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_IN.add(string_literal112);


            from=(Token)match(input,VAR,FOLLOW_VAR_in_arrayInput2145); if (state.failed) return retval; 
            if ( state.backtracking==0 ) stream_VAR.add(from);


            if ( state.backtracking==0 ) { 
              ((operator_scope)operator_stack.peek()).result.setInput(0, getVariable(from).getStream());
              for(int index = 0; index < list_names.size(); index++) {
            	  putVariable((Token) list_names.get(index), new JsonStreamExpression(null, index)); 
              }
            }

            // AST REWRITE
            // elements: 
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            if ( state.backtracking==0 ) {

            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (EvaluationExpression)adaptor.nil();
            // 338:3: ->
            {
                root_0 = null;
            }


            retval.tree = root_0;
            }

            }

            retval.stop = input.LT(-1);


            if ( state.backtracking==0 ) {

            retval.tree = (EvaluationExpression)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }

        catch (RecognitionException e) {
          throw e;
        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "arrayInput"

    // $ANTLR start synpred1_Meteor
    public final void synpred1_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:105:5: ( operatorExpression )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:105:6: operatorExpression
        {
        pushFollow(FOLLOW_operatorExpression_in_synpred1_Meteor353);
        operatorExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred1_Meteor

    // $ANTLR start synpred2_Meteor
    public final void synpred2_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:109:4: ( orExpression '?' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:109:5: orExpression '?'
        {
        pushFollow(FOLLOW_orExpression_in_synpred2_Meteor373);
        orExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,55,FOLLOW_55_in_synpred2_Meteor375); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred2_Meteor

    // $ANTLR start synpred3_Meteor
    public final void synpred3_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:111:4: ( orExpression IF )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:111:5: orExpression IF
        {
        pushFollow(FOLLOW_orExpression_in_synpred3_Meteor414);
        orExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,IF,FOLLOW_IF_in_synpred3_Meteor416); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred3_Meteor

    // $ANTLR start synpred4_Meteor
    public final void synpred4_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:159:4: ( '(' ID ')' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:159:5: '(' ID ')'
        {
        match(input,38,FOLLOW_38_in_synpred4_Meteor883); if (state.failed) return ;

        match(input,ID,FOLLOW_ID_in_synpred4_Meteor885); if (state.failed) return ;

        match(input,39,FOLLOW_39_in_synpred4_Meteor887); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred4_Meteor

    // $ANTLR start synpred5_Meteor
    public final void synpred5_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:161:4: ( generalPathExpression AS )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:161:5: generalPathExpression AS
        {
        pushFollow(FOLLOW_generalPathExpression_in_synpred5_Meteor913);
        generalPathExpression();

        state._fsp--;
        if (state.failed) return ;

        match(input,AS,FOLLOW_AS_in_synpred5_Meteor915); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred5_Meteor

    // $ANTLR start synpred6_Meteor
    public final void synpred6_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:167:5: ( pathExpression )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:167:6: pathExpression
        {
        pushFollow(FOLLOW_pathExpression_in_synpred6_Meteor958);
        pathExpression();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred6_Meteor

    // $ANTLR start synpred7_Meteor
    public final void synpred7_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:8: ( '?.' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:179:9: '?.'
        {
        match(input,56,FOLLOW_56_in_synpred7_Meteor1032); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred7_Meteor

    // $ANTLR start synpred8_Meteor
    public final void synpred8_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:12: ( '.' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:180:13: '.'
        {
        match(input,45,FOLLOW_45_in_synpred8_Meteor1061); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred8_Meteor

    // $ANTLR start synpred9_Meteor
    public final void synpred9_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:181:11: ( '[' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:181:12: '['
        {
        match(input,57,FOLLOW_57_in_synpred9_Meteor1089); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred9_Meteor

    // $ANTLR start synpred10_Meteor
    public final void synpred10_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:185:4: ( ID '(' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:185:5: ID '('
        {
        match(input,ID,FOLLOW_ID_in_synpred10_Meteor1115); if (state.failed) return ;

        match(input,38,FOLLOW_38_in_synpred10_Meteor1117); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred10_Meteor

    // $ANTLR start synpred11_Meteor
    public final void synpred11_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:188:4: ( VAR '[' VAR )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:188:5: VAR '[' VAR
        {
        match(input,VAR,FOLLOW_VAR_in_synpred11_Meteor1140); if (state.failed) return ;

        match(input,57,FOLLOW_57_in_synpred11_Meteor1142); if (state.failed) return ;

        match(input,VAR,FOLLOW_VAR_in_synpred11_Meteor1144); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred11_Meteor

    // $ANTLR start synpred12_Meteor
    public final void synpred12_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:190:6: ( ID ':' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:190:7: ID ':'
        {
        match(input,ID,FOLLOW_ID_in_synpred12_Meteor1165); if (state.failed) return ;

        match(input,47,FOLLOW_47_in_synpred12_Meteor1167); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred12_Meteor

    // $ANTLR start synpred13_Meteor
    public final void synpred13_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:211:5: ( ID ':' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:211:6: ID ':'
        {
        match(input,ID,FOLLOW_ID_in_synpred13_Meteor1327); if (state.failed) return ;

        match(input,47,FOLLOW_47_in_synpred13_Meteor1329); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred13_Meteor

    // $ANTLR start synpred14_Meteor
    public final void synpred14_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:2: ( '[' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:3: '['
        {
        match(input,57,FOLLOW_57_in_synpred14_Meteor1976); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred14_Meteor

    // $ANTLR start synpred15_Meteor
    public final void synpred15_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:23: ( VAR )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:24: VAR
        {
        match(input,VAR,FOLLOW_VAR_in_synpred15_Meteor1985); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred15_Meteor

    // $ANTLR start synpred16_Meteor
    public final void synpred16_Meteor_fragment() throws RecognitionException {
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:38: ( ',' )
        // /home/arv/workspace/private/meteor/meteor-meteor/src/main/java/eu/stratosphere/meteor/Meteor.g:294:39: ','
        {
        match(input,42,FOLLOW_42_in_synpred16_Meteor1993); if (state.failed) return ;

        }

    }
    // $ANTLR end synpred16_Meteor

    // Delegated rules

    public final boolean synpred14_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_Meteor() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_Meteor_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_statement_in_script121 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_script123 = new BitSet(new long[]{0x4000000200010002L,0x0000000000000006L});
    public static final BitSet FOLLOW_assignment_in_statement137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_statement141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_packageImport_in_statement145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionDefinition_in_statement149 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_javaudf_in_statement153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_packageImport168 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_packageImport172 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_packageImport183 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_packageImport187 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_VAR_in_assignment204 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_assignment206 = new BitSet(new long[]{0x4000000000010000L,0x0000000000000004L});
    public static final BitSet FOLLOW_operator_in_assignment210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_functionDefinition232 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_functionDefinition234 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_FN_in_functionDefinition236 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_functionDefinition238 = new BitSet(new long[]{0x0000008000010000L});
    public static final BitSet FOLLOW_ID_in_functionDefinition247 = new BitSet(new long[]{0x0000048000000000L});
    public static final BitSet FOLLOW_42_in_functionDefinition254 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_functionDefinition258 = new BitSet(new long[]{0x0000048000000000L});
    public static final BitSet FOLLOW_39_in_functionDefinition269 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_contextAwareExpression_in_functionDefinition281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_javaudf302 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_javaudf304 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_JAVAUDF_in_javaudf306 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_javaudf308 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_javaudf312 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_javaudf314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternaryExpression_in_contextAwareExpression342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operatorExpression_in_expression357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternaryExpression_in_expression363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression381 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_ternaryExpression383 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression387 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_ternaryExpression389 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression422 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IF_in_ternaryExpression424 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_ternaryExpression451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andExpression_in_orExpression464 = new BitSet(new long[]{0x0000000002000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_OR_in_orExpression468 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_68_in_orExpression472 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_andExpression_in_orExpression477 = new BitSet(new long[]{0x0000000002000002L,0x0000000000000010L});
    public static final BitSet FOLLOW_elementExpression_in_andExpression506 = new BitSet(new long[]{0x0000002000000012L});
    public static final BitSet FOLLOW_AND_in_andExpression510 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_37_in_andExpression514 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_elementExpression_in_andExpression519 = new BitSet(new long[]{0x0000002000000012L});
    public static final BitSet FOLLOW_comparisonExpression_in_elementExpression548 = new BitSet(new long[]{0x0000000000440002L});
    public static final BitSet FOLLOW_NOT_in_elementExpression553 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_IN_in_elementExpression556 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_comparisonExpression_in_elementExpression560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arithmeticExpression_in_comparisonExpression601 = new BitSet(new long[]{0x0076001000000002L});
    public static final BitSet FOLLOW_50_in_comparisonExpression607 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_54_in_comparisonExpression613 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_49_in_comparisonExpression619 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_53_in_comparisonExpression625 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_52_in_comparisonExpression631 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_36_in_comparisonExpression637 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_arithmeticExpression_in_comparisonExpression642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiplicationExpression_in_arithmeticExpression722 = new BitSet(new long[]{0x0000090000000002L});
    public static final BitSet FOLLOW_40_in_arithmeticExpression728 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_43_in_arithmeticExpression734 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_multiplicationExpression_in_arithmeticExpression739 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_preincrementExpression_in_multiplicationExpression782 = new BitSet(new long[]{0x0000400010000002L});
    public static final BitSet FOLLOW_STAR_in_multiplicationExpression788 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_46_in_multiplicationExpression794 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_preincrementExpression_in_multiplicationExpression799 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_preincrementExpression840 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_preincrementExpression_in_preincrementExpression842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_preincrementExpression847 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_preincrementExpression_in_preincrementExpression849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryExpression_in_preincrementExpression854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_castExpression_in_unaryExpression873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_castExpression891 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_castExpression895 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_castExpression897 = new BitSet(new long[]{0x2A00004260090100L,0x0000000000000009L});
    public static final BitSet FOLLOW_generalPathExpression_in_castExpression901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generalPathExpression_in_castExpression921 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_AS_in_castExpression923 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_castExpression927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generalPathExpression_in_castExpression938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_valueExpression_in_generalPathExpression950 = new BitSet(new long[]{0x0300200000000002L});
    public static final BitSet FOLLOW_pathExpression_in_generalPathExpression964 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathExpression_in_contextAwarePathExpression993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_pathExpression1036 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_pathExpression1041 = new BitSet(new long[]{0x0300200000000002L});
    public static final BitSet FOLLOW_45_in_pathExpression1065 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_pathExpression1070 = new BitSet(new long[]{0x0300200000000002L});
    public static final BitSet FOLLOW_arrayAccess_in_pathExpression1093 = new BitSet(new long[]{0x0300200000000002L});
    public static final BitSet FOLLOW_methodCall_in_valueExpression1121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parenthesesExpression_in_valueExpression1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_valueExpression1133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_streamIndexAccess_in_valueExpression1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_valueExpression1153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_valueExpression1173 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_valueExpression1175 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_valueExpression1181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayCreation_in_valueExpression1201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_objectCreation_in_valueExpression1207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operator_in_operatorExpression1220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_parenthesesExpression1241 = new BitSet(new long[]{0x6A00124A60090100L,0x000000000000004DL});
    public static final BitSet FOLLOW_expression_in_parenthesesExpression1243 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_parenthesesExpression1245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_methodCall1274 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_methodCall1276 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_methodCall1282 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_methodCall1284 = new BitSet(new long[]{0x6A0012CA60090100L,0x000000000000004DL});
    public static final BitSet FOLLOW_expression_in_methodCall1291 = new BitSet(new long[]{0x0000048000000000L});
    public static final BitSet FOLLOW_42_in_methodCall1297 = new BitSet(new long[]{0x6A00124A60090100L,0x000000000000004DL});
    public static final BitSet FOLLOW_expression_in_methodCall1301 = new BitSet(new long[]{0x0000048000000000L});
    public static final BitSet FOLLOW_39_in_methodCall1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_fieldAssignment1333 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_fieldAssignment1335 = new BitSet(new long[]{0x6A00124A60090100L,0x000000000000004DL});
    public static final BitSet FOLLOW_expression_in_fieldAssignment1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_fieldAssignment1354 = new BitSet(new long[]{0x0308200000000000L});
    public static final BitSet FOLLOW_45_in_fieldAssignment1363 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_STAR_in_fieldAssignment1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_fieldAssignment1379 = new BitSet(new long[]{0x4000000000010000L,0x0000000000000004L});
    public static final BitSet FOLLOW_operator_in_fieldAssignment1383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_contextAwarePathExpression_in_fieldAssignment1398 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_47_in_fieldAssignment1409 = new BitSet(new long[]{0x6A00124A60090100L,0x000000000000004DL});
    public static final BitSet FOLLOW_expression_in_fieldAssignment1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_objectCreation1478 = new BitSet(new long[]{0x0000000200010000L,0x0000000000000020L});
    public static final BitSet FOLLOW_fieldAssignment_in_objectCreation1481 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_42_in_objectCreation1484 = new BitSet(new long[]{0x0000000200010000L});
    public static final BitSet FOLLOW_fieldAssignment_in_objectCreation1486 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_42_in_objectCreation1490 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_objectCreation1495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_literal1525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_literal1541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DECIMAL_in_literal1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_literal1573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UINT_in_literal1591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_literal1597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_literal1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_arrayAccess1627 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_STAR_in_arrayAccess1629 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_arrayAccess1631 = new BitSet(new long[]{0x0300200000000000L});
    public static final BitSet FOLLOW_pathExpression_in_arrayAccess1635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_arrayAccess1655 = new BitSet(new long[]{0x0000000040080000L});
    public static final BitSet FOLLOW_INTEGER_in_arrayAccess1660 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_UINT_in_arrayAccess1666 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_arrayAccess1669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_arrayAccess1687 = new BitSet(new long[]{0x0000000040080000L});
    public static final BitSet FOLLOW_INTEGER_in_arrayAccess1692 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_UINT_in_arrayAccess1698 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_arrayAccess1701 = new BitSet(new long[]{0x0000000040080000L});
    public static final BitSet FOLLOW_INTEGER_in_arrayAccess1706 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_UINT_in_arrayAccess1712 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_arrayAccess1715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_streamIndexAccess1743 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_streamIndexAccess1752 = new BitSet(new long[]{0x2A00004260090100L,0x0000000000000009L});
    public static final BitSet FOLLOW_generalPathExpression_in_streamIndexAccess1756 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_streamIndexAccess1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_arrayCreation1787 = new BitSet(new long[]{0x6A00124A60090100L,0x000000000000004DL});
    public static final BitSet FOLLOW_expression_in_arrayCreation1791 = new BitSet(new long[]{0x0400040000000000L});
    public static final BitSet FOLLOW_42_in_arrayCreation1794 = new BitSet(new long[]{0x6A00124A60090100L,0x000000000000004DL});
    public static final BitSet FOLLOW_expression_in_arrayCreation1798 = new BitSet(new long[]{0x0400040000000000L});
    public static final BitSet FOLLOW_42_in_arrayCreation1802 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_arrayCreation1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_readOperator_in_operator1842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_writeOperator_in_operator1846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_genericOperator_in_operator1850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_readOperator1864 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_readOperator1866 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_ID_in_readOperator1871 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_readOperator1876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_readOperator1882 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_readOperator1884 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_readOperator1888 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_readOperator1890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_writeOperator1904 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_writeOperator1908 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_writeOperator1910 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_ID_in_writeOperator1915 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_writeOperator1920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_writeOperator1926 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_writeOperator1928 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_STRING_in_writeOperator1932 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_writeOperator1934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_genericOperator1955 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_genericOperator1957 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_genericOperator1963 = new BitSet(new long[]{0x0200000200010000L});
    public static final BitSet FOLLOW_operatorFlag_in_genericOperator1971 = new BitSet(new long[]{0x0200000200010000L});
    public static final BitSet FOLLOW_arrayInput_in_genericOperator1980 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_input_in_genericOperator1989 = new BitSet(new long[]{0x0000040000010002L});
    public static final BitSet FOLLOW_42_in_genericOperator1997 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_input_in_genericOperator1999 = new BitSet(new long[]{0x0000040000010002L});
    public static final BitSet FOLLOW_operatorOption_in_genericOperator2004 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ID_in_operatorOption2026 = new BitSet(new long[]{0x2A00124A60090100L,0x0000000000000049L});
    public static final BitSet FOLLOW_contextAwareExpression_in_operatorOption2035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operatorFlag2056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_input2079 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_IN_in_input2081 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_input2087 = new BitSet(new long[]{0x2A00124A60090102L,0x0000000000000049L});
    public static final BitSet FOLLOW_contextAwareExpression_in_input2106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_arrayInput2124 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_arrayInput2128 = new BitSet(new long[]{0x0400040000000000L});
    public static final BitSet FOLLOW_42_in_arrayInput2131 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_arrayInput2135 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_arrayInput2139 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_IN_in_arrayInput2141 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_arrayInput2145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operatorExpression_in_synpred1_Meteor353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_synpred2_Meteor373 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_55_in_synpred2_Meteor375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_orExpression_in_synpred3_Meteor414 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IF_in_synpred3_Meteor416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_synpred4_Meteor883 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_synpred4_Meteor885 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_synpred4_Meteor887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_generalPathExpression_in_synpred5_Meteor913 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_AS_in_synpred5_Meteor915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pathExpression_in_synpred6_Meteor958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_synpred7_Meteor1032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_synpred8_Meteor1061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_synpred9_Meteor1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred10_Meteor1115 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_synpred10_Meteor1117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_synpred11_Meteor1140 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_synpred11_Meteor1142 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_VAR_in_synpred11_Meteor1144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred12_Meteor1165 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_synpred12_Meteor1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_synpred13_Meteor1327 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_synpred13_Meteor1329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_synpred14_Meteor1976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_synpred15_Meteor1985 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_synpred16_Meteor1993 = new BitSet(new long[]{0x0000000000000002L});

}