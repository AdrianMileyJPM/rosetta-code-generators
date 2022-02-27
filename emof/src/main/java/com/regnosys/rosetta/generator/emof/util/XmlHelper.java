package com.regnosys.rosetta.generator.emof.util ;

import java.util.Objects;

public class XmlHelper {
    public static String ECORE_NS = "" ;
    public static String LINE_SEPARATOR = System.getProperty ( "line.separator") ;
    public static String TAG_SEPARATOR = " " ; // System.getProperty ( "line.separator") ;

    public XmlHelper() { /* do nothing */ }

    public static String documentHeader ( String modelName , String version ) {
        return new StringBuilder()
            .append ( "<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
            .append ( LINE_SEPARATOR )
            .append ( "<xmi:XMI xmlns:xmi=\"http://schema.omg.org/spec/XMI/2.1\">")
            .append ( LINE_SEPARATOR )
            .append ( "<emof:Package xmlns:emof=\"http://schema.omg.org/spec/MOF/2.0/emof.xml\"" )
                .append ( " xmi:type=\"Package\"")
                .append ( " xmi:id=\"" + IdentifierGenerator.fromOnePart(modelName) + "\"")
                .append ( " name=\"" + modelName + "\"")
                .append ( " version=\"" + version + "\"")
                .append ( ">")
            .append ( LINE_SEPARATOR )
            .toString() ;
    }

    public static String documentEnd() {
        return new StringBuilder()
            .append ( "</emof:Package>")
            .append ( LINE_SEPARATOR )
            .append ( "</xmi:XMI>")
            .append ( LINE_SEPARATOR )
            .toString() ;
    }

    public static String typedTagBegin ( String tagName , String cdmTypeName , Boolean endTag ) {
        StringBuilder sb = new StringBuilder()
            .append ( "<" )
            .append ( tagName )
            .append ( addAttribute ( "xmi:type" , cdmTypeName ) ) ;
        if ( endTag == true ) { sb.append ( "/>") ; }
        return sb.toString() ;
    }

    public static String untypedTagBegin ( String tagType , Boolean endTag ) {
        StringBuilder sb = new StringBuilder()
            .append ( "<" )
            .append ( tagType ) ;
        if ( endTag == true ) { sb.append ( "/>") ; }
        return sb.toString() ;
    }

    public static String closeTag() {
        return ">" + LINE_SEPARATOR ;
    }

    public static String endTag() {
        return "/>" + LINE_SEPARATOR ;
    }

    public static String endBlockTag ( String tagName ) {
        return "</" + XmlHelper.ECORE_NS + tagName + ">" ;
    }

    public static String addAttribute ( String name , String value ) {
        return TAG_SEPARATOR + name + "=" + "\"" + value + "\"" + TAG_SEPARATOR ;
    }

    public static String convertCommentToXML ( String in ) {
        return in.replaceAll ( "<" , "&lt;" )
            .replaceAll ( ">" , "&gt;")
            .replaceAll ( "\"" , "&quot;")
            .replaceAll ( "\'" , "&apos;")
            .replaceAll ( "&" , "&amp;") ;
    }

    public static String addComment ( String annotatedElementId , String commentIn ) {
        if (Objects.isNull(commentIn) != true ) {
            return new StringBuilder()
                    .append("<ownedComment xmi:type=\"Comment\"")
                    .append(" xmi:id=\"" + IdentifierGenerator.fromMetaPartName(annotatedElementId, "Comment") + "\"")
                    .append(" body=\"" + XmlHelper.convertCommentToXML(commentIn) + "\"")
                    .append(">")
                    .append(LINE_SEPARATOR)
                    .append("<annotatedElement xmi:idref=\"" + annotatedElementId + "\"/>")
                    .append(LINE_SEPARATOR)
                    .append("</ownedComment>")
                    .append(LINE_SEPARATOR)
                    .toString();
        } else {
            return "" ;
        }
    }
}