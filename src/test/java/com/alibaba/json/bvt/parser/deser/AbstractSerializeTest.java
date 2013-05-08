package com.alibaba.json.bvt.parser.deser;

import junit.framework.TestCase;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

public class AbstractSerializeTest extends TestCase {
    
    protected void setUp() throws Exception {
        ObjectDeserializer serializerB = ParserConfig.getGlobalInstance().getDeserializer(B.class);
        ParserConfig.getGlobalInstance().putDeserializer(A.class, serializerB);
    }
    
    protected void tearDown() throws Exception {
        ParserConfig.getGlobalInstance().putDeserializer(A.class, null);
    }

    public void test_mapping_0() throws Exception {
        String text = "{\"@type\":\"com.alibaba.json.bvt.parser.deser.AbstractSerializeTest$A\"}";

        ObjectDeserializer serializerB = ParserConfig.getGlobalInstance().getDeserializer(B.class);
        ParserConfig.getGlobalInstance().putDeserializer(A.class, serializerB);

        B b = (B) JSON.parse(text);
        Assert.assertNotNull(b);
    }
    
    public void test_mapping_1() throws Exception {
        String text = "{\"@type\":\"com.alibaba.json.bvt.parser.deser.AbstractSerializeTest$A\",\"id\":123}";

        ObjectDeserializer serializerB = ParserConfig.getGlobalInstance().getDeserializer(B.class);
        ParserConfig.getGlobalInstance().putDeserializer(A.class, serializerB);

        B b = (B) JSON.parse(text);
        Assert.assertNotNull(b);
        Assert.assertEquals(123, b.getId());
    }

    public void test_mapping_2() throws Exception {
        String text = "{\"@type\":\"com.alibaba.json.bvt.parser.deser.AbstractSerializeTest$A\",\"id\":234,\"name\":\"abc\"}";

        ObjectDeserializer serializerB = ParserConfig.getGlobalInstance().getDeserializer(B.class);
        ParserConfig.getGlobalInstance().putDeserializer(A.class, serializerB);

        B b = (B) JSON.parse(text);
        Assert.assertNotNull(b);
        Assert.assertEquals(234, b.getId());
        Assert.assertEquals("abc", b.getName());
    }

    public static abstract class A {

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class B extends A {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
