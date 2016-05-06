package com.krishantha.sample;


import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Iterator;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

/**
 * Created by krishantha on 12/2/15.
 */
public class XmlComparator {

    public static void main(String[] args) throws IOException, SAXException {

        xmlComparator("src/original.xml", "src/response.xml");


    }

    private static void xmlComparator(String expectedXmlFilePath, String currentXmlFilePath) throws SAXException, IOException {
        //ignore while space differances
        XMLUnit.setIgnoreWhitespace(true);
        //ignore attribute order
        XMLUnit.setIgnoreAttributeOrder(true);
        //ignore comment differances
        XMLUnit.setIgnoreComments(true);
        //ignore differance on CData and text
        XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);

        InputSource expected = new InputSource(expectedXmlFilePath);
        InputSource current = new InputSource(currentXmlFilePath);

        DetailedDiff detailedDiff = new DetailedDiff(new Diff(expected, current));

        //ignore the sorting mismatch issues
        detailedDiff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());

        //this will print even if order mismatch elements are there. if you want to skip this use assertor
        Iterator i = detailedDiff.getAllDifferences().iterator();
        while (i.hasNext()) {
            System.out.println(i.next().toString());
        }
        System.out.println("================== if soarting issues are ignored =============================");
        //this can use ignore soarting issues and assert
        assertXMLEqual("XML files are mismatch", detailedDiff, true);

    }

}
