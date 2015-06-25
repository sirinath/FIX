package com.susico.codegen

import groovy.util.XmlSlurper

public class GenFromXML {
    static void main(String[] args) {
        def list = new XmlSlurper().parse(new File("C:\\Users\\sirin_000\\IdeaProjects\\Trader\\FIX\\src\\main\\resources\\FIX44.xml"))

        print list.toString()
    }
}