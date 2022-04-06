/* Copyright 2012 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.predic8.wstool.creator

import com.predic8.soamodel.Consts
import com.predic8.wsdl.Definitions
import com.predic8.wsdl.WSDLParser
import com.predic8.xml.util.ClasspathResolver
import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper

class SOAResponseCreatorTest extends GroovyTestCase{
  
  def req

  void testResponseRequest(){
//    def sw = new StringWriter()
//    def creator = new SOAResponseCreator(getDefinitions("netsuite/netsuite.wsdl"),
//            new RequestTemplateCreator(),
//            new MarkupBuilder(sw))
//    //creator.maxRecursionDepth = 10
//    creator.createRequest 'NetSuitePortType', 'add', 'NetSuiteBinding'
//    println(sw);
    WSDLParser parser = new WSDLParser();
    Definitions wsdl = parser.parse("https://webservices.netsuite.com/wsdl/v2021_2_0/netsuite.wsdl");
    StringWriter writer = new StringWriter();
    SOAResponseCreator creator = new SOAResponseCreator(wsdl, new RequestTemplateCreator(), new MarkupBuilder(writer));
    //creator.createRequest(PortType name, Operation name, Binding name);
    creator.createRequest("NetSuitePortType", "add", "NetSuiteBinding");
    System.out.println(writer);
  }


  private getRequest(sw) {
    new XmlSlurper().parseText(sw.toString())
  }
  
  private def getDefinitions(input) {
    def parser = new WSDLParser(resourceResolver: new ClasspathResolver())
    parser.parse(input)
  }
}